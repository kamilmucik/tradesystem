package com.gft.services.persist;

import com.gft.dto.*;
import com.gft.dto.model.UserDetails;

/**
 * Created by kamu on 8/16/2016.
 */
public interface UserService {

    LoginUserResponse login(LoginUserRequest request);

    GetUserDetailsResponse getUserDetails(GetUserDetailsRequest request);

    UserDetails getUserDetails(String email);

}
