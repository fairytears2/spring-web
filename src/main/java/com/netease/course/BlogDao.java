package com.netease.course;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface BlogDao {
	
	@Results(value = {
			@Result(property = "blogTitle", column = "blogTitle" ),
			@Result(property="blogContent",column = "blogContent")
		})
	@Insert("insert into blog(blogTitle,blogContent) values(#{blogTitle},#{blogContent}) ")
	public void insertblog(@Param("blogTitle")String title,@Param("blogContent")String content);
	
	public int count();
	
}
