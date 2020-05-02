package ec.edu.ups.mysql.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.modelo.Telefono;

public class JDBCTelefonoDAO extends JDBCGenericDAO<Telefono, Integer> implements TelefonoDAO{

	@Override
	public void createTable() {
		conexionUno.update("DROP TABLE IF EXISTS telefono");
		conexionUno.update("CREATE TABLE 'telefono' ("
				+ "'tel_codigo' int(11) NOT NULL AUTO_INCREMENT, "
				+ "'tel_numero' varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL, "
				+ "'tel_tipo' varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL, "
				+ "'tel_operadora' varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL, "
				+ "'fk_usu_cedula' varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL, "
				+ "PRIMARY KEY ('tel_codigo'), "
				+ "KEY 'telefono_usuario_fk' ('fk_usu_cedula'), "
				+ "CONSTRAINT 'telefono_usuario_fk' FOREIGN KEY ('fk_usu_cedula') "
				+ "REFERENCES 'usuario' ('usu_cedula')"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci");
		DAOFactory.getFactory().getTelefonoDAO().createTable();
	}

	@Override
	public void create(Telefono telefono) {
		String telefonoCreate = "INSERT telefono VALUES ("
				+ telefono.getNumero() + ", "
				+ telefono.getTipo() + ", "
				+ telefono.getOperadora() + ", "
				+ telefono.getFk_cedula() + ", "					
				+ ")";
		System.out.println("");
		conexionUno.update(telefonoCreate);
	}

	@Override
	public Telefono read(Integer id) {
		Telefono telefono = null;
		ResultSet rs = conexionUno.query("Select * from telefono where tel_codigo = " + id);
		try {
			if(rs != null && rs.next()) {
				telefono = new Telefono(rs.getInt(1), rs.getString(2), 
						rs.getString(3), rs.getString(4), rs.getString(5));
			}
		}catch(SQLException e) {
			System.out.println("Error el metodo JDBCTelefonoDAO:Read ->" + e.getMessage());
		}
		if(telefono != null)
			return telefono;
		else
			return null;
	}

	@Override
	public void update(Telefono telefono) {
		System.out.println("Actualizando: "+ telefono);
		String updateTelefono = "UPDATE telefono set "
				+ "tel_numero = '" + telefono.getNumero()+ "', "
				+ "tel_tipo = '" + telefono.getTipo()+ "', "
				+ "tel_operadora = '" + telefono.getOperadora()+ "' where tel_codigo ="
						+ telefono.getCodigo();
		System.out.println(conexionUno.update(updateTelefono));
	}

	@Override
	public void delete(Telefono telefono) {
		System.out.println(conexionUno.update("delete from telefono where tel_codigo = " + telefono.getCodigo()));
	}

	@Override
	public List<Telefono> find() {
		List<Telefono> lstTelefonos = new ArrayList<>();
		ResultSet rs = conexionUno.query("Select * from telefono");
		try {
			while(rs.next()) {
				Telefono telefono = new Telefono(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5));
				lstTelefonos.add(telefono);
			}
		}catch(SQLException e) {
			System.out.println("");
		}
		return lstTelefonos;
	}

	@Override
	public Set<Telefono> buscarPorTelefonoID(int telefonoID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
