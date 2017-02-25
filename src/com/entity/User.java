package com.entity;

import java.io.Serializable;
/**
 * 用户实体类
 * @author Administrator
 *
 */

public class User implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 6568431424677151070L;
	//取自数据库表中字段
	private String uid;
	private String loginname;
	private String loginpass;
	private String email;
	private boolean status;
	private String activationcode;
	
	//取自前台页面字段
	
	private String reloginpass;
	public String getReloginpass() {
		return reloginpass;
	}
	public void setReloginpass(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	private String verifyCode;
	public String getLoginpassagain() {
		return reloginpass;
	}
	public void setLoginpassagain(String reloginpass) {
		this.reloginpass = reloginpass;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getLoginpass() {
		return loginpass;
	}
	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getActivationcode() {
		return activationcode;
	}
	public void setActivationcode(String activationcode) {
		this.activationcode = activationcode;
	}
	public User(String uid, String loginname, String loginpass, String email, boolean status) {
		super();
		this.uid = uid;
		this.loginname = loginname;
		this.loginpass = loginpass;
		this.email = email;
		this.status = status;
	}
	public User() {
		super();
	}
	
	
}
