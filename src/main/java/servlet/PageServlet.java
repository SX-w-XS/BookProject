package servlet;

import bean.PageInfo;
import bean.UserInfo;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 分页servlet
 */
@WebServlet(name="pageServlet",urlPatterns = "/pageServlet")
public class PageServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        Integer currentPageNum = getCurrentPage(currentPageStr);

        PageInfo<UserInfo> pageInfo = new PageInfo<>();
        userService.selectUserListByPage(currentPageNum,pageInfo);
        request.setAttribute("pageInfo", pageInfo);
        request.getRequestDispatcher("/user/user.jsp").forward(request,response);
    }

    private Integer getCurrentPage(String currentPagestr) {
        if (null == currentPagestr) {
            currentPagestr = "1";
        }
        return Integer.valueOf(currentPagestr);
    }
}
