package com.netease.course;



import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/api")
public class Test {

	@RequestMapping("/blog")
	@ResponseBody
	public String update(@RequestParam String blogTitle, @RequestParam String blogContent, HttpServletResponse resp){
		ApplicationContext context = new  ClassPathXmlApplicationContext("application-context.xml");
		BlogDao blogdao = context.getBean("blogdao", BlogDao.class);
		if(blogTitle.length()<21&&blogContent.length()<101&&blogTitle.length()>0&&blogContent.length()>0)
		{
			System.out.println("OK");
			blogdao.insertblog(blogTitle, blogContent);
			((ConfigurableApplicationContext)context).close();
			resp.setStatus(200);
			return "200,ok.blog insert success.<br>Please open developer tools to check statuscode.";			
		}else {
			 System.out.println("Fail");
			 ((ConfigurableApplicationContext)context).close();
			 resp.setStatus(200);
			 return "blog insert failed.<br>Please open developer tools to check statuscode.<br>\nCheck the size of Title or Content.<br><h1>BlogTitle:"+blogTitle;
		}
		
	}
}
