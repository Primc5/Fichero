import java.io.IOException;
import java.sql.SQLException;

public class Leer {

	public static void main(String[] args) throws IOException, SQLException {
		Controlador Controlador = new ControladorImpl();
		Modelo Modelo = new ModeloImpl();
		Vista Vista = new VistaImpl();
		
		Controlador.setModelo(Modelo);
		Modelo.setVista(Vista);
		Vista.setModelo(Modelo);
		Vista.setControlador(Controlador);
		Controlador.setVista(Vista);
		
		Modelo.coneccion();
	}

	


	

	

	

}