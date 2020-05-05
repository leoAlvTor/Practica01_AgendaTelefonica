package ec.edu.ups.dao;

import java.util.Set;

import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, String>{
	
	public abstract Set<Telefono> listarTelefonosCedula(String cedula);
	
	public abstract boolean logInUsuario(String usuario, String password);
	
	public abstract Set<Telefono> listarTelefonosCorreo(String correo);
	
}
