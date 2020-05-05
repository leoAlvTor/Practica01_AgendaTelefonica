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
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;

/**
 * Servlet implementation class ServletCabecera
 */
@WebServlet("/ServletCabecera")
public class ServletCabecera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private HttpServletRequest request;
    private HttpServletResponse response;
	
    public ServletCabecera() {
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
		System.out.println(request.getParameter("btn"));
		if(request.getParameter("btn").equals("listar_numeros"))
			listarMisNumeros();
		
	}
	
	private void listarMisNumeros() {
		Object[] objs = new Object[2];
		objs[0] = true;
		String correo = String.valueOf(request.getSession(false).getAttribute("usuario"));
		UsuarioDAO usuDao = DAOFactory.getFactory().getUsuarioDAO();
		List<Telefono> lstTelefonos = new ArrayList<>(usuDao.listarTelefonosCorreo(correo));
		objs[1] = lstTelefonos;
		
		try {
			request.setAttribute("lst_telefonos", objs);
			getServletContext().getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void redirect() {
	}

}
