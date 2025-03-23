
package cl.duoc.poo.bd;

import java.sql.*;



/**
 *
 * @author sebastian ganz 
 */
public class Conexion {

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/cine";
            String usuarioDB = "root";
            String contraseñaDB = "";
            conexion = DriverManager.getConnection(url, usuarioDB, contraseñaDB);
            System.out.println("Conectado Correctamente");

            //conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }
}
