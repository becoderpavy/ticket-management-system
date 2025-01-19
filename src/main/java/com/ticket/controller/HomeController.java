package com.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.dto.PswdResetRequest;
import com.ticket.endpoint.HomeEndpoint;
import com.ticket.service.HomeService;
import com.ticket.service.UserService;
import com.ticket.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HomeController implements HomeEndpoint {

	@Autowired
	private HomeService homeService;

	@Autowired
	private UserService userService;

	@GetMapping("/verify")
	public ResponseEntity<?> verifyUserAccount(String uid, String code) throws Exception {
		log.info("HomeController : verifyUserAccount() : Exceution Start");
		Boolean verifyAccount = homeService.verifyAccount(Integer.parseInt(uid), code);
		if (verifyAccount)
			return CommonUtil.createBuildResponseMessage("Account verification success", HttpStatus.OK);

		log.info("HomeController : verifyUserAccount() : Exceution End");
		return CommonUtil.createErrorResponseMessage("Invalid Verification link", HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<?> sendEmailForPasswordReset(String email, HttpServletRequest request) throws Exception {
		userService.sendEmailPasswordReset(email, request);
		return CommonUtil.createBuildResponseMessage("Email Send Success !! Check Email Reset Password", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> verifyPasswordResetLink(Integer uid, String code) throws Exception {
		userService.verifyPswdResetLink(uid, code);
		return CommonUtil.createBuildResponseMessage("verification success", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> resetPassword(PswdResetRequest pswdResetRequest) throws Exception {
		userService.resetPassword(pswdResetRequest);
		return CommonUtil.createBuildResponseMessage("Password reset succes", HttpStatus.OK);
	}

}
