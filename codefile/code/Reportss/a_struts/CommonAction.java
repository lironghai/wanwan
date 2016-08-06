package com.sinosoft.ems.common;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;

import com.sinosoft.ems.entity.Application;
import com.sinosoft.ems.permission.PermissionAction;
import com.sinosoft.helper.StringHelper;
import com.sinosoft.util.jpa.SpecificationQuery;

/**
 * 人员管理/当前登录人员信息自己信息修改功能
 * 
 * @author LuoGang
 *
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommonAction extends PermissionAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5171132586027689280L;

	@Resource(name = "commonService")
	CommonService service;

	/**
	 * 查询人员
	 * 
	 * @return
	 */
	public String query() {
		PageRequest pageRequest = new PageRequest(this.pageNumber, this.pageSize);
		Specification<Application> spec = common == null ? null : (new Specification<Application>() {
			@Override
			public Predicate toPredicate(Root<Application> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				SpecificationQuery<Application> sq = new SpecificationQuery<Application>(root, query, cb);
				if (!StringHelper.isEmpty(common.getId())) sq.ilike("code", common.getId());
				//if (!StringHelper.isEmpty(purchase.getName())) sq.ilike("name", purchase.getName());
				return sq.toPredicate();
			}
		});
		json = service.query(spec, pageRequest);
		return JSON;
	}

	/** 当前要处理的人员 */
	protected Application common;

	public Application getCommon() {
		return common;
	}

	public void setCommon(Application common) {
		this.common = common;
	}
}