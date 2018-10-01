import java.io.IOException;
import java.sql.SQLException;

public interface Modelo {
	public void setVista(Vista Vista);
	public void coneccion() throws IOException, SQLException;
	public void perderbd(String archivo) throws SQLException, IOException;
	public void destruir() throws SQLException;
	public void escribirBd(String pako[]) throws SQLException;
	public String[][] leerbase() throws SQLException;
}
