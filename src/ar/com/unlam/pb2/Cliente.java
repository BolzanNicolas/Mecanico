package ar.com.unlam.pb2;

public class Cliente {

	private String nombre;
	private Integer numMovil;
	private long llegada;
	
	public Cliente(String nombre, Integer numMovil) {
		this.nombre = nombre;
		this.numMovil = numMovil;
		this.llegada = Reloj.ahora();
	}
	
	public Cliente(String nombre, Integer numMovil, long llegada) {
		this.nombre = nombre;
		this.numMovil = numMovil;
		this.llegada = llegada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNumMovil() {
		return numMovil;
	}

	public void setNumMovil(Integer numMovil) {
		this.numMovil = numMovil;
	}

	public long getLlegada() {
		return llegada;
	
	}
}
