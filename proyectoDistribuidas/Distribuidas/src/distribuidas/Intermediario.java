package distribuidas;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Intermediario {
    private String ip="localhost"; // Puedes cambiar a localhost
    private int puerto = 5000; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
    private Registry registry;
    private InterfazConexionSQL interfaz;

    public Intermediario() {
        super();
    }
    
    public void iniciarConexion() throws RemoteException, NotBoundException{
        registry = LocateRegistry.getRegistry(ip, puerto);
        interfaz = (InterfazConexionSQL) registry.lookup("ServidorConexionSQL"); //Buscar en el registro...
    }
    
    public void insertarPaquete(String sql) throws RemoteException{
        interfaz.insertarPaquete(sql);
    }
    public String solicitarPaquete(String sql, int div) throws RemoteException{
        return interfaz.solicitarPaquete(sql, div);
    }
}
