package com.example.work03personalitytest.service;

public class DBConnectionInfo {
	
	private String jdbcDriverName;
	private String url;
	private String username;
	private String password;
	
	public String getJdbcDriverName() {
		return jdbcDriverName;
	}
	
	public void setJdbcDriverName(String jdbcDriverName) {
		this.jdbcDriverName = jdbcDriverName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
