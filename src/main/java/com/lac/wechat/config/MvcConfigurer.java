package com.lac.wechat.config;

import com.lac.wechat.domain.User;
import com.lac.wechat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 * 配置应用的MVC全局特性
 *
 * @author lac
 * @version 1.0
 * @date 2019/8/25 0025 - 19:40
 */
@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private UserService userService;


    //格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
        //registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    //拦截器
    /*
    通过 addInterceptors方法可以设置多个拦截器，
    比如对特定的URI设定拦截器检查用户是否登录，
    打印处理用户请求耗费的时间等。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //增加一个拦截器，检查会话
        registry.addInterceptor(new SessionHandlerInterceptor())
                .addPathPatterns("/second/apply.do")
                .addPathPatterns("/second/appointment.do");
    }

    //配置静态资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }

    //跨域访问配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    //uri到视图的映射
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //系统登录页面
        /* registry.addViewController("/login").setViewName("/login.html");*/
    }


    /**
     * 自定义session拦截器
     */
    class SessionHandlerInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            User user = (User) request.getSession().getAttribute("login_user");
            if (null == user) {
                String openid = request.getParameter("openid");
                user = userService.getUserById(openid);
                if (null == user) {
                    response.sendRedirect("/login");
                    return false;
                } else {
                    request.getSession().setAttribute("login_user", user);
                    return true;
                }
            } else {
                return true;
            }

        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            //Controller方法处理完毕后，调用此方法
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            // 页面渲染完毕后调用此方法，通常用来清除某些资源使用，类似Java语法的finally 作用。
        }
    }

}

