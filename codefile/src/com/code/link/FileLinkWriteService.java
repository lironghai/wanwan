package com.code.link;
 
import com.code.file.core.param.Path;
import com.code.file.core.param.Smbol;
import com.code.file.util.file.FileUtil;

/**
 * view
 * @author coco
 *
 */
public class FileLinkWriteService {
  
	private String file;
	/**
	 * write 
	 */
	public void write(Module model, String module, String append) {
		toFileList(model, module, append);
	}
	
	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * @param model
	 * @param module
	 * @param append
	 */
	private void toFileList(Module model, String module, String append){
		String dir = Path.ROOT + model.getOutPutPath() + Smbol.l + module + Smbol.l + model.getModuleFolder();
		String content = "";
		this.file = dir + Smbol.l + getModule(module, append);
		for (int i = 0; i < model.getFileModels().length; i++) {
			content += model.getFileModels()[i].getContent() + Smbol.tr;
		}
		FileUtil.createFile(file, dir, content);
	}
 
	/**
	 * 给文件名替换名字
	 * @param module
	 * @param append
	 * @return
	 */
	private String getModule(String module, String append){ 
		return append.replaceAll("@module", module);
	}
 
	public String getFile() {
		return file;
	}	
	 
}
