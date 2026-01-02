package com.security.mfa.userService;

import com.security.mfa.model.UserMfa;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class MyuserDetail implements UserDetails {
    private  UserMfa userMfa;
    public MyuserDetail(UserMfa userMfa)
    {
        this.userMfa=userMfa;
    }
    public MyuserDetail()
    {}
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(userMfa.getRoles().split(" ")).stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return userMfa.getUserPassword();
    }

    @Override
    public String getUsername() {
        return userMfa.getUserName();
    }
}
