package operaciones;

public class Jugador {
	//Atributos
	private int id_jugador;
	private String nombre;
	private int puntuacionMasAlta;
	//Constructor
	
	//Get y Set
	public int getId_jugador() {
		return id_jugador;
	}
	public void setId_jugador(int id_jugador) {
		this.id_jugador = id_jugador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntuacionMasAlta() {
		return puntuacionMasAlta;
	}
	public void setPuntuacionMasAlta(int puntuacionMasAlta) {
		this.puntuacionMasAlta = puntuacionMasAlta;
	}

}
