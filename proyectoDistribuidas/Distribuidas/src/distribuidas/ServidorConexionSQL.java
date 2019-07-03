package distribuidas;


import distribuidas.Conexion;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorConexionSQL {
    private static final int PUERTO = 5000; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Conexion conexion=new Conexion();
        Remote remote = UnicastRemoteObject.exportObject(new InterfazConexionSQL() {
            @Override
            public void insertarPaquete(String sql) throws RemoteException {
                conexion.ejecutarSQLinsert(sql);
            };

            @Override
            public String solicitarPaquete(String sql, int div) throws RemoteException {
                return conexion.ejecutarSQLselect(sql, div);
            };
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor SQL escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("ServidorConexionSQL", remote);
    }   
}