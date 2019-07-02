package distribuidas;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interfaz extends Remote {
    public void registrarPaquete(int idInstrumento,String nombreInstrumento, String paquete) throws RemoteException;
    public String solicitarPaquete(int idInstrumento, int numero2) throws RemoteException;
}
