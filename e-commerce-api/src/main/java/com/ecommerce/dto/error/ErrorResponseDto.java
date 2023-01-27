package com.ecommerce.dto.error;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private int httpCode;
	private String httpMessage;
	private List<ErrorInformationDto> moreInformation;

}
