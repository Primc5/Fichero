import java.io.IOException;
import java.sql.SQLException;

public interface Vista {
	public void setControlador(Controlador Controlador);
	public void setModelo(Modelo Modelo);
	public void setFichero(String fichero);
	public void esc() throws IOException, SQLException;
	public String[] escribir() throws IOException;
}
