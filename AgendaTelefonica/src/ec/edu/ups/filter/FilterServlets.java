package ec.edu.ups.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "FilterServlets", urlPatterns = {"/ServletBusquedas", "/ServletCabacera", "/ServletCrtUpt", "/ServletDelete", "/ServletIMG"})
public class FilterServlets implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        System.out.println("LOADED"+(((HttpServletRequest) request).getSession((false))));
        if(((HttpServletRequest) request).getSession(false) != null){
            if(((HttpServletRequest) request).getSession(false).getAttribute("logeado") != null) {
                if(((Boolean) ((HttpServletRequest) request).getSession(false).getAttribute("logeado")) && (((HttpServletRequest) request).getSession(false).getAttribute("usuario")) != null) {
                    chain.doFilter(request, response);
                }else{
                    redirectIndex(request, response);
                }
            }else {
                redirectIndex(request, response);
            }
        }else{
            redirectIndex(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

    private void redirectIndex(ServletRequest rsqS, ServletResponse rspS) {
        HttpServletResponse rsp = (HttpServletResponse) rspS;
        HttpServletRequest rsq = (HttpServletRequest) rsqS;
        try {
            rsp.sendRedirect(rsq.getContextPath() + "/public/Index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
