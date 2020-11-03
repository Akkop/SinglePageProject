package com.akk.vhr.service;

import com.akk.vhr.mapper.MenuMapper;
import com.akk.vhr.model.Hr;
import com.akk.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuMapper menuMapper;
    public List<Menu> getMenusByHrId() {
        // 由于前端数据不可信，登录后用户信息会保存到内存中  使用SecurityContextHolder中取出登录id
        return menuMapper.getMenusByHrId(((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    public List<Menu> getAllMenusWithRole(){
        return menuMapper.getAllMenusWithRole();
    }
}
