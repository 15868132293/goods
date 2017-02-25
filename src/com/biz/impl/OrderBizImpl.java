package com.biz.impl;

import com.biz.IorderBiz;
import com.commons.StrKit;
import com.dao.Iorderdao;
import com.dao.impl.OrderdaoImpl;
import com.entity.Order;

public class OrderBizImpl implements IorderBiz {
	Iorderdao iod = new OrderdaoImpl();
	@Override
	public Order submitorder(Order order) {
		Order o = new Order();
		order.setOid(StrKit.uuid());
		order.setStatus(1);
		order.setOrdertime(String.format("%tF %<tT", new java.util.Date()));
		o = iod.submitorder(order);
		return o;
	}

}
