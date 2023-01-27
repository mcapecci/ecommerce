package com.ecommerce.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PricesEntity
 * 
 * @author Eva Magalí Capecci
 *
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRICES")
public class PricesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "BRAND_ID")
	private BrandEntity brand;

	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	// TODO
	@Column(name = "PRICE_LIST")
	private Integer priceList;

	@ManyToOne(optional = false)
	@JoinColumn(name = "PRODUCT_ID")
	private ProductEntity product;

	@Column(name = "PRIORITY")
	private Integer priority;

	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "CURR")
	@Setter(AccessLevel.NONE)
	@Getter(AccessLevel.NONE)
	private String currency;

	public void setCurrency(Currency currency) {
		this.currency = currency.getCurrencyCode();
	}

	public Currency getCurrency() {
		return Currency.getInstance(currency);
	}
}