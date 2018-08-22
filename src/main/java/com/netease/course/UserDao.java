package com.netease.course;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.netease.course.User;

public interface UserDao {
	
	@Results(value = {
			@Result(property = "userName", column = "userName" ),
			@Result(property="userPassword",column = "userPassword")
		})
	@Select("select* from user")
	public List<User> userList ();
	
	 @Select ("select * from user where userName=#{username}")
	 public User select(@Param("username")String username);
  
}
