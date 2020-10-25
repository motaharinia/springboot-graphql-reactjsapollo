package com.motaharinia.presentation.etcitem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.motaharinia.business.service.etcitem.EtcItemService;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.genericmodel.CustomComboModel;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.jstree.JstreeNodeModel;
import com.motaharinia.msutility.search.filter.SearchFilterModel;
import com.motaharinia.presentation.adminuserorganizationunit.AdminUserOrganizationUnitModel;
import com.motaharinia.presentation.generic.CustomComboFilterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-28<br>
 * Time: 16:37:17<br>
 * Description:<br>
 * Description:<br>
 * کلاس کنترلر مقادیر ثابت
 */
@RestController
public class EtcItemController {
    private final EtcItemService etcItemService;

    @Autowired
    public EtcItemController(EtcItemService etcItemService) {
        this.etcItemService = etcItemService;
    }


    /**
     * این متد مدل رشته جیسون فیلتر کاستوم کامبو را ورودی میگیرد و مدل داده کاستوم کامبو را خروجی میدهد
     *
     * @param customComboFilterModelJson رشته جیسون فیلتر کاستوم کامبو
     * @return خروجی: مدل داده کاستوم کامبو
     * @throws JsonProcessingException
     */
    @GetMapping("/etcItem")
    public CustomComboModel customCombo(@RequestParam(name = "customComboFilterModel") Optional<String> customComboFilterModelJson) throws JsonProcessingException {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        CustomComboFilterModel customComboFilterModel = customObjectMapper.readValue(customComboFilterModelJson.get(), CustomComboFilterModel.class);

        CustomComboModel customComboModel = etcItemService.customCombo(customComboFilterModel);
        if (!ObjectUtils.isEmpty(customComboModel.getDataList())) {
            customComboModel.getDataList().stream().forEach((item) -> {
                System.out.println("EtcItemController.customCombo loop item.getValue():" + item.getValue()  + " , item.getCaption():"+ item.getCaption());
            });
        }

        return customComboModel;
    }

}
