package com.biz.impl;

import java.util.List;

import com.biz.IBookBiz;
import com.dao.IBookdao;
import com.dao.impl.BookdaoImpl;
import com.entity.Book;

public class BookBizImpl implements IBookBiz {
	IBookdao ibd = new BookdaoImpl();
	@Override
	public List<Book> listBooksByCid(String cid) {
		List<Book> list = ibd.listBooksByCid(cid);
		return list;
	}
	@Override
	public Book singlebook(String bname) {
		Book book = ibd.singlebook(bname);
		return book;
	}

}
