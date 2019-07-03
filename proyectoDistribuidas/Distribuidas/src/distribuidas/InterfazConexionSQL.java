package distribuidas;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazConexionSQL extends Remote {
    public void insertarPaquete(String sql) throws RemoteException;
    public String solicitarPaquete(String sql, int div) throws RemoteException;
}
