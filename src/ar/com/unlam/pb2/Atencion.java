package ar.com.unlam.pb2;

public class Atencion {
	
	private Cliente cliente;
	private long horaAtencion;
	
	public Atencion(Cliente cliente) {
		this.cliente = cliente;
		this.horaAtencion = Reloj.ahora();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public long getHoraAtencion() {
		return horaAtencion;
	}
	
}
