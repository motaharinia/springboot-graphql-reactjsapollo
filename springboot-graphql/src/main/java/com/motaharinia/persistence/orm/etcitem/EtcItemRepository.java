package com.motaharinia.persistence.orm.etcitem;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

//https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
//https://attacomsian.com/blog/spring-data-jpa-specifications
//https://github.com/pramoth/specification-with-projection
//https://stackoverflow.com/questions/22171822/spring-data-jpa-specification-to-select-specific-columns

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس ریپازیتوری مقادیر ثابت
 */
@Repository
public interface EtcItemRepository extends JpaRepository<EtcItem, Integer>, JpaSpecificationExecutorWithProjection<EtcItem> {
    EtcItem findByValueAndType(String value, String type);

    EtcItem findByValueAndTypeAndTypeTag(String value, String type, String typeTag);

    List<EtcItem> findAllByTypeTag(String typeTag);

    /**
     * این متد بر اساس نوع مقدار ثابت و مشخصات فعال/غیرفعال و نمایش/عدم نمایش ، تعداد سطرهای داده موجود را برمیگرداند
     * @param type نوع مقدار ثابت
     * @param invalid غیرفعالها را هم در نظر بگیرد؟
     * @param hidden عدم نمایش ها را هم در نظر بگیرد؟
     * @return خروجی: تعداد سطرهای داده
     */
    @Query(value = "SELECT count(obj.id) FROM EtcItem obj WHERE obj.type= :type and obj.invalid= :invalid and obj.hidden= :hidden")
    Long countByTypeAndInvalidAndHidden(@Param("type") String type, @Param("invalid") Boolean invalid, @Param("hidden") Boolean hidden);

    /**
     * این متد بر اساس نوع مقدار ثابت و مشخصات فعال/غیرفعال و نمایش/عدم نمایش ، لیستی از آرایه ستونهای داده موجود را برمیگرداند
     * @param type نوع مقدار ثابت
     * @param invalid غیرفعالها را هم در نظر بگیرد؟
     * @param hidden عدم نمایش ها را هم در نظر بگیرد؟
     * @return خروجی: لیستی از آرایه ستونهای داده مورد نظر
     */
    @Query(value = "SELECT obj.id,obj.langKey,obj.value FROM EtcItem obj WHERE obj.type= :type and obj.invalid= :invalid and obj.hidden= :hidden")
    List<Object[]> arrayListByTypeAndInvalidAndHidden(@Param("type") String type, @Param("invalid") Boolean invalid, @Param("hidden") Boolean hidden);

    /**
     * این متد بر اساس کلمه جستجو شده و مشخصات فعال/غیرفعال و نمایش/عدم نمایش ، تعداد سطرهای داده موجود را برمیگرداند
     * @param type نوع مقدار ثابت
     * @param value کلمه جستجو شده
     * @param invalid غیرفعالها را هم در نظر بگیرد؟
     * @param hidden عدم نمایش ها را هم در نظر بگیرد؟
     * @return خروجی: تعداد سطرهای داده
     */
    @Query(value = "SELECT count(obj.id) FROM EtcItem obj WHERE obj.type= :type and obj.value like %:value% and obj.invalid= :invalid and obj.hidden= :hidden")
    Long countByTypeAndValueAndInvalidAndHidden(@Param("type") String type, @Param("value") String value, @Param("invalid") Boolean invalid, @Param("hidden") Boolean hidden);

    /**
     * این متد بر اساس کلمه جستجو شده و مشخصات فعال/غیرفعال و نمایش/عدم نمایش ، لیستی از آرایه ستونهای داده موجود را برمیگرداند
     * @param type نوع مقدار ثابت
     * @param value کلمه جستجو شده
     * @param invalid غیرفعالها را هم در نظر بگیرد؟
     * @param hidden عدم نمایش ها را هم در نظر بگیرد؟
     * @param pageable مشخصات صفحه بندی
     * @return خروجی: لیستی از آرایه ستونهای داده مورد نظر
     */
    @Query(value = "SELECT obj.id,obj.langKey,obj.value FROM EtcItem obj WHERE obj.type= :type and obj.value like %:value% and obj.invalid= :invalid and obj.hidden= :hidden")
    List<Object[]> arrayListByTypeAndValueAndInvalidAndHidden(@Param("type") String type, @Param("value") String value, @Param("invalid") Boolean invalid, @Param("hidden") Boolean hidden, Pageable pageable);





}
