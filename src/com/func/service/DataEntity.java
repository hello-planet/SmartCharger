package com.func.service;

import java.util.List;
import java.util.Map;

/**
 * Common interface for data.<br>
 * 
 * @author helloplanet
 * @create 2018.4.15
 *
 */
public abstract class DataEntity {

	public List<?> toListForm() {
		return null;
	};

	public Map<?, ?> toMapForm() {
		return null;
	};

	public String toStringForm() {
		return null;
	};

	// TODO toJSON
//	String toJSONForm() {
//		return null;
//	};

}
