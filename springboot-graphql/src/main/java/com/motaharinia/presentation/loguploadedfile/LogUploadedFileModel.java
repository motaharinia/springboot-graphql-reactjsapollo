/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.presentation.loguploadedfile;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class LogUploadedFileModel {

    private String fileKey;
    private String fileName;
    private String fileExtension;
    private String fileFullName;
    private Date fileUploadDateTime;
    private long fileSize;
    private String fileMimeType;
    private String fileUploadedPath;
    private String fileEntity;
    private String fileSubSystem;
    private byte[] fileByteArray;
    private String directoryRealPath;
    private String directoryHashedPath;

    //getter-setter:
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

    public Date getFileUploadDateTime() {
        return fileUploadDateTime;
    }

    public void setFileUploadDateTime(Date fileUploadDateTime) {
        this.fileUploadDateTime = fileUploadDateTime;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
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

    public byte[] getFileByteArray() {
        return fileByteArray;
    }

    public void setFileByteArray(byte[] fileByteArray) {
        this.fileByteArray = fileByteArray;
    }

    public String getDirectoryRealPath() {
        return directoryRealPath;
    }

    public void setDirectoryRealPath(String directoryRealPath) {
        this.directoryRealPath = directoryRealPath;
    }

    public String getDirectoryHashedPath() {
        return directoryHashedPath;
    }

    public void setDirectoryHashedPath(String directoryHashedPath) {
        this.directoryHashedPath = directoryHashedPath;
    }

}
