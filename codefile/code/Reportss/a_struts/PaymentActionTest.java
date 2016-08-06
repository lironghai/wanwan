package ems.test.system;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.ems.system.DictionaryAction;

import ems.test.application.ApplicationTest;

public class DictionaryActionTest extends ApplicationTest{
	
	@Autowired
	private DictionaryAction action;
	
	@Test
	public void testQuery(){
		action.setPageSize(4);
		action.setType("01"); 
		action.query();
		System.out.println(action.getJson());
	}
}