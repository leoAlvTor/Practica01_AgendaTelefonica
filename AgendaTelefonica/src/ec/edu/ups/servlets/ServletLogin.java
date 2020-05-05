package ec.edu.ups.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(login(request)) {
			if(crearSesion(request)) {
				System.out.println("REDIRECT");
				response.sendRedirect(request.getContextPath() + "/private/Servicios.jsp");
			}
		}
	}
	
	private boolean login(HttpServletRequest request) {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		
		UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
		if(usuarioDAO.logInUsuario(usuario, password)) {
			return true;
		}else {
			return false;
		}
	}
	
	private boolean crearSesion(HttpServletRequest request) {
		HttpSession sesion = null;
		sesion = request.getSession(true);
		sesion.setAttribute("logeado", true);
		return true;
	}

}
