package servlet;

import bean.ReaderCardInfo;
import bean.UserInfo;
import service.IReaderCardService;
import service.impl.ReaderCardServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 借书卡servlet
 */
@WebServlet(name = "readerCardServlet", urlPatterns = "/readerCardServlet")
public class ReaderCardServlet extends HttpServlet {
    IReaderCardService cardService = new ReaderCardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type != null && !"".equals(type)){
            if(Constant.SERVLET_TYPE_LIST.equals(type)) {
                //显示所有借书卡信息
                cardInfoList(request, response);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)) {
                //修改借书卡信息
                cardInfoUpdate(request,response);
            }else if(Constant.SERVLET_TYPE_DELETE.equals(type)) {
                //删除借书卡信息
                cardInfoDelete(request,response);
            }else if("queryById".equals(type)) {
                //根据借书卡ID查询单条信息
                String id = request.getParameter("id");
                //调用ID查询方法
                ReaderCardInfo card = cardService.queryByID(Integer.parseInt(id));
                //跳转到更新借书卡更新页面同时保存数据
                request.setAttribute("card", card);
                request.getRequestDispatcher("/card/cardUpdate.jsp").forward(request,response);
            }
        }
    }

    private void cardInfoDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer count = -1;
        count = cardService.deleteByID(id);
        if(count > 0){
            System.out.println("读书卡信息删除成功...");//后台测试显示成功信息
        }else {
            System.out.println("读书卡信息删除失败...");//后台测试显示失败信息
        }
        cardInfoList(request,response);
    }
    private void cardInfoUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReaderCardInfo card = new ReaderCardInfo();
        //获取提交的修改后的读书卡信息
        card.setId(Integer.valueOf(request.getParameter("id")));
        card.setCardState(Integer.valueOf(request.getParameter("state")));
        card.setReadTime(Integer.valueOf(request.getParameter("time")));
        Integer count = -1;
        //调用更新方法
        count = cardService.updateReaderCard(card);
        if(count > 0){
            System.out.println("读书卡信息修改成功...");//后台测试显示成功信息
            cardInfoList(request,response);
        }else {
            System.out.println("读书卡信息修改失败...");//后台测试显示失败信息
            response.sendRedirect("/card/cardUpdate.jsp");
        }
    }
    private void cardInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询当前登录用户的借书卡信息
        UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        if(!("Admin1".equals(user.getUserName()) || "Admin2".equals(user.getUserName()))){
            //如果登录者是普通用户，查询自己的借书卡信息
            if(!user.getUserName().equals("")) {//判断登陆者信息是否为空
                ReaderCardInfo card = cardService.queryByName(user.getUserName());
                request.setAttribute("card",card);
                request.getRequestDispatcher("/card/user_card.jsp").forward(request,response);//跳转到用户我的借书卡界面
            }
        }else {
            //如果登录者是管理员，查询所有的借书卡信息
            List<ReaderCardInfo> list = cardService.list(null);
            request.setAttribute("list",list);
            request.getRequestDispatcher("/card/card.jsp").forward(request,response);//跳转到管理员借书卡管理界面
        }
    }
}
