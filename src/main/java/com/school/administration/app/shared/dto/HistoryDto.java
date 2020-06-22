package com.school.administration.app.shared.dto;

import java.io.Serializable;

public class HistoryDto implements Serializable{

	private static final long serialVersionUID = -2636519360152932508L;
	private String idTmoney;
	private String idFusion;
	private String token;
	private String startDate;
	private String stopDate;
	private String transactionType;
	private Integer merchantId;
	private Integer page;
	private Integer max;
	public String getIdTmoney() {
		return idTmoney;
	}
	public void setIdTmoney(String idTmoney) {
		this.idTmoney = idTmoney;
	}
	public String getIdFusion() {
		return idFusion;
	}
	public void setIdFusion(String idFusion) {
		this.idFusion = idFusion;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStopDate() {
		return stopDate;
	}
	public void setStopDate(String stopDate) {
		this.stopDate = stopDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer i) {
		this.merchantId = i;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getMax() {
		return max;
	}
	public void setMax(Integer max) {
		this.max = max;
	}
}
