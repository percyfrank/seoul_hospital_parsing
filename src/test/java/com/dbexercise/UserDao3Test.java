package com.dbexercise;

import com.dbexercise.dao.UserDao3;
import com.dbexercise.dao.UserDaoFactory;
import com.dbexercise.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class) // Junit에서 Spring기능을 사용하기 위함(없으면 Autowired등 안먹힘)
@ContextConfiguration(classes = UserDaoFactory.class) // 설정 정보
class UserDao3Test {

    @Autowired
    ApplicationContext context;

    private UserDao3 userDao;
    private User user1,user2,user3;

    @BeforeEach
    void setUp() {
        this.userDao = this.context.getBean("awsUserDao", UserDao3.class);
        this.user1 = new User("1", "권오석", "1234");
        this.user2 = new User("2", "권오석1", "12345");
        this.user3 = new User("3", "권오석2", "123456");
    }

    @Test
    public void addAndGet() throws Exception {
        //given
//        User user1 = new User("1", "권오석", "1234");

        //when
        userDao.deleteAll();
        //then
        assertEquals(0,userDao.getCount());

        userDao.add(user1);
        assertEquals(1,userDao.getCount());
        User byId1 = userDao.getById(user1.getId());

        assertEquals(user1.getName(),byId1.getName());
        assertEquals(user1.getPassword(),byId1.getPassword());

    }

    @Test
    public void count() {
        //given
        userDao.deleteAll();
        assertEquals(0, userDao.getCount());
        //when
        userDao.add(user1);
        assertEquals(1,userDao.getCount());
        userDao.add(user2);
        assertEquals(2,userDao.getCount());
        userDao.add(user3);
        assertEquals(3,userDao.getCount());
        //then
    }

    @Test
    public void getById() throws Exception {
        //then
        assertThrows(EmptyResultDataAccessException.class, () -> {
            userDao.getById("30");
        });
    }

}