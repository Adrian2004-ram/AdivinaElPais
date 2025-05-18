package operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComunidadDAO {
	public ComunidadDAO() {}
	
	
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
	
	
	//Sacar pais base de datos y enviar a interfaz
	public String comunidadesSacadas() {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="SELECT NOMBRE, FOTO_BANDERA, FOTO_COMIDA, FOTO_LUGAR FROM COMUNIDAD ";
		Statement sentencia;
		String resultado = "";
		//usamos la conexion para sacar el nombre y fotos de la comunidad
		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while (rs.next()) {
	            String nombre = rs.getString("NOMBRE");
	            String foto1 = rs.getString("FOTO_BANDERA");
	            String foto2 = rs.getString("FOTO_COMIDA");
	            String foto3 = rs.getString("FOTO_LUGAR");
	            
	            resultado = resultado +  nombre + "|" + foto1 + "|" + foto2 + "|" + foto3 + "|";
			}
			
			conexion.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	
		return resultado;
		
	}
	
}