package com.akk.vhr.controller;

import com.akk.vhr.model.Menu;
import com.akk.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    // 前端数据是不可信的
    @Autowired
    MenuService MenuService;
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return MenuService.getMenusByHrId();
    }
}
