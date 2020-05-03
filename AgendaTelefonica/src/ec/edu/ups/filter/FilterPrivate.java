package ec.edu.ups.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FilterPrivate
 */
@WebFilter({"/FilterPrivate", "/ServletRegister"})
public class FilterPrivate implements Filter {

    /**
     * Default constructor. 
     */
    public FilterPrivate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession sesion = ((HttpServletRequest) request).getSession(false);
		System.out.println("ME LLAMARON!");
		if(sesion != null && (boolean) sesion.getAttribute("logeado")) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/public/Index.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
