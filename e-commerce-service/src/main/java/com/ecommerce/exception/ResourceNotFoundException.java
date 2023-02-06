package com.ecommerce.exception;

/**
 * ResourceNotFoundException
 *
 * @author Eva Magalí Capecci
 */
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4120225472909223706L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
