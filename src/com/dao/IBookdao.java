package com.dao;

import java.util.List;

import com.entity.Book;

public interface IBookdao {

	List listBooksByCid(String cid);

	Book singlebook(String bname);

}
