package servlet;

import bean.UserInfo;
import service.IUserService;
import service.impl.UserServiceImpl;
import utils.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录servlet
 */
@WebServlet(name = "loginServlet", urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    IUserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        //设置编码方式
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type != null && !"".equals(type)){
            if("signup".equals(type)){
                //注册用户
                userSignUp(request,response);
            }else if(Constant.SERVLET_TYPE_UPDATE.equals(type)){
                passwordUpdate(request,response);//忘记密码？修改密码
            }else if("check".equals(type)){
                //检验用户是否存在
                String userName = request.getParameter("userName");
                String s = userService.checkUserName(userName);
                response.getWriter().println(s);
                response.flushBuffer();
            }
        }else{
            loginCheck(request, response);//登录信息检验
        }
    }

    private void loginCheck(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        HttpSession session = request.getSession();
        int flag = 1;
        //获取表单提交的用户名和密码
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        //调用方法实现登录处理
        UserInfo user = userService.checkLoginInfo(userName,password);
        if(user != null){
            //登录成功
            user.setPassword(null);//不能把密码储存在session中
            //把登录的用户信息储存起来
            session.setAttribute(Constant.SESSION_LOGIN_USER,user);
            response.sendRedirect("/main.jsp");
        }else {
            //登录失败
            flag = 0;
            session.setAttribute("massage","用户名或者密码输入错误，请重试！");
            response.sendRedirect("/login/login.jsp?loginFlag="+flag);
        }
    }

    private void userSignUp(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        HttpSession session = request.getSession();
        //获取客户端提交的用户信息
        UserInfo user = new UserInfo();
        user.setUserName(request.getParameter("userName"));
        user.setUserSex(request.getParameter("sex"));
        user.setBirthday(request.getParameter("birthday"));
        user.setIdentify(request.getParameter("identify"));
        user.setTel(request.getParameter("tel"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        Integer count = userService.userRegister(user);
        if(count > 0) {
            //注册成功，返回登录页面
            session.setAttribute("massage", "注册成功！请登录");//返回显示成功提示
            response.sendRedirect("/login/login.jsp");
        }else{
            //注册失败，返回注册页面
            System.out.println("添加用户失败...");//后台测试显示失败信息
            response.sendRedirect("/login/userRegister.jsp");
            session.setAttribute("massage1","注册失败，请重试！");
        }
    }

    private void passwordUpdate(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        HttpSession session = request.getSession();
        //获取客户端提交的用户信息
        UserInfo user = new UserInfo();
        user.setUserName(request.getParameter("userName"));
        user.setIdentify(request.getParameter("identify"));
        user.setPassword(request.getParameter("password"));
        String re_password  = request.getParameter("re_password");
        if((user.getPassword().equals(re_password))) {
            //调用方法完成密码修改
            Integer count = -1;
            count = userService.passwordUpdate(user);
            if (count > 0) {
                //修改密码成功，返回登录页面
                session.setAttribute("massage", "修改密码成功，请登录！");//返回显示成功提示
                response.sendRedirect("/login/login.jsp");
            } else {
                //修改密码失败，返回修改密码界面
                System.out.println("修改密码失败...");//后台测试显示失败信息
                session.setAttribute("massage2", "用户名或者身份证号输入错误，请重试！");
                response.sendRedirect("/login/passwordUpdate.jsp");
            }
        }else {
            //由于确认密码检验失败，返回修改密码页面
            System.out.println("两次密码输入不一致，修改失败...");//后台测试显示失败信息
            session.setAttribute("massage2" ,"两次输入密码不一致，请重试！");//返回显示失败提示
            response.sendRedirect("/login/passwordUpdate.jsp");
        }
    }
}
