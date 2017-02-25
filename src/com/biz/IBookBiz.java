package com.biz;

import java.util.List;

import com.entity.Book;

public interface IBookBiz {

	List listBooksByCid(String cid);

	Book singlebook(String bname);

}
