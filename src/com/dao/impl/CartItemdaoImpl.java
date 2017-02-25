package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Request;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.commons.StrKit;
import com.dao.IcartItemdao;
import com.dbutils.TxQueryRunner;
import com.entity.Book;
import com.entity.Cartitem;
import com.entity.Page;
import com.sun.corba.se.impl.ior.NewObjectKeyTemplateBase;

public class CartItemdaoImpl implements IcartItemdao {
	TxQueryRunner tq = new TxQueryRunner();	
	@Override
	public Page count(int pageNum) {
		Page pg = new Page();
		try {
			pg.setPagesize(3);
			int pagesize = pg.getPagesize();
			Number i = (Number)tq.query("select count(*) from t_cartitem", new ScalarHandler());
			pg.setTotalrecords(i.intValue());
			pg.setPageNum(pageNum);
			List<Cartitem> list = tq.query("select * from t_cartitem limit ?,?", new BeanListHandler<>(Cartitem.class), new Object[]{(pageNum-1)*pagesize,pagesize*pageNum});
			pg.setList(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pg;
	}
	@Override
	public boolean add(Cartitem cartitem) {
		boolean bl = false;
		try {
			Cartitem c = tq.query("select * from t_cartitem where bid=? and uid=?", new BeanHandler<>(Cartitem.class), new Object[]{cartitem.getBid(),cartitem.getUid()});
			//如果该商品第一次加购物车
			if(c==null){
				cartitem.setCartItemId(StrKit.uuid());
				int i = tq.update("insert into t_cartitem (cartItemId,quantity,bid,uid) values(?,?,?,?)", new Object[]{cartitem.getCartItemId(),cartitem.getQuantity(),cartitem.getBid(),cartitem.getUid()});
				if(i>0){
					bl = true;
				}
				//如果不是，则需要增加quantity
			}else{
				tq.update("update t_cartitem set quantity=? where bid=? and uid=?", new Object[]{c.getQuantity()+cartitem.getQuantity(),cartitem.getBid(),cartitem.getUid()});
				bl=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bl;
	}
	@Override
	public List<Cartitem> findAll(String uid) {
		List<Cartitem> cartItemList = null;
		try {
			cartItemList = tq.query("select * from t_cartitem where uid=?", new BeanListHandler<>(Cartitem.class), new Object[]{uid});
			for(Cartitem cartItem : cartItemList){
				Book book = tq.query("select * from t_book where bid=?", new BeanHandler<>(Book.class), new Object[]{cartItem.getBid()});
				cartItem.setBook(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartItemList;
	}
	@Override
	public boolean jia(Cartitem cartitem) {
		boolean bl = false;
		int i;
		try {
			i = tq.update("update t_cartitem set quantity=? where bid=? and uid=?", new Object[]{cartitem.getQuantity(),cartitem.getBid(),cartitem.getUid()});
			if(i>0){
				bl=true;
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bl;
	}

	@Override
	public Cartitem changequantity(Cartitem cartitem) {
		Cartitem c = new Cartitem();
		try {
			if(cartitem.getQuantity()==0){
				tq.update("delete from t_cartitem where cartItemId=?", new Object[]{cartitem.getCartItemId()});
				c=null;
			}else{
			int i = tq.update("update t_cartitem set quantity=? where cartItemId=?",new Object[]{cartitem.getQuantity(),cartitem.getCartItemId()});
			if(i>0){
				c=tq.query("select * from t_cartitem where cartItemId=?", new BeanHandler<Cartitem>(Cartitem.class), new Object[]{cartitem.getCartItemId()});
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
		
	}
	@Override
	public Book findabookbybid(String bid) {
		Book book = new Book();
		try {
			 book = tq.query("select * from t_book where bid=?", new BeanHandler<Book>(Book.class),new Object[]{bid});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}
	@Override
	public boolean delete(String cartItemId) {
		boolean bl=false;
		try {
			if(tq.update("delete from t_cartitem where cartItemId=?", new Object[]{cartItemId}) >0 ){
				bl=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bl;
	}
	@Override
	public List find(String uid) {
		List books = new ArrayList<>();
		try {
			List<Cartitem> cartitem = tq.query("select * from t_cartitem where uid = ?", new BeanListHandler<>(Cartitem.class),new Object[]{uid});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	@Override
	public Cartitem findcartItememId(String cartItemId) {
		Cartitem c = null;
		try {
			c = tq.query("select * from t_cartitem where cartItemId=?", new BeanHandler<>(Cartitem.class),new Object[]{cartItemId});
			c.setBook(tq.query("select * from t_book where bid=?",new BeanHandler<>(Book.class),new Object[]{c.getBid()}));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	

}
