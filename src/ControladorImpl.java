import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ControladorImpl implements Controlador {

	private Modelo Modelo;
	private Vista Vista;
	private String fichero;

	public void setModelo(Modelo Modelo) {
		this.Modelo = Modelo;
	}

	public void setVista(Vista Vista) {
		this.Vista = Vista;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public void borrarbuscarlocal(String archivo, int selec) throws IOException {
		String cadena;
		ArrayList<String> arrlist = new ArrayList<String>();
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);

		while ((cadena = b.readLine()) != null) {
			arrlist.add(cadena);
		}
		b.close();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));
		int o = 0;
		int contador = 0;
		while ((cadena = arrlist) != null) {
			String[] pako = cadena.split("=");
			if (pako[0].equals("id") && pako[1].equals(String.valueOf(selec))) {
				o = 5;
			}
			if (o > 0) {
				System.out.println(cadena);

				o--;
			}

			else {
				if (contador == 0) {
					writer.append("id=" + cadena + "\n");
				} else if (contador == 1) {
					writer.append("nombre=" + cadena + "\n");
				} else if (contador == 2) {
					writer.append("clase=" + cadena + "\n");
				} else if (contador == 3) {
					writer.append("lvl=" + cadena);
				} else if (contador == 4) {
					writer.append("\n");
				} else if (contador == 5) {
					writer.append("\n");
					contador = 0;
				}

			}
			contador++;
		}
		writer.close();
		b.close();
	}

	public void buscarlocal(String archivo, int selec) throws IOException {
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		int o = 0;
		while ((cadena = b.readLine()) != null) {
			String[] pako = cadena.split("=");
			if (pako[0].equals("id") && pako[1].equals(String.valueOf(selec))) {
				o = 4;
			}
			while (o > 0) {

				System.out.println(cadena);
				cadena = b.readLine();
				o--;
			}
		}
		b.close();
	}

	public void perderlocal(String[][] strings) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fichero));

		for (int i = 0; i < strings[0].length; i++) {

			writer.append("id=" + strings[0][i] + "\n");
			writer.append("nombre=" + strings[1][i] + "\n");
			writer.append("clase=" + strings[2][i] + "\n");
			writer.append("lvl=" + strings[3][i]);
			writer.append("\n\n");

		}
		writer.close();

	}

	public void imprimir(String[][] strings) {

		for (int k = 0; k < strings[0].length - 1; k++) {
			for (int i = 0; i < strings.length; i++) {
				System.out.print(strings[i][k] + " ");
			}
			System.out.println("");
		}

	}

	public void escribi(String pako[]) throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter(fichero, true));

		writer.append("\n" + "id=" + pako[0] + "\n");
		writer.append("\n" + "nombre=" + pako[1] + "\n");
		writer.append("clase=" + pako[2] + "\n");
		writer.append("lvl=" + pako[3]);

		writer.close();
	}

	public void muestraContenido(String archivo) throws FileNotFoundException, IOException {
		String cadena;
		FileReader f = new FileReader(archivo);
		BufferedReader b = new BufferedReader(f);
		int pk = 0;
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
			if (pk == 4) {
				System.out.println();
				pk = -1;
			}
			pk++;
		}
		b.close();
	}

}
