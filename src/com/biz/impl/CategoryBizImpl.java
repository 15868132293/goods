package com.biz.impl;

import java.util.List;

import com.biz.IcategoryBiz;
import com.dao.Icategorydao;
import com.dao.impl.CategoryDaoImpl;

public class CategoryBizImpl implements IcategoryBiz {
	Icategorydao icd = new CategoryDaoImpl();
	@Override
	public List findAll() {
		List list = icd.findAll();
		return list;
	}

}
