package com.ucap.cloud_web.constant;

/**<p>Description:  zTree树形结构对象VO类</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: TreeVo </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：zhurk </p>
 * <p>@date：2015-12-7下午2:08:57 </p>
 */
public class TreeVo {
	private String id;// 节点id
	private String pId;// 父节点pId I必须大写
	private String name;// 节点名称
	private String open = "false";// 是否展开树节点，默认不展开
	private String isParent = "false";// 是否是节点
	private String type; // 是否门户
	private int isexp; // 1正常，2例外，3关停
	private String siteCode;//站点标识码
	private String iconSkin = "";// 站点图标
	private int level;//层级
	private int isBm;//是否部委
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconSkin() {
		return iconSkin;
	}

	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}

	public int getIsexp() {
		return isexp;
	}

	public void setIsexp(int isexp) {
		this.isexp = isexp;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getIsBm() {
		return isBm;
	}

	public void setIsBm(int isBm) {
		this.isBm = isBm;
	}

}
