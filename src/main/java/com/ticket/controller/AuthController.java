package com.ticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.dto.LoginRequest;
import com.ticket.dto.LoginResponse;
import com.ticket.dto.UserRequest;
import com.ticket.endpoint.AuthEndpoint;
import com.ticket.service.AuthService;
import com.ticket.util.CommonUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController implements AuthEndpoint{

	@Autowired
	private AuthService authService;

	@Override
	public ResponseEntity<?> registerUser(@RequestBody UserRequest userDto, HttpServletRequest request) throws Exception {
		log.info("AuthController : registerUser() : Exceution Start");
		String url = CommonUtil.getUrl(request);
		Boolean register = authService.register(userDto, url);
		if (!register) {
			log.info("Error : {}","Register failed");
			return CommonUtil.createErrorResponseMessage("Register failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("AuthController : registerUser() : Exceution End");
		return CommonUtil.createBuildResponseMessage("Register success", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

		LoginResponse loginResponse = authService.login(loginRequest);
		if (ObjectUtils.isEmpty(loginResponse)) {
			return CommonUtil.createErrorResponseMessage("invalid credential", HttpStatus.BAD_REQUEST);
		}
		return CommonUtil.createBuildResponse(loginResponse,HttpStatus.OK);
	}

}
