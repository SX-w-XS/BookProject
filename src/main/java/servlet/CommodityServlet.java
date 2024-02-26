package servlet;

import bean.CommoityInfo;
import bean.UserInfo;
import service.ICommodityService;
import service.impl.CommodityServiceImpl;
import utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 积分商品servlet
 */
@WebServlet(name = "commodityServlet",urlPatterns = "/commodityServlet")
public class CommodityServlet extends HttpServlet {
    private ICommodityService commodityService = new CommodityServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取提交的type数据
        String type = req.getParameter(Constant.REQUEST_PARAMETER_TYPE);

        if (Constant.SERVLET_TYPE_QUERY.equals(type)) {
            queryCommodityList(req, resp);
        } else if (Constant.SERVLET_TYPE_SAVE.equals(type)) {
            AddCommodity(req, resp);
        } else if (Constant.SERVLET_TYPE_QUERYBYID.equals(type)) {
            queryById(req, resp);
        } else if (Constant.SERVLET_TYPE_DELETE.equals(type)) {
            DeleteCommodity(req,resp);
        } else if (Constant.SERVLET_TYPE_UPDATE.equals(type)) {
            AddCommodity(req, resp);
        } else if (Constant.SERVLET_TYPE_BUY.equals(type)) {
            try {
                Buy(req,resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            queryCommodityList(req, resp);
        }
    }

    private void Buy(HttpServletRequest req, HttpServletResponse resp)throws IOException, SQLException {
        String id = req.getParameter("id");
        UserInfo user = (UserInfo) req.getSession().getAttribute(Constant.SESSION_LOGIN_USER);
        commodityService.Buy(Integer.valueOf(id),user.getUserName());
        resp.sendRedirect("/commodityServlet");
    }

    private void DeleteCommodity(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        commodityService.deleteByid(Integer.valueOf(id));
        resp.sendRedirect("/commodityServlet");
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        CommoityInfo commodity = commodityService.querybyId(Integer.valueOf(id));
        /*System.out.println(commodity);*/
        req.setAttribute("commodity",commodity);
        req.getRequestDispatcher("/commodity/commodityAdd.jsp").forward(req, resp);
    }

    private void queryCommodityList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CommoityInfo> list = commodityService.list(null);
        req.setAttribute("list",list);
        req.getRequestDispatcher("/commodity/commodityList.jsp").forward(req, resp);
    }

    private void AddCommodity(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        CommoityInfo commodityInfo = new CommoityInfo();
        commodityInfo.setCid(Integer.valueOf(req.getParameter("id")));
        commodityInfo.setCname(req.getParameter("cname"));
        //System.out.println(commodityInfo.getCname());
        commodityInfo.setCprice(Integer.valueOf(req.getParameter("cprice")));
        commodityInfo.setCnumber(Integer.valueOf(req.getParameter("cnumber")));
        String datetime = req.getParameter("cpudate");//获取表单信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date renodate = sdf.parse(datetime);
            commodityInfo.setCpudate(renodate);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        commodityInfo.setCstate(Integer.valueOf(req.getParameter("cstates")));
        commodityInfo.setCintroduction(req.getParameter("cintroduction"));
        System.out.println("商品"+commodityInfo);
        int count = -1;
        if (commodityInfo.getCid()!=null&& commodityInfo.getCid()>0)
        {
            count = commodityService.updatecommunity(commodityInfo);
        }else{
            count = commodityService.savecommunity(commodityInfo);
        }

        if(count > 0){
            // 表示操作成功。重新查询所有的用户信息，那么来一次重定向操作
            try {
                resp.sendRedirect("/commodityServlet?type=query");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            // 操作失败
            System.out.println("添加书籍失败...");
            // 同时给出错误提示信息
        }
    }
}

