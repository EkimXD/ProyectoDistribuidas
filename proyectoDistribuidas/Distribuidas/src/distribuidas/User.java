package distribuidas;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class User {
    private String ip; // Puedes cambiar a localhost
    private int puerto = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
    private Registry registry;
    private Interfaz interfaz;

    public User(String ip) {
        this.ip = ip;
    }
    
    public void iniciarConexion() throws RemoteException, NotBoundException{
        registry = LocateRegistry.getRegistry(ip, puerto);
        interfaz = (Interfaz) registry.lookup("Servidor"); //Buscar en el registro...
    }
    
    public float [] consultarGPS(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(1,cantidad);        
        return parse(line);
    }
    
    public float [] consultarNinercial(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(2,cantidad);        
        return parse(line);
    }
    
    public float [] consultarRadioAltimetro(int cantidad) throws RemoteException{
        String line=interfaz.solicitarPaquete(3,cantidad);        
        return parse(line);
    }
    
    private float [] parse(String paquete){
        String token[]=paquete.split(";");
        float aux []=new float [token.length];
        for(int i=0;i<aux.length;i++){
            aux[i]+=Float.parseFloat(token[i]);
        }
        return aux;
    }
    
    public void imprimir(float [] valores){
        String line="";
        for(int i=0;i<valores.length;i++){
            line+=valores[i]+" ";
        }
        System.out.println(line);
    }
}
