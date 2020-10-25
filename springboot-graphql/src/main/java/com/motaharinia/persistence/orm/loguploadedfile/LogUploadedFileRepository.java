/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.persistence.orm.loguploadedfile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public interface LogUploadedFileRepository extends JpaRepository<LogUploadedFile, Integer> {
    LogUploadedFile findByFileKey(String fileKey);
}
