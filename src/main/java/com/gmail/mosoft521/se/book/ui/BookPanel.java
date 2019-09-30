package com.gmail.mosoft521.se.book.ui;

import com.gmail.mosoft521.se.book.commons.ImageUtil;
import com.gmail.mosoft521.se.book.commons.UploadException;
import com.gmail.mosoft521.se.book.entity.BookType;
import com.gmail.mosoft521.se.book.entity.Publisher;
import com.gmail.mosoft521.se.book.service.BookService;
import com.gmail.mosoft521.se.book.service.BookTypeService;
import com.gmail.mosoft521.se.book.service.PublisherService;
import com.gmail.mosoft521.se.book.vo.BookVO;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Collection;
import java.util.Vector;

/**
 * 书本界面
 */
public class BookPanel extends CommonPanel {
    private BookService bookService;
    private BookTypeService bookTypeService;
    private PublisherService publisherService;

    private final static String DEFAULT_File_Path = "upload/no_pic.gif";
    //种类下拉框
    JComboBox typeComboBox;
    //出版社下拉框
    JComboBox concernComboBox;
    //书本id
    JTextField bookId;
    //书本名称
    JTextField bookName;
    //书本价格
    JTextField price;
    //书本介绍
    JTextArea intro;
    JButton clearButton;
    JButton saveButton;
    //查询中的书本名称
    JTextField nameQueryTextField;
    //查询按钮
    JButton queryButton;
    //图片上传按钮
    JButton imageButton;
    //文件选择器
    FileChooser chooser;
    //当前界面所显示的图片
    ImageIcon currentImage;
    //当前界面所显示图片的路径
    String currentImagePath;
    //显示图片的JLabel
    JLabel imageLabel;
    JTextField author;
    //查看大图的JFrame
    ImageFrame imageFrame;
    private Vector columns;

    public BookPanel(ApplicationContext context) {
        this.bookService = context.getBean("bookService", BookService.class);
        this.bookTypeService = context.getBean("bookTypeService", BookTypeService.class);
        this.publisherService = context.getBean("publisherService", PublisherService.class);
        setViewDatas();
        initColumns();

        //设置列表
        DefaultTableModel model = new DefaultTableModel(getDatas(), this.columns);
        JTable table = new CommonJTable(model);
        setJTable(table);

        JScrollPane upPane = new JScrollPane(table);
        upPane.setPreferredSize(new Dimension(1000, 350));

        //设置添加, 修改的界面
        JPanel downPane = new JPanel();
        downPane.setLayout(new BoxLayout(downPane, BoxLayout.X_AXIS));

        Box downBox1 = new Box(BoxLayout.X_AXIS);
        //添加id隐藏域
        bookId = new JTextField(10);
        bookId.setVisible(false);
        downBox1.add(bookId);
        //列表下面的box
        downBox1.add(new JLabel("书本名称："));
        downBox1.add(Box.createHorizontalStrut(10));
        bookName = new JTextField(10);
        downBox1.add(bookName);
        downBox1.add(Box.createHorizontalStrut(30));

        downBox1.add(new JLabel("价格："));
        downBox1.add(Box.createHorizontalStrut(10));
        price = new JTextField(10);
        downBox1.add(price);
        downBox1.add(Box.createHorizontalStrut(30));

        downBox1.add(new JLabel("作者："));
        downBox1.add(Box.createHorizontalStrut(10));
        author = new JTextField(10);
        downBox1.add(author);
        downBox1.add(Box.createHorizontalStrut(30));

        /***************************************************/
        Box downBox4 = new Box(BoxLayout.X_AXIS);

        downBox4.add(new JLabel("所属种类："));
        downBox4.add(Box.createHorizontalStrut(10));
        typeComboBox = new JComboBox();
        addTypes();
        downBox4.add(typeComboBox);
        downBox4.add(Box.createHorizontalStrut(30));

        downBox4.add(new JLabel("出版社："));
        concernComboBox = new JComboBox();
        addConcerns();
        downBox4.add(concernComboBox);
        downBox4.add(Box.createHorizontalStrut(30));

        downBox4.add(new JLabel("书本图片："));
        this.chooser = new FileChooser(this);
        this.imageButton = new JButton("请选择文件");
        downBox4.add(this.imageButton);
        downBox4.add(Box.createHorizontalStrut(30));

        /*******************************************************/
        Box downBox2 = new Box(BoxLayout.X_AXIS);
        downBox2.add(new JLabel("书本简介："));
        downBox2.add(Box.createHorizontalStrut(10));

        intro = new JTextArea("", 5, 5);
        JScrollPane introScrollPane = new JScrollPane(intro);
        intro.setLineWrap(true);
        downBox2.add(introScrollPane);
        downBox2.add(Box.createHorizontalStrut(30));
        /*******************************************************/
        Box downBox3 = new Box(BoxLayout.X_AXIS);

        saveButton = new JButton("保存");
        downBox3.add(saveButton);
        downBox3.add(Box.createHorizontalStrut(30));

        clearButton = new JButton("清空");
        downBox3.add(clearButton);
        downBox3.add(Box.createHorizontalStrut(30));

        /*******************************************************/
        Box downLeftBox = new Box(BoxLayout.Y_AXIS);

        downLeftBox.add(getSplitBox());
        downLeftBox.add(downBox1);
        downLeftBox.add(getSplitBox());
        downLeftBox.add(downBox4);
        downLeftBox.add(getSplitBox());
        downLeftBox.add(downBox2);
        downLeftBox.add(getSplitBox());
        downLeftBox.add(downBox3);

        Box downRightBox = new Box(BoxLayout.Y_AXIS);
        this.imageLabel = new JLabel();
        this.imageLabel.setPreferredSize(new Dimension(200, 200));
        this.currentImage = new ImageIcon(DEFAULT_File_Path);
        this.currentImagePath = DEFAULT_File_Path;
        this.imageLabel.setIcon(this.currentImage);
        JScrollPane p = new JScrollPane(this.imageLabel);
        downRightBox.add(p);

        downPane.add(downLeftBox);
        downPane.add(downRightBox);

        /*******************查询******************/
        JPanel queryPanel = new JPanel();
        Box queryBox = new Box(BoxLayout.X_AXIS);
        queryBox.add(new JLabel("书名："));
        queryBox.add(Box.createHorizontalStrut(30));
        nameQueryTextField = new JTextField(20);
        queryBox.add(nameQueryTextField);
        queryBox.add(Box.createHorizontalStrut(30));
        queryButton = new JButton("查询");
        queryBox.add(queryButton);
        queryPanel.add(queryBox);
        this.add(queryPanel);

        //列表为添加界面
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upPane, downPane);
        split.setDividerSize(5);
        this.add(split);
        //添加监听器
        initListeners();
    }

    private void initColumns() {
        this.columns = new Vector();
        this.columns.add("id");
        this.columns.add("书本名称");
        this.columns.add("简介");
        this.columns.add("作者");
        this.columns.add("所属种类");
        this.columns.add("出版社");
        this.columns.add("库存");
        this.columns.add("价格");
    }

    public void initImage() {
        this.currentImage = new ImageIcon(DEFAULT_File_Path);
        this.currentImagePath = DEFAULT_File_Path;
        refreshImage();
    }

    private void initListeners() {
        //表格选择监听器
        getJTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                //当选择行时鼠标释放时才执行
                if (!event.getValueIsAdjusting()) {
                    //如果没有选中任何一行, 则返回
                    if (getJTable().getSelectedRowCount() != 1) return;
                    view();
                }
            }
        });
        //清空按钮监听器
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        //保存按钮监听器
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //验证空值
                if (bookName.getText().trim().equals("")) {
                    showWarn("请输入书的名称");
                    return;
                }
                save();
            }
        });
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                query();
            }
        });
        //图片上传按钮
        imageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addImage();
            }
        });
        this.imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                showImageFrame();
            }
        });
    }

    //打开显示图片的JFrame
    private void showImageFrame() {
        if (this.imageFrame == null) {
            this.imageFrame = new ImageFrame(this.currentImage);
        }
        this.imageFrame.refresh(getBigImage());
    }

    //获取大图片的路径
    private ImageIcon getBigImage() {
        String smallImagePath = this.currentImagePath;
        if (smallImagePath.equals(this.DEFAULT_File_Path)) {
            return this.currentImage;
        }
        //获取无后缀文件名(带路径)
        String temp = smallImagePath.substring(0, smallImagePath.lastIndexOf("."));
        //拼装大图路径全文件名
        String bigImagePath = temp + "-big" + smallImagePath.substring(smallImagePath.lastIndexOf("."),
                smallImagePath.length());
        return new ImageIcon(bigImagePath);
    }

    //添加文件的方法
    private void addImage() {
        chooser.showOpenDialog(this);
    }

    //上传图片
    public void upload(File selectFile) {
        try {
            //使用uuid生成文件名，保证文件名唯一
            String uuid = ImageUtil.getUUID();
            //缩略图的url
            String smallFilePath = "upload/" + uuid + ".jpg";
            //原图的url
            String bigFilePath = "upload/" + uuid + "-big.jpg";
            //生成缩略图
            File file = ImageUtil.makeImage(selectFile, smallFilePath, "jpg", true);
            //生成原图
            File source = ImageUtil.makeImage(selectFile, bigFilePath, "jpg", false);
            //设置界面显示的图片对象
            this.currentImage = new ImageIcon(file.getAbsolutePath());
            //设置界面显示的图片url
            this.currentImagePath = smallFilePath;
            //刷新图片显示区
            refreshImage();
        } catch (UploadException e) {
            e.printStackTrace();
            showWarn(e.getMessage());
        }
    }

    //刷新图片显示的JLabel
    private void refreshImage() {
        this.imageLabel.setIcon(this.currentImage);
    }

    //根据名称查询书
    private void query() {
        String name = this.nameQueryTextField.getText();
        java.util.List<BookVO> books = bookService.find(name);
        Vector<Vector> datas = changeDatas(books);
        setDatas(datas);
        refreshTable();
    }

    //清空表单, 刷新列表
    public void clear() {
        refreshTable();
        this.bookId.setText("");
        this.bookName.setText("");
        this.price.setText("");
        this.intro.setText("");
        this.author.setText("");
        this.typeComboBox.removeAllItems();
        this.concernComboBox.removeAllItems();
        addTypes();
        addConcerns();
        //清空图片域
        this.currentImage = new ImageIcon(DEFAULT_File_Path);
        refreshImage();
        this.currentImagePath = DEFAULT_File_Path;
    }

    //保存
    private void save() {
        //如果bookId的文本框(隐藏)的值为空, 则是新增, 否则为修改
        if (this.bookId.getText().equals("")) {
            add();
        } else {
            update();
        }
    }

    //新增书本
    private void add() {
        if (!validatePrice()) {
            JOptionPane.showMessageDialog(this, "请输入正确的价格");
            return;
        }
        //新增书本时库存为0
        BookVO book = getBook();
        bookService.add(book);
        //重新读取数据
        setViewDatas();
        //刷新列表, 清空表单
        clear();
    }

    //验证输入
    private boolean validatePrice() {
        String price = this.price.getText();
        try {
            Integer p = Integer.parseInt(price);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //修改书本, 修改时不需要修改库存, 因为库存取决于销售与入货
    private void update() {
        BookVO book = getBook();
        //由于是修改, 因此需要设置id
        book.setId(Integer.parseInt(this.bookId.getText()));
        bookService.update(book);
        //重新读取数据
        setViewDatas();
        //刷新列表, 清空表单
        clear();
    }

    //从界面中获取数据并封装成Book对象
    private BookVO getBook() {
        String bookName = this.bookName.getText();
        String price = this.price.getText();
        String intro = this.intro.getText();
        String author = this.author.getText();
        BookType type = (BookType) this.typeComboBox.getSelectedItem();
        Publisher concern = (Publisher) this.concernComboBox.getSelectedItem();
        BookVO bookVO = new BookVO();
        bookVO.setBookName(bookName);
        bookVO.setBookPrice(Double.parseDouble(price));
        bookVO.setBookIntro(intro);
        bookVO.setAuthor(author);
        bookVO.setTypeId(type.getId());
        bookVO.setPubId(concern.getId());
        bookVO.setRepertorySize(0L);
        bookVO.setImageUrl(currentImagePath);

        return bookVO;
    }

    //查看书本
    private void view() {
        String id = getSelectId(getJTable());
        BookVO book = bookService.get(Integer.parseInt(id));
        this.bookId.setText(book.getId().toString());
        this.bookName.setText(book.getBookName());
        this.price.setText(book.getBookPrice().toString());
        this.intro.setText(book.getBookIntro());
        this.author.setText(book.getAuthor());
        this.typeComboBox.setSelectedItem(makeType(book.getBookType()));
        this.concernComboBox.setSelectedItem(makeConcern(book.getPublisher()));
        this.currentImage = new ImageIcon(book.getImageUrl());
        this.currentImagePath = book.getImageUrl();
        refreshImage();
    }

    /*
     * 实现父类方法, 查询数据库并返回对应的数据格式, 调用父类的setDatas方法设置数据集合
     */
    public void setViewDatas() {
        //查找对应的数据
        java.util.List<BookVO> books = bookService.getAll();
        //转换显示格式
        Vector<Vector> datas = changeDatas(books);
        //调用父类方法设置结果集合
        setDatas(datas);
    }

    //将数据转换成视图表格的格式
    private Vector<Vector> changeDatas(java.util.List<BookVO> datas) {
        Vector<Vector> view = new Vector<Vector>();
        for (BookVO book : datas) {
            Vector v = new Vector();
            v.add(book.getId());
            v.add(book.getBookName());
            v.add(book.getBookIntro());
            v.add(book.getAuthor());
            v.add(book.getBookType().getTypeName());
            v.add(book.getPublisher().getPubName());
            v.add(book.getRepertorySize());
            v.add(book.getBookPrice());
            view.add(v);
        }
        return view;
    }

    @Override
    public Vector<String> getColumns() {
        return this.columns;
    }

    @Override
    public void setTableFace() {
        //隐藏id列
        getJTable().getColumn("id").setMinWidth(-1);
        getJTable().getColumn("id").setMaxWidth(-1);
        getJTable().getColumn("简介").setMinWidth(350);
        getJTable().setRowHeight(30);
    }

    //从数据库中获取全部的种类并添加到下拉框中
    private void addTypes() {
        //调用种类业务接口取得全部的种类
        Collection<BookType> types = this.bookTypeService.getAll();
        for (BookType type : types) {
            //typeComboBox是种类下拉框对象
            this.typeComboBox.addItem(makeType(type));
        }
    }

    //从数据库中获取全部的出版社并添加到下拉框中
    private void addConcerns() {
        Collection<Publisher> concers = this.publisherService.getAll();
        for (Publisher c : concers) {
            this.concernComboBox.addItem(makeConcern(c));
        }
    }

    //创建一个Type对象, 用于添加到下拉框中, 该方法中创建的Type对象重写了toString和equals方法
    private BookType makeType(final BookType source) {
        BookType type = new BookType() {
            public String toString() {
                return source.getTypeName();
            }

            public boolean equals(Object obj) {
                if (obj instanceof BookType) {
                    BookType t = (BookType) obj;
                    if (getId().equals(t.getId())) return true;
                }
                return false;
            }
        };
        type.setId(source.getId());
        return type;
    }

    //创建一个Concern对象, 用于添加到下拉框中, 该方法中创建的Concern对象重写了toString和equals方法
    private Publisher makeConcern(final Publisher c) {
        Publisher concern = new Publisher() {
            public String toString() {
                return c.getPubName();
            }

            public boolean equals(Object obj) {
                if (obj instanceof Publisher) {
                    Publisher co = (Publisher) obj;
                    if (getId().equals(co.getId())) return true;
                }
                return false;
            }
        };
        concern.setId(c.getId());
        return concern;
    }


}

class FileChooser extends JFileChooser {
    //书本管理界面对象
    BookPanel bookPanel;

    public FileChooser(BookPanel bookPanel) {
        this.bookPanel = bookPanel;
    }

    //选择了文件后触发
    public void approveSelection() {
        //获得选择的文件
        File file = getSelectedFile();
        //调用书本管理界面对象的upload方法
        this.bookPanel.upload(file);
        super.approveSelection();
    }
}
