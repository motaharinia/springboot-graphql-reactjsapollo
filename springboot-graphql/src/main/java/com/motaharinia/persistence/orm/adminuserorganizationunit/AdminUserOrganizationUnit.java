package com.motaharinia.persistence.orm.adminuserorganizationunit;

import com.motaharinia.msutility.entity.GenericEntity;

import javax.persistence.*;
import java.util.Collection;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی واحد سازمانی ادمین
 */
@Entity
@Table(name = "admin_user_organization_unit")
public class AdminUserOrganizationUnit extends GenericEntity {

    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * نام
     */
    @Column(name = "title")
    private String title;

    /**
     * کد
     */
    @Column(name = "code")
    private String code;

    /**
     * والد
     */
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private AdminUserOrganizationUnit parent;

    /**
     * فرزندان
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Collection<AdminUserOrganizationUnit> childrenCollection;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public AdminUserOrganizationUnit getParent() {
        return parent;
    }

    public void setParent(AdminUserOrganizationUnit parent) {
        this.parent = parent;
    }

    public Collection<AdminUserOrganizationUnit> getChildrenCollection() {
        return childrenCollection;
    }

    public void setChildrenCollection(Collection<AdminUserOrganizationUnit> childrenCollection) {
        this.childrenCollection = childrenCollection;
    }
}
