package com.motaharinia.business.service.etcitem;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.genericmodel.CustomComboModel;
import com.motaharinia.persistence.orm.etcitem.EtcItem;
import com.motaharinia.presentation.generic.CustomComboFilterModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس مدیریت مقادیر ثابت
 */
@Component
public interface EtcItemService  {

    /**
     * این متد یک مقدار ثابت را با استفاده از شناسه آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     * @param id شناسه مقدار ثابت
     * @param etcItemEnumClass کلاس مقدارثابت
     * @param checkTypeTag  تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @NotNull
    EtcItem findByIdAndCheckEntity(@NotNull Integer id,@NotNull  Class etcItemEnumClass, String checkTypeTag,@NotNull  Boolean checkInvalid) throws Exception;

    /**
     * این متد یک مقدار ثابت را با استفاده از مقدار آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     * @param checkEtcItemEnum  مقدار ثابت که حاوی مقدار برای جستجو است
     * @param checkTypeTag  تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @NotNull
    EtcItem findByValueAndCheckEntity(@NotNull EtcItemEnum checkEtcItemEnum, String checkTypeTag,@NotNull  Boolean checkInvalid) throws Exception;
    
//     @Override
//    CustomComboModel customCombo(CustomComboFilterModel model) throws Exception;

    /**
     * این متد typeTag دلخواه را دریافت میکند و تمام مقادیر ثابت مربوط به آن typeTag را خروجی میدهد
     * @param typeTag تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @return خروجی: لیستی از مقادیر ثابت
     */
    @NotNull
    List<EtcItem> findByTypeTag(@NotNull String typeTag);


    /**
     * این متد مدل فیلتر کاستوم کامبو را ورودی میگیرد و مدل داده کاستوم کامبو را خروجی میدهد<br>
     * در کاستوم کامبو(نوع کامبو) قابلیت جستجو و صفحه بندی نداریم و تمام داده ها خروجی داده میشود<br>
     * در کاستوم کامبو(نوع اتوکامپلیت) قابلیت جستجو و صفحه بندی داریم و داده ها متناسب با کلمه جستجو شده خروجی داده میشود
     * @param customComboFilterModel مدل فیلتر کاستوم کامبو
     * @return خروجی: مدل داده کاستوم کامبو
     * @throws Exception
     */
    @NotNull
    CustomComboModel customCombo(@NotNull CustomComboFilterModel customComboFilterModel);

    /**
     * این متد نوع و فعال/غیرفعال و نمایش/عدم نمایش را بعنوان ورودی میگیرد و لیستی از آرایه ستونهای داده مورد نظر را خروجی میدهد<br>
     *این متد برای کامبوهای قدیمی بک اند هستند که اگر بک اند هم با ریکت زده شود این متد باید حذف گردد چون ریکت با کاستوم کامبو جدید کار میکند
     * @param type نوع
     * @param invalid فعال غیرفعال
     * @param hidden نمایش عدم نمایش
     * @return خروجی: لیستی از آرایه ستونهای داده مورد نظر
     */
    List<Object[]> combo(String type, Boolean invalid, Boolean hidden);
}
