package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ServletBusquedas
 */
@WebServlet("/ServletBusquedas")
public class ServletBusquedas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBusquedas() {
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
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		if(request.getParameter("btn").equals("bscCorreo")) {
			buscarCorreo(request.getParameter("correo"));
		}else if(request.getParameter("btn").equals("bscCedula")) {
			buscarCedula(request.getParameter("cedula"));
		}
	}
	
	private void buscarCorreo(String correo) {
		Object[] objs = new Object[2];
		objs[0] = false;
		UsuarioDAO usuarioDao = DAOFactory.getFactory().getUsuarioDAO();
		TelefonoDAO telefonoDAO = DAOFactory.getFactory().getTelefonoDAO();
		List<Telefono> lstTelefonos = new ArrayList<>(telefonoDAO.listarTelefonosCorreo(correo));
		objs[1] = lstTelefonos;
		try {
			if(lstTelefonos.size() == 0) {
				request.setAttribute("error", new ec.edu.ups.modelo.Error("Error al obtener los telefonos por el correo ingresada.",
						"Asegurese de que haya ingresado una direccion de correo valida."));
				despacharPeticiones();
			}else {
				request.setAttribute("error", null);
				request.setAttribute("lst_telefonos", objs);
				despacharPeticiones();
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void buscarCedula(String cedula) {
		Object[] objs = new Object[2];
		objs[0] = false;
		UsuarioDAO usuarioDao = DAOFactory.getFactory().getUsuarioDAO();
		TelefonoDAO telefonoDAO = DAOFactory.getFactory().getTelefonoDAO();
		List<Telefono> lstTelefonos = new ArrayList<>(telefonoDAO.listarTelefonosCedula(cedula));
		objs[1] = lstTelefonos;
		try {
			if(lstTelefonos.size() == 0) {
				request.setAttribute("error", new ec.edu.ups.modelo.Error("Error al obtener los telefonos por la cedula ingresada.",
						"Asegurese de que haya ingresado una cedula valida."));
				despacharPeticiones();
			}else {
				request.setAttribute("error", null);
				request.setAttribute("lst_telefonos", objs);
				despacharPeticiones();
			}
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	private void despacharPeticiones() throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
	}

}
