package com.chupryna.spring.services;

import com.chupryna.spring.security.UserDetail;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    public String showLoggedUserInfo(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "Hi, " + userDetail.getUser().getFirstName() + " " + userDetail.getUser().getLastName();
    }

}
