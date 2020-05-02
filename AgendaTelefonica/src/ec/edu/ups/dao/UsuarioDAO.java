package ec.edu.ups.dao;

import java.util.Set;

import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario, String>{
	
	public abstract Set<Telefono> listarTelefonos(String cedula);
	
}
