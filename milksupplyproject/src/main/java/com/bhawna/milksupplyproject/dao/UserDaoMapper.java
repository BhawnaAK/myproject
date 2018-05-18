package com.bhawna.milksupplyproject.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.bhawna.milksupplyproject.model.User;
public class UserDaoMapper implements ResultSetMapper<User> {


	private static final String ID = "user_id";
	private static final String NAME = "name";
	private static final String LASTNAME = "last_name";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "password";


	@Override
	public User map(int i, ResultSet resultSet, StatementContext stmtContext) throws SQLException {
		User user =  new User(resultSet.getString(NAME),resultSet.getString(LASTNAME),resultSet.getString(PHONE),resultSet.getString(EMAIL),resultSet.getString(PASSWORD));
		user.setId(resultSet.getLong(ID));
		return user;
	}

}
