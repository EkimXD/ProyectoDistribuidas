package distribuidas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 *
 * @author dekim
 */
public class Conexion {
    
    private String url;
    private String username;
    private String password; 
    private Connection connection;
    private Statement statement;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3306/mydb";
        this.username = "root";
        this.password = "root";
    }  
    
    public void ejecutarSQLinsert(String sql){
        try {
            iniciarConexion();
            this.statement.execute(sql);
            cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public String ejecutarSQLselect(String sql, int div){//ni le hagan caso a esta parte de codigo XD
        String line="";
        try {
            iniciarConexion();
            ResultSet rs = this.statement.executeQuery(sql);
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
            cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return line;
    }
    
    private void iniciarConexion() throws SQLException{
        this.connection= DriverManager.getConnection(this.url, this.username, this.password);
        this.statement = connection.createStatement();
    }
    private void cerrarConexion() throws SQLException{
        this.statement.close();
        this.connection.close();
    }
}
