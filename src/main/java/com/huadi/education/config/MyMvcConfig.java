package com.huadi.education.config;

import com.huadi.education.component.LoginHandlerInteceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/expose-black.html").setViewName("expose-black");
        registry.addViewController("/expose-white.html").setViewName("expose-white");
        registry.addViewController("/report.html").setViewName("report");
    }

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInteceptor()).addPathPatterns("/index.html");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("配置文件已经生效");
        //关于图片上传后需要重启服务器才能刷新图片
        //这是一种保护机制，为了防止绝对路径被看出来，目录结构暴露
        //解决方法:将虚拟路径/upload/images/
        //配置为项目中的upload/images文件所在的路径
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\upload\\images\\";
        System.out.println(path);
        registry.addResourceHandler("/upload/images/**").addResourceLocations("file:" + path);

    }
}
