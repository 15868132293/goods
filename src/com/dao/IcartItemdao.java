package com.dao;

import java.util.List;

import com.entity.Book;
import com.entity.Cartitem;
import com.entity.Page;

public interface IcartItemdao {



	Page count(int pageNum);

	boolean add(Cartitem cartitem);

	List<Cartitem> findAll(String uid);

	Cartitem changequantity(Cartitem cartitem);

	boolean jia(Cartitem cartitem);

	Book findabookbybid(String bid);

	boolean delete(String cartItemId);

	List find(String uid);

	Cartitem findcartItememId(String cartItemId);


}
