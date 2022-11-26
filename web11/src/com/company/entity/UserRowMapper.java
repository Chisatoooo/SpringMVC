package com.company.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int count) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setUserpwd(rs.getString("userpwd"));
		user.setPhone(rs.getString("phone"));
		user.setUname(rs.getString("uname"));
		user.setIDnumber(rs.getString("IDnumber"));
		user.setBirthday(rs.getString("birthday"));
		user.setAddress(rs.getString("address"));
		user.setHeadimage(rs.getString("headimage"));
		user.setDate(rs.getString("date"));
		user.setState(rs.getString("state"));
		return user;
	}

}
