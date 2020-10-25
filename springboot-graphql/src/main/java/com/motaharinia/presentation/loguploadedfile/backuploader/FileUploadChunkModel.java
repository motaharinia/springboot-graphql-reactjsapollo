package com.motaharinia.presentation.loguploadedfile.backuploader;

import com.motaharinia.presentation.loguploadedfile.SubSystemEnum;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadChunkModel {

//    private MultipartFile file;
    private SubSystemEnum subSystem;
    private String entity;
    private String fileKey;
    private String name;
    private String filePath;
    private Integer chunks = -1;
    private Integer chunk = -1;

    //getter-setter:
//    public MultipartFile getFile() {
//        return file;
//    }
//
//    public void setFile(MultipartFile file) {
//        this.file = file;
//    }

    public SubSystemEnum getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(SubSystemEnum subSystem) {
        this.subSystem = subSystem;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

}
