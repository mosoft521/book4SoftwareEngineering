package com.gmail.mosoft521.se.book.ui;

import com.gmail.mosoft521.se.book.service.BookService;
import com.gmail.mosoft521.se.book.service.BookTypeService;
import com.gmail.mosoft521.se.book.service.InRecordService;
import com.gmail.mosoft521.se.book.service.PublisherService;
import com.gmail.mosoft521.se.book.service.SaleRecordService;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;

/**
 * 主界面的JFrame
 */
public class MainFrame extends JFrame {
    //业务接口
    private BookTypeService bookTypeService;
    private PublisherService publisherService;
    private BookService bookService;
    private SaleRecordService saleRecordService;
    private InRecordService inRecordService;

    BookTypePanel bookTypePanel;
    CommonPanel currentPanel;
    private SalePanel salePanel;
    private RepertoryPanel repertoryPanel;
    private BookPanel bookPanel;
    private PublisherPanel publisherPanel;

    private String title = "长江图书公司记账信息管理系统-";

    private Action sale = new AbstractAction("销售记账", new ImageIcon("images/sale.gif")) {
        public void actionPerformed(ActionEvent e) {
            changePanel(salePanel);
        }
    };

    private Action repertory = new AbstractAction("进货记账", new ImageIcon("images/repertory.gif")) {
        public void actionPerformed(ActionEvent e) {
            changePanel(repertoryPanel);
        }
    };

    private Action book = new AbstractAction("图书录入", new ImageIcon("images/book.gif")) {
        public void actionPerformed(ActionEvent e) {
            changePanel(bookPanel);
            bookPanel.initImage();
            bookPanel.repaint();
        }
    };

    private Action type = new AbstractAction("图书种类录入", new ImageIcon("images/type.gif")) {
        public void actionPerformed(ActionEvent e) {
            changePanel(bookTypePanel);
        }
    };

    private Action concern = new AbstractAction("出版社录入", new ImageIcon("images/concern.gif")) {
        public void actionPerformed(ActionEvent e) {
            changePanel(publisherPanel);
        }
    };

    public MainFrame(ApplicationContext context) {
        this.bookTypeService = context.getBean("bookTypeService", BookTypeService.class);
        this.publisherService = context.getBean("publisherService", PublisherService.class);
        this.bookService = context.getBean("bookService", BookService.class);
        this.saleRecordService = context.getBean("saleRecordService", SaleRecordService.class);
        this.inRecordService = context.getBean("inRecordService", InRecordService.class);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("菜单");
        menuBar.add(menu);

        menu.add(sale).setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
        menu.add(repertory).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.CTRL_MASK));
        menu.add(book).setAccelerator(KeyStroke.getKeyStroke('B', InputEvent.CTRL_MASK));
        menu.add(type).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));
        menu.add(concern).setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_MASK));

        //让销售界面作为第一显示界面
        this.salePanel = new SalePanel(context);
        this.add(salePanel);
        this.currentPanel = salePanel;
        //初始化销售界面的数据
        this.salePanel.initData();

        //初始化库存管理界面
        repertoryPanel = new RepertoryPanel(context);
        //初始化图书管理界面
        bookPanel = new BookPanel(context);
        //初始化出版社管理界面
        publisherPanel = new PublisherPanel(context);
        //初始化种类管理界面
        bookTypePanel = new BookTypePanel(context);

        this.setJMenuBar(menuBar);
        this.setTitle("长江图书公司记账信息管理系统-销售记账");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setResizable(false);
        this.setSize(1150,740); //窗体大小
        this.setLocationRelativeTo(null); //在屏幕中居中显示
        this.setVisible(true); //显示
    }

    //切换各个界面
    private void changePanel(CommonPanel commonPanel) {
        //移除当前显示的JPanel
        this.remove(currentPanel);
        //添加需要显示的JPanel
        this.add(commonPanel);
        //设置当前的JPanel
        this.currentPanel = commonPanel;
        this.repaint();
        this.setVisible(true);
        //设置标题
        this.setTitle(commonPanel.getTitle());
        //重新读取数据
        commonPanel.setViewDatas();
        //刷新列表
        commonPanel.clear();
    }
}
