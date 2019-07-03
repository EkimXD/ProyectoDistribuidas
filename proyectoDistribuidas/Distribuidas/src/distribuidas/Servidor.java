package distribuidas;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Servidor {
    private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        
        Intermediario intermediario=new Intermediario();
        intermediario.iniciarConexion();
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            
            @Override
            public void registrarPaquete(int idInstrumento,String nombreInstrumento, String paquete) throws RemoteException {
                String line="INSERT INTO INSTRUMENTO VALUES ("+idInstrumento+",'"
                        +nombreInstrumento+"','"+paquete+"',CURTIME())";
                intermediario.insertarPaquete(line);
            };

            @Override
            public String solicitarPaquete(int idInstrumento, int numero2) throws RemoteException {
               String line="select * FROM INSTRUMENTO where idInstrumento= "
                        +idInstrumento+" order by horainstrumento desc limit "+numero2;
               return intermediario.solicitarPaquete(line, numero2);
            };

        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Servidor", remote);
    }   
}