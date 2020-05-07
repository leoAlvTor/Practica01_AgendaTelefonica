package ec.edu.ups.dao;

import java.util.Set;

import ec.edu.ups.modelo.Telefono;

public interface TelefonoDAO extends GenericDAO<Telefono, Integer>{
	public abstract Telefono buscarTelefonoNumCorreo(String numero, String cedula);

	public abstract Set<Telefono> listarTelefonosCorreo(String correo);

	public abstract Set<Telefono> listarTelefonosCedula(String cedula);
}
