package operaciones;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Jugador {
	//Atributos
	private String nombre;
	private int puntuacionPartida;
	private LocalDate fecha;
	private LocalTime hora;
	//Constructor
	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
		//Metemos la fecha y hora de comienzo de partida
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		//inicializamos la punutacion a 0
		this.puntuacionPartida = 0;
	}
	public Jugador(String nombre, int puntuacionPartida, LocalDate fecha, LocalTime hora) {
        super();
        this.nombre = nombre;
        this.puntuacionPartida = puntuacionPartida;
        this.fecha = fecha;
        this.hora = hora;
    }
	//METODO
	public String fechaFormateada() {
        DateTimeFormatter formatoF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.fecha.format(formatoF);
	}
	public String horaFormateada() {
        DateTimeFormatter formatoH = DateTimeFormatter.ofPattern("HH:mm");
        return this.hora.format(formatoH);
	}
	//Get y Set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntuacionPartida() {
		return puntuacionPartida;
	}
	public void setPuntuacionPartida(int puntuacionPartida) {
		this.puntuacionPartida = puntuacionPartida;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public LocalTime getHora() {
		return hora;
	}
	public void setHora(LocalTime hora) {
		this.hora = hora;
	}


}