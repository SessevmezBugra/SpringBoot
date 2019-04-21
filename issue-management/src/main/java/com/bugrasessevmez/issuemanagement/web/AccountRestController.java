package com.bugrasessevmez.issuemanagement.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bugrasessevmez.issuemanagement.dao.UserRepository;
import com.bugrasessevmez.issuemanagement.dto.LoginRequest;
import com.bugrasessevmez.issuemanagement.dto.RegistrationRequest;
import com.bugrasessevmez.issuemanagement.dto.TokenResponse;
import com.bugrasessevmez.issuemanagement.entity.User;
import com.bugrasessevmez.issuemanagement.security.JwtTokenUtil;
import com.bugrasessevmez.issuemanagement.service.UserService;

@RestController
@RequestMapping("/rest/token")
public class AccountRestController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        final User user = userRepository.findByUsername(request.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(new TokenResponse(user.getUsername(), token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        Boolean response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
    }
    
}
