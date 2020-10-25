package com.motaharinia.presentation.loguploadedfile.frontuploader;

import com.motaharinia.presentation.loguploadedfile.SubSystemEnum;
import org.springframework.web.multipart.MultipartFile;


public class FineUploaderChunkModel {

    private String qquuid;
    private String qqfilename;
    private Long qqtotalfilesize;
    private MultipartFile qqfile;
    private Integer qqpartindex;
    private Integer qqpartbyteoffset;
    private Long qqchunksize;
    private Integer qqtotalparts;
    private SubSystemEnum subSystem;
    private String entity;

//      private MultipartFile file;
//    private SubSystemEnum subSystem;
//    private String entity;
//    private String fileKey;
//    private String name;
//    private String filePath;
//    private Integer chunks = -1;
//    private Integer chunk = -1;
    
    
    //getter-setter:

    public String getQquuid() {
        return qquuid;
    }

    public void setQquuid(String qquuid) {
        this.qquuid = qquuid;
    }

    public String getQqfilename() {
        return qqfilename;
    }

    public void setQqfilename(String qqfilename) {
        this.qqfilename = qqfilename;
    }

    public Long getQqtotalfilesize() {
        return qqtotalfilesize;
    }

    public void setQqtotalfilesize(Long qqtotalfilesize) {
        this.qqtotalfilesize = qqtotalfilesize;
    }

    public MultipartFile getQqfile() {
        return qqfile;
    }

    public void setQqfile(MultipartFile qqfile) {
        this.qqfile = qqfile;
    }

    public Integer getQqpartindex() {
        return qqpartindex;
    }

    public void setQqpartindex(Integer qqpartindex) {
        this.qqpartindex = qqpartindex;
    }

    public Integer getQqpartbyteoffset() {
        return qqpartbyteoffset;
    }

    public void setQqpartbyteoffset(Integer qqpartbyteoffset) {
        this.qqpartbyteoffset = qqpartbyteoffset;
    }

    public Long getQqchunksize() {
        return qqchunksize;
    }

    public void setQqchunksize(Long qqchunksize) {
        this.qqchunksize = qqchunksize;
    }

    public Integer getQqtotalparts() {
        return qqtotalparts;
    }

    public void setQqtotalparts(Integer qqtotalparts) {
        this.qqtotalparts = qqtotalparts;
    }

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
    
}
