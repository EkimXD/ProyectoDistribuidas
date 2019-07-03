package distribuidas;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Usuario {
    
    public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
        int segundosEspera=5;
        //iniciar una nueva instancia para usuarios
        User usuario=new User("192.168.2.106");// en ves de localhost va la ip del host destino
        //iniciar conexion
        usuario.iniciarConexion();
        
        //--------------NOTAS
        //usuario.imprimir(float array) imprime un arreglo
        //usuario.consultarGPS(int cantidadRegistros) retorna un arreglo float
        while(true){
            usuario.imprimir(usuario.consultarGPS(segundosEspera));
            usuario.imprimir(usuario.consultarNinercial(segundosEspera));
            usuario.imprimir(usuario.consultarRadioAltimetro(segundosEspera));
            Thread.sleep(segundosEspera*1000);
        }
        
    }
}
