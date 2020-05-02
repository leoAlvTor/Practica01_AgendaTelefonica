package ec.edu.ups.dao;

import ec.edu.ups.mysql.jdbc.JDBCTelefonoDAO;
import ec.edu.ups.mysql.jdbc.JDBCUsuarioDAO;

public class JDBCDAOFactory extends DAOFactory{

	@Override
	public void createTables() {
		this.getTelefonoDAO().createTable();
		this.getUsuarioDAO().createTable();
	}
	

	@Override
	public TelefonoDAO getTelefonoDAO() {
		return new JDBCTelefonoDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new JDBCUsuarioDAO();
	}
	
	
	
}
