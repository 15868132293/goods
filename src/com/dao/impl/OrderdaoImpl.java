package com.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.Iorderdao;
import com.dbutils.TxQueryRunner;
import com.entity.Order;

public class OrderdaoImpl implements Iorderdao {
	TxQueryRunner tq = new TxQueryRunner();
	@Override
	public Order submitorder(Order o) {
		Order order = new Order();
		try {
			int i = tq.update("insert into t_order(oid,ordertime,total,status,address,uid) values(?,?,?,?,?,?)", new Object[]{o.getOid(),o.getOrdertime(),o.getTotal(),o.getStatus(),o.getAddress(),o.getUid()});
			if(i>0){
				order = tq.query("select * from t_order where oid=?", new BeanHandler<Order>(Order.class),new Object[]{o.getOid()});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

}
