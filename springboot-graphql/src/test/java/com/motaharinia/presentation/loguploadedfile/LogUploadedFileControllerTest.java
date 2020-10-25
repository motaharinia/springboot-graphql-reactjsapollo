package com.motaharinia.presentation.loguploadedfile;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.genericmodel.ComboTypeEnum;
import com.motaharinia.msutility.genericmodel.CustomComboModel;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import com.motaharinia.persistence.orm.etcitem.EtcItemInitialData;
import com.motaharinia.presentation.generic.CustomComboFilterModel;
import com.motaharinia.presentation.generic.EntityEnum;
import com.motaharinia.presentation.generic.EntityParametersModeEnum;
import com.motaharinia.presentation.loguploadedfile.backuploader.FileUploadChunkModel;
import com.motaharinia.presentation.loguploadedfile.frontuploader.FineUploaderChunkModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس تست ماژول فایل سیستم
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogUploadedFileControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private CustomObjectMapper customObjectMapper = new CustomObjectMapper();

    @Autowired
    private WebApplicationContext webApplicationContext;


    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws InvocationTargetException, UtilityException, IllegalAccessException, BusinessException {
        Locale.setDefault(new Locale("fa"));
    }




    @Test
    @Order(1)
    public void uploadBackPanelTest() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/fso/upload/COMMON/member";
            //تعریف فایل
            String fileContect="Mostafa Motaharinia. Email:eng.motahari@gmail.com";
            MockMultipartFile firstFile = new MockMultipartFile("file", "memberInfo.txt", "text/plain", fileContect.getBytes());
            //تعریف مدل چانک(قسمتی از فایل) برای ارسال
            FileUploadChunkModel fileUploadChunkModel=new FileUploadChunkModel();
            fileUploadChunkModel.setName("memberInfo.txt");
            fileUploadChunkModel.setChunk(0);
            fileUploadChunkModel.setChunks(1);
            fileUploadChunkModel.setFileKey(StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_LOWER,20,false));
            fileUploadChunkModel.setFilePath("memberInfo.txt");
            fileUploadChunkModel.setEntity("member");
            fileUploadChunkModel.setSubSystem(SubSystemEnum.COMMON);
            String fileUploadChunkModelJson= customObjectMapper.writeValueAsString(fileUploadChunkModel);

            //ارسال فایل
            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(MockMvcRequestBuilders.multipart(uri)
                    .file(firstFile)
                    .param("params", fileUploadChunkModelJson)
                    .characterEncoding("UTF-8"))
                    .andExpect(MockMvcResultMatchers.status().isOk());

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(2)
    public void uploadFrontPanelTest() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/fso/upload/COMMON/member/fine";
            //تعریف فایل
            String fileContect="Mostafa Motaharinia. Email:eng.motahari@gmail.com";
            MockMultipartFile firstFile = new MockMultipartFile("qqfile", "memberInfo.txt", "text/plain", fileContect.getBytes());
            //ارسال فایل به همراه اطلاعات فیلدهای مدل چانک
            MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
            mockMvc.perform(MockMvcRequestBuilders.multipart(uri)
                    .file(firstFile)
                    .param("qquuid", StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_LOWER,20,false))
                    .param("qqfilename", "memberInfo.txt")
                    .param("qqtotalfilesize", ((Integer)fileContect.getBytes().length).toString())
                    .param("qqpartindex", "0")
                    .param("qqpartbyteoffset", "0")
                    .param("qqchunksize", ((Integer)fileContect.getBytes().length).toString())
                    .param("qqtotalparts", "1")
                    .characterEncoding("UTF-8"))
                    .andExpect(MockMvcResultMatchers.status().isOk());

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
