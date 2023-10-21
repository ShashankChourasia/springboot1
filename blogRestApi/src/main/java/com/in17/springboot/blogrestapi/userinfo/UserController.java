package com.in17.springboot.blogrestapi.userinfo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.in17.springboot.blogrestapi.springsecurityjwt.AuthRequest;
import com.in17.springboot.blogrestapi.springsecurityjwt.JwtService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
    private AuthenticationManager authenticationManager;


	@PostMapping("/signup")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody User user) {
		userService.addUser(user);
		String responseBody = "New user created successfully.";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "Value");
        HttpStatus status = HttpStatus.CREATED; // Set the desired HTTP status code

        return new ResponseEntity<>(responseBody, headers, status);
	}
	
	@PostMapping("/login")
	public JwtToken authenticateAndGettoken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
        	
        	JwtToken token= new JwtToken();
        	token.setJwtToken(jwtService.generateToken(authRequest.getName()));
        	token.setUserId(userService.getUserByName(authRequest.getName()));
            
        	return token;
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<Optional<User>> getUser(@PathVariable int id)  {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
//	@PostMapping("/authenticate")
//	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws Exception {
//	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
//	    if (authentication.isAuthenticated()) {
//	        String token = jwtService.generateToken(authRequest.getName());
//	        
//	        String encodedToken = URLEncoder.encode(token, "UTF-8");
//	        
//	        // Create a cookie with the JWT token
//	        Cookie cookie = new Cookie("jwtToken", token);
//	        cookie.setHttpOnly(true);
//	        cookie.setMaxAge(86400); // Set the cookie expiration time (in seconds)
//	        cookie.setPath("/posts"); // Set the cookie path
//
//	        // Add the cookie to the response
//	        response.addCookie(cookie);
//
//	        return token;
//	    } else {
//	        throw new UsernameNotFoundException("Invalid user request!");
//	    }
//	}
}
