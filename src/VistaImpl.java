import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class VistaImpl implements Vista{

	private Controlador Controlador;
	private Modelo Modelo;
	private String fichero;
	
	public void setControlador(Controlador Controlador){
		this.Controlador = Controlador;
	}
	public void setModelo(Modelo Modelo){
		this.Modelo = Modelo;
	}
	public void setFichero(String fichero){
		this.fichero = fichero;
		Controlador.setFichero(fichero);
	}
	
	private Scanner sc = new Scanner(System.in);
	
	public void esc() throws IOException, SQLException {
		int ever = 0;
		int pako = 0;
		while (ever == 0) {
			pako = 0;
			System.out.println("Pulsa intro para continuar");
			sc.nextLine();
			System.out.println("1.Leer");
			System.out.println("2.Escribir");
			System.out.println("3.Leer Base de Datos");
			System.out.println("4.Escribir Base de Datos");
			System.out.println("5.Sobreescribir archivos locales (Borrara el local)");
			System.out.println("6.Sobreescribir base de datos (Borra la base de datos)");
			System.out.println("7.Seleccionar uno del fichero (busca por id)");
			System.out.println("0.Salir");
			String pre = sc.nextLine();
			int num = 9999992;
			try {
				num = Integer.parseInt(pre);
			} catch (NumberFormatException ex) {
				System.out.println("Dato incorrecto bro");
			}
			String confi = "";
			if (num == 1) {
				Controlador.muestraContenido(fichero);
			} else if (num == 2) {
				Controlador.escribi(escribir());
			} else if (num == 3) {
				Controlador.imprimir(Modelo.leerbase());
			} else if (num == 4) {
				Modelo.escribirBd(escribir());
			} else if (num == 5) {
				System.out.println("Esta seguro? Y/N");
				while (pako == 0) {
					confi = sc.nextLine();
					if (confi.equals("Y")) {
						Controlador.perderlocal(Modelo.leerbase());
						pako++;
					} else if (confi.equals("N")) {
						pako++;
					} else {
						System.out.println("Orden no valida");
					}
				}

			} else if (num == 6) {
				System.out.println("Esta seguro? Y/N");
				while (pako == 0) {
					confi = sc.nextLine();
					if (confi.equals("Y")) {
						Modelo.destruir();
						Modelo.perderbd(fichero);
						pako++;
					} else if (confi.equals("N")) {
						pako++;
					} else {
						System.out.println("Orden no valida");
					}
				}
			} else if (num == 7) {

				int pakou = 9999;
				int eververso = 0;
				while (eververso == 0) {
					System.out.println("1.Leer seleccionado");
					System.out.println("2.Borrar seleccionado");
					System.out.println("3.Volver");

					String po = sc.nextLine();
					try {
						pakou = Integer.parseInt(po);
						eververso++;
					} catch (NumberFormatException ex) {
						System.out.println("Dato incorrecto bro");
					}
				}
				int a = 0;
				if (pakou == 1) {
					while (a == 0) {
						System.out.println("Seleccione la id");
						try {
							int selec = Integer.parseInt(sc.nextLine());
							Controlador.buscarlocal(fichero, selec);
							a = 1;
						} catch (NumberFormatException ex) {
							System.out.println("Dato Incorrecto Bro");
						}
					}
				} else if (pakou == 2) {
					System.out.println("Seleccione la id");
					try {
						int selec = Integer.parseInt(sc.nextLine());
						Controlador.borrarbuscarlocal(fichero, selec);
						a = 1;
					} catch (NumberFormatException ex) {
						System.out.println("Dato Incorrecto Bro");
					}
				} else if (pakou == 0) {
					
				}
				


			} else if (num == 0) {
				ever++;
				System.out.println("Pase un buen dia ᕕ(✿◕‿◕)ᕗ");
			}
		}
	}
	public String[] escribir() throws IOException {
		System.out.println(" ");
		String pako[] = new String[4];
		System.out.print("id=");
		pako[0] = sc.nextLine();
		System.out.print("nombre=");
		pako[1] = sc.nextLine();
		System.out.print("clase=");
		pako[2] = sc.nextLine();
		System.out.print("lvl=");
		pako[3] = sc.nextLine();

		return pako;
	}
}
