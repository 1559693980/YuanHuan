package com.hzc.yuanhuan.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.hzc.yuanhuan.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserLogin implements UserDetails {

    private User user;

    private List<String> permissions;

    @JSONField(serialize = false)
    private List<GrantedAuthority> authorities;

    public UserLogin(User user, List<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中string类型的信息封装成simpleGrantedAuthority类
        //List<GrantedAuthority> list = new ArrayList<>();
        //for (String permission : permissions) {
        //    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
        //    list.add(simpleGrantedAuthority);
        //}
        //或者这样写
        if (authorities != null){
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 0;
    }
}