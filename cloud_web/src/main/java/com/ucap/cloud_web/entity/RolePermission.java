package com.ucap.cloud_web.entity;

import java.util.Date;

/**
 * RolePermission entity. @author MyEclipse Persistence Tools
 */
public class RolePermission implements java.io.Serializable {

	private static final long serialVersionUID = 1167900432405270755L;
	private Integer id;
	private Role role;
	private Permission permission;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer creater;
	private Integer modifyer;

	// Constructors

	/** default constructor */
	public RolePermission() {
	}

	/** minimal constructor */
	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
	}

	/** full constructor */
	public RolePermission(Role role, Permission permission, String status, Date created, Date lastmod, Integer creater,
			Integer modifyer) {
		this.role = role;
		this.permission = permission;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.creater = creater;
		this.modifyer = modifyer;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
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