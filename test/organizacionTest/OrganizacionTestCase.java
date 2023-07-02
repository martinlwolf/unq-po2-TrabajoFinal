package organizacionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import organizacion.FuncionalidadExterna;
import organizacion.Organizacion;
import organizacion.TipoOrganizacion;
import ubicacion.Ubicacion;
import zonaCobertura.ZonaCobertura;

public class OrganizacionTestCase {
	
	private Organizacion org;
	private Ubicacion ubi;
	private FuncionalidadExterna func;
	private FuncionalidadExterna func2;
	private ZonaCobertura zona;
	
	@BeforeEach
	public void setup() {
		ubi = mock(Ubicacion.class);
		func = mock(FuncionalidadExterna.class);
		func2 = mock(FuncionalidadExterna.class);
		org = new Organizacion(ubi, TipoOrganizacion.ASISTENCIA, 10, func, func2);
		zona = mock(ZonaCobertura.class);
	}
	
	@Test
	public void constrcutorTest() {
		assertEquals(ubi, org.getUbicacion());
		assertEquals(TipoOrganizacion.ASISTENCIA, org.getTipo());
		assertEquals(10, org.getCantEmpleados());
	}
	
	@Test
	public void cargaRealizadaTest() {
		org.cargaRealizada(null, null);
		verify(func).nuevoEvento(org,null,null);
	}
	
	@Test
	public void validacionRealizadaTest() {
		org.validacionRealizada(null, null);
		verify(func2).nuevoEvento(org,null,null);
	}
	
	@Test
	public void setFuncValidacionTest() {
		org.setFuncValidacion(func);
		org.validacionRealizada(null, null);
		verify(func2, never()).nuevoEvento(org,null,null);//como cambio la funcionalidad ya no le llega ningun mensaje
		verify(func).nuevoEvento(org,null,null);// ahora le llega el mensaje a func
	}
	
	@Test
	public void setFuncCargaTest() {
		org.setFuncCarga(func2);
		org.validacionRealizada(null, null);
		verify(func, never()).nuevoEvento(org,null,null); //como cambio la funcionalidad ya no le llega ningun mensaje
		verify(func2).nuevoEvento(org,null,null); // ahora le llega el mensaje a func2
	}
	
	@Test
	public void suscribirseAZonaTest() {
		org.suscribirseAZona(zona);
		verify(zona).addOrganizacion(org);
	}
	
	@Test
	public void desuscribirseDeZonaTest() {
		org.desuscribirseDeZona(zona);
		verify(zona).removeOrganizacion(org);
	}
}
