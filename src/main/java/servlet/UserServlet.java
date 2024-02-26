package servlet;

import bean.UserInfo;
import service.IUserService;
import service.impl.UserServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 用户servlet
 */
@WebServlet(name ="userServlet",urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    private IUserService userService=new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String type=req.getParameter(Constant.REQUEST_PARAMETER_TYPE);
        if(type !=null && !"".equals(type)){
            if (Constant.SERVLET_TYPE_SAVE.equals(type)){
                //添加用户
                saveUser(req, resp);
            } else if (Constant.SERVLET_TYPE_SEARCH.equals(type)) {
                //查询用户
                String keyword=req.getParameter("keyWord");
                String like_keyword="%"+keyword+"%";
                List<UserInfo> list=userService.searchUserByName(like_keyword);
                req.setAttribute("list",list);
                req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
            } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
                //删除用户
                deleteUserByName(req,resp);
            } else if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
                queryUser(req,resp);
            }else if(Constant.SERVLET_TYPE_SElF.equals(type)){
                String userName = req.getParameter("userName");
                String id = req.getParameter("id");
                UserInfo user = userService.queryById(Integer.parseInt(id));
                req.setAttribute("user",user);
                List<String> list=userService.getRecommendBook(userName);
                List<String> list1=new ArrayList<>();
                List<Integer>  a=new ArrayList<>();
                Random r=new Random();

                int n=r.nextInt(list.size())+0;
                if(list.size()>=6){
                for(int i=0;i<=5;i++){
                    a.add(n);
                    n=r.nextInt(list.size())+0;
                    for(int j=1;j<=a.size();j++){
                        while(a.get(j-1)==n){
                            n=r.nextInt(list.size())+0;
                            j=1;
                        }
                    }
                    list1.add(list.get(n));
                }}
                else {
                    for(int i=0;i<=5;i++){
                        a.add(n);
                        n=r.nextInt(list.size())+0;
                        for(int j=1;j<=a.size();j++){
                            while(a.get(j-1)==n){
                                n=r.nextInt(list.size())+0;
                                j=1;
                            }
                        }
                        list1.add(list.get(n));
                    }
                }
                System.out.println(list);
                System.out.println(list1);
                req.setAttribute("list1",list1);
                req.getRequestDispatcher("/user/userSelf.jsp").forward(req,resp);
            }else if (Constant.REQUEST_PARAMETER_QUERYBYID.equals(type)) {
                String id = req.getParameter("id");
                UserInfo user = userService.queryById(Integer.parseInt(id));
                req.setAttribute("user",user);
                req.getRequestDispatcher("/user/userUpdate.jsp").forward(req,resp);
            } else if (Constant.SERVLET_TYPE_CHECK.equals(type)){
                String userName = req.getParameter("userName");
                String s = userService.checkUserName(userName);
                resp.getWriter().println(s);
                resp.flushBuffer();
            } else if (Constant.SERVLET_TYPE_PAGE.equals(type)) {
                String username=req.getParameter("userName");
                List<Integer> b1 = new ArrayList<>();
                //x轴
                if (username.equals("Admin1")||username.equals("Admin2")){
                b1=userService.countTime(null);}
                else {
                    b1=userService.userTime(username);
                }
                //y轴
                ArrayList<String> a=new ArrayList<>();
                ArrayList<String> b=new ArrayList<>();
                LocalDateTime localDateTime = LocalDateTime.now();
                System.out.println(localDateTime);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY");
                DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM");
                Integer mtime=Integer.valueOf(dateTimeFormatter1.format(localDateTime));
                Integer ytime=Integer.valueOf(dateTimeFormatter.format(localDateTime));
                for (int i=0;i<6;i++){
                    String time=ytime+"-"+mtime;
                    a.add(time);
                    System.out.println(time);
                    if(mtime>1){
                        mtime=mtime-1;
                    }
                    else {
                        ytime=ytime-1;
                        mtime=12;
                    }
                }
                System.out.println(a);
                for (int i=5;i>=0;i--){
                    b.add(a.get(i));
                }
                System.out.println(b);
                PrintWriter out = resp.getWriter();	//获取输出流对象
                com.google.gson.JsonArray aa=new com.google.gson.JsonArray();
                com.google.gson.JsonArray bb=new com.google.gson.JsonArray();
                for(int i=0;i<=5;i++){
                    aa.add(b.get(i));
                }
                System.out.println(aa);
                for (int i=0;i<=5;i++){
                    bb.add(b1.get(i));
                }
                String json = "{\"echatX\":"+ aa+",\"echatY\":"+ bb +"}";
                if(!json.equals("")) {
                    out.println(json.substring(0, json.length()));	//去除最后一个逗号
                }

                out.flush();
                out.close();
            }
        }
        else {
            //查看
            queryUser(req,resp);
        }

    }

    private void deleteUserByName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        Integer count = userService.deleteUserByName(name);
        resp.sendRedirect("/pageServlet");
    }
    private void saveUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserInfo user =new UserInfo();
        user.setUserName(req.getParameter("userName"));
        user.setPassword(req.getParameter("password"));
        user.setTel(req.getParameter("phoneNum"));
        user.setIntegral(Integer.parseInt(req.getParameter("bookInt")));
        user.setUserSex(req.getParameter("userSex"));
        user.setIdentify(req.getParameter("idNumber"));
        user.setEmail(req.getParameter("email"));
        String datetime = req.getParameter("birth");//获取表单信息
        user.setBirthday(datetime);
        String id=req.getParameter("id");
        if (id == null){
            user.setId(null);
        }else{
            user.setId(Integer.parseInt(id));
        }
        user.setIsDeleted(1);
        Integer count = -1;
        if (user.getId() != null && user.getId() >0){
            count = userService.updateUser(user);
        }else {
            count = userService.saveUser(user,user.getIntegral());
        }
        if(count > 0){
            resp.sendRedirect("/pageServlet");
        }else {
            System.out.println("操作失败...");
            resp.sendRedirect("/pageServlet");
        }
    }
    private void queryUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserInfo> list = userService.getUser(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/user/user.jsp").forward(req, resp);
    }
    private Integer getCurrentPage(String  currentPage){
        if(currentPage==null){
            currentPage="1";
        }
        return  Integer.valueOf(currentPage);
    }
}
