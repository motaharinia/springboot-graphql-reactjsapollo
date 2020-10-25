package com.motaharinia.presentation.adminuser;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.motaharinia.business.service.adminuser.AdminUserSearchViewTypeEnum;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.search.data.SearchDataModel;
import com.motaharinia.msutility.search.filter.*;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import com.motaharinia.presentation.adminuserskill.AdminUserSkillModel;
import com.motaharinia.persistence.orm.etcitem.EtcItemInitialData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

//https://reflectoring.io/unit-testing-spring-boot/
//https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html
//https://attacomsian.com/blog/http-requests-resttemplate-spring-boot#

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس تست ماژول ادمین
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminUserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EtcItemInitialData etcItemInitialData;

    /**
     * شیی crud
     */
    private static Integer crudId =23;
    private static String random;

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
    public void create() {
        try {
            String uri = "http://localhost:" + port + "/adminUser";

            random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);
            CustomDate dateOfBirth = new CustomDate();
            dateOfBirth.setYear(1399);
            dateOfBirth.setMonth(4);
            dateOfBirth.setDay(3);

            AdminUserModel adminUserModel = new AdminUserModel();
            adminUserModel.setFirstName("Mostafa " + random);
            adminUserModel.setLastName("Motaharinia " + random);
            adminUserModel.setPassword("123456789");
            adminUserModel.setUsername("eng.motahari_" + random + "@gmail.com");
            adminUserModel.setDateOfBirth(dateOfBirth);
            adminUserModel.setGender_id(1);
            adminUserModel.setDefaultAdminUserContact_address("Shahrak Gharb " + random);
            adminUserModel.setSkillList(Arrays.asList(new AdminUserSkillModel[]{new AdminUserSkillModel(null, "skill-" + random), new AdminUserSkillModel(null, "skill-" + StringTools.generateRandomString(RandomGenerationTypeEnum.NUMBER, 5, false))}));

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<AdminUserModel> entity = new HttpEntity<>(adminUserModel, headers);
            ResponseEntity<AdminUserModel> response = this.restTemplate.exchange(uri, HttpMethod.POST, entity, AdminUserModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            adminUserModel = response.getBody();
            assertThat(adminUserModel.getGender_id()).isEqualTo(1);
            crudId = adminUserModel.getId();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void readById() {
        try {
            String uri = "http://localhost:" + port + "/adminUser/" + crudId;

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<AdminUserModel> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, AdminUserModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            AdminUserModel adminUserModel = response.getBody();
            assertThat(adminUserModel.getId()).isEqualTo(crudId);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(3)
    public void readGrid() throws JsonProcessingException {
        System.out.println("LocaleContextHolder.getLocale()" + LocaleContextHolder.getLocale());
        try {
            String uri = "http://localhost:" + port + "/adminUser";

            if (ObjectUtils.isEmpty(random)) {
                random = "skill";
            }
            CustomDate dateOfBirthFrom = new CustomDate();
            dateOfBirthFrom.setYear(1399);
            dateOfBirthFrom.setMonth(4);
            dateOfBirthFrom.setDay(3);

            CustomDate dateOfBirthTo = new CustomDate();
            dateOfBirthTo.setYear(1399);
            dateOfBirthTo.setMonth(4);
            dateOfBirthTo.setDay(6);


            List<String> usernameList = new ArrayList<>();
            usernameList.add("eng.motahari_pxQti@gmail.com");
            usernameList.add("eng.motahari_kqFkT@gmail.com");
            usernameList.add("eng.motahari_cxgFw@gmail.com");

            List<SearchFilterRestrictionModel> searchFilterRestrictionModelList = new ArrayList<>();
            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("firstName", SearchFilterOperationEnum.MATCH, "mostafa", SearchFilterNextConditionOperatorEnum.AND));
            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("defaultAdminUserContact.address", SearchFilterOperationEnum.MATCH, "Shahrak Gharb", SearchFilterNextConditionOperatorEnum.AND));
            //searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("skillSet.title", SearchFilterOperationEnum.MATCH, random,SearchFilterNextConditionOperatorEnum.AND));
//            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("dateOfBirth", SearchFilterOperationEnum.GREATER_THAN_EQUAL, dateOfBirthFrom,SearchFilterNextConditionOperatorEnum.AND));
//            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("dateOfBirth", SearchFilterOperationEnum.LESS_THAN_EQUAL, dateOfBirthTo,SearchFilterNextConditionOperatorEnum.AND));
//            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("username", SearchFilterOperationEnum.IN, usernameList,SearchFilterNextConditionOperatorEnum.AND));
//            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("skillSet", SearchFilterOperationEnum.MEMBER_OF, 45,SearchFilterNextConditionOperatorEnum.AND));
            List<SearchFilterSortModel> searchFilterSortModelList = new ArrayList<>();
            searchFilterSortModelList.add(new SearchFilterSortModel("firstName", SearchFilterSortTypeEnum.ASC));
            searchFilterSortModelList.add(new SearchFilterSortModel("lastName", SearchFilterSortTypeEnum.DSC));
            searchFilterSortModelList.add(new SearchFilterSortModel("defaultAdminUserContact.address", SearchFilterSortTypeEnum.DSC));
            searchFilterSortModelList.add(new SearchFilterSortModel("gender.value", SearchFilterSortTypeEnum.DSC));
            //searchFilterSortModelList.add(new SearchFilterSortModel("defaultAdminUserContact.type.value", SearchFilterSortTypeEnum.DSC));
            SearchFilterModel searchFilterModel = new SearchFilterModel();
            searchFilterModel.setPage(0);
            searchFilterModel.setRows(20);
            searchFilterModel.setRestrictionList(searchFilterRestrictionModelList);
            searchFilterModel.setSortList(searchFilterSortModelList);

            uri += "?searchFilterModel={searchFilterModel}&searchViewTypeEnum={searchViewTypeEnum}&searchValueList={searchValueList}";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<SearchFilterModel> entity = new HttpEntity<>(searchFilterModel, headers);
            ResponseEntity<SearchDataModel> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, SearchDataModel.class, this.customObjectMapper.writeValueAsString(searchFilterModel), AdminUserSearchViewTypeEnum.ADMIN_USER_BRIEF.toString(), new String[]{});
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            SearchDataModel searchDataModel = response.getBody();
            assertThat(searchDataModel.getPage()).isEqualTo(searchFilterModel.getPage());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(4)
    public void update() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/adminUser";

            //جستجوی ادمین جهت ویرایش
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<AdminUserModel> response = this.restTemplate.exchange(uri + "/" + crudId, HttpMethod.GET, entity, AdminUserModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            AdminUserModel adminUserModel = response.getBody();
            assertThat(adminUserModel.getId()).isEqualTo(crudId);

            random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);
            CustomDate dateOfBirth = new CustomDate();
            dateOfBirth.setYear(1399);
            dateOfBirth.setMonth(12);
            dateOfBirth.setDay(22);

            //ویرایش اطلاعات مدل
            adminUserModel.setFirstName(adminUserModel.getFirstName() + "Updated");
            adminUserModel.setLastName(adminUserModel.getLastName() + "Updated");
            adminUserModel.setPassword(adminUserModel.getPassword() + "Updated");
            adminUserModel.setUsername("updated" + adminUserModel.getUsername());
            adminUserModel.setDateOfBirth(dateOfBirth);
            adminUserModel.setGender_id(2);
            adminUserModel.setDefaultAdminUserContact_address(adminUserModel.getDefaultAdminUserContact_address() + "Updated");
            adminUserModel.getSkillList().add(new AdminUserSkillModel(null, "skill-added in update"));

            // build the request
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            entity = new HttpEntity<>(adminUserModel, headers);
            response = this.restTemplate.exchange(uri, HttpMethod.PUT, entity, AdminUserModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            adminUserModel = response.getBody();
            assertThat(adminUserModel.getGender_id()).isEqualTo(2);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(5)
    public void delete() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/adminUser/" + crudId;

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<AdminUserModel> response = this.restTemplate.exchange(uri, HttpMethod.DELETE, entity, AdminUserModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            AdminUserModel adminUserModel = response.getBody();
            assertThat(adminUserModel.getId()).isEqualTo(crudId);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}