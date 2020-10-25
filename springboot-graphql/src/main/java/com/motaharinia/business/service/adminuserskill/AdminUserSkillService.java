package com.motaharinia.business.service.adminuserskill;

import com.motaharinia.persistence.orm.adminuser.AdminUser;
import com.motaharinia.presentation.adminuserskill.AdminUserSkillModel;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس مدیریت مهارتها
 */
public interface AdminUserSkillService {
    /**
     * متد ثبت مهارت برای ادمین
     *
     * @param adminUser انتیتی ادمین
     * @param adminUserSkillModelList لیست مدلهای ثبت
     * @return خروجی: انتیتی ادمین تکمیل شده
     */
    @NotNull
    AdminUser createByAdminUser(@NotNull AdminUser adminUser, @NotNull List<AdminUserSkillModel> adminUserSkillModelList);

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @NotNull
    AdminUserSkillModel readById(@NotNull Integer id);


    /**
     * متد ویرایش مهارت برای ادمین
     *
     * @param adminUser انتیتی ادمین
     * @param adminUserSkillModelList لیست مدلهای ویرایش
     * @return خروجی: انتیتی ادمین تکمیل شده
     */
    @NotNull
    AdminUser updateByAdminUser(@NotNull AdminUser adminUser,@NotNull  List<AdminUserSkillModel> adminUserSkillModelList);


    /**
     * متد حذف مهارت برای ادمین
     *
     * @param adminUser انتیتی ادمین
     * @return خروجی: انتیتی ادمین که مهارتهای آن حذف شده
     */
    @NotNull
    AdminUser deleteByAdminUser(@NotNull AdminUser adminUser) ;

}
