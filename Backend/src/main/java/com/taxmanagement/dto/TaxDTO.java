package com.taxmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class TaxDTO {

	private Long taxFormId;

	@NotBlank(message="form_type cant be blank")//cant be null & accepts atleast one char
	private String formType;

	@NotNull(message="filling_date cant be null")//cant be null & accepts anytype
	@PastOrPresent(message="filling_date cant be in future")
	private Date filingDate;

	@NotNull(message="total_tax cant be null")
	@Positive(message="total_tax should always be positive")
	private BigDecimal totalTaxAmount;

	@NotNull(message="user_id cant be null")
	private int userId;

	public TaxDTO() {
	}

	public TaxDTO(Long taxFormId, String formType, Date filingDate, BigDecimal totalTaxAmount, int userId) {
		this.taxFormId = taxFormId;
		this.formType = formType;
		this.filingDate = filingDate;
		this.totalTaxAmount = totalTaxAmount;
		this.userId = userId;
	}

	public Long getTaxFormId() {
		return taxFormId;
	}

	public void setTaxFormId(Long taxFormId) {
		this.taxFormId = taxFormId;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public Date getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	public BigDecimal getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TaxDTO [taxFormId=" + taxFormId + ", formType=" + formType + ", filingDate=" + filingDate
				+ ", totalTaxAmount=" + totalTaxAmount + ", userId=" + userId + "]";
	}
}
