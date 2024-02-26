package servlet;

import bean.BookInfo;
import bean.BorrowInfo;
import bean.ReaderCardInfo;
import bean.UserInfo;
import service.IBookService;
import service.IBorrowService;
import service.IReaderCardService;
import service.impl.BookServiceImpl;
import service.impl.BorrowServiceImpl;
import service.impl.ReaderCardServiceImpl;
import utils.BookFlagE;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 借阅信息servlet
 */
@WebServlet(name = "borrowServlet", urlPatterns = "/borrowServlet")
public class BorrowServlet extends HttpServlet {
    IBookService bookService = new BookServiceImpl();
    IReaderCardService cardService = new ReaderCardServiceImpl();
    IBorrowService borrowService = new BorrowServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码方式
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type != null && !"".equals(type)){
            if("borrow".equals(type)){
                //借阅书籍
                bookBorrow(request,response);
            }else if("back".equals(type)){
                //归还书籍
                bookBack(request,response);
            }else if(Constant.SERVLET_TYPE_QUERY.equals(type)){
                //查询所有书籍
                bookQueryByState(request,response);
            }else if(Constant.SERVLET_TYPE_LIST.equals(type)){
                //显示所有的借阅信息
                borrowInfoList(request,response);
            }
        }
    }

    private void bookBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BorrowInfo borrow = new BorrowInfo();
        Integer count = 0;
        Integer count1 = 0;
        Integer count2 = 0;
        //获取客户端的书籍名称
        String bookName = request.getParameter("bookName");
        //根据书籍名称查找相关的借阅记录
        borrow = borrowService.queryBorrowByBookName(bookName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取系统当前时间作为归还时间
        Date date = Calendar.getInstance().getTime();
        borrow.setBackTime(sdf.format(date));
        borrow.setBorrowState(1);
        //调用归还书籍的方法
        count1 = borrowService.updateBorrowInfo(borrow);
        if(count1 > 0){
            //修改借书卡中借阅书籍数量
            count = borrowService.countReadCount(borrow.getReaderName());
            borrowService.updateReadCount(count,borrow.getReaderName());
            System.out.println("归还书籍成功...");//后台测试显示成功信息
            System.out.println(borrow.getBookName());
            System.out.println("----------------");
            //如果借阅成功，更改所借阅的书籍状态为 1 借阅
            count2 = borrowService.updateBookStateFree(borrow.getBookName());
            //根据归还时已借阅的时间来进行积分的加减
            integralUpdate(request,response,borrow);
            if(count2 > 0){
                System.out.println("修改书籍状态成功...");//后台测试显示成功信息
                System.out.println("----------------");
            } else {
                System.out.println("修改书籍状态失败...");//后台测试显示失败信息
                System.out.println("----------------");
            }
            borrowInfoList(request,response);
        }else {
            System.out.println("归还书籍失败...");//后台测试显示失败信息
            System.out.println("----------------");
            borrowInfoList(request,response);
        }
    }

    private void bookBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        BorrowInfo borrow = new BorrowInfo();
        Integer count = 0;
        Integer count1 = 0;
        Integer count2 = 0;
        //获取当前登录用户的信息
        UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        //调用方法获取借书卡状态
        ReaderCardInfo card = cardService.queryByName(user.getUserName());
        if(card.getCardState().equals(0)){//借书卡状态为不可以使用
            //System.out.println("借书卡状态为不可以使用，无法完成借书！");//后台测试显示失败信息
            session.setAttribute("msg","当前借书卡不可以使用，无法完成借书，请联系管理员！");
            response.sendRedirect("/card/user_card.jsp");
        }else {//借书卡状态为可以使用
            if(card.getReadCount()>=4) {
                //System.out.println("当前借阅书籍数超过4本，无法完成借书！");//后台测试显示失败信息
                session.setAttribute("msg","当前借阅书籍数超过4本，无法完成借书！");
                response.sendRedirect("/card/user_card.jsp");
            }else {
                borrow.setReaderName(user.getUserName());
                borrow.setBookName(request.getParameter("bookName"));
                //获取系统当前时间作为借阅时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = Calendar.getInstance().getTime();
                borrow.setBorrowTime(sdf.format(date));
                borrow.setBorrowState(0);
                //调用添加借阅记录方法
                count1 = borrowService.insertBorrowInfo(borrow);
                if(count1 > 0){
                    //修改借书卡中借阅书籍数量
                    count = borrowService.countReadCount(borrow.getReaderName());
                    borrowService.updateReadCount(count,borrow.getReaderName());
                    System.out.println("借阅记录添加成功...");//后台测试显示成功信息
                    //如果借阅成功，更改所借阅的书籍状态为 1 借阅
                    count2 = borrowService.updateBookStateBorrow(borrow.getBookName());
                    if(count2 > 0){
                        System.out.println("修改书籍状态成功...");//后台测试显示成功信息
                    } else {
                        System.out.println("修改书籍状态失败...");//后台测试显示失败信息
                    }
                    borrowInfoList(request,response);
                }else {
                    System.out.println("借阅记录添加失败...");//后台测试显示失败信息
                    borrowInfoList(request,response);
                }
            }
        }
    }

    private void borrowInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<BorrowInfo> list;
        //获取当前登录用户的信息
        UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        if(!("Admin1".equals(user.getUserName()) || "Admin2".equals(user.getUserName()))){
            //如果登录者是普通用户，查询自己的借阅记录
            if(!user.getUserName().equals("")) {//判断登陆者信息是否为空
                //根据用户名查询所有的借阅记录
                list = borrowService.listByReaderName(user.getUserName());
                request.setAttribute("list",list);
                request.getRequestDispatcher("/borrow/borrowInfo.jsp").forward(request,response);//跳转到借阅记录界面
            }
        }else {
            //如果登录者是管理员，查询所有用户的借阅记录
            list = borrowService.list(null);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/borrow/borrowInfo.jsp").forward(request,response);//跳转到借阅记录界面
        }
    }

    private void bookQueryByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<BookInfo> list = bookService.queryByState(BookFlagE.FREE.code);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/borrow/bookBorrow.jsp").forward(request,response);
    }

    private void integralUpdate(HttpServletRequest request, HttpServletResponse response, BorrowInfo borrow){
        UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        ReaderCardInfo card = cardService.queryByName(user.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date borrowTime = sdf.parse(borrow.getBorrowTime());
            Date backTime = sdf.parse(borrow.getBackTime());
            Integer readTime = card.getReadTime();
            System.out.println("最长借阅时间："+readTime+"天");
            //计算已借阅的时间
            long d = backTime.getTime() - borrowTime.getTime();
            long day = d/(24*60*60*1000);
            System.out.println("已借阅的时间："+day+"天");
            if (day > readTime){
                //如果已借阅的时间大于最长借阅时间：积分-100
                card.setIntegral(card.getIntegral()-100);
                cardService.updateIntegral(card);
            } else if (day >= 3) {
                //如果已借阅的时间大于等于三天且小于最长借阅时间：积分+50
                card.setIntegral(card.getIntegral()+50);
                cardService.updateIntegral(card);
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
