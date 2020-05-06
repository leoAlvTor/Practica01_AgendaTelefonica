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
	
	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String cedula = request.getParameter("cedula");
		boolean flag = false;
		if(cedula.isEmpty()) {
			request.setAttribute("error", new Error("No se puede crear el registro!", "La cedula esta vacia."));
			flag = true;
		}
		String nombre = request.getParameter("nombre");
		if(nombre.isEmpty()) {
			request.setAttribute("error", new Error("No se puede crear el registro!", "El nombre esta vacio."));
			flag = true;
		}
		String apellido = request.getParameter("apellido");
		if(apellido.isEmpty()) {
			request.setAttribute("error", new Error("No se puede crear el registro!", "El apellido esta vacio"));
			flag = true;
		}
		String correo = request.getParameter("correo");
		if(correo.isEmpty()) {
			request.setAttribute("error", new Error("No se puede crear el registro!", "El correo esta vacio."));
			flag = true;
		}
		String password = request.getParameter("password");
		if(password.isEmpty()) {
			request.setAttribute("error", new Error("No se puede crear el registro!", "La contrasena no puede estar vacia!"));
			flag = true;
		}
		if(!flag) {
			Usuario usuario = new Usuario(cedula, nombre, apellido, password, correo);
			UsuarioDAO usuarioDAO = DAOFactory.getFactory().getUsuarioDAO();
			if(usuarioDAO.create(usuario)) {
				response.sendRedirect(request.getContextPath()+"/public/Index.html");
			}else {
				request.setAttribute("error", new Error("No se ha podido crear el usuario.", "La cedula que ha ingresado ya existe."));
				String url = "/public/Register.jsp";
				getServletContext().getRequestDispatcher(url).forward(request, response);
			}
		}else {
			getServletContext().getRequestDispatcher("/public/Register.jsp").forward(request, response);
		}
	}

}
