package com.motaharinia.persistence.orm.adminusercontact;


import com.motaharinia.msutility.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی اطلاعات تماس ادمین
 */
@Entity
@Table(name = "admin_user_contact")
public class AdminUserContact extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * نشانی
     */
    @Column(name = "address")
    private String address;

    @Override
    public String toString() {
        return "AdminUserContact{" +
                "id=" + id +
                ", address='" + address + '\'' +
                '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
