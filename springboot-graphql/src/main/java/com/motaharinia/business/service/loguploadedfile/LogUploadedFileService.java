package com.motaharinia.business.service.loguploadedfile;

import com.motaharinia.presentation.loguploadedfile.backuploader.FileUploadChunkModel;
import com.motaharinia.presentation.loguploadedfile.LogUploadedFileModel;
import com.motaharinia.presentation.loguploadedfile.frontuploader.FineUploaderChunkModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public interface LogUploadedFileService {

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به بک پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile        آرایه داده ارسالی از کلاینت
     * @param fileUploadChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    LogUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FileUploadChunkModel fileUploadChunkModel) throws Exception;

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به فرانت پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile          آرایه داده ارسالی از کلاینت
     * @param fineUploaderChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    LogUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FineUploaderChunkModel fineUploaderChunkModel) throws Exception;


    /**
     * این متد یک لاگ دیتابیس از اطلاعات فایل آپلود شده در دیتابیس ذخیره مینماید
     * @param logUploadedFileModel مدل فایل آپلود شده
     * @return خروجی: مدل فایل آپلود شده
     * @throws Exception
     */
    LogUploadedFileModel create(LogUploadedFileModel logUploadedFileModel)throws Exception;

    /**
     * این متد کلید فایل مورد نظر فایل آپلود شده را از ورودی دریافت کرده و مدل آن را خروجی میدهد
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    LogUploadedFileModel getLogUploadedFileModel(String fileKey) throws Exception;

    /**
     * این متد کلید فایل آپلود شده مورد نظر را از ورودی دریافت کرده و آن را حذف مینماید
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @throws Exception
     */
    void delete(String fileKey) throws Exception;

}
