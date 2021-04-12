package br.com.compassouol.catalogo.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Error error = new Error();
		error.setStatus_code(status.value());
		error.setMessage(ex.getMessage());

		return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
	}
		
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Error error = new Error();
		error.setStatus_code(status.value());
		error.setMessage("Um ou mais campos estão inválidos! "
				+ "Preencha todos os campos corretamente e tente novamente.");
			
		return super.handleExceptionInternal(ex, error, headers, status, request);
	}
}