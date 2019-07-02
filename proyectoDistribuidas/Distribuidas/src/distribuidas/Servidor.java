package distribuidas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.DriverManager;
import java.sql.Statement;
import servicios.Conexion;
public class Servidor {
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el cliente
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Remote remote = UnicastRemoteObject.exportObject(new Interfaz() {
            
            @Override
            public void registrarPaquete(int idInstrumento,String nombreInstrumento, String paquete) throws RemoteException {
                ejecutarSQLinsert("INSERT INTO INSTRUMENTO VALUES ("+idInstrumento+",'"+nombreInstrumento
                        +"','"+paquete+"',CURTIME())");
            };

            @Override
            public String solicitarPaquete(int idInstrumento, int numero2) throws RemoteException {
                return ejecutarSQLselect("select * FROM INSTRUMENTO where idInstrumento= "+idInstrumento+" order by horainstrumento desc limit "+numero2,numero2);
            };
        }, 0);
        Registry registry = LocateRegistry.createRegistry(PUERTO);
       	System.out.println("Servidor escuchando en el puerto " + String.valueOf(PUERTO));
        registry.bind("Servidor", remote);
    }
    
    public static void ejecutarSQLinsert(String sql){
        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            String username = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, username, password);
            
            Statement statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static String ejecutarSQLselect(String sql, int div){//ni le hagan caso a esta parte de codigo XD
        String line="";
        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            String username = "root";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){                
                String paquete = rs.getString("dataInstrumento");
                String token[]=paquete.split(";");
                double aux []=new double[token.length];
                for(int i=0;i<aux.length;i++){
                    aux[i]+=Double.parseDouble(token[i]);
                }
                while (rs.next()) {
                    paquete = rs.getString("dataInstrumento");
                    String token2[]=paquete.split(";");
                    for(int i=0;i<aux.length;i++){
                        aux[i]+=Double.parseDouble(token2[i]);
                    }
                }
                for(int i=0;i<aux.length-1;i++){
                        line+=aux[i]/div+";";// posible error entteros
                    }
                line+=aux[aux.length-1]/div;
            }
            
            rs.close();
            statement.close();
            connection.close();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        
        return line;
    }
    
    
}