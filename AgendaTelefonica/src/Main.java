import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.TelefonoDAO;
import ec.edu.ups.dao.UsuarioDAO;
import ec.edu.ups.modelo.Telefono;
import ec.edu.ups.modelo.Usuario;

public class Main {
	public static void main(String[] args) {
		UsuarioDAO usuarioDao = DAOFactory.getFactory().getUsuarioDAO();
		TelefonoDAO telefonoDao = DAOFactory.getFactory().getTelefonoDAO();
		
		Usuario objUsuario = new Usuario();
		objUsuario.setCedula("0101130862");
		objUsuario.setNombre("Leo");
		objUsuario.setApellido("Alvarado");
		objUsuario.setCorreo("Correo");
		objUsuario.setPassword("pass");
		System.out.println(objUsuario + "++" + usuarioDao);
		
		// Create
		usuarioDao.create(objUsuario);
		// Read
		System.out.println(usuarioDao.read("0101130862").toString());
		// Update
		objUsuario.setNombre("Celso Leonardo");
		objUsuario.setApellido("Alvarado Torres");
		usuarioDao.update(objUsuario);
		// FIND
		System.out.println(usuarioDao.find());
		// Listar Telefonos
		System.out.println(usuarioDao.listarTelefonos("0101130862"));
		
		
		Telefono t = new Telefono();
		t.setCodigo(1);
		t.setNumero("0979395837");
		t.setTipo("Celular");
		t.setOperadora("Movaistar");
		t.setFk_cedula("0101130862");
		
		// Create
		telefonoDao.create(t);
		// Read
		System.out.println(telefonoDao.read(1).toString());
		// Update
		t.setNumero("0988847177");
		t.setOperadora("Oscuro");
		telefonoDao.update(t);
		// Find
		System.out.println(telefonoDao.find());
		
		// DELETE DE TELEFONO
		//telefonoDao.delete(t);
		
		
	}
}
