package com.motaharinia.persistence.orm.adminuserskill;


import com.motaharinia.msutility.entity.GenericEntity;
import com.motaharinia.persistence.orm.adminuser.AdminUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی مهارت ادمین
 */
@Entity
@Table(name = "admin_user_skill")
public class AdminUserSkill extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * عنوان
     */
    @Column(name = "title")
    private String title;


    /**
     * ادمین های متصل به این مهارت
     */
    @ManyToMany(mappedBy = "skillSet", fetch = FetchType.LAZY)
    private Collection<AdminUser> userCollection;

    @Override
    public String toString() {
        return "AdminUserJob{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<AdminUser> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<AdminUser> adminUserCollection) {
        this.userCollection = adminUserCollection;
    }
}
