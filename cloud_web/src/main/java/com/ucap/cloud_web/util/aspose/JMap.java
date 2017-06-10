package com.ucap.cloud_web.util.aspose;

import java.util.Map;

public class JMap {
	private int type;
	private Map<String, Object> map;

	public JMap(int type, Map<String, Object> map) {
		this.type = type;
		this.map = map;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
