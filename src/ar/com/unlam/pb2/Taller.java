package ar.com.unlam.pb2;
import java.util.ArrayList;

public class Taller {

	private String nombre;
	private Integer telefono;
	private ArrayList<Cliente>clientes;
	private ArrayList<Atencion>atendidos;

	public Taller(String nombre, Integer telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.clientes = new ArrayList<Cliente>();
		this.atendidos = new ArrayList<Atencion>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Atencion> getAtendidos() {
		return atendidos;
	}

	public void setAtendidos(ArrayList<Atencion> atendidos) {
		this.atendidos = atendidos;
	}

	public Boolean nuevoCliente(Cliente cliente) {
		return clientes.add(cliente);

	}

	public Cliente atenderCliente() {
		if(clientes.isEmpty()) {
			throw new NoHayClienteException();
		} else {
			Cliente clienteSinAtender = clientes.remove(0);
			Atencion nuevaAtencion = new Atencion(clienteSinAtender);
			atendidos.add(nuevaAtencion);
			return clienteSinAtender;
		}
	}

	public long tiempoEsperaNoAtendidos() {		
		long sumaMinutos = 0;
		
		for(Cliente cliente : clientes) {
			long horaActual = Reloj.ahora();
			long milliseconds = horaActual - cliente.getLlegada();
			long minutes = (milliseconds/1000) / 60;
			sumaMinutos += minutes;
		}
		long promedioEsperaMinutos = sumaMinutos / clientes.size();
		
		return promedioEsperaMinutos;
	}

	public long tiempoEsperaAtendidos() {		
		long sumaMinutos = 0;
		
		for(Atencion atendido : atendidos) {
			long hora = atendido.getHoraAtencion();
			long milliseconds = hora - atendido.getCliente().getLlegada();
			long minutes = (milliseconds/1000) / 60;
			sumaMinutos += minutes;
		}
		long promedioEsperaMinutos = sumaMinutos / atendidos.size();
		
		return promedioEsperaMinutos;
	}
	
	public Integer cantidadClientesEnEspera() {
		return clientes.size();
	}

	public Integer cantidadClientesAtendidos() {
		return atendidos.size();
	}
}
