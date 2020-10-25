package com.motaharinia.business.service.adminuser;


import com.motaharinia.business.service.BusinessExceptionEnum;
import com.motaharinia.business.service.adminuserskill.AdminUserSkillService;
import com.motaharinia.business.service.etcitem.EtcItemService;
import com.motaharinia.business.service.etcitem.GenderEnum;
import com.motaharinia.config.graphql.GraphQLCustomException;
import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.search.data.SearchDataModel;
import com.motaharinia.msutility.search.filter.SearchFilterModel;
import com.motaharinia.persistence.orm.adminuser.AdminUser;
import com.motaharinia.persistence.orm.adminuser.AdminUserRepository;
import com.motaharinia.persistence.orm.adminuser.AdminUserSpecification;
import com.motaharinia.persistence.orm.adminusercontact.AdminUserContact;
import com.motaharinia.persistence.orm.adminusercontact.AdminUserContactRepository;
import com.motaharinia.presentation.adminuser.AdminUserModel;
import com.motaharinia.presentation.adminuserskill.AdminUserSkillModel;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

//https://www.baeldung.com/spring-data-jpa-projections
//https://walczak.it/blog/spring-data-jpa-projection-dynamic-queries


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت ادمین ها
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminUserServiceImpl implements AdminUserService {


    /**
     * ریپازیتوری ادمین
     */
    private AdminUserRepository adminUserRepository;
    /**
     * ریپازیتوری اطلاعات تماس ادمین
     */
    private AdminUserContactRepository adminUserContactRepository;

    /**
     * سرویس مهارتها
     */
    private AdminUserSkillService adminUserSkillService;

    /**
     * سرویس مقادیر ثابت
     */
    private EtcItemService etcItemService;

    /**
     * مشخصات جستجوی ادمین
     */
    private AdminUserSpecification adminUserSpecification;
    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;

    /**
     * متد سازنده پیش فرض
     */
    public AdminUserServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public AdminUserServiceImpl(AdminUserRepository adminUserRepository, AdminUserContactRepository adminUserContactRepository, AdminUserSkillService adminUserSkillService, EtcItemService etcItemService, AdminUserSpecification adminUserSpecification, ModelMapper modelMapper) {
        this.adminUserRepository = adminUserRepository;
        this.adminUserContactRepository = adminUserContactRepository;
        this.adminUserSkillService = adminUserSkillService;
        this.etcItemService = etcItemService;
        this.adminUserSpecification = adminUserSpecification;
        this.modelMapper = modelMapper;
    }

    /**
     * متد ثبت
     *
     * @param adminUserModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public AdminUserModel create(@NotNull AdminUserModel adminUserModel) throws Exception {

        if(adminUserModel.getUsername().equalsIgnoreCase("eng.motahari@gmail.com")){
            throw new GraphQLCustomException(BusinessExceptionEnum.DUPLICATE_EMAIL, "sample description");
        }
        //ثبت اطلاعات تماس ادمین
        AdminUserContact adminUserContact = new AdminUserContact();
        adminUserContact.setAddress(adminUserModel.getDefaultAdminUserContact_address());
        adminUserContact = adminUserContactRepository.save(adminUserContact);

        //ثبت ادمین
        AdminUser adminUser = new AdminUser();
        adminUser.setFirstName(adminUserModel.getFirstName());
        adminUser.setLastName(adminUserModel.getLastName());
        adminUser.setPassword(adminUserModel.getPassword());
        adminUser.setUsername(adminUserModel.getUsername());
        if (!ObjectUtils.isEmpty(adminUserModel.getDateOfBirth())) {
            adminUser.setDateOfBirth(CalendarTools.getDateFromCustomDate(adminUserModel.getDateOfBirth()));
        }
        adminUser.setGender(etcItemService.findByIdAndCheckEntity(adminUserModel.getGender_id(), GenderEnum.class, null, true));
        //ثبت مهارتهای ادمین
        adminUser = adminUserSkillService.createByAdminUser(adminUser, adminUserModel.getSkillList());

        adminUser = adminUserRepository.save(adminUser);
        adminUser.setDefaultAdminUserContact(adminUserContact);
        adminUserModel.setId(adminUser.getId());
        return adminUserModel;
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    @NotNull
    public AdminUserModel readById(@NotNull Integer id) throws UtilityException {
        AdminUser adminUser = adminUserRepository.findById(id).get();
        AdminUserModel adminUserModel = new AdminUserModel();
        adminUserModel.setId(adminUser.getId());
        adminUserModel.setUsername(adminUser.getUsername());
        adminUserModel.setFirstName(adminUser.getFirstName());
        adminUserModel.setLastName(adminUser.getLastName());
        adminUserModel.setPassword(adminUser.getPassword());
        adminUserModel.setDateOfBirth(new CustomDate(adminUser.getDateOfBirth()));
        if (!ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
            adminUserModel.setDefaultAdminUserContact_address(adminUser.getDefaultAdminUserContact().getAddress());
        }
        if (!ObjectUtils.isEmpty(adminUser.getSkillSet())) {
            adminUser.getSkillSet().stream().forEach(item -> {
                adminUserModel.getSkillList().add(new AdminUserSkillModel(item.getId(), item.getTitle()));
            });
        }
//    UserModel  userModel= (UserModel) SerializationUtils.clone(adminuser);
        return adminUserModel;
    }

    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel       مدل فیلتر جستجو
     * @param searchViewTypeInterface کلاس اینترفیس نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList         لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @Override
    @NotNull
    public SearchDataModel readGrid(@NotNull SearchFilterModel searchFilterModel, @NotNull Class searchViewTypeInterface, @NotNull List<Object> searchValueList) throws UtilityException {
        //تعریف فیلترهای جستجو
        adminUserSpecification = (AdminUserSpecification) searchFilterModel.makeSpecification(new AdminUserSpecification());
        //جستجو بر طبق فیلترهای جستجو و کلاس اینترفیس نوع نمایش و صفحه بندی دلخواه کلاینت ساید
        Page<AdminUserSearchViewTypeBrief> viewPage = adminUserRepository.findAll(adminUserSpecification, searchViewTypeInterface, searchFilterModel.makePageable());
        //تعریف خروجی بر اساس جستجو
        SearchDataModel searchDataModel = new SearchDataModel(viewPage, searchFilterModel, searchViewTypeInterface, "");
        return searchDataModel;
    }

    /**
     * متد ویرایش
     *
     * @param adminUserModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @Override
    @NotNull
    public AdminUserModel update(@NotNull AdminUserModel adminUserModel) throws Exception {
        AdminUser adminUser = adminUserRepository.findById(adminUserModel.getId()).get();
        adminUser.setFirstName(adminUserModel.getFirstName());
        adminUser.setLastName(adminUserModel.getLastName());
        adminUser.setPassword(adminUserModel.getPassword());
        adminUser.setUsername(adminUserModel.getUsername());
        if (!ObjectUtils.isEmpty(adminUserModel.getDateOfBirth())) {
            adminUser.setDateOfBirth(CalendarTools.getDateFromCustomDate(adminUserModel.getDateOfBirth()));
        }
        adminUser.setGender(etcItemService.findByIdAndCheckEntity(adminUserModel.getGender_id(), GenderEnum.class, null, true));
        if (ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
            AdminUserContact adminUserContact = new AdminUserContact();
            adminUserContact.setAddress(adminUserModel.getDefaultAdminUserContact_address());
            adminUserContact = adminUserContactRepository.save(adminUserContact);
            adminUser.setDefaultAdminUserContact(adminUserContact);
        } else {
            adminUser.getDefaultAdminUserContact().setAddress(adminUserModel.getDefaultAdminUserContact_address());
        }

        //ویرایش مهارتهای ادمین
        adminUser = adminUserSkillService.updateByAdminUser(adminUser, adminUserModel.getSkillList());
        adminUserRepository.save(adminUser);
        return adminUserModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    public AdminUserModel delete(@NotNull Integer id) throws UtilityException {
        AdminUserModel adminUserModel = this.readById(id);
        AdminUser adminUser = adminUserRepository.findById(adminUserModel.getId()).get();
        //حذف اطلاعات تماس ادمین
        adminUserContactRepository.deleteById(adminUser.getDefaultAdminUserContact().getId());
        //حذف مهارتهای ادمین
        adminUser = adminUserSkillService.deleteByAdminUser(adminUser);
        adminUserRepository.save(adminUser);
        adminUserRepository.delete(adminUser);
        return adminUserModel;
    }

    @NotNull
    private AdminUserModel convertToDto(@NotNull AdminUser adminUser) {
        AdminUserModel adminUserModel = modelMapper.map(adminUser, AdminUserModel.class);
//        userModel.setSubmissionDate(adminuser.getSubmissionDate(), userService.getCurrentUser().getPreference().getTimezone());
        return adminUserModel;
    }
}
