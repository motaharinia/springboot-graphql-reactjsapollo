package com.motaharinia.business.service.adminuserskill;


import com.motaharinia.persistence.orm.adminuser.AdminUser;
import com.motaharinia.persistence.orm.adminuserskill.AdminUserSkill;
import com.motaharinia.persistence.orm.adminuserskill.AdminUserSkillRepository;
import com.motaharinia.presentation.adminuser.AdminUserModel;
import com.motaharinia.presentation.adminuserskill.AdminUserSkillModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//https://www.baeldung.com/spring-data-jpa-projections
//https://walczak.it/blog/spring-data-jpa-projection-dynamic-queries


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *   کلاس پیاده سازی اینترفیس مدیریت مهارتها
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminUserSkillServiceImpl implements AdminUserSkillService {


    /**
     * ریپازیتوری مهارت
     */
    private AdminUserSkillRepository adminUserSkillRepository;

    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;


    /**
     * متد سازنده پیش فرض
     */
    public AdminUserSkillServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public AdminUserSkillServiceImpl(AdminUserSkillRepository adminUserSkillRepository, ModelMapper modelMapper) {
        this.adminUserSkillRepository = adminUserSkillRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * متد ثبت مهارت برای ادمین
     *
     * @param adminUser               انتیتی ادمین
     * @param adminUserSkillModelList لیست مدلهای ثبت
     * @return خروجی: انتیتی ادمین تکمیل شده
     */
    @Override
    public AdminUser createByAdminUser(AdminUser adminUser, List<AdminUserSkillModel> adminUserSkillModelList) {
        if(!ObjectUtils.isEmpty(adminUserSkillModelList)) {
            for (AdminUserSkillModel adminUserSkillModel : adminUserSkillModelList) {
                //بررسی و در صورت نیاز ثبت مهارت جدید
                AdminUserSkill adminUserSkill = adminUserSkillRepository.findByTitle(adminUserSkillModel.getTitle());
                if (ObjectUtils.isEmpty(adminUserSkill)) {
                    adminUserSkill = new AdminUserSkill();
                    adminUserSkill.setTitle(adminUserSkillModel.getTitle());
                    adminUserSkill = adminUserSkillRepository.save(adminUserSkill);
                }
                // اضافه کردن به کالکشن
                adminUser.getSkillSet().add(adminUserSkill);
            }
        }
        return adminUser;
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    public AdminUserSkillModel readById(Integer id) {
        AdminUserSkill adminUserSkill = adminUserSkillRepository.findById(id).get();
        AdminUserSkillModel adminUserSkillModel = new AdminUserSkillModel();
        adminUserSkillModel.setId(adminUserSkill.getId());
        adminUserSkillModel.setTitle(adminUserSkill.getTitle());
//    UserModel  userModel= (UserModel) SerializationUtils.clone(adminuser);
        return adminUserSkillModel;
    }


    /**
     * متد ویرایش مهارت برای ادمین
     *
     * @param adminUser               انتیتی ادمین
     * @param adminUserSkillModelList لیست مدلهای ویرایش
     * @return خروجی: انتیتی ادمین تکمیل شده
     */
    @Override
    public AdminUser updateByAdminUser(AdminUser adminUser, List<AdminUserSkillModel> adminUserSkillModelList) {
        AdminUserSkill adminUserSkill = null;
        if(!ObjectUtils.isEmpty(adminUserSkillModelList)) {
            Set<Integer> dbIdList = adminUser.getSkillSet().stream().map(item -> item.getId()).collect(Collectors.toSet());

            //حذف مواردی که در لیست مدل نیست
            //آی دی هایی که باید از کالکشن حذف شوند
            Set<Integer> idForRemoveList = new HashSet<>();
            idForRemoveList.addAll(dbIdList);
            idForRemoveList.removeAll(adminUserSkillModelList.stream().map(item -> item.getId()).collect(Collectors.toSet()));
            for (Integer idForRemove : idForRemoveList) {
                adminUserSkill = adminUserSkillRepository.findById(idForRemove).get();
                adminUser.getSkillSet().remove(adminUserSkill);
            }

            //ثبت موارد جدید و اضافه کردن به کالکشن
            for (AdminUserSkillModel adminUserSkillModel : adminUserSkillModelList) {
                if (ObjectUtils.isEmpty(adminUserSkillModel.getId())) {
                    adminUserSkill = new AdminUserSkill();
                    adminUserSkill.setTitle(adminUserSkillModel.getTitle());
                    adminUserSkill = adminUserSkillRepository.save(adminUserSkill);
                    adminUser.getSkillSet().add(adminUserSkill);
                } else {
                    if (!dbIdList.contains(adminUserSkillModel.getId())) {
                        adminUserSkill = adminUserSkillRepository.findById(adminUserSkillModel.getId()).get();
                        adminUser.getSkillSet().add(adminUserSkill);
                    }
                }
            }
        }
        return adminUser;
    }


    /**
     * متد حذف مهارت برای ادمین
     *
     * @param adminUser انتیتی ادمین
     * @return خروجی: انتیتی ادمین که مهارتهای آن حذف شده
     */
    @Override
    public AdminUser deleteByAdminUser(AdminUser adminUser) {
        adminUser.getSkillSet().clear();
        return adminUser;
    }


    private AdminUserModel convertToDto(AdminUser adminUser) {
        AdminUserModel adminUserModel = modelMapper.map(adminUser, AdminUserModel.class);
//        userModel.setSubmissionDate(adminuser.getSubmissionDate(), userService.getCurrentUser().getPreference().getTimezone());
        return adminUserModel;
    }
}
