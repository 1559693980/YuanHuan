package com.hzc.yuanhuan.expression;

import com.hzc.yuanhuan.domain.UserLogin;
import com.hzc.yuanhuan.entity.User;
import com.hzc.yuanhuan.repository.PermsViewRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("security")
public class SecurityExpression {

    @Resource
    private PermsViewRepository permsViewRepository;

    public boolean hasAuthority(String authority){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserLogin userLogin = (UserLogin) authentication.getPrincipal();
        User user = userLogin.getUser();
        List<String> permsByUserId = permsViewRepository.findPermsByUserId(user.getUserId());
        for (String s : permsByUserId) {
            if (s.equals(authority)){
                return true;
            }
        }
        return false;
    }
}
