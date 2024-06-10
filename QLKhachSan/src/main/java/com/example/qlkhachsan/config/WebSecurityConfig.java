//cấu hình cách ứng dụng xử lý việc xác thực người dùng,
// quyền truy cập và các cấu hình bảo mật khác

package com.example.qlkhachsan.config;

import com.example.qlkhachsan.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration //đánh dấu đây là class cấu hình Spring
@EnableWebSecurity //bật tính năng bảo mật web của Spring Security
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired //tìm và inject module (các class có đánh dấu như @Component @Service, @Controller, @Repository,...)
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private DataSource dataSource;

    //Phương thức dùng mã hóa mật khẩu người dùng
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    //Phương thức cấu hình va dang ky cach Spring Security se xác thực người dùng và ma khoa mật khẩu
    // (lay thong tin nguoi dung va ma hoa mat khau) roi sau do so sanh thong tin dang nhap voi thong tin trong db
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }
    //AuthenticationManagerBuilder la cong cu su dung de cau hinh va xay dung AuthenticationManager
    //AuthenticationManager su dung de xac thuc nguoi dung

    @Override
    // cấu hình quy tắc bảo mật cho các URL trong ứng dụng
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); //tắt bảo vệ CSRF trong Spring Security
        // (một loại tấn công khi kẻ tấn công sử dụng các request giả mạo
        // từ một trang web khác để thực hiện các hành động bất hợp pháp trên trang web đích)

        //URL được truy cập công cộng
        http.authorizeRequests().antMatchers("/", "/logout").permitAll()
                .and()
                .formLogin() //kich hoat form login (goi bo loc UsernamePasswordAuthenticationFilter)
                    .usernameParameter("username") //Thiet lap ten tham so cho username
                    .passwordParameter("pass") //Thiet lap ten tham so cho password
                    .loginPage("/login") //Thiet lap URL cua trang dang nhap
                .and()
                .logout() //kich hoat logout (goi bo loc LogoutFilter)
                    .logoutUrl("/logout") //Thiet lap URL logout
                    .logoutSuccessUrl("/") //Thiet lap URL sau khi logout thanh cong
                    .invalidateHttpSession(true) //Invalidate session sau khi logout
                    .deleteCookies("JSESSIONID") //xoa cookie sau khi logout
                .permitAll();

        //URL yêu cầu vai trò nào cũng truy cập được
//        http.authorizeRequests().antMatchers("/").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/").permitAll(); //kich hoat authorizeRequests (goi bo loc FilterSecurityInterceptor)
//        http.authorizeRequests().antMatchers("/dangky").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        //URL yêu cầu vai trò ROLE_USER
        http.authorizeRequests().antMatchers("/checkin").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/checkout").access("hasRole('ROLE_USER')");

        //URL yêu cầu vai trò ROLE_ADMIN
        http.authorizeRequests().antMatchers("/khachhang").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/quanlyphong").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/quanlynhanvien").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/quanlythue").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/thongkethang").access("hasAnyRole( 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/thongkenam").access("hasAnyRole( 'ROLE_ADMIN')");

        //URL xử lý lỗi truy cập bị từ chối
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }


    @Bean //quản lý, đảm bảo phương thức này được sử dụng đúng cách và có thể được inject vào các Component khác
    //Lưu trữ thông tin các Token nhớ mật khẩu
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource); // cau hinh db theo dataSource
        return db;
    }
}