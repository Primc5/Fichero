import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Leer {
    
    public void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        int pk = 0;
        while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            if(pk == 2){
            	System.out.println();
            	pk = -1;
            }
            pk++;
        }
        b.close();
    }
    
    public void escribi(String pako[]) 
    		  throws IOException {
    		    
    		    BufferedWriter writer = new BufferedWriter(new FileWriter("src/wea", true));
    		    
    		    writer.append("\n" + "nombre=" + pako[0] + "\n");
    		    writer.append("clase=" + pako[1] + "\n");
    		    writer.append("lvl=" + pako[2]);
    		     
    		    writer.close();
    		}
    
    private Scanner sc = new Scanner(System.in);

    
    public String[] escribir() throws IOException{
        System.out.println(" ");
        String pako [] = new String[3];
        System.out.print("nombre=");
		pako[0] = sc.nextLine();
        System.out.print("clase=");
		pako[1] = sc.nextLine();
        System.out.print("lvl=");
		pako[2] = sc.nextLine();
		
		
		return pako;
    }
    
    
    public String[][] leerbase() throws SQLException{
    	ResultSetMetaData rsmd = (ResultSetMetaData) rset.getMetaData();
		int columnsNumber = rsmd.getColumnCount();                     

		// Iterate through the data in the result set and display it. 
		String[][] arry = new String[3][columnsNumber];
		int y = 0;
		while (rset.next()) {
		//Print one row
			int k = 0;
			
		for(int i = 2 ; i <= columnsNumber; i++){
			
		      arry[k][y] = (rset.getString(i) ); //Print one element of a row
		      k++;
		}
		y++;//Move to the next line to print the next row.           
		
		    }
		return arry;
    }
    
    public void escribirBd(String pako[]) throws SQLException{

		
		PreparedStatement stmt = conexion.prepareStatement("INSERT INTO `jugadores`(`nombre`, `clase`, `lvl`) VALUES (?,?,?)");
		 
		stmt.setString(1,pako[0]);
		stmt.setString(2,pako[1]);
		stmt.setString(3,pako[2]);
		
		stmt.executeUpdate();
    }
    
    public void imprimir (String[][] strings){
    	
    	for(int i = 0; i < strings.length; i++){
    		for(int k = 0; k < strings[1].length - 1; k++){
    			System.out.print(strings[k][i] + " ");
    		}
    		System.out.println("");
    	}
    	
    }
    
    public void perderlocal(String[][] strings) throws IOException{
    	 BufferedWriter writer = new BufferedWriter(new FileWriter("src/wea"));
    	for(int i = 0; i < strings.length; i++){
		   

		    writer.append("nombre=" + strings[0][i] + "\n");
		    writer.append("clase=" + strings[1][i] + "\n");
		    writer.append("lvl=" + strings[2][i]);
		    if(i < strings.length-1){
		    	writer.append("\n\n");
		    }
		    
    	}
    	writer.close();
    	
    }
    
    public void destruir() throws SQLException{
    	PreparedStatement stmt = conexion.prepareStatement("DELETE FROM jugadores WHERE 'true'='true'");
    	stmt.executeUpdate();
    }
    
    public void perderbd(String archivo) throws SQLException, IOException{
    	
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        int puta = 0;
        PreparedStatement stmt = null;
        while((cadena = b.readLine())!=(null)) {
        	
        	String pick [] = cadena.split("=");
        	if(puta == 0){
        		stmt = conexion.prepareStatement("INSERT INTO `jugadores`(`nombre`, `clase`, `lvl`) VALUES (?,?,?)");
        		System.out.println(pick[1]);
        		stmt.setString(1,pick[1]);
        	}
        	else if(puta == 1){
        		System.out.println(pick[1]);
        		stmt.setString(2,pick[1]);
        	}
        	else{
        		System.out.println(pick[1]);
        		stmt.setString(3,pick[1]);
        		stmt.executeUpdate();
        		puta = -1;
        	}
        	puta++;
        }
        b.close();
    	
    	

    }
    
    private Connection conexion;
    private ResultSet rset;
    public static void main(String[] args) throws IOException, SQLException{
    	
    	Leer leer = new Leer();
    	leer.coneccion();
		
    }
    
    public void coneccion() throws IOException, SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/jaimet2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			conexion = DriverManager.getConnection(url, "root", "");
			if (conexion != null)
				System.out.println("Conexión establecida");
			Statement stmt = conexion.createStatement();
			rset = stmt.executeQuery("Select * from jugadores");
			ResultSetMetaData rsmd = (ResultSetMetaData) rset.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
//			conexion.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No he cargado el driver");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Otros errores");
			e.printStackTrace();
		}
		esc();
    }
    
    public void esc() throws IOException, SQLException{
    	int ever = 0;
    	while(ever == 0){
    	System.out.println("1.Leer");
    	System.out.println("2.Escribir");
    	System.out.println("3.Leer Base de Datos");
    	System.out.println("4.Escribir Base de Datos");
    	System.out.println("5.Sobreescribir archivos locales (Borrara el local)");
    	System.out.println("6.Sobreescribir base de datos (Borra la base de datos)");
    	System.out.println("7.Salir");
    	int num = Integer.parseInt(sc.nextLine());
    	if(num == 1){
    		muestraContenido("src/wea");
    	}
    	else if(num == 2){
    		escribi(escribir());
    	}
    	else if(num == 3){
    		imprimir(leerbase());
    	}
    	else if(num == 4){
    		escribirBd(escribir());
    	}
    	else if(num == 5){
    		perderlocal(leerbase());
    	}
    	else if(num == 6){
    		destruir();
    		perderbd("src/wea");
    	}
    	else if(num == 7){
    		ever++;	
    	}
    	else{
    		System.out.println("Numero incorrecto bro");
    	}
    	}
    }
    
}