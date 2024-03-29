//xác định người dùng dựa trên tên người dùng và cung cấp
// thông tin chi tiết về họ, bao gồm cả quyền hạn

package com.example.qlkhachsan.service;

import com.example.qlkhachsan.Repository.RoleRepository;
import com.example.qlkhachsan.Repository.UserRepository;
import com.example.qlkhachsan.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired   ;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service//đánh dấu là một bean Service trong ứng dụng Spring
@Transactional//tất cả các phương thức trong service này sẽ được quản lý bởi
// transaction của Spring, đảm bảo tính nhất quán và đồng nhất trong các thao tác với cơ sở dữ liệu.
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired//tim va inject module UserRepository
    private UserRepository userRepository;

    @Autowired //tim va inject module RoleRepository
    private RoleRepository roleRepository;

    @Override
    //thực hiện tìm kiếm người dùng trong cơ sở dữ liệu dựa trên tên người dùng được cung cấp.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findUserName(username); //tim kiem nguoi dung trong CSDL dua tren username
        if(appUser == null) { //kiem tra xem nguoi dung co ton tai khong
            throw new UsernameNotFoundException("USer "+ username + " not found!!!");
        }

        //lay danh sach quyen han cua nguoi dung dua tren userId
        List<String> roleNames = roleRepository.getRoleNames(appUser.getUserId());

        //tao danh sach cac "GrantedAuthority" tu` danh sach quyen han
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(roleNames != null) {
            for(String role : roleNames) {
                //moi quyen han duoc tao thanh 1 doi tuong GrantedAuthority va them vao danh sach
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        //tao va tra ve doi tuong chua thong tin chi tiet va quyen han cua nguoi dung
        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), appUser.getEncrytedPassword(), grantList);
        return userDetails;
    }
}