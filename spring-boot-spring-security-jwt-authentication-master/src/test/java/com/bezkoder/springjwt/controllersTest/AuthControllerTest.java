package com.bezkoder.springjwt.controllersTest;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;

@SpringBootTest
public class AuthControllerTest {

	private static final Logger log = LoggerFactory.getLogger(AuthControllerTest.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
//	AuthService authService;

	@Test
	public void registerUserTest() {
		SignupRequest signupRequest = new SignupRequest();
		Set<String> role = new HashSet<>();
		role.add("USER");
		signupRequest.setEmail("mohammed@kn.com");
		signupRequest.setPassword("123456");
		signupRequest.setUsername("mohammed");
		signupRequest.setRole(role);
		try {
//			BaseResponse<Void> baseResponse = authService.registerUser(signupRequest);
//			BaseResponse<Void> baseResponse2 = new BaseResponse<>();
//			assertEquals(baseResponse2.getData(), baseResponse.getData());
		} catch (Exception e) {
			log.info("error:" + e.getMessage());
		}
	}

	@Test
	public void NotregisterUserTest() {
		SignupRequest signupRequest = new SignupRequest();
		Set<String> role = new HashSet<>();
		role.add("user");
		signupRequest.setEmail("mohammed@kn.com");
		signupRequest.setPassword("123456");
		signupRequest.setUsername("mohammed");
		signupRequest.setRole(role);
		try {
//			BaseResponse<Void> baseResponse = authService.registerUser(signupRequest);
//			BaseResponse<Void> baseResponse2 = new BaseResponse<>();
//			assertNotEquals(baseResponse2, baseResponse);
		} catch (Exception e) {
			log.info("error:" + e.getMessage());
		}
	}

	@Test
	public void authenticateUserTest() {
		SignupRequest signupRequest = new SignupRequest();
		Set<String> role = new HashSet<>();
		role.add("user");
		signupRequest.setEmail("mohammed@kn.com");
		signupRequest.setPassword("123456");
		signupRequest.setUsername("mohammed");
		signupRequest.setRole(role);
		try {
//			BaseResponse<Void> baseResponse = authService.registerUser(signupRequest);
//			BaseResponse<Void> baseResponse2 = new BaseResponse<>();
//			assertNotEquals(baseResponse2, baseResponse);
		} catch (Exception e) {
			log.info("error:" + e.getMessage());
		}
	}
}
