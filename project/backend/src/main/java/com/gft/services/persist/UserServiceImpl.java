package com.gft.services.persist;

import com.gft.dao.UserDao;
import com.gft.dto.*;
import com.gft.dto.model.UserDetails;
import com.gft.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kamu on 8/16/2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao dao;

    @Transactional(readOnly = true)
    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = dao.find(request.getLogin(),request.getPassword());
        int code;
        String msg = "";
        if (user != null){
            user.setLastLogin(new Date());
            dao.save(user);
            code = 211;
        } else {
            code = 2;
            msg = "User was not found.";
        }
        return new LoginUserResponse(request.getLogin(), request.getPassword(),code, msg);
    }

    @Transactional(readOnly = true)
    @Override
    public GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request) {
        User user = dao.find(request.getLogin());
        GetUserDetailsResponse response = new GetUserDetailsResponse(user.getFirstName(), user.getLastName(), 211);
        response.setFreeResources(user.getAmount());
        BigDecimal wallet = new BigDecimal(0);
        //user.getAssets().stream().forEach(n-> {wallet.add(n.getBuyValue());});
        response.setWalletValue(wallet);

        return response;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails getUserDetails(String email) {
        User user = dao.find(email);
        BigDecimal wallet = new BigDecimal(0);

        UserDetails ud =new UserDetails(user.getFirstName(), user.getLastName(), user.getAmount(),wallet, wallet);
        return ud;
    }



    public void setDao(UserDao dao) {
        this.dao = dao;
    }
}
