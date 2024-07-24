package ar.com.unlam.pb2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTaller {

	Taller taller = new Taller("Bilbo", 3425);

	@Before
	public void setup() {
		taller = new Taller("Bilbo", 3425);
	}

//	• Añadir un cliente (nuevoCliente). 
	@Test
	public void nuevoCliente() {
		Cliente cliente = new Cliente("Nico", 3);
		assertTrue(taller.nuevoCliente(cliente));
	}
	
//	• Atender a un cliente (atenderCliente). Retorna el cliente atendido y lanzará una Exception en
//	el caso de no haber clientes que atender. 
	@Test
	public void atenderClienteFallaSiNoHayCliente() {
		try {
			taller.atenderCliente();
			fail("No se produjo exception");
		} catch (NoHayClienteException exception) {
			// Fallo con exito
		}
	}
	
	@Test
	public void atenderClienteRetornaCliente() {
		Cliente cliente = new Cliente("Nico", 3);
		taller.nuevoCliente(cliente);

		assertNotNull(taller.atenderCliente());
	}
	
//	• Obtener el tiempo medio de espera de los clientes que
//	aún no han sido atendidos (tiempoEsperaNoAtendidos). Expresado en minutos. 
	@Test
	public void tiempoEsperaNoAtendidos() {
		// Pasamos los minutos a milisegundos
		long dosMinEnMillis = (2 * 1000) * 60;
		long cuatroMinEnMillis = (4 * 1000) * 60;

		// Le restamos los minutos (en milisegundos) al tiempo actual
		long llegadaHaceDosMin = Reloj.ahora() - dosMinEnMillis;
		long llegadaHaceCuatroMin = Reloj.ahora() - cuatroMinEnMillis;
		
		// Creamos 2 clientes con tiempo de llegada restado
		Cliente nico = new Cliente("Nico", 3, llegadaHaceDosMin);
		Cliente ciri = new Cliente("Ciri", 4, llegadaHaceCuatroMin);
		
		taller.nuevoCliente(nico);
		taller.nuevoCliente(ciri);

		long promedio = taller.tiempoEsperaNoAtendidos();
		
		// El promedio tiene que dar 3, porque (2 + 4) / 2 = 3
		assertEquals(promedio, 3);
	}
	
//	• Obtener el tiempo medio de espera de los clientes ya atendidos (tiempoEsperaAtendidos). 
	@Test
	public void tiempoEsperaAtendido() {
		// Pasamos los minutos a milisegundos
		long dosMinEnMillis = (2 * 1000) * 60;
		long cuatroMinEnMillis = (4 * 1000) * 60;

		// Le restamos los minutos (en milisegundos) al tiempo actual
		long llegadaHaceDosMin = Reloj.ahora() - dosMinEnMillis;
		long llegadaHaceCuatroMin = Reloj.ahora() - cuatroMinEnMillis;

		// Creamos 2 clientes con tiempo de llegada restado
		Cliente nico = new Cliente("Nico", 3, llegadaHaceDosMin);
		Cliente ciri = new Cliente("Ciri", 4, llegadaHaceCuatroMin);

		taller.nuevoCliente(nico);
		taller.nuevoCliente(ciri);
		
		taller.atenderCliente();
		taller.atenderCliente();

		long promedio = taller.tiempoEsperaAtendidos();

		// El promedio tiene que dar 3, porque (2 + 4) / 2 = 3
		assertEquals(promedio, 3);
	}
//	• Obtener cantidad de clientes en espera (obtenerCantidadClientesEnEspera). 
	@Test
	public void obtenerCantidadClientesEnEspera() {
		Cliente nico = new Cliente("Nico", 3);
		Cliente ciri = new Cliente("Ciri", 4);
		Cliente taura = new Cliente("Taura", 2);

		taller.nuevoCliente(nico);
		taller.nuevoCliente(ciri);
		taller.nuevoCliente(taura);
		taller.atenderCliente();
		int clientesSinAtender = taller.cantidadClientesEnEspera();
		assertEquals(clientesSinAtender, 2);
	}
	
//	• Obtener cantidad de clientes atendidos (obtenerCantidadClientesAtenidos).
	@Test
	public void obtenerCantidadClientesAtendidos() {
		Cliente nico = new Cliente("Nico", 3);
		Cliente ciri = new Cliente("Ciri", 4);
		Cliente taura = new Cliente("Taura", 2);

		taller.nuevoCliente(nico);
		taller.nuevoCliente(ciri);
		taller.nuevoCliente(taura);
		taller.atenderCliente();
		taller.atenderCliente();
		taller.atenderCliente();
		int clientesAtendidos = taller.cantidadClientesAtendidos();
		assertEquals(clientesAtendidos, 3);
	}
}
