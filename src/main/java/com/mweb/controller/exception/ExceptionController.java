package com.mweb.controller.exception;

import java.io.IOException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory
			.getLogger(ExceptionController.class);

	@ExceptionHandler
	public ModelAndView handleException(NoSuchRequestHandlingMethodException ex) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("404");
		return mav;
	}
	

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException() {
		logger.error("IOException handler executed");
		// returning 404 error code
	}
	
//	@RequestMapping(value="/404")
//	public String pageNotFound() {
//		return "404";
//	}
}
