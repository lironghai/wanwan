package com.code.word;

import com.code.file.core.WriteService;
import com.code.file.core.model.Model;
import com.code.file.core.param.Path;
import com.code.file.util.file.FileUtil;
import com.code.word.filter.WordFilter;

/**
 * view
 * @author coco
 *
 */
public class WordWriteService extends WriteService {
   
	/**
	 * write 
	 */
	public void write(Model model, String module, String append) {
		toFileList((WordModule)model, module, append);
	}
	
	/**
	 * 生成多个文件的方法：文件参数通过moduleFiles指定
	 * @param model
	 * @param module
	 * @param append
	 */
	private void toFileList(WordModule model, String module, String append){
		String dir = Path.ROOT + model.getOutputPath() + "/" + module + "/" + model.getModuleFolder();
		String content = "";
		WordFilter filter = new WordFilter();
		this.file = dir + "/" + filter.toModule(append, module);
		String[] words = model.getContent().split("\t|\r\n");
		String field = null;
		String word = null;
		for (int i = 0; i < words.length; i++) {
			word = words[i];
			if(word.contains("/")){
				field = word.split("-")[0];
			}
			if(words[i].length() > 0){ 
				content += filter.filter(word, model, field); 	 
			}
		}
		System.out.println(content);
		FileUtil.createFile(this.file, dir, content);
	}

}
