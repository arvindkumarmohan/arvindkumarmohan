package com.example.Test;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingController {
	
	  private static final String template = "Hello, %s!";
	    private final AtomicLong counter = new AtomicLong();
	    
	    //Component
	    //define a priave variable
	    private GreetingComponent g;
	    
	    //greeting component will be injected during controller instantiation
	    @Autowired
	    public GreetingController(GreetingComponent g) {
	    	this.g = g;
	    }

	    @GetMapping("/greeting")
	    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
	        return new Greeting(counter.incrementAndGet(), String.format(template, name));
	    }
	    
	    //test the greeting component
	    @GetMapping("/testgreeting")
	    public ResponseEntity<String> getMessage(){
	    	return ResponseEntity.ok(g.getMessage());
	    }

}
