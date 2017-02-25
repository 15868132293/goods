package com.biz.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.ICartItemBiz;
import com.commons.StrKit;
import com.connection.ConnectionPoolManager;
import com.dao.IcartItemdao;
import com.dao.impl.CartItemdaoImpl;
import com.entity.Book;
import com.entity.Cartitem;
import com.entity.Page;
import com.sun.org.apache.regexp.internal.recompile;

public class CartItemBizImpl implements ICartItemBiz {
	IcartItemdao icd = new CartItemdaoImpl();
	@Override
	public Page count(int pageNum) {
		Page i = icd.count(pageNum);
		return i;
	}
	@Override
	public boolean add(Cartitem cartitem) {
		boolean bl = false;
		bl = icd.add(cartitem);
		return bl;
	}
	@Override
	public List<Cartitem> findAll(String uid) {
		List<Cartitem> cartItemList = null;
		try {
			ConnectionPoolManager.beginTransaction();
			cartItemList = icd.findAll(uid);
			ConnectionPoolManager.commitTransaction();
		} catch (SQLException e) {
			try {
				ConnectionPoolManager.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			
		}
		return cartItemList;
	}
	@Override
	public boolean jia(Cartitem cartitem) {
		boolean bl = false;
		cartitem.setCartItemId(StrKit.uuid());
		bl = icd.jia(cartitem);
		return bl;
	}
	@Override
	public Cartitem changequantity(Cartitem cartitem) {
		Cartitem bl = new Cartitem();
		bl = icd.changequantity(cartitem);
		return bl;
	}
	@Override
	public Book findabookbybid(String bid) {
		Book book = new Book();
		book = icd.findabookbybid(bid);
		return book;
	}
	@Override
	public boolean delete(String cartItemId) {
		boolean bl = false;
		bl = icd.delete(cartItemId);
		return bl;
	}
	@Override
	public List find(String uid) {
		List list = new ArrayList<>();
		list = icd.find(uid);
		
		return list;
	}
	@Override
	public Cartitem findcartItem(String cartItemId) {
		return icd.findcartItememId(cartItemId);
	}

	

}
