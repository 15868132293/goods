package com.biz;

import java.util.List;

import com.entity.Book;
import com.entity.Cartitem;
import com.entity.Page;

public interface ICartItemBiz {

	Page count(int pageNum);

	boolean add(Cartitem cartitem);

	List<Cartitem> findAll(String uid);

	boolean jia(Cartitem cartitem);


	Cartitem changequantity(Cartitem cartitem);

	Book findabookbybid(String bid);

	boolean delete(String cartItemId);

	List find(String uid);

	Cartitem findcartItem(String cartItemId);

	


}
