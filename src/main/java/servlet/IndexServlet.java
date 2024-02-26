package servlet;

import bean.AllCountInfo;
import bean.BookCountInfo;
import bean.DiscussInfo;
import bean.MessageInfo;
import service.IDiscussService;
import service.IMessageService;
import service.IndexService;
import service.impl.DiscussServiceImpl;
import service.impl.IndexServiceImpl;
import service.impl.MessageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 首页信息servlet
 */
@WebServlet(name = "indexServlet", urlPatterns = "/indexServlet")
public class IndexServlet extends HttpServlet {
    IDiscussService discussService = new DiscussServiceImpl();
    IMessageService messageService = new MessageServiceImpl();
    IndexService indexService = new IndexServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //首页显示图书种类统计
        List<BookCountInfo> list1 = null;
        try {
            list1 = indexService.list(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("count",list1);

        //首页显示部分评论
        List<DiscussInfo> list2 = discussService.partList(null);//获取最新5条评论
        req.setAttribute("comment",list2);

        //首页显示部分消息
        List<MessageInfo> list3 = messageService.GetSomeMessage(null);
        req.setAttribute("message",list3);

        //首页显示信息统计
        List<AllCountInfo> list4 = null;
        try {
            list4 = indexService.list2(null);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("list",list4);
        //重定向
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
