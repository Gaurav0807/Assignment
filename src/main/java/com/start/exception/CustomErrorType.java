package com.start.exception;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.MultiValueMap;

public class CustomErrorType {
	
	 private String errorMessage;
	 
	    public CustomErrorType(String errorMessage){
	        this.errorMessage = errorMessage;
	    }
	 
	    public String getErrorMessage() {
	        return errorMessage;
	    }

	
}
