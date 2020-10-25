package com.motaharinia.business.service.loguploadedfile;

import com.motaharinia.business.service.BusinessExceptionEnum;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.entity.EntityTools;
import com.motaharinia.msutility.fso.FsoTools;
import com.motaharinia.persistence.orm.etcitem.EtcItemRepository;
import com.motaharinia.persistence.orm.loguploadedfile.LogUploadedFile;
import com.motaharinia.persistence.orm.loguploadedfile.LogUploadedFileRepository;
import com.motaharinia.presentation.loguploadedfile.backuploader.FileUploadChunkModel;
import com.motaharinia.presentation.loguploadedfile.LogUploadedFileModel;
import com.motaharinia.presentation.loguploadedfile.frontuploader.FineUploaderChunkModel;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
@Qualifier("LogUploadedFileServiceImpl")
@Service
@Transactional(rollbackFor = Exception.class)
public class LogUploadedFileServiceImpl implements LogUploadedFileService {

    /**
     * مسیر موقت جهت آپلود فایلهای پروزه
     */
    @Value("${fso.path.upload.directory}")
    private String FSO_PATH_UPLOAD_DIRECTORY = "/mbazardata/uploadedfile";

    /**
     * ریپازیتوری ادمین
     */
    private LogUploadedFileRepository logUploadedFileRepository;

    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;

    /**
     * متد سازنده
     */
    @Autowired
    public LogUploadedFileServiceImpl(LogUploadedFileRepository logUploadedFileRepository, ModelMapper modelMapper) {
        this.logUploadedFileRepository = logUploadedFileRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به بک پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile        آرایه داده ارسالی از کلاینت
     * @param fileUploadChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public LogUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FileUploadChunkModel fileUploadChunkModel) throws Exception {
        //تعریف و ساخت پوشه ای برای فایلهای در حال آپلود"uploading" در پوشه آپلود
        String uploadingDirectory = FSO_PATH_UPLOAD_DIRECTORY + "/" + "uploading";
        FsoTools.createDirectoryIfNotExist(uploadingDirectory);

        //مسیر فایل در حال آپلود طبق کلید فایل که از سمت کلاینت می آبد
        String uploadingFilePath = uploadingDirectory + "/" + fileUploadChunkModel.getFileKey();
        File uploadingFile = new File(uploadingFilePath);
        FileUtils.writeByteArrayToFile(uploadingFile, multipartFile.getBytes(), true);

        //ایجاد مدل فایل آپلود
        LogUploadedFileModel logUploadedFileModel = new LogUploadedFileModel();
        if (fileUploadChunkModel.getChunks() - 1 == fileUploadChunkModel.getChunk()) {
            logUploadedFileModel.setFileKey(fileUploadChunkModel.getFileKey());
            logUploadedFileModel.setFileByteArray(FileUtils.readFileToByteArray(uploadingFile));
            logUploadedFileModel.setFileSize(logUploadedFileModel.getFileByteArray().length);
            logUploadedFileModel.setFileUploadDateTime(new Date());
            fileUploadChunkModel.setName(this.fixFailedFileNameCharacter(fileUploadChunkModel.getName()));
            logUploadedFileModel.setFileFullName(fileUploadChunkModel.getName());
            logUploadedFileModel.setFileEntity(fileUploadChunkModel.getEntity());
            logUploadedFileModel.setFileSubSystem(fileUploadChunkModel.getSubSystem().getValue());
            logUploadedFileModel.setFileExtension(FsoTools.getFileExtension(fileUploadChunkModel.getName()));
            logUploadedFileModel.setFileName(FsoTools.getFileNameWithoutExtension(fileUploadChunkModel.getName()));
            String mainPath = "/" + removeNonAlphabetic(fileUploadChunkModel.getSubSystem().getValue()) + "/" + removeNonAlphabetic(fileUploadChunkModel.getEntity());
            logUploadedFileModel.setDirectoryRealPath(checkLastCharOfPath(mainPath + fileUploadChunkModel.getFilePath()));

            //ذخیره فایل بر روی فایل سیستم از روی مدل دیتابیسی فایل آپلود شده
            logUploadedFileModel = this.saveUploadedFile(logUploadedFileModel, logUploadedFileModel.getFileKey());
            //ذخیره اطلاعات در دیتابیس از روی مدل دیتابیسی فایل آپلود شده
            this.create(logUploadedFileModel);

            //حذف فایل موقت در حال آپلود
            uploadingFile.delete();

            return logUploadedFileModel;
        } else {
            return null;
        }
    }

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به فرانت پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile          آرایه داده ارسالی از کلاینت
     * @param fineUploaderChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public LogUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FineUploaderChunkModel fineUploaderChunkModel) throws Exception {
        //تعریف و ساخت پوشه ای برای فایلهای در حال آپلود"uploading" در پوشه آپلود
        String uploadingDirectory = FSO_PATH_UPLOAD_DIRECTORY + "/" + "uploading";
        FsoTools.createDirectoryIfNotExist(uploadingDirectory);

        //مسیر فایل در حال آپلود طبق کلید فایل که از سمت کلاینت می آبد
        String uploadingFilePath = uploadingDirectory + "/" + fineUploaderChunkModel.getQquuid();
        File uploadingFile = new File(uploadingFilePath);
        FileUtils.writeByteArrayToFile(uploadingFile, multipartFile.getBytes(), true);

        //ایجاد مدل فایل آپلود
        LogUploadedFileModel logUploadedFileModel = new LogUploadedFileModel();
        if (fineUploaderChunkModel.getQqtotalparts() - 1 == fineUploaderChunkModel.getQqpartindex()) {
            logUploadedFileModel.setFileKey(fineUploaderChunkModel.getQquuid());
            logUploadedFileModel.setFileByteArray(FileUtils.readFileToByteArray(uploadingFile));
            logUploadedFileModel.setFileSize(logUploadedFileModel.getFileByteArray().length);
            logUploadedFileModel.setFileUploadDateTime(new Date());
            fineUploaderChunkModel.setQqfilename(this.fixFailedFileNameCharacter(fineUploaderChunkModel.getQqfilename()));
            logUploadedFileModel.setFileFullName(fineUploaderChunkModel.getQqfilename());
            logUploadedFileModel.setFileEntity(fineUploaderChunkModel.getEntity());
            logUploadedFileModel.setFileSubSystem(fineUploaderChunkModel.getSubSystem().getValue());
            logUploadedFileModel.setFileExtension(FsoTools.getFileExtension(fineUploaderChunkModel.getQqfilename()));
            logUploadedFileModel.setFileName(FsoTools.getFileNameWithoutExtension(fineUploaderChunkModel.getQqfilename()));
            String mainPath = "/" + removeNonAlphabetic(fineUploaderChunkModel.getSubSystem().getValue()) + "/" + removeNonAlphabetic(fineUploaderChunkModel.getEntity());
//            logUploadedFileModel.setDirectoryRealPath(checkLastCharOfPath(mainPath + fineUploaderChunkModel.getFilePath()));

            //ذخیره فایل بر روی فایل سیستم از روی مدل دیتابیسی فایل آپلود شده
            logUploadedFileModel = this.saveUploadedFile(logUploadedFileModel, logUploadedFileModel.getFileKey());
            //ذخیره اطلاعات در دیتابیس از روی مدل دیتابیسی فایل آپلود شده
            this.create(logUploadedFileModel);

            return logUploadedFileModel;
        } else {
            return null;
        }
    }

    @Override
    public LogUploadedFileModel create(LogUploadedFileModel logUploadedFileModel)throws Exception{
        //ساخت انتیتی فایل آپلود شده از مدل فایل آپلود شده
        LogUploadedFile logUploadedFile = new LogUploadedFile();
        logUploadedFile.setFileExtension(logUploadedFileModel.getFileExtension());
        logUploadedFile.setFileFullName(logUploadedFileModel.getFileFullName());
        logUploadedFile.setFileKey(logUploadedFileModel.getFileKey());
        logUploadedFile.setFileMimeType(logUploadedFileModel.getFileMimeType());
        logUploadedFile.setFileName(logUploadedFileModel.getFileName());
        logUploadedFile.setFileSize(logUploadedFileModel.getFileSize());
        logUploadedFile.setFileUploadDateTime(new Date());
        logUploadedFile.setFileUploadedPath(logUploadedFileModel.getFileUploadedPath());
        logUploadedFile.setFileEntity(logUploadedFileModel.getFileEntity());
        logUploadedFile.setFileSubSystem(logUploadedFileModel.getFileSubSystem());
        logUploadedFileRepository.save(logUploadedFile);
        return  logUploadedFileModel;
    }

    /**
     * این متد کلید فایل مورد نظر فایل آپلود شده را از ورودی دریافت کرده و مدل آن را خروجی میدهد
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public LogUploadedFileModel getLogUploadedFileModel(String fileKey) throws Exception {
        if ((fileKey == null) || (fileKey.length() == 0)) {
            throw new BusinessException(LogUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }

        LogUploadedFileModel logUploadedFileModel = new LogUploadedFileModel();
        LogUploadedFile logUploadedFile = logUploadedFileRepository.findByFileKey(fileKey);

        if (logUploadedFile != null) {
            logUploadedFileModel = (LogUploadedFileModel) EntityTools.convertToModel(logUploadedFileModel, logUploadedFile);
            //خواندن اطلاعات فایل در مدل
            File uploadedFile = new File(logUploadedFileModel.getFileUploadedPath());
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(uploadedFile);
            byte fileContent[] = new byte[(int) uploadedFile.length()];
            fileInputStream.read(fileContent);
            logUploadedFileModel.setFileByteArray(fileContent);
        } else {
            throw new BusinessException(LogUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }

        return logUploadedFileModel;
    }

    /**
     * این متد کلید فایل آپلود شده مورد نظر را از ورودی دریافت کرده و آن را حذف مینماید
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @throws Exception
     */
    @Override
    public void delete(String fileKey) throws Exception {
        LogUploadedFile logUploadedFile = logUploadedFileRepository.findByFileKey(fileKey);
        if (logUploadedFile != null) {
            logUploadedFileRepository.delete(logUploadedFile);
            File file = new File(FSO_PATH_UPLOAD_DIRECTORY + "/" + logUploadedFile.getFileKey());
            if ((file.exists())) {
                FileUtils.deleteQuietly(file);
            }
        } else {
            throw new BusinessException(LogUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }
    }


    /**
     * این متد مطابق مدل دیتابیسی اطلاعات فایل آپلود شده آن را در فایل سیستم ذخیره مینماید
     *
     * @param logUploadedFileModel مدل فایل آپلود شده
     * @param fileKey              کلید فایل آپلود شده
     * @return خروجی: مدل فایل آپلود شده کامل شده
     * @throws Exception
     */
    private LogUploadedFileModel saveUploadedFile(LogUploadedFileModel logUploadedFileModel, String fileKey) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String fileName = "uploaded_" + sdf.format(cal.getTime()) + "_" + logUploadedFileModel.getFileFullName();
        String fileKeyDirectoryPath = FSO_PATH_UPLOAD_DIRECTORY + "/" + fileKey;
        File directory = new File(fileKeyDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileUtils.writeByteArrayToFile(new File(fileKeyDirectoryPath + "/" + fileName), logUploadedFileModel.getFileByteArray());
        logUploadedFileModel.setFileMimeType(FsoTools.getMimeTypeModel(fileKeyDirectoryPath + "/" + fileName).getMimeType());
        logUploadedFileModel.setFileUploadedPath(fileKeyDirectoryPath + "/" + fileName);
        return logUploadedFileModel;
    }


    /**
     * این متد کارکترهای غیر الفبایی را از رشته ورودی حذف میکند
     *
     * @param inputString رشته ورودی
     * @return خروجی: رشته بررسی شده و اصلاح شده
     */
    private static String removeNonAlphabetic(String inputString) {
        inputString = inputString.replaceAll("[^a-zA-Z]", "");
        return inputString;
    }

    /**
     * این متد انتهای مسیر دایرکتوری را چک میکند اگر اسلش ندارد به آن اضافه میکند
     *
     * @param directoryPath مسیر دایرکتوری
     * @return خروجی: مسیر دایرکتوری که حتما در انتهای آن اسلش دارد
     */
    private static String checkLastCharOfPath(String directoryPath) {
        String lastChar = directoryPath.substring(directoryPath.length() - 1);
        if (!"/".equals(lastChar)) {
            directoryPath = directoryPath + "/";
        }
        return directoryPath;
    }

    /**
     * این متد کارکترهای عربی را با معادل فارسی آن جایگزین میکند
     *
     * @param fileName نام فایل ورودی
     * @return خروجی: نام فایل اصلاح شده و بدون کارکترهای عربی
     */
    private static String fixFailedFileNameCharacter(String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            return "";
        }
        HashMap<String, String> replaceHashMap = new HashMap<>();
        replaceHashMap.put(Character.toString((char) 63), "ي");
        replaceHashMap.put(Character.toString((char) 1740), "ي");
        replaceHashMap.put(Character.toString((char) 1705), "ك");
        replaceHashMap.put(Character.toString((char) 1607), "ھ");
        replaceHashMap.put(Character.toString((char) 1575), "أ");
        replaceHashMap.put(Character.toString((char) 1570), "أ");
        replaceHashMap.put(Character.toString((char) 1608), "ؤ");
        for (Map.Entry<String, String> entry : replaceHashMap.entrySet()) {
            while (fileName.contains(entry.getKey())) {
                fileName = fileName.replace(entry.getKey(), entry.getValue());
            }
        }
        return fileName;
    }
}
