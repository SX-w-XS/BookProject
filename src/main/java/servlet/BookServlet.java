package servlet;

import bean.BookInfo;
import service.IBookService;
import service.impl.BookServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *书籍servlet
 */
@WebServlet(name = "bookServlet",urlPatterns = "/bookServlet")
public class BookServlet extends HttpServlet {
    private IBookService bookService = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        // 获取提交的type数据
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
            queryBookList(req, resp);
        } else if (Constant.SERVLET_TYPE_SAVE.equals(type)) {
            addBook(req, resp);
        } else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)) {
            queryById(req, resp);
        } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
            deleteBook(req,resp);
        } else if (Constant.SERVLET_TYPE_UPDATE.equals(type)) {
            addBook(req, resp);
        } else if (Constant.SERVLET_TYPE_SEARCHBYNAME.equals(type)) {
            searchByName(req, resp);
        } else if (Constant.SERVLET_TYPE_SEARCHBYTYPE.equals(type)) {
            searchByType(req, resp);
        } else {
            queryBookList(req, resp);
        }
    }

    private void searchByType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int n = Integer.parseInt(req.getParameter("stype"));
        List<BookInfo> list = bookService.listByBookType(null,n);
        String str = req.getParameter("borrow");
        if(str.equals("borrow")){
            req.setAttribute("list",list);
            req.getRequestDispatcher("/borrow/bookBorrow.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("list",list);
            req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bname = req.getParameter("sname");
        System.out.println(bname);
        List<BookInfo> list = bookService.listByBookName(null,bname);
        System.out.println(list);
        String str = req.getParameter("borrow");
        if(str.equals("borrow")){
            req.setAttribute("list",list);
            req.getRequestDispatcher("/borrow/bookBorrow.jsp").forward(req, resp);
        }else {
            req.setAttribute("list", list);
            req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
        }
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        BookInfo book = bookService.queryById(Integer.valueOf(id));
        req.setAttribute("book",book);
        req.getRequestDispatcher("/book/bookAdd.jsp").forward(req, resp);
    }

    private void queryBookList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {// 查询所有的书籍信息
        List<BookInfo> list = bookService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/book/book.jsp").forward(req, resp);
    }

    private void addBook(HttpServletRequest req, HttpServletResponse resp) {
        // 获取客户提交的用户信息
        BookInfo book = new BookInfo();
        book.setbId(Integer.valueOf(req.getParameter("id")));
        book.setbName(req.getParameter("bookName"));
        book.setAuthor(req.getParameter("author"));
        book.setPublish(req.getParameter("publish"));
        book.setIsbn(req.getParameter("isbn"));
        book.setIntroduction(req.getParameter("introduction"));
        book.setPressmark(Integer.valueOf(req.getParameter("pressMark")));
        book.setPrice(Float.parseFloat(req.getParameter("price")));
        book.setType(Integer.valueOf(req.getParameter("bookType")));
        book.setState(0);
        String datetime = req.getParameter("puDate");//获取表单信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date renodate = sdf.parse(datetime);
            book.setPudate(renodate);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("书籍信息:"+book);
        int count = -1;
        if (book.getbId()!=null&& book.getbId()>0)
        {
            count =bookService.updateBook(book);
        }else{
            count = bookService.saveBook(book);
        }// count 是返回的影响行数 count > 0 表示操作成功。
        if(count > 0){// 表示操作成功。重新查询所有的用户信息，那么来一次重定向操作
            try {
                resp.sendRedirect("/bookServlet?type=query");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{// 操作失败
            System.out.println("添加书籍失败...");// 同时给出错误提示信息
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        System.out.println(id);
        bookService.deleteById(Integer.valueOf(id));
        resp.sendRedirect("/bookServlet");
    }
}