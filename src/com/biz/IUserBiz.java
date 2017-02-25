package com.biz;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;

import com.entity.User;

public interface IUserBiz {


	boolean existLoginname(String loginname);

	boolean checkEmail(String email);

	boolean regist(User user) throws SQLException, MessagingException, IOException;

	void activatioin(String code);

	User login(User user);

	boolean changepw(User user);


}
