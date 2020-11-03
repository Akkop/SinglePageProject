package com.akk.vhr.config;

import com.akk.vhr.model.Menu;
import com.akk.vhr.model.Role;
import com.akk.vhr.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

// 该类的功能是启动权限访问，根据用户传来的请求地址，分析出请求需要的角色1
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    MenuService menuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    // Collection当前请求所需要的角色 Object是FilterInvocation对象
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 这里就是获取到了请求的地址
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 然后就是去数据库去匹配对应的角色 先做查询
        List<Menu> allMenusWithRole = menuService.getAllMenusWithRole();
        // 一条一条的遍历比较
        for (Menu menu : allMenusWithRole) {
            // 如果传进来的url在查询的数据中进行比较 当前调符合传进来的url 那么返回这个角色
           if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
               //获取角色
                List<Role> roles = menu.getRoles();
                String[] strings = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    strings[i] = roles.get(i).getName();
                }
            // 由于要返回Collection<>类型 所以使用SecurityConfig.createList 他的参数是数组 所以进行了上面的循环遍历
                return SecurityConfig.createList(strings);
            }
        }
        // 没有匹配上的 登录之后访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
