package com.security.mfa.userService;

import com.security.mfa.jpaRepo.RepoForJpa;
import com.security.mfa.mfaService.NoUserMfaFound;
import com.security.mfa.model.UserMfa;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    private final Logger LOG = LoggerFactory.getLogger(MyUserDetailsService.class);
    @Autowired
    private RepoForJpa repoForJpa;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOG.info("UserName  :" +repoForJpa.findByUserName(username).orElseThrow(NoUserMfaFound::new).getUserName());
        LOG.info(" Password  :" +repoForJpa.findByUserName(username).orElseThrow(NoUserMfaFound::new).getUserPassword());
        UserMfa userMfa = repoForJpa.findByUserName(username).orElseThrow(NoUserMfaFound::new);
            return new MyuserDetail(userMfa);

    }
}
