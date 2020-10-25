package com.motaharinia.persistence.orm.adminuser;

import com.motaharinia.business.service.adminuser.AdminUserSearchViewTypeBrief;
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
 *  کلاس ریپازیتوری ادمین
 */
@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Integer>, JpaSpecificationExecutorWithProjection<AdminUser> {

    List<AdminUserSearchViewTypeBrief> findAllUserByFirstName(String firstName);
//    List<GridDataRowUserBriefView> readBriefView(String firstName);

//    User findUserByUsername(String username);
//    User findUserById(Integer id);
//    List<User> findAllBy


//    @Query("select lo.startDate,count(*) from LoanOffer lo where lo.loan.fsp= :fsp and lo.startDate between :fromDate and :toDate Group by lo.startDate")
//    public List<Object[]> arrayListLastMonthLoans(@Param("fsp")Fsp fsp,@Param("fromDate")LocalDate fromDate,@Param("toDate") LocalDate toDate);


//    @Transactional(timeout = 10)


    @Query("select obj.id,obj.firstName,obj.lastName from AdminUser obj where obj.firstName= :firstName")
    List<Object[]> arrayListGrid(@Param("firstName")String firstName);

    AdminUser findByLastNameLikeAndFirstNameStartingWith(String lastName, String firstName);

    List<AdminUser> findAllByLastNameLikeAndFirstNameStartingWith(String lastName, String firstName);


}
