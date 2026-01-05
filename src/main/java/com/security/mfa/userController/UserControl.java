package com.security.mfa.userController;

import com.security.mfa.mfaService.MfaService;
import com.security.mfa.model.UserMfa;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class UserControl {

    private final MfaService mfaService;
    private static final Logger log = LoggerFactory.getLogger(MfaService.class);

    @GetMapping("/all")
    public ResponseEntity<List<UserMfa>>getAllUser()
    {
        return ResponseEntity.ok(mfaService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity<UserMfa>addUserToDatabase(@RequestBody UserMfa userMfa)
    {
        log.info(userMfa.toString());
        return ResponseEntity.ok(mfaService.addUser(userMfa));
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<UserMfa>getAllUserById(@PathVariable("id")Long  id)
    {
        return ResponseEntity.ok(mfaService.getUserWithId(id));
    }
    @GetMapping("/sent")
    public String getOttSent()
    {
        return "Ott sent";
    }


}
