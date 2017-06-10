package com.ucap.cloud_web.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Role entity. @author MyEclipse Persistence Tools
 */
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = -8220535212044563981L;
	private Integer roleId;
	private String name;
	private String description;
	private String status;
	private Date created;
	private Date lastmod;
	private Integer sort;
	private Integer creater;
	private Integer modifyer;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);
	private Set<RolePermission> rolePermissions = new HashSet<RolePermission>(0);

	// Constructors
	/** default constructor */
	public Role() {
	}

	/** full constructor */
	public Role(String name, String description, String status, Date created, Date lastmod, Integer sort,
			Integer creater, Integer modifyer, Set<UserRole> userRoles, Set<RolePermission> rolePermissions) {
		this.name = name;
		this.description = description;
		this.status = status;
		this.created = created;
		this.lastmod = lastmod;
		this.sort = sort;
		this.creater = creater;
		this.modifyer = modifyer;
		this.userRoles = userRoles;
		this.rolePermissions = rolePermissions;
	}

	// Property accessors
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<RolePermission> getRolePermissions() {
		return this.rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

}