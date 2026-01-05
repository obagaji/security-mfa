package com.security.mfa.mfaService;

import com.security.mfa.model.UserMfa;

import java.util.function.Supplier;

public class NoUserMfaFound extends RuntimeException implements Supplier<UserMfa> {
    @Override
    public UserMfa get() {
         UserMfa user = new UserMfa();
               user.setUserId(0L);
                user.setUserAge(0);
                user.setRoles("");
                user.setUserName("");
                user.setUserPassword("");
                return user;
    }
}
