package operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JugadorDAO {
	
	//Metodo
	private static Connection conectar() {
		Connection con = null;

		String url = "jdbc:mysql://localhost/Adivina_la_comunidad";
		try {
			con = DriverManager.getConnection(url, "root", "1234");
		} catch (SQLException ex) {
			System.out.println("Error al conectar al SGBD.");
		}

		return con;
	}
	
	//crea jugador
	/*public void meterUsuario(String nombre) {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="INSERT INTO JUGADOR VALUE(?, )";
		Statement sentencia;
		String resultado = "";
		//usamos la conextion
		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while (rs.next()) {
	            String nombre = rs.getString("nombre");
	            
	            
	            resultado = resultado + id + "|" +  nombre + "|" + foto1 + "|" + foto2 + "|" + foto3 + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		//devuelvelo
		return resultado;
	}
	//emviar puntuacion
	public void puntuacion(int puntuancion) {
		
	}
*/
}
