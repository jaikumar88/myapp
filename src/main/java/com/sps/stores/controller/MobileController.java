package com.sps.stores.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sps.stores.model.Greeting;

@RestController
public class MobileController {

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
    private  String template = "Welcome, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	if(isCurrentAuthenticationAnonymous()){
    		template = "Welcome, %s!";
    	} else {
    		template = "Sorry, %s!";
    	}
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    /**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
}