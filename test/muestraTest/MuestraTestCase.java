package muestraTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.EstadoDeMuestra;
import muestra.EstadoMuestraBasico;
import muestra.EstadoMuestraExperto;
import muestra.Muestra;
import opinion.Opinion;
import opinion.OpinionBasica;
import opinion.OpinionExperta;
import opinion.TipoDeOpinion;
import participantes.Participante;
import ubicacion.Ubicacion;

public class MuestraTestCase {
	
	 Muestra muestra;
	 Participante p;
	 Ubicacion u;
	 EstadoDeMuestra estadoBasico;
	
	@BeforeEach
	public void setup() {
		p = new Participante(); 
		u = mock(Ubicacion.class);
		muestra = new Muestra(p, this.u);
		this.estadoBasico = mock(EstadoMuestraBasico.class);
	}
	/*
	 * cuando se crea una muestra se asigna un autor y su ubicacion, no esta verificada y la lista de opiniones vacia
	 */
	@Test
	public void testConstructor() {
		
		assertEquals(p, muestra.getAutor());
		assertEquals(u, muestra.getUbicacion());
		assertFalse(muestra.isVerificada());
		assertTrue(muestra.getOpiniones().isEmpty());
	}

	@Test
	public void testAlPedirElResultadoActualDeLaMuestraSeDelegaASuEstado() {
		this.muestra.setEstado(this.estadoBasico); // Este estado se setea como un mock solo para poder verificar que recibe el mensaje
		this.muestra.resultadoActual();
		verify(this.muestra.getEstado(), times(1)).resultadoActual(this.muestra);
	}
	
	
	@Test
	public void testLaMuestraFueOpinadaPorUnParticipanteEnEspecifico() {
		this.p.opinar(muestra, TipoDeOpinion.CHINCHE_FOLIADA, LocalDate.now());
		assertTrue(this.muestra.fueOpinadaPor(p));
	}
	
	@Test
	public void testLaMuestraNoFueOpinadaPorUnParticipanteEnEspecifico() {
		assertFalse(this.muestra.fueOpinadaPor(p));
	}
	
	@Test
	public void testSoloOpinaronBasicosVerdadero() {
		this.muestra.agregarOpinion(new OpinionBasica(TipoDeOpinion.CHINCHE_FOLIADA, p, LocalDate.now()));
		assertTrue(this.muestra.soloOpinaronBasicos());
	}
	
	@Test
	public void testSoloOpinaronBasicosFalso() {
		this.muestra.agregarOpinion(new OpinionExperta(TipoDeOpinion.CHINCHE_FOLIADA, p, LocalDate.now()));
		assertFalse(this.muestra.soloOpinaronBasicos());
	}
	
	@Test
	public void testGetOpinionesExpertas() {
		Opinion opinion1 = mock(OpinionExperta.class);
		Opinion opinion2 = mock(OpinionBasica.class);
		this.muestra.agregarOpinion(opinion1);
		this.muestra.agregarOpinion(opinion2);
		
		assertEquals(opinion1, this.muestra.getOpinionesExpertas().get(0));
	}
	
	@Test
	public void testlaMuestraSeEncuentraAMenosDe() {
		Muestra muestra2 = mock(Muestra.class);
		Ubicacion ubicacion2 = mock(Ubicacion.class);
		when(this.u.laUbicacionSeEncuentraAMenosDe(ubicacion2, 1000)).thenReturn(true);
		when(muestra2.getUbicacion()).thenReturn(ubicacion2);
		assertTrue(this.muestra.laMuestraSeEncuentraAMenosDe(muestra2, 1000));
		verify(muestra2, times(1)).getUbicacion();
		verify(this.u, times(1)).laUbicacionSeEncuentraAMenosDe(ubicacion2, 1000);
	}
	
	@Test
	public void testLasMuestrasQueSeEncuentrenAMenosDe() {
		Muestra muestra2 = mock(Muestra.class);
		Muestra muestra3 = mock(Muestra.class);
		Muestra muestra4 = mock(Muestra.class);
		when(muestra2.laMuestraSeEncuentraAMenosDe(this.muestra, 1000)).thenReturn(true);
		when(muestra3.laMuestraSeEncuentraAMenosDe(this.muestra, 1000)).thenReturn(true);
		when(muestra4.laMuestraSeEncuentraAMenosDe(this.muestra, 1000)).thenReturn(false);
		assertEquals(Arrays.asList(muestra2, muestra3), this.muestra.muestrasQueEstenAUnaDistanciaMenorA(Arrays.asList(muestra2, muestra3, muestra4), 1000));
	}
	
	@Test
	public void setVerificadaTest() {
		muestra.setVerificada();
		assertTrue(muestra.isVerificada());
	}
	
	@Test
	public void getFechaTest() {
		this.muestra = new Muestra(p, LocalDate.of(2000, 10, 1), u);
		assertEquals(this.muestra.getFecha(), LocalDate.of(2000, 10, 1));
	}
	
	
}
