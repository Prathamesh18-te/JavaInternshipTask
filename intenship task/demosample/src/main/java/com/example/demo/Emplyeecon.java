package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/employee")
public class Emplyeecon {
	 @Autowired
	    private EmployeeService employeeService;

	   
	    @PostMapping
	    public ResponseEntity<?> createEmployee(@RequestBody Employee employee, HttpServletRequest request) {
	       
	        String token = request.getHeader("Authorization");
	        if (token == null || !token.startsWith("Bearer ")) {
	            return ResponseEntity.status(401).body("Access Token Expired or Invalid");
	        }

	        
	        boolean isTokenValid = validateToken(token.substring(7)); 
	        if (!isTokenValid) {
	            return ResponseEntity.status(401).body("Access Token Expired or Invalid");
	        }

	      
	        Employee createdEmployee = employeeService.createEmployee(employee);
	        
	        
	        return ResponseEntity.status(201).body("Employee Added");
	    }

	   
	    private boolean validateToken(String token) {
	   
	        return true;
	    }
	    
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<?> getEmployeeById(@PathVariable Long id, HttpServletRequest request) {
	        
	        String token = request.getHeader("Authorization");
	        if (token == null || !token.startsWith("Bearer ")) {
	            return ResponseEntity.status(401).body("Access Token Expired or Invalid");
	        }

	       
	        boolean isTokenValid = validateToken(token.substring(7));  
	        if (!isTokenValid) {
	            return ResponseEntity.status(401).body("Access Token Expired or Invalid");
	        }

	       
	        Employee employee = employeeService.getEmployeeById(id);
	        if (employee == null) {
	            return ResponseEntity.status(404).body("Employee Not Found");
	        }

	        return ResponseEntity.ok(employee);
	    }
	}

