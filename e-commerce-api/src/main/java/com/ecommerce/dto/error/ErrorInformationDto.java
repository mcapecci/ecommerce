package com.ecommerce.dto.error;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorInformationDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String field;
	private List<ErrorValueDto> value;

}
