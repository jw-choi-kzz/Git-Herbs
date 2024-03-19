package githerbs.global.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import githerbs.global.exception.CustomException;

@RestControllerAdvice
public class APIExceptionHandler {
	@ExceptionHandler({CustomException.class})
	protected ResponseEntity<Object> handleCustomException(CustomException ex){
		return new ResponseEntity<Object>(new Object(), HttpStatusCode.valueOf(ex.getErrorCode().getStatus()));
	}
}