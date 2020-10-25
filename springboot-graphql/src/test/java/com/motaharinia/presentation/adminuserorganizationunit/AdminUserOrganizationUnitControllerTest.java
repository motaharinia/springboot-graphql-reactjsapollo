package com.motaharinia.presentation.adminuserorganizationunit;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.jstree.JstreeNodeModel;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

//https://reflectoring.io/unit-testing-spring-boot/
//https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس تست ماژول واحد سازمانی ادمین
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminUserOrganizationUnitControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * شیی crud
     */
    private static Integer crudId;
    private static String random;


    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws InvocationTargetException, UtilityException, IllegalAccessException, BusinessException {
        Locale.setDefault(new Locale("fa"));
    }


    @Test
    @Order(1)
    public void create() {
        try {
            String uri = "http://localhost:" + port + "/adminUserOrganizationUnit";
            Map<String, String> variableHashMap = new HashMap<String, String>();

            random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);

            AdminUserOrganizationUnitModel adminUserOrganizationUnitModel = new AdminUserOrganizationUnitModel();
            adminUserOrganizationUnitModel.setTitle("OrganizationUnitTitle " + random);
            adminUserOrganizationUnitModel.setCode("OrganizationUnitCode " + random);
            adminUserOrganizationUnitModel.setParent_id(null);

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AdminUserOrganizationUnitModel> entity = new HttpEntity<>(adminUserOrganizationUnitModel, headers);
            ResponseEntity<AdminUserOrganizationUnitModel> response = this.restTemplate.exchange(uri, HttpMethod.POST, entity, AdminUserOrganizationUnitModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            adminUserOrganizationUnitModel=response.getBody();
            assertThat(adminUserOrganizationUnitModel.getId()).isNotEqualTo(null);
            crudId = adminUserOrganizationUnitModel.getId();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void createChild1() {
        try {
            String uri = "http://localhost:" + port + "/adminUserOrganizationUnit";

            random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);

            AdminUserOrganizationUnitModel adminUserOrganizationUnitModel = new AdminUserOrganizationUnitModel();
            adminUserOrganizationUnitModel.setTitle("OrganizationUnitTitle " + random);
            adminUserOrganizationUnitModel.setCode("OrganizationUnitCode " + random);
            adminUserOrganizationUnitModel.setParent_id(crudId);

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AdminUserOrganizationUnitModel> entity = new HttpEntity<>(adminUserOrganizationUnitModel, headers);
            ResponseEntity<AdminUserOrganizationUnitModel> response = this.restTemplate.exchange(uri, HttpMethod.POST, entity, AdminUserOrganizationUnitModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            adminUserOrganizationUnitModel=response.getBody();
            assertThat(adminUserOrganizationUnitModel.getId()).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(3)
    public void createChild2() {
        try {
            String uri = "http://localhost:" + port + "/adminUserOrganizationUnit";

            random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);

            AdminUserOrganizationUnitModel adminUserOrganizationUnitModel = new AdminUserOrganizationUnitModel();
            adminUserOrganizationUnitModel.setTitle("OrganizationUnitTitle " + random);
            adminUserOrganizationUnitModel.setCode("OrganizationUnitCode " + random);
            adminUserOrganizationUnitModel.setParent_id(crudId);

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AdminUserOrganizationUnitModel> entity = new HttpEntity<>(adminUserOrganizationUnitModel, headers);
            ResponseEntity<AdminUserOrganizationUnitModel> response = this.restTemplate.exchange(uri, HttpMethod.POST, entity, AdminUserOrganizationUnitModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            adminUserOrganizationUnitModel=response.getBody();
            assertThat(adminUserOrganizationUnitModel.getId()).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(4)
    public void tree() {
        try {
            String uri = "http://localhost:" + port + "/adminUserOrganizationUnit/0";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<JstreeNodeModel[]> response = this.restTemplate.exchange(uri,HttpMethod.GET,entity, JstreeNodeModel[].class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            JstreeNodeModel[] jstreeNodeModelArray=response.getBody();
            assertThat(jstreeNodeModelArray.length).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}
