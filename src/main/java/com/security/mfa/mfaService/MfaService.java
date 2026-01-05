package com.security.mfa.mfaService;


import com.security.mfa.jpaRepo.RepoForJpa;
import com.security.mfa.model.UserMfa;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MfaService {
    private final static Logger log = LoggerFactory.getLogger(MfaService.class);
    private final RepoForJpa repoForJpa;
    private final PasswordEncoder passwordEncoder;

    public UserMfa addUser(UserMfa userMfa)
    {
        log.info("Service class  UserMfa Input  ;"+userMfa.toString());
        userMfa.setUserPassword(passwordEncoder.encode(userMfa.getUserPassword()));
        return repoForJpa.save(userMfa);
    }
    public List<UserMfa> getAllUser()
    {
        return repoForJpa.findAll();
    }
    public UserMfa getUserWithId(Long id)
    {
        return repoForJpa.findById(id).orElseThrow(NoUserMfaFound::new);
    }
    public UserMfa getUserWithUserName(String name)
    {
        return repoForJpa.findByUserName(name).orElseThrow(NoUserMfaFound::new);
    }
 /*   public UserMfa getUserWithUsername(String  userName)
    {
        return repoForJpa.findByUserName(userName).orElseThrow(NoUserMfaFound::new);
    }*/

}
