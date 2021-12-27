package com.movie.surfmie.config;

import com.movie.surfmie.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 페이지 권한 설정
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/mypage/**").hasRole("MEMBER")
                .antMatchers("/movie/mylist").hasRole("MEMBER")
                .antMatchers("/").permitAll()

                // 로그인 설정
                .and().formLogin()
                .loginPage("/login")            // 로그인 페이지
                .defaultSuccessUrl("/")         // 로그인 성공 후 이동할 페이지
                .permitAll()                    // 모든 사람에게 권한

                // 로그아웃 설정
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))          // 로그아웃 페이지
                .logoutSuccessUrl("/")                                                      // 로그아웃 성공 후 이동할 페이지
                .invalidateHttpSession(true)                                                // 로그아웃 후 세션 모두 삭제

                // 권한 없을 때
                .and().exceptionHandling()
                .accessDeniedPage("/denied");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }
}
