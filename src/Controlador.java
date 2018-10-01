import java.io.FileNotFoundException;
import java.io.IOException;

public interface Controlador {
	public void setModelo(Modelo Modelo);
	public void setVista(Vista Vista);
	public void borrarbuscarlocal(String archivo, int selec) throws IOException;
	public void setFichero(String fichero);
	public void buscarlocal(String archivo, int selec) throws IOException;
	public void perderlocal(String[][] strings) throws IOException;
	public void imprimir(String[][] strings);
	public void escribi(String pako[]) throws IOException;
	public void muestraContenido(String archivo) throws FileNotFoundException, IOException;

}
