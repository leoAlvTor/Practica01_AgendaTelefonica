package ec.edu.ups.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;

/**
 * Servlet implementation class ServletDelete
 */
@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDelete() {
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
		HttpSession sesion = request.getSession(false);
		String correo = String.valueOf(sesion.getAttribute("usuario"));
		String id = request.getParameter("imp_delete");
		delete(correo, id);
		request.getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
	}
	
	private void delete(String ...strings) {
		UsuarioDAO usuario = DAOFactory.getFactory().getUsuarioDAO();
		String cedula = usuario.getCedula(strings[0]);
		
		TelefonoDAO tlf = DAOFactory.getFactory().getTelefonoDAO();
		
		boolean rtn = tlf.delete(new Telefono(Integer.valueOf(strings[1]), "", "", "", cedula));
		
	}

}
