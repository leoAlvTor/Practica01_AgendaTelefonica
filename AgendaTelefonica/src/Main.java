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
		
		Telefono t = new Telefono();
		t.setNumero("0979395837");
		t.setTipo("Celular");
		t.setOperadora("Movaistar");
		t.setFk_cedula("0101130862");
		
		usuarioDao.create(objUsuario);
		telefonoDao.create(t);
		
		System.out.println("--> Creacion de usuario: " + usuarioDao.find());
		System.out.println("--> Creacion de telefono: " + telefonoDao.find());
		
		System.out.println("--> Buscar por id: " + usuarioDao.read(objUsuario.getCedula()));
		System.out.println("--> Listar telefonos x cedula: "+ usuarioDao.listarTelefonos(objUsuario.getCedula()));
		
		
		
	}
}
