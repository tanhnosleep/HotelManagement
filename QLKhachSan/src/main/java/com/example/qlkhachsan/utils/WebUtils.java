// cung cấp các phương thức tiện ích liên quan đến xử lý thông
// tin người dùng (Xu li chuoi) trong một ứng dụng sử dụng Spring Security.

package com.example.qlkhachsan.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class WebUtils {

    public static String toString(User user) { //Cấp quyền cho người dùng
        StringBuilder sb = new StringBuilder(); //khởi tạo đối tượng để tạo chuỗi kết quả

        sb.append("UserName:").append(user.getUsername()); //tên người dùng cua User sẽ được thêm vào chuỗi kết quả

        Collection<GrantedAuthority> authorities = user.getAuthorities(); //các quyền của người dùng được lấy ra từ thư viện GranteAuthority
        if (authorities != null && !authorities.isEmpty()) { //Nếu danh sách các quyền không rỗng, chúng sẽ được thêm vào chuỗi kết quả
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString(); //trả về chuỗi kết quả
    }

}