import distribuidas.Interfaz;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Cliente {
	private static final String IP = "localhost"; // Puedes cambiar a localhost
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	private static Registry registry;
        private static Interfaz interfaz;
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
        //----------IMPORTANTE-------------
        //Aqui se muestra como conectar la interfaz con el resto del sistema
        iniciarConexion();//aqui se inicia la conexion, obligatorio!!!
        while(true){
            //---------------------------------------------
            //asi puedes obtener los valores en forma de array double de cada instrumento
            //consultarGPS(5)
            //consultarNinercial(5)
            //consultarRadioAltimetro(5);   
            //el 5 representa que se desea respuesta de un historico de hace 5 segundos
            imprimir(consultarGPS(5));
            imprimir2(consultarNinercial(5));
            imprimir(consultarRadioAltimetro(5));            
            Thread.sleep(5000);
            System.out.println();
        }
    }
    
    public static void iniciarConexion() throws RemoteException, NotBoundException{
        registry = LocateRegistry.getRegistry(IP, PUERTO);
        interfaz = (Interfaz) registry.lookup("Servidor"); //Buscar en el registro...
    }
    
    public static double [] consultarGPS(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(1,cantidad);        
        return parse(line);
    }
    
    public static Object [] consultarNinercial(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(2,cantidad);        
        return intPrimeros(parse(line), 3);
    }
    
    public static double [] consultarRadioAltimetro(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(3,cantidad);        
        return parse(line);
    }
    
    private static double[] parse(String paquete){
        String token[]=paquete.split(";");
        double aux []=new double[token.length];
        for(int i=0;i<aux.length;i++){
            aux[i]+=Double.parseDouble(token[i]);
        }
        return aux;
    }
    
    private static Object [] intPrimeros(double [] valores, int cant){
        Object objetos []=new Object[valores.length];
        for(int i=0;i<valores.length;i++){
            if(cant>i){
                objetos[i]=(int)(valores[i]);
            }else{
                objetos[i]=valores[i];
            }
        }
        return objetos;
    }
    
    private static void imprimir(double [] valores){
        String line="";
        for(int i=0;i<valores.length;i++){
            line+=valores[i]+" ";
        }
        System.out.println(line);
    }
    
    private static void imprimir2(Object [] valores){
        String line="";
        for(int i=0;i<valores.length;i++){
            line+=valores[i]+" ";
        }
        System.out.println(line);
    }
}
