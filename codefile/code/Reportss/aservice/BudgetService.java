package com.sinosoft.ems.budget;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.ems.dao.ApplicationDao;
import com.sinosoft.ems.entity.Application;
import com.sinosoft.util.jpa.JpaService;
import com.sinosoft.util.jpa.SinglePropertySpecification;

@Service
public class BudgetService extends JpaService<Application, String, ApplicationDao> {

	/**
	 * 查询单个Budget的主表
	 * 
	 * @param id
	 * @return
	 */
	public Application get(final String id) {
		return dao.findOne(new SinglePropertySpecification<Application>(true, "id", id));
	}

	@Resource
	@Override
	public void setDao(ApplicationDao dao) {
		this.dao = dao;
	}

}