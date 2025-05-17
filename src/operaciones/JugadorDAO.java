package operaciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.PreparedStatement;

public class JugadorDAO {
	
	public void JugadorDAO() {}
	
	//Metodo
	private static Connection conectar() {
		Connection con = null;

		 String url = "jdbc:mysql://localhost:3306/Adivina_la_comunidad";
	        try {
	            con = DriverManager.getConnection(url, "root", "1234");
		} catch (SQLException ex) {
			System.out.println("Error al conectar al SGBD.");
		}

		return con;
	}
	
	//crea jugador
	public void insertJugador(Jugador player) {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="INSERT INTO JUGADOR (NOMBRE_USUARIO, PUNTUACION_MAX, FECHA, HORA) VALUES (?, ?, ?, ?);";
		PreparedStatement sentencia;
		//usamos la conextion
		try {
			
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, player.getNombre());
			sentencia.setInt(2, 0);
			sentencia.setDate(3, Date.valueOf(player.getFecha()));
			sentencia.setTime(4, Time.valueOf(player.getHora()));

			sentencia.executeUpdate();
			
			conexion.close();
			
			 System.err.println("Jugador guardado con éxito.");		

		} catch (SQLException e) {
			 System.err.println("Error al guardar jugador: " + e.getMessage());
		}
		
	}
	
	//emviar puntuacion
	public void puntuacion(int puntuancion, Jugador player) {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="UPDATE JUGADOR SET PUNTUACION_PARTIDA = ? WHERE NOMBRE_USUARIO = ? AND FECHA = ? AND HORA = ?;";
		PreparedStatement sentencia;
		//usamos la conextion
		try {
			
			sentencia = conexion.prepareStatement(sql);
			sentencia.setInt(1, puntuancion);
			sentencia.setString(2, player.getNombre());
			sentencia.setDate(3, Date.valueOf(player.getFecha()));
			sentencia.setTime(4, Time.valueOf(player.getHora()));

			sentencia.executeUpdate();
			
			conexion.close();
			
			 System.err.println("Punutacion guardado con éxito.");		

			
		} catch (SQLException e) {
			 System.err.println("Error al guardar la punutacoin: " + e.getMessage());
		}
	}
	
	//Devuelve un string con todas las punutaciones de cada jugador
	public String logPunutacoines() {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="SElECT NOMBRE_USUARIO, PUNTUACION_PARTIDA, FECHA, HORA FROM JUGADOR";
		Statement sentencia;
		String resultado = "";
		//usamos la conextion
		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while (rs.next()) {
	            String nombre = rs.getString("NOMBRE_USUARIO");
	            String punutacion = rs.getString("PUNTUACION_PARTIDA");
	            Date fecha = rs.getDate("FECHA");
	            Time hora = rs.getTime("HORA");
	            
	            resultado = resultado +  nombre + "|" + punutacion + "|" + fecha + "|" + hora + "|";
			}
			
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		//devuelvelo
		return resultado;
	}

}
