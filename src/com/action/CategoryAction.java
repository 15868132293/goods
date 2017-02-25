package com.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biz.IcategoryBiz;
import com.biz.impl.CategoryBizImpl;
import com.servlet.BaseServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CategoryAction extends BaseServlet implements Servlet {
	
	private static final long serialVersionUID = 1L;
	IcategoryBiz icb = new CategoryBizImpl();
	public String listCategories(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		List list = icb.findAll();
		request.getSession().setAttribute("parents", list);
		String str = JSON.toJSONString(list);
		out.print(str);
		out.close();
		return null;
	}
}
