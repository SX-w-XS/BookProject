package Filter;

import utils.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * 拦截过滤器设置的请求，判断当前的登录状态，登录则可以访问，否则会跳转登录页面。
 */
@WebFilter(filterName = "action_filter",urlPatterns = {"/main.jsp","/index.jsp","/book/*","/borrow/*","/card/*","/commodity/*","/discuss/*" +
        "/login/*","/message/*","/user/*"})//设置过滤器属性
public class filter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //获取请求的地址
        String requestURI = req.getRequestURI();
        if(requestURI.contains("login/login.jsp") || requestURI.contains("loginServlet")){
            //开始直接访问登录页面
            chain.doFilter(request, response);
        }else{
            HttpSession session = req.getSession();
            //获取登陆者的信息
            Object attribute = session.getAttribute(Constant.SESSION_LOGIN_USER);
            if(attribute != null){
                //！null说明已经登录，可以访问
                chain.doFilter(request, response);
            }else{
                //null说明没有登录，返回登录页面
                session.setAttribute("massage", "请先登录哦");
                rep.sendRedirect("/login/login.jsp");
            }
        }
    }
}
