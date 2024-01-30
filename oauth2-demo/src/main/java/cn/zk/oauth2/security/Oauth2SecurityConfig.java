package cn.zk.oauth2.security;

import cn.zk.oauth2.security.filter.CustomAuthenticationProcessingFilter;
import cn.zk.oauth2.security.handler.CustomizeAuthenticationFailureHandler;
import cn.zk.oauth2.security.handler.CustomizeAuthenticationSuccessHandler;
import cn.zk.oauth2.security.provider.EmilAuthenticationProvider;
import cn.zk.oauth2.security.provider.PasswordAuthenticationProvider;
import cn.zk.oauth2.security.provider.SmsCodeAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Collections;

/**
 * @author ZK
 * @date 2024/1/17
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Oauth2SecurityConfig {

    private final EmilAuthenticationProvider emilAuthenticationProvider;
    private final PasswordAuthenticationProvider passwordAuthenticationProvider;
    private final SmsCodeAuthenticationProvider smsCodeAuthenticationProvider;
//    private final ThirdCodeAuthenticationProvider thirdCodeAuthenticationProvider;

    @Bean
    public CustomAuthenticationProcessingFilter customAuthenticationProcessingFilter() {
        ProviderManager providerManager = new ProviderManager(passwordAuthenticationProvider,
                emilAuthenticationProvider, smsCodeAuthenticationProvider);
        CustomAuthenticationProcessingFilter filter = new CustomAuthenticationProcessingFilter(providerManager);
        filter.setAuthenticationSuccessHandler(new CustomizeAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new CustomizeAuthenticationFailureHandler());
        return filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(urlBasedCorsConfigurationSource()) // 配置跨域问题
                .and()
                .csrf().disable();//关闭默认登录页
        http

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用session;
        http.addFilterAt(customAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2Login();
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(urlBasedCorsConfigurationSource());
    }

    // 配置跨域访问资源
    private UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.setExposedHeaders(Collections.singletonList(HttpHeaders.SET_COOKIE));
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }
}
