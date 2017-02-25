package com.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.IorderBiz;
import com.biz.impl.OrderBizImpl;
import com.commons.BeanKit;
import com.entity.Order;
import com.entity.User;
import com.servlet.BaseServlet;


public class OrderAction extends BaseServlet {
	IorderBiz iob = new OrderBizImpl();
	private static final long serialVersionUID = 1L;
	public String submitorder(HttpServletRequest request, HttpServletResponse response){
		Map<String, String[]> map = request.getParameterMap();
		Map<String,String> map1 = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> entry = it.next();
			String[] s = entry.getValue();
			map1.put(entry.getKey(), s[0]);
		}
		
		Order order = BeanKit.toBean(map1, Order.class);
		User u = (User) request.getSession().getAttribute("user");
		order.setUid(u.getUid());
		Order neworder = iob.submitorder(order);
		request.setAttribute("orderthing", neworder);
		return "f:/jsps/order/ordersucc.jsp";
	}
}
