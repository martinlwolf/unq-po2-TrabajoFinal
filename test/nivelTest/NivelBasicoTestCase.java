package nivelTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import muestra.EstadoMuestraExperto;
import nivelesParticipantes.NivelBasico;
import nivelesParticipantes.NivelExperto;
import opinion.OpinionBasica;
import participantes.Participante;

public class NivelBasicoTestCase {
	
	NivelBasico nivelBasico;
	Participante participante;
	private ArgumentCaptor<NivelExperto> argumentCaptor;

	
	@BeforeEach
	public void setup() {
		nivelBasico = new NivelBasico();
		participante = mock(Participante.class);
		this.argumentCaptor = ArgumentCaptor.forClass(NivelExperto.class);

	}
	
	@Test
	public void puedeMantenerSuNivelTest() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		assertTrue(nivelBasico.debeCambiarSuNivel(participante));
	}
	
	@Test
	public void puedeMantenerSuNivelTest2() {
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(10);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(10);
		assertFalse(nivelBasico.debeCambiarSuNivel(participante));
	}
	
	@Test
	public void actualizarNivelTest() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(30);
		nivelBasico.actualizarNivel(participante);
		verify(this.participante, times(1)).setNivel(argumentCaptor.capture());
	}
	
	@Test
	public void actualizarNivelTest2() {
		
		when(participante.cantEnviosDeLosUltimos30Dias(LocalDate.now())).thenReturn(1);
		when(participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now())).thenReturn(1);
		nivelBasico.actualizarNivel(participante);
		verify(this.participante, times(0)).setNivel(argumentCaptor.capture());
	}
	
	@Test
	public void testCuandoElNivelBasicoCreaUnaOpinionEstaEsBasica() {
		assertTrue(this.nivelBasico.crearOpinion(null, participante, null) instanceof OpinionBasica);
	}

}
