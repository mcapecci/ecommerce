package com.ecommerce.service.handler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecommerce.dto.error.ErrorInformationDto;
import com.ecommerce.dto.error.ErrorResponseDto;
import com.ecommerce.dto.error.ErrorValueDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

	/**
	 * <p>
	 * Method that management the unknown errors.
	 *
	 * @param e RuntimeException
	 * @return Response entity.
	 *
	 * @see ExceptionHandler
	 */
	@ExceptionHandler(value = RuntimeException.class)
	protected ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex) {

		log.error("handled RuntimeException by ExceptionHandlerController.handleRuntimeException", ex);
		return getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex, 1);
	}

	/**
	 * <p>
	 * Method that management the unknown errors.
	 *
	 * @param e Exception
	 * @return Response entity.
	 *
	 * @see ExceptionHandler
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {

		log.error("handled Exception by ExceptionHandlerController.handleException", ex);
		return getErrorResponse(HttpStatus.BAD_REQUEST, ex, 2);
	}

	/**
	 * <p>
	 * Method that management the ui errors.
	 *
	 * @param e MethodArgumentNotValidException
	 * @return Response entity.
	 *
	 * @see ExceptionHandler
	 * @see MethodArgumentNotValidException
	 */
	@ExceptionHandler(value = { MethodArgumentNotValidException.class, MissingServletRequestParameterException.class })
	public ResponseEntity<ErrorResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		log.error(
				"handled MethodArgumentNotValidException by ExceptionHandlerController.handleMethodArgumentNotValidException",
				ex);
		List<ErrorInformationDto> moreInformation = ex.getBindingResult().getAllErrors().stream().map((error) -> {
			ErrorInformationDto errorInformationDto = new ErrorInformationDto();
			errorInformationDto.setField(((FieldError) error).getField());
			errorInformationDto.setValue(List.of(new ErrorValueDto(5, error.getDefaultMessage())));
			return errorInformationDto;
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), moreInformation));
	}

	/**
	 *
	 * @param httpCode
	 * @param ex
	 * @param errorCode
	 * @return
	 */
	private ResponseEntity<ErrorResponseDto> getErrorResponse(HttpStatus httpCode, Exception ex, long errorCode) {
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		errorResponse.setHttpCode(httpCode.value());
		errorResponse.setHttpMessage(httpCode.getReasonPhrase());

		List<ErrorValueDto> values = Arrays.asList(new ErrorValueDto(errorCode, ex.getMessage()));
		List<ErrorInformationDto> moreInfomation = Arrays.asList(new ErrorInformationDto("API", values));
		errorResponse.setMoreInformation(moreInfomation);

		return ResponseEntity.status(httpCode).body(errorResponse);
	}

}