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

		String url = "jdbc:mysql://localhost/Adivina_la_comunidad";
		try {
			con = DriverManager.getConnection(url, "root", "1234");
		} catch (SQLException ex) {
			System.out.println("Error al conectar al SGBD.");
		}

		return con;
	}
	
	
	//Sacar pais base de datos y enviar a interfaz
	public String paisSacado() {
		//Base de datos
		Connection conexion = conectar();
		//saca paises de base de datos
		String sql="SELECT COMUNIDAD_ID, NOMBRE, FOTO_BANDERA, FOTO_COMIDA, FOTO_LUGAR FROM COMUNIDAD ";
		Statement sentencia;
		String resultado = "";
		//usamos la conextion
		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			
			while (rs.next()) {
	            String nombre = rs.getString("nameComunidad");
	            String foto1 = rs.getString("foto1");
	            String foto2 = rs.getString("foto2");
	            String foto3 = rs.getString("foto3");
	            
	            resultado = resultado +  nombre + "|" + foto1 + "|" + foto2 + "|" + foto3 + "|";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		//devuelvelo
		return resultado;
		
	}

}
