package com.gmail.mosoft521.se.book.ui;

import com.gmail.mosoft521.se.book.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * 登录的JFrame
 */
public class LoginFrame extends JFrame {

    private static Logger LOGGER = LoggerFactory.getLogger(LoginFrame.class);

    private ApplicationContext context;
    private UserService userService;

    //定义"帐号"和"密码"的标签
    private JLabel acc = new JLabel("帐号 ");
    private JLabel pass = new JLabel("密码 ");
    //定义存放用户帐号和密码的文本项
    private JTextField accText = new JTextField();
    private JPasswordField passText = new JPasswordField();
    //定义登录界面的Box容器，以便使用BoxLayout布局器
    private Box up = Box.createHorizontalBox();
    private Box center = Box.createHorizontalBox();
    private Box upCenter = Box.createVerticalBox();
    private Box down = Box.createHorizontalBox();
    //定义登录按钮
    private JButton login = new JButton("登录");

    public LoginFrame(ApplicationContext context) {
        this.context = context;
        this.userService = context.getBean("userService", UserService.class);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //布局各容器，并设置各容器的水平和垂直间距
        up.add(Box.createHorizontalStrut(50));
        up.add(acc);
        up.add(Box.createHorizontalStrut(10));
        up.add(accText);
        up.add(Box.createHorizontalStrut(100));

        center.add(Box.createHorizontalStrut(50));
        center.add(pass);
        center.add(Box.createHorizontalStrut(10));
        center.add(passText);
        center.add(Box.createHorizontalStrut(100));

        upCenter.add(Box.createVerticalStrut(20));
        upCenter.add(up);
        upCenter.add(Box.createVerticalStrut(20));
        upCenter.add(center);
        upCenter.add(Box.createVerticalStrut(20));

        down.add(Box.createHorizontalStrut(270));
        down.add(login, BorderLayout.EAST);
        down.add(Box.createHorizontalStrut(30));
        down.add(Box.createVerticalStrut(50));

        this.add(upCenter, BorderLayout.CENTER);
        this.add(down, BorderLayout.SOUTH);
        this.setBounds(300, 250, 350, 200);
        this.pack();
        this.setVisible(true);
        this.setTitle("长江图书公司记账系统-登录");
        initListeners();
    }

    public void initListeners() {
        this.login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("login");
                login();
            }
        });
        this.login.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LOGGER.info("login");
                login();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public void login() {
        String name = this.accText.getText().trim();
        char[] passes = this.passText.getPassword();
        StringBuffer password = new StringBuffer();
        for (char c : passes) {
            password.append(c);
        }
        try {
            userService.login(name, password.toString());
            new MainFrame(context);
            this.setVisible(false);
        } catch (Exception e) {
            showWarn(e.getMessage());
        }
    }

    //显示警告
    protected int showWarn(String message) {
        return JOptionPane.showConfirmDialog(this, message, "警告", JOptionPane.OK_CANCEL_OPTION);
    }
}
