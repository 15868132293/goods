package com.dao;

import com.entity.User;

public interface Iuserdao {



	boolean existLoginname(String loginname);

	boolean checkEmail(String email);

	boolean regist(User user);

	User findByCode(String code);

	void updateStatus(String uid, boolean b);

	User login(User user);

	boolean changepw(User user);

}
