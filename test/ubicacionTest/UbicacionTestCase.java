package ubicacionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import ubicacion.Ubicacion;

public class UbicacionTestCase {
	private Ubicacion ubicacion1;
	@Mock private Ubicacion ubicacion2;
	@Mock private Ubicacion ubicacion3;
	@Mock private Ubicacion ubicacion4;
	
	@BeforeEach
	public void setUp() {
		this.ubicacion1 = new Ubicacion(20.0, 30.0);
		this.ubicacion2 = mock(Ubicacion.class);
		this.ubicacion3 = mock(Ubicacion.class);
		this.ubicacion4 = mock(Ubicacion.class);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(20.0, this.ubicacion1.getLatitud());
		assertEquals(30.0, this.ubicacion1.getLongitud());
	}
	
	@Test
	public void testDistanciaAOtraUbicacion() {
		when(this.ubicacion2.getLatitud()).thenReturn(10.0);
		when(this.ubicacion2.getLongitud()).thenReturn(10.0);
		double distancia = this.ubicacion1.distanciaA(ubicacion2);
		assertEquals(2415.2423704914736, distancia);
		verify(this.ubicacion2, times(2)).getLatitud();
		verify(this.ubicacion2, times(1)).getLongitud();
	}
	
	@Test
	public void testLaUbicacionSeEncuentraAMenosDeUnaDistancia() {
		when(this.ubicacion2.getLatitud()).thenReturn(10.0);
		when(this.ubicacion2.getLongitud()).thenReturn(10.0);
		assertTrue(this.ubicacion1.laUbicacionSeEncuentraAMenosDe(ubicacion2, 3000)); //La distancia es 2415.24
	}
	
	@Test
	public void testLaUbicacionNoSeEncuentraAMenosDeUnaDistancia() {
		when(this.ubicacion2.getLatitud()).thenReturn(10.0);
		when(this.ubicacion2.getLongitud()).thenReturn(10.0);
		assertFalse(this.ubicacion1.laUbicacionSeEncuentraAMenosDe(ubicacion2, 2000)); //La distancia es 2415.24
	}
	
	@Test
	public void testLasUbicacionesQueSeEncuentranAMenosDeUnaDistancia() {
		when(this.ubicacion2.getLatitud()).thenReturn(10.0);
		when(this.ubicacion2.getLongitud()).thenReturn(10.0);
		when(this.ubicacion3.getLatitud()).thenReturn(15.0);
		when(this.ubicacion3.getLongitud()).thenReturn(15.0);
		when(this.ubicacion4.getLatitud()).thenReturn(80.0);
		when(this.ubicacion4.getLongitud()).thenReturn(80.0);
		assertEquals(Arrays.asList(ubicacion2, ubicacion3), this.ubicacion1.ubicacionesQueSeEncuentranAMenosDe(Arrays.asList(ubicacion2, ubicacion3, ubicacion4), 3000));
	}
	
	@Test
	public void setLatitudTest() {
		ubicacion1.setLatitud(10);
		assertEquals(10, ubicacion1.getLatitud());
	}
	
	@Test
	public void setLongitudTest() {
		ubicacion1.setLongitud(10);
		assertEquals(10, ubicacion1.getLongitud());
	}
	
}
