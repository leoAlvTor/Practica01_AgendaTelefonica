package ec.edu.ups.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpRetryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Error;
import ec.edu.ups.modelo.Usuario;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
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
		registrarUsuario(request, response);
	}
	
	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
		String cedula = request.getParameter("cedula");
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		
		Usuario usuario = new Usuario(cedula, nombre, apellido, password, correo);
		UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
		
		if(usuarioDAO.create(usuario)) {
			try {
				response.sendRedirect(request.getContextPath()+"/public/Index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			Error e = new Error("No se ha podido crear el usuario.", "La cedula que ha ingresado ya existe.");
			request.setAttribute("error", e);
			String url = "/public/Register.jsp";
			try {
				getServletContext().getRequestDispatcher(url).forward(request, response);
			} catch (ServletException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
