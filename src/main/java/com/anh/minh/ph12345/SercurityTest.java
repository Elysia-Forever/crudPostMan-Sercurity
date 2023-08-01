package com.anh.minh.ph12345;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SercurityTest {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        PasswordEncoder encoder = passwordEncoder();
//        UserDetails user = User.builder()  // .withDefaultPasswordEncoder() để mã hóa mk
//                .username("minh")
//                .password("{noop}12345")
//                .roles("USER")
//                .build();

        UserDetails user = User.builder()
                .username("minh")
                .password(encoder.encode("12345"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("phong")
                .password(encoder.encode("54321"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user,admin);
    }



        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf(cs -> cs.disable())
                    .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.FORWARD,DispatcherType.ERROR).permitAll()
                        .requestMatchers( "/xin-chao").permitAll() //ai cũng có thể truy cập URL này
                        .requestMatchers("/khach-hang/hien-thi").hasRole("USER") //chỉ USER mới truy cập được
                        .anyRequest().permitAll()   // Phải xác thực(đăng nhập) mới có thể truy cập đến các trang
                    )
               //    .httpBasic(Customizer.withDefaults()) //Login with PostMan
                   .formLogin(form -> form.permitAll().defaultSuccessUrl("/xin-chao"))
               //     Cho phép ai cũng vào được form cửa Security
                   .logout(logout -> logout.permitAll())
                   .build();

        }


}

