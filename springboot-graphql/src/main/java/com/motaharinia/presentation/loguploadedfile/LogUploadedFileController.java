package com.motaharinia.presentation.loguploadedfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motaharinia.business.service.loguploadedfile.LogUploadedFileService;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.json.PrimitiveResponse;
import com.motaharinia.presentation.loguploadedfile.backuploader.FileUploadChunkModel;
import com.motaharinia.presentation.loguploadedfile.frontuploader.FineUploaderChunkModel;
import com.motaharinia.presentation.loguploadedfile.frontuploader.FineUploaderResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping({"/fso"})
public class LogUploadedFileController {

    private final LogUploadedFileService logUploadedFileService;

    @Autowired
    public LogUploadedFileController(LogUploadedFileService logUploadedFileService) {
        this.logUploadedFileService = logUploadedFileService;
    }


    @RequestMapping(value = "/upload/{subSystem}/{entity}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody PrimitiveResponse uploadBackPanel(HttpServletRequest request, Locale locale, @RequestBody MultipartFile file, @RequestParam String params, @PathVariable("subSystem") SubSystemEnum subSystem, @PathVariable("entity") String entity) throws Exception {
        ObjectMapper mapper = new CustomObjectMapper();
        System.out.println("params:"+params);
        FileUploadChunkModel fileUploadChunkModel = mapper.readValue(params, FileUploadChunkModel.class);
        fileUploadChunkModel.setSubSystem(subSystem);
        fileUploadChunkModel.setEntity(entity);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        logUploadedFileService.uploadToFileModel(file, fileUploadChunkModel);
        //ایجاد خروجی برای آپلودر بک پنل
        return new PrimitiveResponse(true);
    }

    @RequestMapping(value = "/upload/{subSystem}/{entity}/fine", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody FineUploaderResponseModel uploadFrontPanel(HttpServletRequest request, Locale locale, @RequestParam(required = true) String qquuid, @RequestParam(required = true) String qqfilename, @RequestParam(required = true) Long qqtotalfilesize, @RequestBody MultipartFile qqfile, @RequestParam(required = false, defaultValue = "0") Integer qqpartindex, @RequestParam(required = false, defaultValue = "0") Integer qqpartbyteoffset, @RequestParam(required = false, defaultValue = "0") Long qqchunksize, @RequestParam(required = false, defaultValue = "1") Integer qqtotalparts, @PathVariable("subSystem") SubSystemEnum subSystem, @PathVariable("entity") String entity) throws Exception {
        FineUploaderChunkModel fineUploaderChunkModel = new FineUploaderChunkModel();
        fineUploaderChunkModel.setQquuid(qquuid);
        fineUploaderChunkModel.setQqfilename(qqfilename);
        fineUploaderChunkModel.setQqtotalfilesize(qqtotalfilesize);
        fineUploaderChunkModel.setQqfile(qqfile);
        fineUploaderChunkModel.setQqpartindex(qqpartindex);
        fineUploaderChunkModel.setQqpartbyteoffset(qqpartbyteoffset);
        fineUploaderChunkModel.setQqchunksize(qqchunksize);
        fineUploaderChunkModel.setQqtotalparts(qqtotalparts);
        fineUploaderChunkModel.setSubSystem(subSystem);
        fineUploaderChunkModel.setEntity(entity);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        logUploadedFileService.uploadToFileModel(qqfile, fineUploaderChunkModel);
        //ایجاد خروجی جهت آپلود فایل در فرانت پنل
        FineUploaderResponseModel fineUploaderResponseModel = new FineUploaderResponseModel();
        fineUploaderResponseModel.setSuccess(Boolean.TRUE);
        return fineUploaderResponseModel;
    }



//    @RequestMapping(value = "/upload/{subSystem}/{entity}", method = RequestMethod.POST, produces = "application/json")
//    public @ResponseBody
////    PrimitiveResponse upload(HttpServletRequest request, Locale locale, @RequestBody MultipartFile file, @RequestParam String params,
//    PrimitiveResponse upload(@RequestBody MultipartFile file, @RequestParam String params,
//                             @PathVariable("subSystem") SubSystemEnum subSystem, @PathVariable("entity") String entity) throws Exception {
//        ObjectMapper mapper = new CustomObjectMapper();
//        FileUploadChunkModel fileUploadChunkModel = mapper.readValue(params, FileUploadChunkModel.class);
//        fileUploadChunkModel.setSubSystem(subSystem);
//        fileUploadChunkModel.setEntity(entity);
//        logUploadedFileService.uploadToFileModel(file, fileUploadChunkModel);
//
//        return new PrimitiveResponse(true);
//    }











//    @GraphQLMutation(name = "uploadFile")
//    public Boolean uploadFile(FileUpload fileUpload) {
//
//        String fileContentType = fileUpload.getContentType();
//        byte[] fileContent = fileUpload.getContent();
//
//        // Do something in order to persist the file :)
//
//
//        return true;
//    }


//    @CrossOrigin
//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseBody
//    public Map<String, Object> post(final MultipartHttpServletRequest httpServletRequest, @RequestParam String operations, @RequestParam String map) throws IOException {
//
//        final Map<String, Object> operationsMap = GraphQLUploadExtension.process(operations, map, httpServletRequest::getFile);
//
//        //noinspection unchecked
//        return execute(
//                request,
//                (String) operationsMap.getOrDefault("query", ""),
//                (Map<String, Object>) operationsMap.get("variables")
//        );
//    }


//    public FSOController(GraphQL graphQL, GraphQLExecutor<NativeWebRequest> executor) {
//        super(graphQL, executor);
//    }

    /**
     * For Requests that follow the GraphQL Multipart Request Spec from: https://github.com/jaydenseric/graphql-multipart-request-spec
     *
     * The Request contains the following parts:
     * operations: JSON String with the GQL Query
     * map: Maps the multipart files to the variables of the GQL Query
     */
//    @PostMapping(
//            value = "${graphql.spqr.http.endpoint:/graphql}",
//            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
//    )
//    @ResponseBody
//    public Object executeMultipartPost(@RequestPart("operations") String operations,
//                                       @RequestPart("map") String map,
//                                       MultipartHttpServletRequest multiPartRequest,
//                                       NativeWebRequest webRequest) throws IOException, ServletException {
//        GraphQLRequest graphQLRequest = new ObjectMapper().readerFor(GraphQLRequest.class).readValue(operations);
//        Map<String, ArrayList<String>> fileMap = new ObjectMapper().readerFor(Map.class).readValue(map);
//
//        mapRequestFilesToVariables(multiPartRequest, graphQLRequest, fileMap);
//        return this.executeJsonPost(graphQLRequest, new GraphQLRequest(null, null, null), webRequest);
//    }
//
//    /**
//     * Maps the files that were sent in a Multipart Request to the corresponding variables of a {@link GraphQLRequest}.
//     * This makes it possible to use a file input like a normal parameter in a GraphQLApi Method.
//     */
//    private void mapRequestFilesToVariables(MultipartHttpServletRequest multiPartRequest, GraphQLRequest graphQLRequest,
//                                            Map<String, ArrayList<String>> fileMap) throws IOException, ServletException {
//        for (var pair : fileMap.entrySet()) {
//            String targetVariable = pair.getValue().get(0).replace("variables.", "");
//            if(graphQLRequest.getVariables().containsKey(targetVariable)) {
//                Part correspondingFile = multiPartRequest.getPart(pair.getKey());
//                graphQLRequest.getVariables().put(targetVariable, correspondingFile);
//            }
//        }
//    }


//    @GraphQLMutation
//    public void uploadTemplate(@GraphQLNonNull @GraphQLArgument(name = "itemTemplate") byte[] uploadFile) throws IOException {
//        Xcelite uploadTemplate = new Xcelite(Files.write(Files.createTempFile("temp", ".xlsx"), uploadFile).toFile());
//        //CustomBeanSheetReader<TemplateItem> reader = new CustomBeanSheetReader<>(uploadTemplate.getSheet(0), TemplateItem.class);
//        //reader.skipHeaderRow(true);
//
//        //return reader.read();
//    }
}
