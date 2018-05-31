/**
 * 
 */
package com.paractice.spring.rest.model;

/**
 * Response pojo
 * 
 * @author equester
 * @date 11-May-2018
 */
public class Response {
	private String status;
	private Integer code;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Response() {
		super();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
