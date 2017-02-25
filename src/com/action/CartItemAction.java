package com.action;

import com.alibaba.fastjson.JSONObject;
import com.biz.ICartItemBiz;
import com.biz.impl.CartItemBizImpl;
import com.commons.BeanKit;
import com.commons.StrKit;
import com.entity.Book;
import com.entity.Cartitem;
import com.entity.Page;
import com.entity.User;
import com.servlet.BaseServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CartItemAction extends BaseServlet {
	ICartItemBiz icd = new CartItemBizImpl();
	/*public String tobuy(HttpServletRequest request, HttpServletResponse response){
		if(request.getSession().getAttribute("user")==null){
			request.setAttribute("code", "error");
			request.setAttribute("msg","加入购物车需先登录");	
			return "f:/jsps/msg.jsp";
		}else{
			String bid = request.getParameter("bid");
			User user = (User) request.getSession().getAttribute("user");
			String uid = user.getUid();
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			Page page = icd.add(bid,uid,pageNum);
			//总记录条数
			Page page1 = icd.count(pageNum);
		//}
		return "f:/jsps/cart/list.jsp";
	}*/
	@SuppressWarnings("rawtypes")
	public String tobuy(HttpServletRequest request, HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getUid();
		Map map = request.getParameterMap();
		Map map1 = new HashMap();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			String[] s = (String[]) entry.getValue();
			map1.put(entry.getKey(), s[0]);
		}
		Cartitem cartitem = BeanKit.toBean(map1, Cartitem.class);
		cartitem.setUid(uid);
		icd.add(cartitem);
		return myCart(request,response);
	}
	
	public String myCart(HttpServletRequest request, HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("user");
		String uid = user.getUid();
		List<Cartitem> cartItemList = icd.findAll(uid);
		request.setAttribute("cartItemList", cartItemList);
		return "f:/jsps/cart/list.jsp";
	}
	
	//按加按减
	@SuppressWarnings("rawtypes")
	public String jia(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map map = request.getParameterMap();
		Map map1 = new HashMap();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Entry) it.next();
			String[] s = (String[]) entry.getValue();
			map1.put(entry.getKey(), s[0]);
		}
		Cartitem cartitem = BeanKit.toBean(map1, Cartitem.class);
		Cartitem c = icd.changequantity(cartitem);
		return null;
	}
	public String todesc(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String bid = request.getParameter("bid");
		Book bood = icd.findabookbybid(bid);
		request.setAttribute("singlebook", bood);
		return "f:/jsps/book/desc.jsp";
	}
	//删除条目
	public String delete(HttpServletRequest request, HttpServletResponse response){
		String cartItemId = request.getParameter("cartItemId");
		icd.delete(cartItemId);
		return myCart(request, response);
	}
	
	public String deleteduo(HttpServletRequest request, HttpServletResponse response){
		String[] cartItemIds = request.getParameterValues("cartItemIds");
		for(String cart : cartItemIds){
			icd.delete(cart);
		}
		return myCart(request, response);
	}
	
	public String showItem(HttpServletRequest request, HttpServletResponse response){
		String[] cartItemIds = request.getParameterValues("cartItemIds");
		List<Cartitem> cartItemList = new ArrayList<Cartitem>();
		for(String cartItemId : cartItemIds){
			 Cartitem cartItem = icd.findcartItem(cartItemId);
			 cartItemList.add(cartItem);
		}
		request.setAttribute("cartItemList", cartItemList);
		return "f:/jsps/cart/showitem.jsp";
	}
	
}
