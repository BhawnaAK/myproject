package com.bhawna.milksupplyproject.service;

import java.util.List;
import java.util.Objects;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.sqlobject.CreateSqlObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bhawna.milksupplyproject.model.User;
import com.bhawna.milksupplyproject.dao.UserDao;

public abstract class UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	 private static final String SUCCESS = "Success";
	    private static final String UNEXPECTED_DELETE_ERROR = "An unexpected error occurred while deleting User.";
	    private static final String USER_NOT_FOUND = "User id %s not found.";
	 
	    @CreateSqlObject
	    abstract UserDao UserDao();
	 
	    public List<User> getUsers() {
	        return UserDao().getUsers();
	    }
	 
	    public User getUser(long id) {
	        User User = UserDao().getUser(id);
	        if (Objects.isNull(User)) {
	            throw new WebApplicationException(String.format(USER_NOT_FOUND, id), Status.NOT_FOUND);
	        }
	        return User;
	    }
	 
	    public User createUser(User User) {
	        UserDao().createUser(User);
	        return UserDao().getUser(UserDao().lastInsertId());
	    }
	 
	    public User editUser(User user) {
	        if (Objects.isNull(UserDao().getUser(user.getId()))) {
	            throw new WebApplicationException(String.format(USER_NOT_FOUND, user.getId()),
	                    Status.NOT_FOUND);
	        }
	        UserDao().editUser(user);
	        return UserDao().getUser(user.getId());
	    }
	 
	    public String deleteUser(final int id) {
	        int result = UserDao().deleteUser(id);
	        logger.info("Result in UserService.deleteUser is: {}", result );
	        switch (result) {
	            case 1:
	                return SUCCESS;
	            case 0:
	                throw new WebApplicationException(String.format(USER_NOT_FOUND, id), Status.NOT_FOUND);
	            default:
	                throw new WebApplicationException(UNEXPECTED_DELETE_ERROR, Status.INTERNAL_SERVER_ERROR);
	        }
	    }

}
