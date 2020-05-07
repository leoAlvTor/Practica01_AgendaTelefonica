package ec.edu.ups.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
		String numero = request.getParameter("bsc_numero");
		if(request.getParameter("btn")  != null) {
			if(request.getParameter("btn").equals("listar_numeros"))
				listarMisNumeros();
		}else if(numero != null) {
			buscarNumero(numero);
		}
	}
	
	private void buscarNumero(String numero) {
		Object[] objs = new Object[2];
		Telefono[] tlfs = new Telefono[1];
		TelefonoDAO t = DAOFactory.getFactory().getTelefonoDAO();
		String correo = String.valueOf(request.getSession(false).getAttribute("usuario"));
		UsuarioDAO usuDao = DAOFactory.getFactory().getUsuarioDAO();
		
		Telefono tlf = t.buscarTelefonoNumCorreo(numero, usuDao.getCedula(correo));
		if(tlf != null) {
			objs[0] = true;
		}else {
			request.setAttribute("error", new Error("No se ha encontrado ningun numero.", "El numero buscado no esta registrado a su nombre."));
			objs[0] = false;
		}
		tlfs[0] = tlf;
		objs[1] = tlfs;

		try {
			request.setAttribute("lst_telefonos", objs);
			getServletContext().getRequestDispatcher("/private/Servicios.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listarMisNumeros() {
		Object[] objs = new Object[2];
		objs[0] = true;
		String correo = String.valueOf(request.getSession(false).getAttribute("usuario"));
		UsuarioDAO usuDao = DAOFactory.getFactory().getUsuarioDAO();
		List<Telefono> lstTelefonos = new ArrayList<>(usuDao.listarTelefonosCorreo(correo));
		objs[1] = lstTelefonos;
		
		if(lstTelefonos.size() == 0) {
			request.setAttribute("error", new Error("No se ha encontrar ningun registro.", "No tiene registrado ningun numero telefonico para mostrar."));
		}
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
