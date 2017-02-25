package com.dao.impl;

import java.sql.SQLException;



import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.Iuserdao;
import com.dbutils.TxQueryRunner;
import com.entity.User;

public class UserDaoImpl implements Iuserdao {


	TxQueryRunner qr = new TxQueryRunner();

	@Override
	public boolean existLoginname(String loginname) {
		boolean bl = false;
		try{
			Number num = (Number)qr.query("select count(*) from t_user where loginname=?",new ScalarHandler<>(),new Object[]{loginname});
			int i = num.intValue();
			if(i>0){
				bl=true;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return bl;
	}

	@Override
	public boolean checkEmail(String email) {
		boolean bl = false;
		try{
			Number num = (Number)qr.query("select count(*) from t_user where email=?",new ScalarHandler<>(),new Object[]{email});
			int i = num.intValue();
			if(i>0){
				bl=true;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return bl;
	}

	@Override
	public boolean regist(User user) {
		Boolean bl = false;
		try {
			int i =qr.update("insert into t_user(uid,loginname,loginpass,email,activationcode) values(?,?,?,?,?)", new Object[]{user.getUid(),user.getLoginname(),user.getLoginpass(),user.getEmail(),user.getActivationcode()});
			if(i>0){
				bl=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
		return bl;
	}

	@Override
	public User findByCode(String code) {
		User user = null;
		try {
			user = qr.query("select * from t_user where activationCode=? ", new BeanHandler<>(User.class),new Object[]{code});
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return user;
	}

	@Override
	public void updateStatus(String uid, boolean b) {
		try {
			qr.update("update t_user set status=? where uid=? ", new Object[] { b, uid });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public User login(User user) {
		try {
			user = qr.query("select * from t_user where loginname=? and loginpass=?", new BeanHandler<>(User.class),new Object[]{user.getLoginname(),user.getLoginpass()});
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return user;
	}

	@Override
	public boolean changepw(User user) {
		boolean bl = false;
		try {
			int i  = qr.update("update t_user set loginpass=? where uid=? ", new Object[]{user.getLoginpass(),user.getUid()});
			if(i>0){
				bl=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return bl;
	}



	
}
