package ec.edu.ups.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;

/**
 * Servlet implementation class ServletCrtUpt
 */
@WebServlet("/ServletCrtUpt")
public class ServletCrtUpt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private HttpServletRequest request;
	private HttpServletResponse response;
    
    public ServletCrtUpt() {
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
		this.request = request;
		this.response = response;
		
		if(request.getParameter("btn").equals("crear"))
			create();
		else if(request.getParameter("btn").equals("actualizar"))
			update();
	}
	
	private void create() throws ServletException {
		TelefonoDAO tlfDao = DAOFactory.getFactory().getTelefonoDAO();
		if(tlfDao.create(getTelefonoParams()))
			try {
				request.setAttribute("error", null);
				response.sendRedirect(request.getContextPath()+"/private/Servicios.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			try {
				request.setAttribute("error", new ec.edu.ups.modelo.Error("No se ha podido crear el telefono", ""));
				request.getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private Telefono getTelefonoParams() {
		String correo = request.getParameter("correo");
		UsuarioDAO usuDao = DAOFactory.getFactory().getUsuarioDAO();
		String cedula = usuDao.getCedula(correo);
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String operadora = request.getParameter("operadora");
		return new Telefono(0, numero, tipo, operadora, cedula);
	}
	
	private void update() throws IOException, ServletException {
		TelefonoDAO tlfDao = DAOFactory.getFactory().getTelefonoDAO();
		Telefono t = getTelefonoParams();
		t.setCodigo(Integer.parseInt(request.getParameter("tel_codigo")));
		if(tlfDao.update(t)) {
			response.sendRedirect("/AgendaTelefonica/private/Servicios.jsp");
			return;
		}else{
			request.setAttribute("error", new ec.edu.ups.modelo.Error("No se ha podido crear el telefono", ""));
			request.getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
		}
		
	}

}
