package com.bhawna.milksupplyproject.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.bhawna.milksupplyproject.model.User;

@RegisterMapper(UserDaoMapper.class)
public interface UserDao {

	@SqlQuery("select * from user;")
    public List<User> getUsers();
 
    @SqlQuery("select * from User where id = :id")
    public User getUser(@Bind("id") final long id);
 
    @SqlUpdate("insert into User(name, department, salary) values(:name, :department, :salary)")
    void createUser(@BindBean final User User);
 
    @SqlUpdate("update User set name = coalesce(:name, name), " +
            " department = coalesce(:department, department), salary = coalesce(:salary, salary)" +
            " where id = :id")
    void editUser(@BindBean final User User);
 
    @SqlUpdate("delete from User where id = :id")
    int deleteUser(@Bind("id") final int id);
 
    @SqlQuery("select last_insert_id();")
    public int lastInsertId();
}
