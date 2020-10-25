package com.motaharinia.presentation.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس کنترلر خانه
 */
@RestController
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "Hello Homepage!, Spring boot 2 (The default database pooling technology in Spring Boot 2.0 has been switched from Tomcat Pool to HikariCP) data jpa with oracle";
    }


}
