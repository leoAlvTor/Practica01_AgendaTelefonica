package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

public class JDBCUsuarioDAO extends JDBCGenericDAO<Usuario, String> implements UsuarioDAO{

	@Override
	public void createTable() {
		conexionUno.update("DROP TABLE IF EXISTS usuario");
		conexionUno.update("CREATE TABLE usuario ("
				+ "'usu_cedula' varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,"
				+ "'usu_nombre' varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,"
				+ "'usu_apellido' varchar(75) COLLATE utf8mb4_unicode_ci NOT NULL,"
				+ "'usu_correo' varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,"
				+ "'usu_contrasena' varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,"
				+ "PRIMARY KEY ('usu_cedula')"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
		DAOFactory.getFactory().getUsuarioDAO().createTable();
	}

	@Override
	public void create(Usuario usuario) {
		String insertUsuario = "INSERT usuario VALUES ("+
				usuario.getCedula() + "," +
				usuario.getNombre() + "," +
				usuario.getApellido() + "," +
				usuario.getCorreo() + "," +
				usuario.getPassword() + ")";
		System.out.println("Insert usuario -->" + insertUsuario);
		conexionUno.update(insertUsuario);
	}

	@Override
	public Usuario read(String cedula) {
		Usuario usuario = null;
		ResultSet rs = conexionUno.query("Select * from uuario where usu_cedula="+cedula);
		try {
			if(rs != null && rs.next()) {
				usuario = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
			}
		}catch(SQLException e) {
			System.out.println("Error en el metodo (JDBCUsuarioDAO: read):" + e.getMessage());
		}
		if(usuario == null) {
			return null;
		}else {
			return usuario;
		}
	}

	@Override
	public void update(Usuario usuario) {
		System.out.println("Actualizando: " + usuario);
		String updateUsuario = "UPDATE usuario set usu_nombre= '" + usuario.getNombre() + "', "
				+ "usu_apellido= '" + usuario.getApellido() + "', "
						+ "usu_correo= '" + usuario.getCorreo() + "', "
								+ "usu_contrasena= '" + usuario.getPassword() + "' "
										+ "where usu_cedula= '"+ usuario.getCedula() + "'";
		System.out.println("Update usuario -->" + updateUsuario);
		conexionUno.update(updateUsuario);
		
	}

	@Override
	public void delete(Usuario usuario) {
		// NO PIDE BORRADO :V
	}

	@Override
	public List<Usuario> find() {
		List<Usuario> list = new ArrayList<Usuario>();
		ResultSet rs = conexionUno.query("select * from usuario");
		try {
			while(rs.next()) {
				Usuario usuario = new Usuario(rs.getString(1), rs.getString(1), 
						rs.getString(1), rs.getString(1), rs.getString(1));
				list.add(usuario);
			}
			return list;
		}catch(SQLException e) {
			System.out.println("Error en el metodo");
			return null;
		}
	}

	@Override
	public Set<Telefono> listarTelefonos(String cedula) {
		Set<Telefono> telefonos = new HashSet<Telefono>();
		ResultSet rs = conexionUno.query("select * from telefonos where fk_usu_cedula = " + cedula);
		if(rs != null) {
			try {
				while(rs.next()) {
					Telefono tf = new Telefono(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(4));
					telefonos.add(tf);
				}
			}catch(SQLException e) {
				System.out.println("Error en el metodo (JDBCUsuario : ListarTelefonos): " + e.getMessage());
			}
			return telefonos;
		}else {
			return null;
		}
	}
	
	

	
}