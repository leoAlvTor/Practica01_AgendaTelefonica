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
@WebFilter(filterName="filterPrivate",
urlPatterns="/private/*")
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
		if(((HttpServletRequest) request).getSession(false) != null && ((HttpServletRequest) request).getSession(false).getAttribute("logeado") != null) {
			if(((Boolean)((HttpServletRequest) request).getSession(false).getAttribute("logeado")) == true) {
				System.out.println("Sesion iniciada correctamente!");
			}
		}else {
			((HttpServletResponse)response).sendRedirect("AgendaTelefonica/public/Index.html");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
