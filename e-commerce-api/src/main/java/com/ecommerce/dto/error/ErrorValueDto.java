package com.ecommerce.dto.error;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorValueDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long errorCode;
	private String message;
}
