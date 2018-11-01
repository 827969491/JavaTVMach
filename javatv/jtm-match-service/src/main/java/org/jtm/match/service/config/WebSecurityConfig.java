//package org.jtm.match.service.config;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * security配置类
// * @author yangri
// *
// */
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	/**
//	 * 将登录方式改为basic auth 认证方式
//	 */
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/**").and().authorizeRequests().anyRequest()
//                .authenticated().and().httpBasic();
//    }
//}
