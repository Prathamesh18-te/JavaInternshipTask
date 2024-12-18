package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	 @Autowired 
	    private EmployeeRepository employeeRepository;

	 
	    public Employee createEmployee(Employee employee) {
	       
	        return employeeRepository.save(employee);
	    }
	        public Employee getEmployeeById(Long id) {
				return employeeRepository.findById(id).orElse(null);
	        	
	        
	    }
}
