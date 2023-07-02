package participanteTest;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import nivelesParticipantes.NivelBasico;
import nivelesParticipantes.NivelExperto;
import participantes.Especialista;
import ubicacion.Ubicacion;

public class EspecialistaTestCase {
	
	@Test
	public void setNivelTest() {
		
		Especialista e = new Especialista();
		
		assertTrue(e.getNivel() instanceof NivelExperto);
		
		e.setNivel(new NivelBasico());
		
		assertTrue(e.getNivel() instanceof NivelExperto);
		
	}
}
