package com.netease.course;

import java.io.IOException;
import java.io.Writer;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.glass.ui.View;




@Controller
@RequestMapping(value = "/view")
public class LoginServlet {
    
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void UserLogindoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/login.html").forward(req, resp);
		System.out.println("login doGet want to login");
		System.out.println("liyuming");

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String  login(HttpServletRequest req, HttpServletResponse resp ,ModelMap map) throws ServletException, IOException, SQLException {
		System.out.println("first into login");
		String view =null ;
		String username = req.getParameter("name");
		String password = req.getParameter("password");
		User user = null;   
		ApplicationContext context = new  ClassPathXmlApplicationContext("application-context.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		user = userDao.select(username);
		System.out.println(username);
		if(user != null) {
			if(password.equals(user.getUserPassword())) {
				map.addAttribute("name",username);
				map.addAttribute("password",password);
				view = "user";
			}else {
				view = "fail";
			}
		}else {
			view = "error";
		}
		((ConfigurableApplicationContext)context).close();
		return view;
	}
	
	
	@RequestMapping(value = "/logout")
	public void loginOut(HttpServletRequest req , HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("<p style='color:\"blue\"'>logout success</p>" + "<a href=\"./login\">Ê×Ò³</a>");
	}
	
		
}
