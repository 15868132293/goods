package com.action;

import com.alibaba.fastjson.JSONObject;
import com.biz.IUserBiz;
import com.biz.impl.UserBizImpl;
import com.commons.BeanKit;
import com.commons.StrKit;
import com.entity.User;
import com.servlet.BaseServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserAction extends BaseServlet implements Servlet {
			
     
	private IUserBiz ubiz = new UserBizImpl();
	public String existLoginname(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		String loginname = request.getParameter("loginname");
		boolean bl = ubiz.existLoginname(loginname);
		if(bl){
			jo.put("status","exist");
		}else{
			jo.put("status","noexist");
		}
		out.print(jo);
		out.flush();
		out.close();
		//如果为字符串，如果字符串包含F：就是转发    如果是R：就是重定向；null 不操作
		return null;
		
	}
	public String checkEmail(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		String email = request.getParameter("email");
		boolean bl = ubiz.checkEmail(email);
		if(bl){
			jo.put("status","exist");
		}else{
			jo.put("status","noexist");
		}
		out.print(jo);
		out.flush();
		out.close();
		return null;
		
	}
	
	public String checkcode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		String code = request.getParameter("code");
		String code1 = (String) request.getSession().getAttribute("vCode");
		String vcode = code1.substring(0, 4);
		if(code.equalsIgnoreCase(vcode)){
			jo.put("status","right");
		}else{
			jo.put("status","wrong");
		}
		out.print(jo);
		out.flush();
		out.close();
		return null;
		
	}
	public String regist(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, MessagingException{
		Map<String, String[]> map = request.getParameterMap();
		Map<String,String> map1 = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> entry = it.next();
			String[] s = entry.getValue();
			map1.put(entry.getKey(), s[0]);
		}
		User user = BeanKit.toBean(map1, User.class);
		
		//用户校验
		Map<String, String> errors = validateRegist(user, request.getSession());
		if (errors != null && errors.size() > 0) {
			request.setAttribute("user", user);
			request.setAttribute("errors", errors);
			return "f:/jsps/user/regist.jsp";
		}
		boolean bl = ubiz.regist(user);
		if(bl){
			request.setAttribute("code", "success");
			request.setAttribute("msg", "恭喜，注册成功！请马上到邮箱完成激活！");
			return "f:/jsps/msg.jsp";
		}
		return "f:/jsps/user/regist.jsp";
		
	}
	private Map<String, String> validateRegist(User formUser, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		String loginname = formUser.getLoginname();
		if (StrKit.isBlank(loginname)) {
			errors.put("loginname", "用户名不能为空！");
		} else if (loginname.length() < 6 || loginname.length() > 20) {
			errors.put("loginname", "用户名长度必须在6~20之间！");
		} else if (ubiz.existLoginname(loginname)) {
			errors.put("loginname", "用户名已被占用！");
		}

		String loginpass = formUser.getLoginpass();
		if (StrKit.isBlank(loginpass)) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() < 6 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在6~20之间！");
		}

		String reloginpass = formUser.getReloginpass();
		if (StrKit.isBlank(reloginpass)) {
			errors.put("reloginpass", "确认密码不能为空！");
		} else if (!reloginpass.equals(loginpass)) {
			errors.put("reloginpass", "两次输入不一致！");
		}

		String email = formUser.getEmail();
		if (StrKit.isBlank(email)) {
			errors.put("email", "Email不能为空！");
		} else if (!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
			errors.put("email", "Email格式错误！");
		} else if (ubiz.checkEmail(email)) {
			errors.put("email", "Email已被注册！");
		}

		String verifyCode = formUser.getVerifyCode();
		String code = (String) session.getAttribute("vCode");
		String vcode = code.substring(0, 4);
		if (StrKit.isBlank(verifyCode)) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}

		return errors;
	}
	public String activation(HttpServletRequest request, HttpServletResponse response){
		String code = request.getParameter("activationCode");
		try {
			ubiz.activatioin(code);
			request.setAttribute("code", "success");
			request.setAttribute("msg", "恭喜，激活成功，请马上登录！");
		} catch (Exception e) {
			request.setAttribute("code", "error");
			request.setAttribute("msg", e.getMessage());
		}

		return "f:/jsps/msg.jsp";
		
	}
	
	/*
	 * 登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response){
		//用户校验
		Map<String, String[]> map = request.getParameterMap();
		Map<String,String> map1 = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, String[]> entry = it.next();
			String[] s = entry.getValue();
			map1.put(entry.getKey(), s[0]);
		}
		User user = BeanKit.toBean(map1, User.class);
		
		Map<String, String> errors = validateLogin(user, request.getSession());
		if(errors.isEmpty()){
			User u = ubiz.login(user);
			if(u==null||!u.isStatus()){
				request.setAttribute("code", "error");
				request.setAttribute("msg","登录失败");
				return "f:/jsps/msg.jsp";
			}else{
				request.getSession().setAttribute("user", u);
				return "f:/jsps/main.jsp";
			}
		}else{
			request.setAttribute("user", user);
			request.setAttribute("errors", errors);
			request.setAttribute("code", "error");
			request.setAttribute("msg","登录失败");	
			return "f:/jsps/msg.jsp";
		}
		
		
	}
	private Map<String, String> validateLogin(User user, HttpSession session) {
		String loginname = user.getLoginname();
		Map<String, String> errors = new HashMap<String, String>();
		if (StrKit.isBlank(loginname)) {
			errors.put("loginname", "登录名不能为空！");
		} else if(loginname.length() < 6 || loginname.length() > 20) {
			errors.put("loginname", "登录名长度必须在6~20之间！");
		} 
		
		String loginpass = user.getLoginpass();
		if (StrKit.isBlank(loginpass)) {
			errors.put("loginpass", "密码不能为空！");
		} else if (loginpass.length() < 6 || loginpass.length() > 20) {
			errors.put("loginpass", "密码长度必须在6~20之间！");
		}
		
		String verifyCode = user.getVerifyCode();
		String code = (String) session.getAttribute("vCode");
		String vcode = code.substring(0, 4);
		if (StrKit.isBlank(verifyCode)) {
			errors.put("verifyCode", "验证码不能为空！");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误！");
		}

		
		return errors;
	}
	//修改密码 验证密码是否正确
	public String validateLoginpass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject jo = new JSONObject();
		String loginpass = request.getParameter("loginpass");
		User user = (User) request.getSession().getAttribute("user");
		user.setLoginpass(loginpass);
		
		User u = ubiz.login(user);
		if(u==null){
			jo.put("status", "wrong");
		}else{
			jo.put("status", "right");
		}
		out.print(jo);
		out.flush();
		out.close();
		//如果为字符串，如果字符串包含F：就是转发    如果是R：就是重定向；null 不操作
		return null;
		
	}
	public String changepassword(HttpServletRequest request, HttpServletResponse response){
		User user = (User) request.getSession().getAttribute("user");
		String loginpass = request.getParameter("loginpass");
		String newpass = request.getParameter("newpass");
		String reloginpass = request.getParameter("reloginpass");
		if(user.getLoginpass().equals(loginpass)&&newpass.equals(reloginpass)){
			user.setLoginpass(newpass);
			Boolean bl = ubiz.changepw(user);
			if(bl){
				request.getSession().setAttribute("user", user);
				request.setAttribute("code", "success");
				request.setAttribute("msg", "密码修改成功");
			}else{
				request.setAttribute("code", "error");
				request.setAttribute("msg", "失败");
			}
		}
		return "f:/jsps/msg.jsp";
	}
	
	public String quit(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("user");
		return "f:/jsps/main.jsp";
		
	}
	public String findAll(HttpServletRequest request, HttpServletResponse response){
		
		return null;
		
	}

	
}
