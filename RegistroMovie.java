package cl.duoc.poo.controlador;

import cl.duoc.poo.bd.Conexion;
import cl.duoc.poo.modelo.Movie;
import cl.duoc.poo.vista.Listar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author sebastian ganz
 */
public class RegistroMovie {

    public ArrayList getTodasLasPeliculas() throws SQLException {
        ArrayList<Movie> pelicula = new ArrayList<>();
        try {

            ResultSet resultado = null;
            Connection conn = Conexion.conectar();

            Statement statement = conn.createStatement();
            resultado = statement.executeQuery("select * from peliculas where estado =1 ;");
            while (resultado.next()) {
                pelicula.add(new Movie(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getInt(4), resultado.getInt(5), resultado.getString(6)));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pelicula;
    }

    public ArrayList getPeliculasPorAno(int inicio, int fin) {
        ArrayList<Movie> pelicula = new ArrayList<>();
        try {

            ResultSet resultado = null;
            Connection conn = Conexion.conectar();

            Statement statement = conn.createStatement();
            resultado = statement.executeQuery("select * from peliculas where estado = 1 AND ano BETWEEN " + inicio + " AND " + fin + " ;");
            while (resultado.next()) {
                pelicula.add(new Movie(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getInt(4), resultado.getInt(5), resultado.getString(6)));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pelicula;
    }

    public ArrayList getPeliculasPorGenero(String genero) {
        ArrayList<Movie> pelicula = new ArrayList<>();
        try {

            ResultSet resultado = null;
            Connection conn = Conexion.conectar();

            Statement statement = conn.createStatement();
            resultado = statement.executeQuery("select * from peliculas where estado = 1 AND genero = '" + genero + "' ;");
            while (resultado.next()) {
                pelicula.add(new Movie(resultado.getInt(1), resultado.getString(2), resultado.getString(3), resultado.getInt(4), resultado.getInt(5), resultado.getString(6)));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pelicula;
    }

    public ArrayList getTodosLosGeneros() throws SQLException {
        ArrayList generos = new ArrayList();
        ResultSet resultado = null;
        Connection conn = Conexion.conectar();
        Statement statement = conn.createStatement();
        resultado = statement.executeQuery("select distinct genero from peliculas where estado = 1 ORDER BY 1 ASC;");
        while (resultado.next()) {
            generos.add(resultado.getString(1));
        }
        conn.close();
        return generos;
    }

    public boolean agregarPelicula(Movie movie) {
        boolean resultado = false;
        Connection conexion = Conexion.conectar();
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO peliculas (titulo, director, ano,duracion, genero) values (?,?,?,?,?);");

            statement.setString(1, movie.getTitulo());
            statement.setString(2, movie.getDirector());
            statement.setInt(3, movie.getAno());
            statement.setInt(4, movie.getDuracion());
            statement.setString(5, movie.getGenero());

            statement.execute();

            conexion.close();
            resultado = true;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    public Movie getPelicula(String idPelicula) {
        Movie pelicula = new Movie();

        Connection conn = Conexion.conectar();
        try {
            Statement statement = conn.createStatement();
            ResultSet resultado = statement.executeQuery("select * from peliculas where estado = 1 AND idpelicula = " + idPelicula + " ;");

            while (resultado.next()) {
                pelicula.setId(Integer.parseInt(idPelicula));
                pelicula.setTitulo(resultado.getString(2));
                pelicula.setDirector(resultado.getString(3));
                pelicula.setAno(resultado.getInt(4));
                pelicula.setDuracion(resultado.getInt(5));
                pelicula.setGenero(resultado.getString(6));

            }
            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return pelicula;
    }

    public boolean editarPelicula(Movie movie) {
        boolean resultado = false;
        Connection conexion = Conexion.conectar();
        try {
            PreparedStatement statement = conexion.prepareStatement("UPDATE peliculas SET titulo = ?, director =?, ano =?, duracion = ?, genero= ? WHERE idpelicula = " + movie.getId() + ";");

            statement.setString(1, movie.getTitulo());
            statement.setString(2, movie.getDirector());
            statement.setInt(3, movie.getAno());
            statement.setInt(4, movie.getDuracion());
            statement.setString(5, movie.getGenero());

            statement.execute();

            conexion.close();
            resultado = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return resultado;
    }

    public boolean eliminarMovie(int idpelicula) {
        try {
            Conexion conexion = new Conexion();
            Connection conect = conexion.conectar();

            String query = "DELETE FROM peliculas WHERE idpelicula=?";
            PreparedStatement stmt = conect.prepareStatement(query);
            stmt.setInt(1, idpelicula);
            stmt.executeUpdate();
            stmt.close();
            conect.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error de SQL al agregar la Pelicula: " + e.getMessage());
            return false;
        } catch (Exception ex) {
            System.out.println("Error al agregar la Pelicula: " + ex.getMessage());
            return false;
        }
    }


    public boolean validacionTitulo(String titulo) {
        try {
           Connection conexion = Conexion.conectar();
            String consulta = "SELECT COUNT(*) FROM peliculas WHERE titulo = ?";
            try (PreparedStatement statement = conexion.prepareStatement(consulta)) {
                statement.setString(1, titulo);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count == 0;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
