package com.biz.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.mail.MessagingException;
import javax.mail.Session;

import com.biz.IUserBiz;
import com.commons.PropKit;
import com.commons.StrKit;
import com.connection.ConnectionPoolManager;
import com.dao.Iuserdao;
import com.dao.impl.UserDaoImpl;
import com.entity.User;
import com.mail.Mail;
import com.mail.MailKit;

public class UserBizImpl implements IUserBiz {

	Iuserdao udao = new UserDaoImpl();

	@Override
	public boolean existLoginname(String loginname) {
		boolean bl = false;
		bl = udao.existLoginname(loginname);
		return bl;
	}

	@Override
	public boolean checkEmail(String email) {
		boolean bl = false;
		bl = udao.checkEmail(email);
		return bl;
	}

	@Override
	public boolean regist(User user) {
		boolean bl = false;
		try {
			ConnectionPoolManager.beginTransaction();

			user.setStatus(false);
			user.setUid(StrKit.uuid());
			user.setActivationcode(StrKit.uuid() + StrKit.uuid());
			bl = udao.regist(user);
			if (bl) {
				PropKit.use("email_template.properties");
				String host = PropKit.get("host");
				String username = PropKit.get("username");
				String password = PropKit.get("password");
				Session session = MailKit.createSession(host, username, password);

				String from = PropKit.get("from");
				String to = user.getEmail();
				String subject = PropKit.get("subject");

				// {0} {1}
				String content = MessageFormat.format(PropKit.get("content"), user.getActivationcode());
				Mail mail = new Mail(from, to, subject, content);

				MailKit.send(session, mail);
				ConnectionPoolManager.commitTransaction();
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ConnectionPoolManager.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return bl;
	}

	@Override
	public void activatioin(String code) {
		try {
			User user = udao.findByCode(code);
			if (user == null)
				throw new Exception("无效的激活码！");
			if (user.isStatus())
				throw new Exception("您已经激活过了，不要二次激活！");
			udao.updateStatus(user.getUid(), true);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public User login(User user) {
		User nuser = udao.login(user);
		
		
		return nuser;
		
	}

	@Override
	public boolean changepw(User user) {
		boolean u = true;
		try {
			ConnectionPoolManager.beginTransaction();
			u = udao.changepw(user);
			ConnectionPoolManager.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ConnectionPoolManager.rollbackTransaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	
		return u;
	}

}
