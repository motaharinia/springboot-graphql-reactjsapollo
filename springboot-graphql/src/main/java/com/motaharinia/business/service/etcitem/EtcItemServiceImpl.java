package com.motaharinia.business.service.etcitem;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.entity.EntityTools;
import com.motaharinia.msutility.genericmodel.CustomComboDataRowModel;
import com.motaharinia.msutility.genericmodel.CustomComboModel;
import com.motaharinia.persistence.orm.etcitem.EtcItem;
import com.motaharinia.persistence.orm.etcitem.EtcItemRepository;
import com.motaharinia.presentation.generic.CustomComboFilterModel;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

//https://www.baeldung.com/spring-data-jpa-projections
//https://walczak.it/blog/spring-data-jpa-projection-dynamic-queries


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت مقادیر ثابت
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EtcItemServiceImpl implements EtcItemService {


    /**
     * ریپازیتوری مقدار ثابت
     */
    private EtcItemRepository etcItemRepository;

    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;


    /**
     * متد سازنده پیش فرض
     */
    public EtcItemServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public EtcItemServiceImpl(EtcItemRepository etcItemRepository, ModelMapper modelMapper) {
        this.etcItemRepository = etcItemRepository;
        this.modelMapper = modelMapper;
    }


    /**
     * این متد یک مقدار ثابت را با استفاده از شناسه آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     *
     * @param id               شناسه مقدار ثابت
     * @param etcItemEnumClass کلاس مقدارثابت
     * @param checkTypeTag     تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid     آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @Override
    public @NotNull EtcItem findByIdAndCheckEntity(@NotNull Integer id, @NotNull Class etcItemEnumClass, String checkTypeTag, @NotNull Boolean checkInvalid) throws Exception {
        if (ObjectUtils.isEmpty(id)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "id");
        }
        if (ObjectUtils.isEmpty(etcItemEnumClass)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "etcItemEnumClass");
        }
        if (ObjectUtils.isEmpty(checkInvalid)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "checkInvalid");
        }
        EtcItem etcItem = etcItemRepository.findById(id).get();
        EntityTools.checkEntity(etcItem, EtcItem.class, checkInvalid);

        //تطبیق مقدار نوع ثابت جستجو شده با نوع کلاس ورودی
        String type = etcItemEnumClass.getSimpleName().replace("Enum", "");
        type = type.substring(0, 1).toLowerCase() + type.substring(1);
        if (!type.equalsIgnoreCase(etcItem.getType())) {
            throw new BusinessException(getClass(), EtcItemBusinessExceptionKeyEnum.ETC_ITEM_TYPE_NOT_MATCH, type + "!=" + etcItem.getType());
        }
        if ((!ObjectUtils.isEmpty(checkTypeTag)) && (!etcItem.getTypeTag().contains(checkTypeTag))) {
            throw new BusinessException(getClass(), EtcItemBusinessExceptionKeyEnum.ETC_ITEM_TYPETAG_NOT_MATCH, etcItem.getTypeTag() + " not contains " + checkTypeTag);
        }
        return etcItem;
    }

    /**
     * این متد یک مقدار ثابت را با استفاده از مقدار آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     *
     * @param checkEtcItemEnum مقدار ثابت که حاوی مقدار برای جستجو است
     * @param checkTypeTag     تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid     آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @Override
    public @NotNull EtcItem findByValueAndCheckEntity(@NotNull EtcItemEnum checkEtcItemEnum, String checkTypeTag, @NotNull Boolean checkInvalid) throws Exception {
        if (ObjectUtils.isEmpty(checkEtcItemEnum)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "checkEtcItemEnum");
        }
        if (ObjectUtils.isEmpty(checkInvalid)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "checkInvalid");
        }
        EtcItem etcItem = null;
        if (ObjectUtils.isEmpty(checkTypeTag)) {
            etcItem = etcItemRepository.findByValueAndType(checkEtcItemEnum.getValue(), checkEtcItemEnum.getType());
        } else {
            etcItem = etcItemRepository.findByValueAndTypeAndTypeTag(checkEtcItemEnum.getValue(), checkEtcItemEnum.getType(), checkTypeTag);
        }
        EntityTools.checkEntity(etcItem, EtcItem.class, checkInvalid);
        return etcItem;
    }

    /**
     * این متد typeTag دلخواه را دریافت میکند و تمام مقادیر ثابت مربوط به آن typeTag را خروجی میدهد
     *
     * @param typeTag تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @return خروجی: لیستی از مقادیر ثابت
     */
    @Override
    public @NotNull List<EtcItem> findByTypeTag(String typeTag) {
        if (ObjectUtils.isEmpty(typeTag)) {
            return new ArrayList<>();
        } else {
            return etcItemRepository.findAllByTypeTag(typeTag);
        }
    }

    /**
     * این متد مدل فیلتر کاستوم کامبو را ورودی میگیرد و مدل داده کاستوم کامبو را خروجی میدهد<br>
     * در کاستوم کامبو(نوع کامبو) قابلیت جستجو و صفحه بندی نداریم و تمام داده ها خروجی داده میشود<br>
     * در کاستوم کامبو(نوع اتوکامپلیت) قابلیت جستجو و صفحه بندی داریم و داده ها متناسب با کلمه جستجو شده خروجی داده میشود
     * @param customComboFilterModel مدل فیلتر کاستوم کامبو
     * @return خروجی: مدل داده کاستوم کامبو
     * @throws Exception
     */
    @Override
    public CustomComboModel customCombo(CustomComboFilterModel customComboFilterModel) {

        Long totalCount = 0l;
        List<Object[]> listObject = new ArrayList();
        List<CustomComboDataRowModel> dataRowModelList = new ArrayList<>();

        switch (customComboFilterModel.getMode()) {
            /**
             * جنسیت
             */
            case ETC_ITEM__GENDER: {
                //if (totalCount > 0) {
                switch (customComboFilterModel.getType()) {
                    case COMBO:
                        totalCount = etcItemRepository.countByTypeAndInvalidAndHidden("gender", false, false);
                        listObject = etcItemRepository.arrayListByTypeAndInvalidAndHidden("gender", false, false);
                        break;
                    case AUTOCOMPLETE:
                        totalCount = etcItemRepository.countByTypeAndValueAndInvalidAndHidden("gender",customComboFilterModel.getInput(), false, false);
                        Pageable pageable = PageRequest.of(customComboFilterModel.getPage(), customComboFilterModel.getRows());
                        listObject = etcItemRepository.arrayListByTypeAndValueAndInvalidAndHidden("gender",customComboFilterModel.getInput(), false, false, pageable);
                        break;
                }
                //}
            }
            break;
        }

        //پر کردن مدل داده
        if (!ObjectUtils.isEmpty(listObject) && listObject.get(0).length > 2) {
            for (Object[] obj : listObject) {
                CustomComboDataRowModel customComboDataRowModel = new CustomComboDataRowModel(obj[0].toString(), (String) obj[1]);
                customComboDataRowModel.getExtMap().put("value", obj[2]);
                dataRowModelList.add(customComboDataRowModel);
            }
        }

        return new CustomComboModel(dataRowModelList, totalCount.intValue(), customComboFilterModel.getPage(), customComboFilterModel.getRows());
    }

    /**
     * این متد نوع و فعال/غیرفعال و نمایش/عدم نمایش را بعنوان ورودی میگیرد و لیستی از آرایه ستونهای داده مورد نظر را خروجی میدهد<br>
     *این متد برای کامبوهای قدیمی بک اند هستند که اگر بک اند هم با ریکت زده شود این متد باید حذف گردد چون ریکت با کاستوم کامبو جدید کار میکند
     * @param type نوع
     * @param invalid فعال غیرفعال
     * @param hidden نمایش عدم نمایش
     * @return خروجی: لیستی از آرایه ستونهای داده مورد نظر
     */
    public List<Object[]> combo(String type, Boolean invalid, Boolean hidden){
        List<Object[]> listObject = etcItemRepository.arrayListByTypeAndInvalidAndHidden(type, invalid, hidden);
        return listObject;
    }

}
