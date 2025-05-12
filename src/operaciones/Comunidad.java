package operaciones;

public class Comunidad {
	//Atributop
	private String nombre;
	private String imgBandera;
	private String imgComida;
	private String imgLugar;
	//Constructor
	public Comunidad(String nombre, String imgBandera, String imgComida, String imgLugar) {
		this.nombre = nombre;
		this.imgBandera = imgBandera;
		this.imgComida = imgComida;
		this.imgLugar = imgLugar;
	}
	public Comunidad() {}
	//Get y Set
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getImgBandera() {
		return imgBandera;
	}
	public void setImgBandera(String imgBandera) {
		this.imgBandera = imgBandera;
	}
	public String getImgComida() {
		return imgComida;
	}
	public void setImgComida(String imgComida) {
		this.imgComida = imgComida;
	}
	public String getImgLugar() {
		return imgLugar;
	}
	public void setImgLugar(String imgLugar) {
		this.imgLugar = imgLugar;
	}

	
}
