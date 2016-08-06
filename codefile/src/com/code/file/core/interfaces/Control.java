package com.code.file.core.interfaces;

import com.code.file.core.model.Model;

/**
 * 控制器接口
 * @author Administrator
 *
 */
public interface Control {
 
	public void read();
	public String toFile(String templateType, String module, Model models);
	public void setModel(Model model);
}
