package servlet;

import bean.MessageInfo;
import service.IMessageService;
import service.impl.MessageServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布消息servlet
 */
@WebServlet(name ="MessageServlet",urlPatterns = "/messageServlet")
public class MessageServlet extends HttpServlet {
    private IMessageService messageService=new MessageServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type=req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type !=null && !"".equals(type)){
            if(Constant.SERVLET_TYPE_SAVE.equals(type)){
                MessageInfo message = new MessageInfo();
                message.setMessageTitile(req.getParameter("messageTitile"));
                message.setMessageState(Integer.parseInt(req.getParameter("messageState")));
                message.setMessageConnect(req.getParameter("messageConnect"));
                message.setMessageContent(req.getParameter("messageContent"));
                int count = -1;
                count = messageService.saveMessage(message);
                if(count>0){
                    resp.sendRedirect("/messageServlet");
                }else{
                    System.out.println("保存失败...");
                }
            } else if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
                query(req, resp);
            } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
                String id = req.getParameter("id");
                messageService.deleteMessage(Integer.parseInt(id));
                resp.sendRedirect("/messageServlet");
            }
        }
        else{
            query(req, resp);
        }
    }
    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MessageInfo> list = new ArrayList<>();
        list = messageService.GetAllMessage(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/message/message.jsp").forward(req, resp);
    }
}
