package com.motaharinia.persistence.orm.etcitem;

import com.motaharinia.business.service.etcitem.EtcItemService;
import com.motaharinia.business.service.etcitem.GenderEnum;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-28<br>
 * Time: 11:26:52<br>
 * Description:<br>
 */
@Component
public class EtcItemInitialData {


    /**
     * سرویس مقادیر ثابت
     */
    @Autowired
    private EtcItemRepository etcItemRepository;
    @Autowired
    private EtcItemService etcItemService;

    public void checkEtcItems() throws BusinessException, IllegalAccessException, UtilityException, InvocationTargetException {
        EtcItem etcItem=null;
        try {
            etcItem = etcItemService.findByIdAndCheckEntity(1, GenderEnum.class, null, true);
        }catch(Exception e){
            etcItem = new EtcItem();
            etcItem.setLangKey("etcItem.common.user.gender.MALE");
            etcItem.setOrderColumn(5);
            etcItem.setType("gender");
            etcItem.setTypeTag("");
            etcItem.setValue("MALE");
            etcItemRepository.save(etcItem);
        }
        try {
            etcItem = etcItemService.findByIdAndCheckEntity(2, GenderEnum.class, null, true);
        }catch(Exception e){
            etcItem = new EtcItem();
            etcItem.setLangKey("etcItem.common.user.gender.FEMALE");
            etcItem.setOrderColumn(6);
            etcItem.setType("gender");
            etcItem.setTypeTag("");
            etcItem.setValue("FEMALE");
            etcItemRepository.save(etcItem);
        }
    }
}
