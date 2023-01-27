package com.ecommerce.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PageResponseDto<T>
 * 
 * @author Eva Magalí Capecci
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * content
	 */
	private List<T> content;

	/**
	 * Returns the total amount of elements.
	 *
	 * @return the total amount of elements
	 */
	private Long totalElements;

	/**
	 * Returns the page to be returned.
	 *
	 * @return the page to be returned
	 */
	private int currentPage;

	/**
	 * Returns the number of items to be returned.
	 *
	 * @return the number of items that pages
	 */
	private int size;

	/**
	 * Returns the number of total pages.
	 *
	 * @return the number of total pages
	 */
	private int totalPages;

}
