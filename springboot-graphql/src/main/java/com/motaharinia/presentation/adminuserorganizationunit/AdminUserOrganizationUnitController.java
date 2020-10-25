package com.motaharinia.presentation.adminuserorganizationunit;

import com.motaharinia.business.service.adminuserorganizationunit.AdminUserOrganizationUnitService;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.jstree.JstreeNodeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-28<br>
 * Time: 16:37:17<br>
 * Description:<br>
 * Description:<br>
 *  کلاس کنترلر واحد سازمانی ادمین
 */
@RestController
public class AdminUserOrganizationUnitController {
    private final AdminUserOrganizationUnitService adminUserOrganizationUnitService;

    @Autowired
    public AdminUserOrganizationUnitController(AdminUserOrganizationUnitService adminUserOrganizationUnitService) {
        this.adminUserOrganizationUnitService = adminUserOrganizationUnitService;
    }


    /**
     * متد ثبت
     *
     * @param adminUserOrganizationUnitModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @PostMapping("/adminUserOrganizationUnit")
    public AdminUserOrganizationUnitModel create(@RequestBody @Validated AdminUserOrganizationUnitModel adminUserOrganizationUnitModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        return adminUserOrganizationUnitService.create(adminUserOrganizationUnitModel);
    }


    /**
     * متد درخت
     *
     * @param id شناسه
     * @return خروجی: درخت
     */
    @GetMapping("/adminUserOrganizationUnit/{id}")
    public List<JstreeNodeModel> readById(@PathVariable Integer id) {
        return adminUserOrganizationUnitService.tree(id);
    }

}
