package servlet;

import utils.Constant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 退出登录servlet：
 * 注销当前保存的登录用户信息
 */
@WebServlet(name = "logoutServlet", urlPatterns = "/logoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //注销信息退出登录
        session.setAttribute(Constant.SESSION_LOGIN_USER,null);
        session.setAttribute("massage",null);
        session.setAttribute("massage1",null);
        session.setAttribute("massage2",null);
        session.setAttribute("msg",null);
        response.sendRedirect("/login/login.jsp");
    }
}
