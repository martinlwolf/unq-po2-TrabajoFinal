package nivelTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import nivelesParticipantes.NivelExperto;
import opinion.OpinionExperta;
import participantes.Participante;

public class NivelExpertoTestCase {
	
	private NivelExperto nivelExperto;
	private Participante participante;
	private ArgumentCaptor<NivelExperto> argumentCaptor;
	
	@BeforeEach
	public void setup() {
		nivelExperto = new NivelExperto();
		participante = mock(Participante.class);
		this.argumentCaptor = ArgumentCaptor.forClass(NivelExperto.class);
	}
	
	@Test
	public void debeCambiarSuNivelTest() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(0);
		assertTrue(nivelExperto.debeCambiarSuNivel(participante));
	}
	
	@Test
	public void debeCambiarSuNivelTest2() {
		
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		assertFalse(nivelExperto.debeCambiarSuNivel(participante));
	}
	
	@Test
	public void actualizarNivelTest() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		nivelExperto.actualizarNivel(participante);
		verify(this.participante, times(0)).setNivel(argumentCaptor.capture());
	}
	
	@Test
	public void actualizarNivelTest2() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(1);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(1);
		nivelExperto.actualizarNivel(participante);
		verify(this.participante, times(1)).setNivel(argumentCaptor.capture());
	}
	
	@Test
	public void puedeOpinarTest() {
		assertTrue(nivelExperto.puedeOpinar(null));
	}
	
	@Test
	public void testCuandoElNivelExpertoCreaUnaOpinionEstaEsExperta() {
		assertTrue(this.nivelExperto.crearOpinion(null, participante, null) instanceof OpinionExperta);
	}

}