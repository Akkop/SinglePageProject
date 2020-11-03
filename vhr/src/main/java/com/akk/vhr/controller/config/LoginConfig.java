package com.akk.vhr.controller.config;

import com.akk.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginConfig {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("尚未登录,请登录");
    }
}
