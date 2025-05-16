package operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class JugadorDAO {
	
	public void JugadorDAO() {}
	
	//Metodo
	private static Connection conectar() {
		Connection con = null;

		 String url = "jdbc:mysql://127.0.0.1:3306/Adivina_la_comunidad";
	        try {
	            con = DriverManager.getConnection(url, "root", "3142cdS");
		} catch (SQLException ex) {
			System.out.println("Error al conectar al SGBD.");
		}

		return con;
	}
	
	//crea jugador
	public void insertJugador(String nombre) {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="INSERT INTO JUGADOR (NOMBRE_USUARIO, PUNTUACION_MAX) VALUES (?, ?);";
		PreparedStatement sentencia;
		//usamos la conextion
		try {
			
			sentencia = conexion.prepareStatement(sql);
			sentencia.setString(1, nombre);
			sentencia.setInt(2, 0);

			sentencia.executeUpdate();
			
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//emviar puntuacion
	public void puntuacion(int puntuancion) {
		
	}

}
