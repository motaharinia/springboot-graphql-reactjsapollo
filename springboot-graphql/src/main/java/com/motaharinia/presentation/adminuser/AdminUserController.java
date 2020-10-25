package com.motaharinia.presentation.adminuser;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.motaharinia.business.service.adminuser.AdminUserService;
import com.motaharinia.business.service.adminuser.*;
import com.motaharinia.business.service.adminuser.AdminUserSearchViewTypeBrief;
import com.motaharinia.business.service.BusinessExceptionEnum;
import com.motaharinia.config.graphql.GraphQLCustomException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.search.data.SearchDataModel;
import com.motaharinia.msutility.search.filter.SearchFilterModel;
import io.leangen.graphql.annotations.*;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
//import io.leangen.graphql.spqr.spring.util.ConcurrentMultiRegistry;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس کنترلر ادمین
 */
@RestController
@GraphQLApi
public class AdminUserController {

    private final AdminUserService adminUserService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }


    /**
     * متد ثبت
     *
     * @param adminUserModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @GraphQLMutation(name = "create")
    @PostMapping("/adminUser")
    public AdminUserModel create(@RequestBody @Validated AdminUserModel adminUserModel) throws Exception {
        return adminUserService.create(adminUserModel);
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @GraphQLQuery(name = "common_adminUser_readById")
    @GetMapping("/adminUser/{id}")
    public AdminUserModel readById(@PathVariable Integer id) throws Exception{
        if(id.equals(0)){
            throw new GraphQLCustomException(BusinessExceptionEnum.ID_NOT_FOUND, "sample description");
        }
        return adminUserService.readById(id);
    }


    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModelJson رشته جیسون مدل فیلتر جستجو
     * @param searchViewTypeEnum    نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList       لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @GetMapping("/adminUser")
    @GraphQLQuery(name = "readGrid")
    public SearchDataModel readGrid(@RequestParam(name = "searchFilterModel") Optional<String> searchFilterModelJson, @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws JsonProcessingException, UtilityException, ClassNotFoundException {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        SearchFilterModel searchFilterModel = customObjectMapper.readValue(searchFilterModelJson.get(), SearchFilterModel.class);
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = AdminUserSearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if (ObjectUtils.isEmpty(searchValueList)) {
            searchValueList = new ArrayList<>();
        }

        SearchDataModel searchDataModel = adminUserService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }

    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel  مدل فیلتر جستجو
     * @param searchViewTypeEnum نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList    لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws JsonProcessingException
     * @throws UtilityException
     */
    @GraphQLQuery(name = "readGridByModel")
    public SearchDataModel readGridByModel(@RequestBody @Validated SearchFilterModel searchFilterModel, @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws Exception {
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = AdminUserSearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if (ObjectUtils.isEmpty(searchValueList)) {
            searchValueList = new ArrayList<>();
        }
        SearchDataModel searchDataModel = adminUserService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }

    /**
     * متد ویرایش
     *
     * @param adminUserModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @GraphQLMutation(name = "update")
    @PutMapping("/adminUser")
    public AdminUserModel update(@RequestBody @Validated AdminUserModel adminUserModel) throws Exception {
        return adminUserService.update(adminUserModel);
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @GraphQLMutation(name = "delete")
    @DeleteMapping("/adminUser/{id}")
    public AdminUserModel delete(@PathVariable Integer id) throws UtilityException {
        return adminUserService.delete(id);
    }

    @GraphQLSubscription(name = "stockPrice")
    public Publisher<StockPrice> stockPrice(String symbol) {
        Random random = new Random();
        return Flux.interval(Duration.ofSeconds(1)) .map(num -> new StockPrice(symbol, random.nextDouble(), LocalDateTime.now()));
    }


    @GraphQLSubscription(name = "briefMesseages")
    public Publisher<NotifyModel> briefMesseages(Integer userId) {
        AtomicInteger count = new AtomicInteger(0);
        return Flux.interval(Duration.ofSeconds(2)) .map(num -> new NotifyModel(count.incrementAndGet()));
    }


//    private final ConcurrentMultiRegistry<String, FluxSink<Task>> subscribers = new ConcurrentMultiRegistry<>();

//    @GraphQLSubscription
//    public Publisher<Task> taskStatusChanged(String code) {
//        return Flux.create(subscriber -> subscribers.add(code, subscriber.onDispose(() -> subscribers.remove(code, subscriber))), FluxSink.OverflowStrategy.LATEST);
//    }
}
