package com.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.dao.IBookdao;
import com.dbutils.TxQueryRunner;
import com.entity.Book;

public class BookdaoImpl implements IBookdao {
	TxQueryRunner qr = new TxQueryRunner();
	@Override
	public List<Book> listBooksByCid(String cid) {
		List<Book> books = new ArrayList<Book>();
		try {
			books = qr.query("select * from t_book where cid=?", new BeanListHandler<Book>(Book.class), new Object[]{cid});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return books;
		
	}
	@Override
	public Book singlebook(String bid) {
		Book book = new Book();
		try {
			book = qr.query("select * from t_book where bid=?", new BeanHandler<>(Book.class), new Object[]{bid});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
	}

}
