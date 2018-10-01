import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ModeloImpl implements Modelo{

	private Vista Vista;
	
	public void setVista(Vista Vista){
		this.Vista = Vista;
		
	}
	
	private Connection conexion;
	private String fichero = "";
	
	private ResultSet rset;
	private ResultSet rseto;
	
	public void coneccion() throws IOException, SQLException {
		String base = "";
		String usuario = "";
		String pass = "";

		try {
			Properties propiedades = new Properties();
			InputStream entrada = null;
			try {
				File miFichero = new File("src/inicio");
				if (miFichero.exists()) {
					entrada = new FileInputStream(miFichero);

					propiedades.load(entrada);

					base = (propiedades.getProperty("url"));
					usuario = (propiedades.getProperty("user"));
					pass = (propiedades.getProperty("pass"));
					fichero = (propiedades.getProperty("fichero"));
					File fi = new File(fichero);
					Vista.setFichero(fichero);
					if (!fi.exists()) {
						System.out.println("Fichero no encontrado bro");
						return;
					}
				} else
					System.err.println("Fichero no encontrado");
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (entrada != null) {
					try {
						entrada.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = base;
			conexion = DriverManager.getConnection(url, usuario, pass);
			if (conexion != null)
				System.out.println("Conexión establecida");
			Statement stmt = conexion.createStatement();
			rset = stmt.executeQuery("Select * from jugadores");
			ResultSetMetaData rsmd = (ResultSetMetaData) rset.getMetaData();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No he cargado el driver");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Otros errores");
			e.printStackTrace();
		}
		Vista.esc();
	}
	
	public void perderbd(String archivo) throws SQLException, IOException {

		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		int contadore = 0;
		PreparedStatement stmt = null;
		while ((cadena = b.readLine()) != (null)) {

			if (!cadena.equals("")) {
				String pick[] = cadena.split("=");

				if (contadore == 0) {
					stmt = conexion.prepareStatement(
							"INSERT INTO `jugadores`(`id`, `nombre`, `clase`, `lvl`) VALUES (?,?,?,?)");
					System.out.println(pick[1]);
					stmt.setString(1, pick[1]);
				} else if (contadore < 3) {
					System.out.println(pick[1]);
					stmt.setString(contadore + 1, pick[1]);
				} else {
					System.out.println(pick[1]);
					stmt.setString(4, pick[1]);
					stmt.executeUpdate();
					contadore = -1;
				}
				contadore++;
			}
		}
		b.close();

	}
	public void destruir() throws SQLException {
		PreparedStatement stmt = conexion.prepareStatement("DELETE FROM jugadores WHERE 'true'='true'");
		stmt.executeUpdate();
	}
	
	public void escribirBd(String pako[]) throws SQLException {

		PreparedStatement stmt = conexion
				.prepareStatement("INSERT INTO `jugadores`(`nombre`, `clase`, `lvl`) VALUES (?,?,?)");

		stmt.setString(1, pako[0]);
		stmt.setString(2, pako[1]);
		stmt.setString(3, pako[2]);

		stmt.executeUpdate();
	}
	
	public String[][] leerbase() throws SQLException {

		Statement stmt = conexion.createStatement();
		rseto = stmt.executeQuery("SELECT count(id) FROM jugadores");
		int row = 0;
		if (rseto.next()) {
			row = Integer.parseInt(rseto.getString(1));

		}

		ResultSetMetaData rsmd = (ResultSetMetaData) rset.getMetaData();
		int columnsNumber = rsmd.getColumnCount();

		String[][] arry = new String[columnsNumber][row];
		int y = 0;
		while (rset.next()) {

			int k = 0;

			for (int i = 1; i <= columnsNumber; i++) {

				arry[k][y] = (rset.getString(i));
				k++;
			}
			y++;

		}
		return arry;
	}

}
