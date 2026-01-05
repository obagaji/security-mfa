package com.security.mfa;

import com.security.mfa.jpaRepo.RepoForJpa;
import com.security.mfa.model.UserMfa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class SecurityMfaApplicationTests {
	@Autowired
	RepoForJpa repoForJpa;

	@Test
	void addMethodTest() {
		UserMfa user = new UserMfa();
		user.setUserPassword("password");
		user.setUserName("musadaniel");
		user.setUserAge(40);
		user.setRoles("USER");
		UserMfa newUser =repoForJpa.save(user);
		assertSame(newUser,user);
	}

}
