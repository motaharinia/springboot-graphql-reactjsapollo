package com.motaharinia.business.service.adminuserorganizationunit;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.jstree.JstreeNodeModel;
import com.motaharinia.presentation.adminuserorganizationunit.AdminUserOrganizationUnitModel;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس مدیریت واحدهای سازمانی ادمین
 */
public interface AdminUserOrganizationUnitService {


    /**
     * متد ثبت
     *
     * @param adminUserOrganizationUnitModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @NotNull
    AdminUserOrganizationUnitModel create(@NotNull AdminUserOrganizationUnitModel adminUserOrganizationUnitModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;


    /**
     * متد درخت
     * @param parentId  شناسه والد(برای دریافت درخت گره های روت عدد صفر ارسال شود)
     * @return خروجی: مدل درخت
     */
    @NotNull
    List<JstreeNodeModel> tree(Integer parentId);

}
