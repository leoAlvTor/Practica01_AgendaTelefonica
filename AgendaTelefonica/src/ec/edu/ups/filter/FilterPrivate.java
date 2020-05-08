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

/**
 * Servlet Filter implementation class FilterPrivate
 */
@WebFilter(filterName="filterPrivate",
urlPatterns={"/private/*"})
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
		}

	}
	
	private void redirectIndex(ServletRequest rsqS, ServletResponse rspS) {
		HttpServletResponse rsp = (HttpServletResponse) rspS;
		HttpServletRequest rsq = (HttpServletRequest) rsqS;
		try {
			rsp.sendRedirect(rsq.getContextPath() + "/public/Index.html");
			return;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
