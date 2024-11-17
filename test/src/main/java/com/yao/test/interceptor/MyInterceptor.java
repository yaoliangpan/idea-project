package com.yao.test.interceptor;
import com.yao.test.common.BaseContext;
import com.yao.test.properties.JwtProperties;
import com.yao.test.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class MyInterceptor implements HandlerInterceptor {

    private final JwtProperties jwtProperties;

    public MyInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    // 该方法在请求处理之前进行调用，返回true表示继续请求处理，返回false表示中断请求处理
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token == null||token.isEmpty()) {
            response.setStatus(401);
            return false;
        }
        String userName = "";
        try {
            // 解析token(可能出现令牌过期)
            Claims result = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            userName = (String) result.get("userName");
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setStatus(402);
            return false;
        }
        BaseContext.setThreadLocal(userName);
        return true;
    }

    // 该方法在请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后），所以在这个方法中对ModelAndView进行操作是无效的。
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 该方法在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行，主要用于清理资源。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除线程变量
        BaseContext.clearThreadLocal();
    }
}
