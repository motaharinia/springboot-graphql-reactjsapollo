package com.motaharinia.presentation.etcitem;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.genericmodel.ComboTypeEnum;
import com.motaharinia.msutility.genericmodel.CustomComboModel;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.persistence.orm.etcitem.EtcItemInitialData;
import com.motaharinia.presentation.generic.CustomComboFilterModel;
import com.motaharinia.presentation.generic.EntityEnum;
import com.motaharinia.presentation.generic.EntityParametersModeEnum;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس تست ماژول مقادیر ثابت
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EtcItemControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EtcItemInitialData etcItemInitialData;

    private CustomObjectMapper customObjectMapper = new CustomObjectMapper();

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws InvocationTargetException, UtilityException, IllegalAccessException, BusinessException {
        Locale.setDefault(new Locale("fa"));
        etcItemInitialData.checkEtcItems();
    }




    @Test
    @Order(1)
    public void customComboTest() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/etcItem";

            CustomComboFilterModel customComboFilterModel = new CustomComboFilterModel();
            customComboFilterModel.setMode(EntityParametersModeEnum.ETC_ITEM__GENDER);
            customComboFilterModel.setType(ComboTypeEnum.COMBO);
            customComboFilterModel.setEntity(EntityEnum.ETC_ITEM);

            uri += "?customComboFilterModel={customComboFilterModel}";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<CustomComboFilterModel> entity = new HttpEntity<>(customComboFilterModel, headers);
            ResponseEntity<CustomComboModel> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, CustomComboModel.class, this.customObjectMapper.writeValueAsString(customComboFilterModel));
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            CustomComboModel customComboModel = response.getBody();
            assertThat(customComboModel.getTotalCount()).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(2)
    public void customComboAutoCompleteTest() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/etcItem";

            CustomComboFilterModel customComboFilterModel = new CustomComboFilterModel();
            customComboFilterModel.setMode(EntityParametersModeEnum.ETC_ITEM__GENDER);
            customComboFilterModel.setType(ComboTypeEnum.AUTOCOMPLETE);
            customComboFilterModel.setEntity(EntityEnum.ETC_ITEM);
            customComboFilterModel.setPage(0);
            customComboFilterModel.setRows(1000000);
            customComboFilterModel.setInput("F");

            uri += "?customComboFilterModel={customComboFilterModel}";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<CustomComboFilterModel> entity = new HttpEntity<>(customComboFilterModel, headers);
            ResponseEntity<CustomComboModel> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, CustomComboModel.class, this.customObjectMapper.writeValueAsString(customComboFilterModel));
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            CustomComboModel customComboModel = response.getBody();
            assertThat(customComboModel.getTotalCount()).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(3)
    public void comboTest() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/getCombo";

            HashMap<String, HashMap<String, Object>> post = new HashMap<>();
            HashMap<String, Object> innerPost = new HashMap<>();

            innerPost.put("entity","etcItem");
            innerPost.put("parametersMode","gender");
            innerPost.put("value","1");
            post.put("memberGender" , innerPost);

            uri += "?post={post}";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<HashMap> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, HashMap.class, this.customObjectMapper.writeValueAsString(post));
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            HashMap<String, List<Object[]>> returnHashmap = response.getBody();
            assertThat(returnHashmap.size()).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


}
