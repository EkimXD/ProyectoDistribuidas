import distribuidas.Interfaz;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Random;
public class InstrumentoRadioaltímetro {
	private static final String IP = "localhost"; // Puedes cambiar a localhost
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        Interfaz interfaz = (Interfaz) registry.lookup("Servidor"); //Buscar en el registro...
        while(true){
		interfaz.registrarPaquete(3,"RadioAltimetro",genData());
		Thread.sleep(1000);
        }
    }

    public static String genData(){
	String line="";
	for(int i=0;i<9;i++){
		line+=Math.round(genAleatorio() * 100d) / 100d+";";
	}
        line+=Math.round(genAleatorio() * 100d) / 100d;
	return line;
    }
    public static double genAleatorio(){
	return (Math.random() * 100) + 1;
    }
}
