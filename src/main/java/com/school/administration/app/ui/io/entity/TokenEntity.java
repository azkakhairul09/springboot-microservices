package com.school.administration.app.ui.io.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity(name="t_token")
public class TokenEntity implements Serializable{
	private static final long serialVersionUID = -4465726475061969211L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE, generator="token")
	@TableGenerator(name="token", table="sequence_id",
			pkColumnName="sequence_name", pkColumnValue="tokenID",
			valueColumnName="sequence_value", allocationSize =1, initialValue=0)
	private long id;
	
	@Column(nullable = false)
	private String tokenId;
	
	@Column(nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
