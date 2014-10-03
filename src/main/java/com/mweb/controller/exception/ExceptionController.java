package com.mweb.controller.exception;

import static com.mweb.common.constats.Constants.NOT_FOUND;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class ExceptionController
{

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(value = { ResourceNotFoundException.class, NoSuchRequestHandlingMethodException.class })
    public String handleException(Exception e) {
        return "404";// view name for 404 error
    }  
	
//	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Such Method Handled")
//	@ExceptionHandler({ResourceNotFoundException.class,NoSuchRequestHandlingMethodException.class})
//	public String handleNotFoundException(ResourceNotFoundException ex)
//	{
//		return NOT_FOUND;
//	}

//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	public String viewEdit(@PathVariable("name") final String name, Model model)
//	{
//		if (name.equals("null"))
//			throw new ResourceNotFoundException();
//		return name;
//	}

//	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
//	@ExceptionHandler(IOException.class)
//	public void handleIOException()
//	{
//		logger.error("IOException handler executed");
//	}

}
