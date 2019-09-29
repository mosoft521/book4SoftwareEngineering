package com.gmail.mosoft521;

import com.gmail.mosoft521.se.book.commons.BusinessException;
import com.gmail.mosoft521.se.book.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Hello World!");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        try {
            userService.login("admin", "admin");
            LOGGER.info("login ok!");
        } catch (BusinessException be) {
            LOGGER.error("login error!");
        }

        LOGGER.info("Byebye World!");
    }
}
