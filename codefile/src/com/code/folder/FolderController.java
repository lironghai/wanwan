package com.code.folder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.code.file.core.Controller;
import com.code.file.core.interfaces.Control;
import com.code.file.core.model.FileVo;
import com.code.file.core.model.Model;
import com.code.file.util.TemplateTypeUtils;
import com.code.list.FileModule;

public class FolderController extends Controller implements Control{

	private Scanner in;

	protected FolderModule getFolderModel(){
		return (FolderModule)this.model;
	}
	
	public void read() {
		String[] tempFiles = TemplateTypeUtils.toFiles(((FileModule) model).getTemplateTypes());
		String type;
		for (int i = 0; i < tempFiles.length; i++) {
			type = tempFiles[i];
			//type = PropertyUtil.upperName(type);
			String[] modules = TemplateTypeUtils.toFiles(model.getModule());
			String file = null;
			for (int j = 0; j < modules.length; j++) {
				file = toFile(type, modules[j], model);
			}
			this.readedFiles.add(file);
			this.writeFiles.add(this.writeService.getFile());
		}
	}

	private String toFile(String templateType, String module, FileVo fileVo){
		String file = null;
		try {
			file = model.getFileTemplate() + templateType;
			in = new Scanner(new File(file));
			toText(module, fileVo);
			in.close();
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}
		return file;
	}

	public void toText(String module, FileVo fileVo){
		String content = "";
		while (in.hasNextLine()) {
			content = (content + in.nextLine() + "\r\n");
		}
		content = content.substring(0, content.length() - 2);
		fileVo.setContent(content);	
	}

	@Override
	public String toFile(String templateType, String module, Model models) {
		// TODO Auto-generated method stub
		return null;
	}
}
