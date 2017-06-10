package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * UserRole entity. @author MyEclipse Persistence Tools
 */
public class UserRole implements java.io.Serializable {

	private static final long serialVersionUID = 3995767024135212110L;

	private Integer id;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;
	private Users users;
	private Role role;

	public UserRole() {
	}

	/** minimal constructor */
	public UserRole(Users users) {
		this.users = users;
	}

	

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastmod() {
		return this.lastmod;
	}

	public void setLastmod(Date lastmod) {
		this.lastmod = lastmod;
	}

	public Integer getCreater() {
		return this.creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
	}

	public Integer getModifyer() {
		return this.modifyer;
	}

	public void setModifyer(Integer modifyer) {
		this.modifyer = modifyer;
	}

}