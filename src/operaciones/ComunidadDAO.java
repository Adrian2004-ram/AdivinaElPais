package operaciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ComunidadDAO {
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
		//saca paises
		
		//devuelvelo
		return "1-2-3-4";
		
	}

}
