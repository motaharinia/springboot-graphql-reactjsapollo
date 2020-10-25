/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.persistence.orm.loguploadedfile;

import com.motaharinia.msutility.entity.GenericEntity;
import com.motaharinia.msutility.entity.OracleUtility;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "log_uploaded_file")
public class LogUploadedFile extends GenericEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "file_key")
    private String fileKey;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_full_name")
    private String fileFullName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_mime_type")
    private String fileMimeType;

    @Column(name = "file_uploaded_path")
    private String fileUploadedPath;

    @Column(name = "file_upload_date_time", columnDefinition = OracleUtility.COLUMN_DEFINITION_DATE)
    private Date fileUploadDateTime;

    @Column(name = "file_entity")
    private String fileEntity;

    @Column(name = "file_subsystem")
    private String fileSubSystem;

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public Date getFileUploadDateTime() {
        return fileUploadDateTime;
    }

    public void setFileUploadDateTime(Date fileUploadDateTime) {
        this.fileUploadDateTime = fileUploadDateTime;
    }

    public String getFileUploadedPath() {
        return fileUploadedPath;
    }

    public void setFileUploadedPath(String fileUploadedPath) {
        this.fileUploadedPath = fileUploadedPath;
    }

    public String getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(String fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getFileSubSystem() {
        return fileSubSystem;
    }

    public void setFileSubSystem(String fileSubSystem) {
        this.fileSubSystem = fileSubSystem;
    }

    
}
