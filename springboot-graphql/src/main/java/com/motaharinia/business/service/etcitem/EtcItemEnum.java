package com.motaharinia.business.service.etcitem;

import org.springframework.util.ObjectUtils;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 * این اینترفیس برای این ایجاد شده است که بتوانیم برای مقادیر ثابت سامانه معادل اینام داشته باشیم
 * برای این کار به جای اینکه تمام مقادیر ثابت سامانه را در یک اینام قرار دهیم آنها را در فایلهای مختلف با پیاده سازی این اینترفیس قرار میدهیم
 * کلاسهای اینام موجود در این پکیج همگی مربوط به ماژول مقادیر ثابت هستند و نباید آنها را در پکیج ماژولهای دیگر بگذاریم
 * چون تمام این کلاسهای اینام مشخص کننده نوع یک انتیتی مقدار ثابت در دیتابیس و بیزینس کد هستند
 * چون مقادیر ثابت هستند باید بتوانیم در بیزینس روی آنها اگر و شرط بنویسیم که تعریف این اینامها به ما در این مورد هم کمک میکند
 * مثل اینکه بخواهیم چک کنیم آیا جنسیت یوزر مرد است یا زن که میتوانیم نوع جنیسیت انتیتی یوزر را با یکی از اینام های مرد یا زن بررسی کنیم
 */
public interface EtcItemEnum {


    /**
     * متد نشان دهنده این که چه ماژولی از این مقدار ثابت استفاده خواهد کرد
     */
    Class getEntityClass();

    //این تابع مقدار فیلد ولیو در انتیتی رو پر میکند

    /**
     * این مقدار value در انتیتی EtcItem را پر میکند
     *
     * @return
     */
    String getValue();

    /**
     * این مقدار type در انتیتی EtcItem را پر میکند
     *
     * @return
     */
    String getType();


//    class CurrentClassGetter extends SecurityManager {
//        public String getClassName() {
//            return getClassContext()[1].getName();
//        }
//    }

    static String getEtcClassName(){
//        return new CurrentClassGetter().getClassName();
        String className="";
        if(!ObjectUtils.isEmpty(Thread.currentThread().getStackTrace()) && Thread.currentThread().getStackTrace().length>1){
            className= Thread.currentThread().getStackTrace()[2].getClassName();
        }
        if(!ObjectUtils.isEmpty(className)){
            className=className.split("\\.")[className.split("\\.").length-1];
        }
        return className;
    }
}
