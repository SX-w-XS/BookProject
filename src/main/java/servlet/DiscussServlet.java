package servlet;

import bean.DiscussInfo;
import bean.UserInfo;
import service.IDiscussService;
import service.impl.DiscussServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 评论servlet
 */
@WebServlet(name = "discussServlet", urlPatterns = "/discussServlet")
public class DiscussServlet extends HttpServlet {
    IDiscussService discussService = new DiscussServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type != null && !"".equals(type)) {
            if(Constant.SERVLET_TYPE_LIST.equals(type)){
                //显示所有评论信息
                commentList(req,resp);
            }else if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                //保存评论信息
                commentSave(req,resp);
            } else if(Constant.SERVLET_TYPE_DELETE.equals(type)){
                //管理员功能：删除评论信息
                commentDelete(req,resp);
            }
        }
    }

    private void commentDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String id = req.getParameter("id");
        Integer count = -1;
        //调用删除评论方法
        count = discussService.deleteComment(id);
        if(count > 0){
            System.out.println("评论删除成功...");//后台测试显示成功信息
        }else {
            System.out.println("评论删除失败...");//后台测试显示失败信息
        }
        System.out.println("-------------");
        commentList(req,resp);
    }

    private void commentSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        DiscussInfo discuss = new DiscussInfo();
        Integer count = -1;
        //获取系统当前时间作为发布时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Calendar.getInstance().getTime();
        //获取当前登录用户的信息
        UserInfo user = (UserInfo) req.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        discuss.setContent(req.getParameter("comment"));
        discuss.setReleaseTime(sdf.format(date));
        discuss.setName(user.getUserName());
        //调用保存评论方法
        count = discussService.saveComment(discuss);
        if(count > 0){
            System.out.println("评论发布成功...");//后台测试显示成功信息
        }else {
            System.out.println("评论发布失败...");//后台测试显示失败信息
        }
        System.out.println("-------------");
        commentList(req,resp);
    }

    private void commentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<DiscussInfo> list = discussService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/discuss/discuss.jsp").forward(req,resp);
    }
}
