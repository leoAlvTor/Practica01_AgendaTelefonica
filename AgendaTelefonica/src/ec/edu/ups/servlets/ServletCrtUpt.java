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
import ec.edu.ups.modelo.Error;
import ec.edu.ups.modelo.Telefono;

/**
 * Servlet implementation class ServletCrtUpt
 */
@WebServlet(urlPatterns = "/ServletCrtUpt")
public class ServletCrtUpt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;

	public ServletCrtUpt() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;

		if (request.getParameter("btn").equals("crear"))
			create();
		else if (request.getParameter("btn").equals("actualizar"))
			update();
	}

	private void create() throws ServletException, IOException {
		TelefonoDAO tlfDao = DAOFactory.getFactory().getTelefonoDAO();
		Telefono t = getTelefonoParams();
		if (t != null) {
			if (tlfDao.create(getTelefonoParams()))
				try {
					request.setAttribute("error", null);
					response.sendRedirect(request.getContextPath() + "/private/Servicios.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					request.setAttribute("error", new ec.edu.ups.modelo.Error("No se ha podido crear el telefono", ""));
					request.getRequestDispatcher(request.getContextPath()+"/private/Servicios.jsp").forward(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

	private Telefono getTelefonoParams() throws ServletException, IOException {
		String correo = request.getParameter("correo");
		System.out.println("CORREO" + correo);
		UsuarioDAO usuDao = DAOFactory.getFactory().getUsuarioDAO();
		String cedula = usuDao.getCedula(correo);
		System.out.println("CEDULA: "  + cedula);
		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");
		String operadora = request.getParameter("operadora");
		if (numero.length() == 9 || numero.length() == 10) {
			return new Telefono(0, numero, tipo, operadora, cedula);
		} else {
			request.setAttribute("error",
					new Error("El numero telefonico debe tener 9 digitos (convencional) o 10 digitos (celular).",
							"Ingreso una cantidad de digitos invalida."));
			request.getRequestDispatcher(request.getContextPath()+"/private/Servicios.jsp").forward(request, response);
			return null;
		}

	}

	private void update() throws IOException, ServletException {
		TelefonoDAO tlfDao = DAOFactory.getFactory().getTelefonoDAO();
		Telefono t = getTelefonoParams();
		String tel_codigo = request.getParameter("tel_codigo");
		if(t!=null && !tel_codigo.equals("")) {
			t.setCodigo(Integer.parseInt(tel_codigo));
			if (tlfDao.update(t)) {
				response.sendRedirect(request.getContextPath()+"/private/Servicios.jsp");
				return;
			} else {
				request.setAttribute("error", new ec.edu.ups.modelo.Error("No se ha podido crear el telefono", ""));
				request.getRequestDispatcher(request.getContextPath()+"/private/Servicios.jsp").forward(request, response);
			}
		}else {
			request.setAttribute("error", new ec.edu.ups.modelo.Error("No se ha podido actualizar el telefono.", "No ha seleccionado un registro para editar!"));
			request.getRequestDispatcher(request.getContextPath()+"/private/Servicios.jsp").forward(request, response);
		}

	}

}
