package com.hzc.yuanhuan.filter;

import com.hzc.yuanhuan.domain.UserLogin;
import com.hzc.yuanhuan.utils.JwtUtils;
import com.hzc.yuanhuan.utils.RedisTemplateUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplateUtil redisTemplateUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request, response);
            return;
        }
        //解析token获取userid
        String id;
        try {
            id = JwtUtils.extractUsername(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token解析失败");
        }
        //通过userid从Redis中获取LoginUser
        UserLogin userLogin = (UserLogin) redisTemplateUtil.getCacheObject("login:" + id);
        if (Objects.nonNull(userLogin)){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin, null, userLogin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else {
            throw new RemoteException("用户未登录");
        }
        filterChain.doFilter(request, response);
    }
}
