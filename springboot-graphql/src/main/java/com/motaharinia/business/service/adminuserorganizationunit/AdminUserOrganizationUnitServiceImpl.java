package com.motaharinia.business.service.adminuserorganizationunit;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.jstree.JstreeNodeAAttrModel;
import com.motaharinia.msutility.jstree.JstreeNodeModel;
import com.motaharinia.msutility.jstree.JstreeNodeStateModel;
import com.motaharinia.persistence.orm.adminuserorganizationunit.AdminUserOrganizationUnit;
import com.motaharinia.persistence.orm.adminuserorganizationunit.AdminUserOrganizationUnitRepository;
import com.motaharinia.presentation.adminuserorganizationunit.AdminUserOrganizationUnitModel;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-28<br>
 * Time: 16:27:33<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت واحدهای سازمانی ادمین ها
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminUserOrganizationUnitServiceImpl implements AdminUserOrganizationUnitService {

    /**
     * ریپازیتوری ادمین
     */
    private AdminUserOrganizationUnitRepository adminUserOrganizationUnitRepository;

    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;

    /**
     * متد سازنده پیش فرض
     */
    public AdminUserOrganizationUnitServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public AdminUserOrganizationUnitServiceImpl(AdminUserOrganizationUnitRepository adminUserOrganizationUnitRepository, ModelMapper modelMapper) {
        this.adminUserOrganizationUnitRepository = adminUserOrganizationUnitRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * متد ثبت
     *
     * @param adminUserOrganizationUnitModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    public @NotNull AdminUserOrganizationUnitModel create(@NotNull AdminUserOrganizationUnitModel adminUserOrganizationUnitModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        //ثبت اطلاعات تماس ادمین
        AdminUserOrganizationUnit adminUserOrganizationUnit = new AdminUserOrganizationUnit();
        adminUserOrganizationUnit.setTitle(adminUserOrganizationUnitModel.getTitle());
        adminUserOrganizationUnit.setCode(adminUserOrganizationUnitModel.getCode());
        if (!ObjectUtils.isEmpty(adminUserOrganizationUnitModel.getParent_id())) {
            AdminUserOrganizationUnit adminUserOrganizationUnitParent = adminUserOrganizationUnitRepository.findById(adminUserOrganizationUnitModel.getParent_id()).get();
            adminUserOrganizationUnit.setParent(adminUserOrganizationUnitParent);
        }
        adminUserOrganizationUnit = adminUserOrganizationUnitRepository.save(adminUserOrganizationUnit);
        adminUserOrganizationUnitModel.setId(adminUserOrganizationUnit.getId());
        return adminUserOrganizationUnitModel;
    }

    /**
     * متد درخت
     *
     * @param parentId شناسه والد(برای دریافت درخت گره های روت عدد صفر ارسال شود)
     * @return خروجی: لیست مدل درخت
     */
    @Override
    public @NotNull List<JstreeNodeModel>  tree(@NotNull Integer parentId) {
        List<JstreeNodeModel> jstreeNodeModelList=new ArrayList<>();
        AdminUserOrganizationUnit adminUserOrganizationUnitParent = null;
        if (!ObjectUtils.isEmpty(parentId) && !parentId.equals(0)) {
            adminUserOrganizationUnitParent = adminUserOrganizationUnitRepository.findById(parentId).get();
        }
        //به دست آوردن لیست مدل فرزندان والد
        List<AdminUserOrganizationUnit> childrenList = adminUserOrganizationUnitRepository.findAllByParent(adminUserOrganizationUnitParent);
        if (!ObjectUtils.isEmpty(childrenList)) {
            List<JstreeNodeModel> childrenModelList=new ArrayList<>();
            String type="";
            HashMap<String, Object> dataMap= new HashMap<String, Object>();
            for(AdminUserOrganizationUnit child:   childrenList){
                 childrenModelList = tree(child.getId());
                //اگر والد فرزندی نداشته باشد آن را به عنوان نوع گره برگ به کلاینت معرفی میکنیم
                type = "default";
                if (ObjectUtils.isEmpty(childrenModelList)) {
                    type = "isLeaf";
                }
                //اگر بخواهیم به هر گره اطلاعات اضافی تری غیر از شناسه و عنوان اضافه کنیم که کلاینت از آن استفاده کند dataMap را اینجا پر میکنیم
                dataMap.clear();
                dataMap.put("code", child.getCode());

                jstreeNodeModelList.add( new JstreeNodeModel(child.getId().toString(), child.getTitle(), childrenModelList, "", new JstreeNodeStateModel(true, false, false), new JstreeNodeAAttrModel("", "_blank"), type, dataMap));
            }
        }
      return jstreeNodeModelList;
    }
}
