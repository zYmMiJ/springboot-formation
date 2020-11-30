package nc.sf2i.formation.exercice9spring.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import nc.sf2i.formation.exercice9spring.exceptions.ApiErrorResponse;
import nc.sf2i.formation.exercice9spring.exceptions.DepartmentNotFoundException;

/**
 * Exception handler for all exceptions.
 */

@RestControllerAdvice
public class TurningAppExceptionHandler extends ResponseEntityExceptionHandler {

	

	

	/**
	 * Handles DepartmentNotFoundException. Thrown when get department fails
	 * fails.
	 *
	 * @param ex the DepartmentNotFoundException
	 * @return the ApiError object
	 */
	@ExceptionHandler( DepartmentNotFoundException.class)
	public final ResponseEntity<ApiErrorResponse> handleConstraintViolationPostCustomer(DepartmentNotFoundException ex) {

		ApiErrorResponse errorResponse = new ApiErrorResponse("DEP_01", ex.getMessage(),
				"JSon String", String.valueOf(HttpStatus.BAD_REQUEST));

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	
    
    

}
