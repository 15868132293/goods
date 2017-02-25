package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.Icategorydao;
import com.dbutils.TxQueryRunner;
import com.entity.Category;


public class CategoryDaoImpl implements Icategorydao {
	TxQueryRunner qr = new TxQueryRunner();
	@Override
	public List<Category> findAll() {
		List<Category> parents = new ArrayList<Category>();
		try {
			//一级分类
			 parents = qr.query("select * from t_category where pid is null", new BeanListHandler<Category>(Category.class));
			//二级分类
			for (Category parent : parents) {
				List<Category> childrens = qr.query("select * from t_category where pid = ?", new BeanListHandler<Category>(Category.class), new Object[]{parent.getCid()});
				for (Category children : childrens) {
					children.setParents(parent);
					children.setUrl("/goods/bookAction?flag=listBooksBycate&cid="+children.getCid());
					children.setTarget("body");
				}
				parent.setChildren(childrens);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return parents;
	}

}
