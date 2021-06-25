package com.tianlin.booking.config;

import com.tianlin.booking.intercepter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
 
  @Autowired
  private LoginInterceptor loginHandlerInterceptor;
 
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    InterceptorRegistration ir = registry.addInterceptor(loginHandlerInterceptor);
    ir.addPathPatterns("/**");
    List<String> irs = new ArrayList<String>();
    irs.add("/account/login*");
    irs.add("/account/signup");
    irs.add("/signup");
    irs.add("/login*");
    irs.add("/img/**");
    ir.excludePathPatterns(irs);
  }
}