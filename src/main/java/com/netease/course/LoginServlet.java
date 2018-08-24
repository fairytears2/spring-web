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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@Controller
@RequestMapping(value = "/view")
public class LoginServlet {
    
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void UserLogindoGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String origUrl = req.getParameter("origUrl");
		req.setAttribute("origUrl", origUrl);
		
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		System.out.println("login doGet want to login");
		System.out.println("liyuming");

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void  login(HttpServletRequest req, HttpServletResponse resp ,Writer writer) throws ServletException, IOException, SQLException {
		System.out.println("first into login");
		String origUrl = req.getParameter("origUrl");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User user = null;   
		ApplicationContext context = new  ClassPathXmlApplicationContext("application-context.xml");
		UserDao userDao = context.getBean("userDao", UserDao.class);
		user = userDao.select(username);
		System.out.println(req.getParameter("username"));
		if(username != null) {
			if(user.getUserPassword().equals(password)) {
				
				
				if (origUrl == null||origUrl == "" ) {
					origUrl = "login_success";
				} else {
					origUrl = URLDecoder.decode(origUrl, "utf-8");
				}
				req.setAttribute("user", username);
				resp.setCharacterEncoding("utf-8");
				req.getRequestDispatcher("/WEB-INF/view/loginsuccess.jsp").forward(req, resp);
			}else {
				backToLoginPage(req, resp, user, origUrl, "密码不正确");
			}
		}
		((ConfigurableApplicationContext)context).close();
	}
	
	
	private void backToLoginPage(HttpServletRequest req, HttpServletResponse resp,User user,  String origUrl,
			String string) throws SQLException, IOException, ServletException {
		req.setAttribute("account", user);
		req.setAttribute("origUrl", origUrl);
		req.setAttribute("errInfo", string);

		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}
	
	
	@RequestMapping(value = "/logout")
	public void loginOut(HttpServletRequest req , HttpServletResponse resp) throws IOException {
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write("<p style='color:\"blue\"'>logout success</p>" + "<a href=\"./login\">首页</a>");
	}
	
	
	
}
