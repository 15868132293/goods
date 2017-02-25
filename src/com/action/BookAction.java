package com.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.biz.IBookBiz;
import com.biz.impl.BookBizImpl;
import com.entity.Book;
import com.servlet.BaseServlet;

import sun.java2d.pipe.AATextRenderer;

/**
 * Servlet implementation class BookAction
 */
public class BookAction extends BaseServlet {
	IBookBiz ibz = new BookBizImpl();
	public String listBooksBycate(HttpServletRequest request, HttpServletResponse response){
		String cid = request.getParameter("cid");
		List list = ibz.listBooksByCid(cid);
		List nlist = new ArrayList<>();
		//用来取
		request.getSession().setAttribute("book1", list);
		//用来给前台获取
		request.getSession().setAttribute("book", nlist);
		if(list.isEmpty()){
			return "f:/jsps/book/list.jsp";
		}else{
			for(int i=0;i<8;i++){
				nlist.add(list.get(i));
			}
			return "f:/jsps/book/list.jsp";
		}
		

		
	}
	public String gotopage(HttpServletRequest request, HttpServletResponse response){
		//一页十二个
		int size = 12;
		
		String pagecount1 = request.getParameter("pc");
		int pagecount = Integer.parseInt(pagecount1);
		List<Book> list = (List) request.getSession().getAttribute("book1");
		List newlist = new ArrayList<>();
		for(int i=8*(pagecount-1);i<8*pagecount;i++){
			newlist.add(list.get(i));
		}
		request.setAttribute("book", newlist);
		request.setAttribute("pagecount", pagecount);
		return "f:/jsps/book/list.jsp";	
	}
	public String singlebook(HttpServletRequest request, HttpServletResponse response){
		String bid = request.getParameter("bid");
		Book book = ibz.singlebook(bid);
		request.setAttribute("singlebook", book);
		return"f:/jsps/book/desc.jsp";	
		
	}
	
	
	public String search(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("bname");
		List<Book> books = (List) request.getSession().getAttribute("book1");
		List list = new ArrayList<>();
		for (Book bk : books) {
			if(bk.getBname().contains(name)){
				list.add(bk);
				request.setAttribute("book", list);
			}
		}
		return "f:/jsps/book/list.jsp";
		
	}
	
   

}
