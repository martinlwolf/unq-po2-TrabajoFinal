package participanteTest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import muestra.Muestra;
import nivelesParticipantes.Nivel;
import nivelesParticipantes.NivelBasico;
import nivelesParticipantes.NivelExperto;
import opinion.Opinion;
import opinion.TipoDeOpinion;
import participantes.Participante;
import ubicacion.Ubicacion;

public class ParticipanteTestsCase {

	private Participante participante;
	private Ubicacion ubicacion;
	private NivelBasico nb;
	private Opinion opinion;
	private Muestra muestra;

	@BeforeEach
	public void setup() {
		ubicacion = mock(Ubicacion.class);
		nb = mock(NivelBasico.class);
		participante = new Participante();
		opinion = mock(Opinion.class);
		muestra = mock(Muestra.class);
	}

	/*
	 * al crearse se le asgina una instancia de NivelBasico
	 */
	@Test
	public void constructorTest() {
		assertTrue(participante.getNivel() instanceof NivelBasico);
	}

	@Test
	public void constructorVacioTest() {
		participante = new Participante();
		assertTrue(participante.getEnvios().isEmpty());
		assertTrue(participante.getRevisiones().isEmpty());
		assertTrue(participante.getNivel() instanceof NivelBasico);
	}

	@Test
	public void getRevisionesTest() {

		assertEquals(new ArrayList<>(), participante.getRevisiones());
	}

	@Test
	public void getEnviosTest() {

		assertEquals(new ArrayList<>(), participante.getEnvios());
	}

	@Test
	public void agregarMuestraTest() {

		participante.agregarMuestra(muestra);
		assertEquals(Arrays.asList(muestra), participante.getEnvios());
	}

	@Test
	public void agregarOpinionTest() {

		participante.agregarOpinion(opinion);
		assertEquals(Arrays.asList(opinion), participante.getRevisiones());
	}

	@Test
	public void revisionesDeLosUltimos30DiasTest() {

		participante.agregarOpinion(opinion);

		when(opinion.getFecha()).thenReturn(LocalDate.of(2023, 6, 4)); // en este caso la opinion se hace en el mismo
																		// dia
		assertEquals(Arrays.asList(opinion), participante.revisionesDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));

		when(opinion.getFecha()).thenReturn(LocalDate.of(2022, 6, 4));// en este caso la opinion se un año antes
		assertEquals(Arrays.asList(), participante.revisionesDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));
	}

	@Test
	public void enviosDeLosUltimos30DiasTest() {

		participante.agregarMuestra(muestra);

		when(muestra.getFecha()).thenReturn(LocalDate.of(2023, 6, 4)); // en este caso la muestra se hace en el mismo
																		// dia
		assertEquals(Arrays.asList(muestra), participante.enviosDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));

		when(muestra.getFecha()).thenReturn(LocalDate.of(2022, 6, 4));// en este caso la muestra se un año antes
		assertEquals(Arrays.asList(), participante.enviosDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));
	}

	@Test
	public void cantEnviosDeLosUltimos30DiasTest() {

		participante.agregarMuestra(muestra);

		when(muestra.getFecha()).thenReturn(LocalDate.of(2023, 6, 4)); // en este caso la muestra se hace en el mismo
																		// dia
		assertEquals(1, participante.cantEnviosDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));

		when(muestra.getFecha()).thenReturn(LocalDate.of(2022, 6, 4));// en este caso la muestra se un año antes
		assertEquals(0, participante.cantEnviosDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));
	}

	@Test
	public void cantRevisionesDeLosUltimos30DiasTest() {

		participante.agregarOpinion(opinion);

		when(opinion.getFecha()).thenReturn(LocalDate.of(2023, 6, 4)); // en este caso la muestra se hace en el mismo
																		// dia
		assertEquals(1, participante.cantRevisionesDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));

		when(opinion.getFecha()).thenReturn(LocalDate.of(2022, 6, 4));// en este caso la muestra se un año antes
		assertEquals(0, participante.cantRevisionesDeLosUltimos30Dias(LocalDate.of(2023, 6, 4)));
	}

	@Test
	public void opinarTest() {
		
		assertTrue(participante.getRevisiones().isEmpty());
		
		when(muestra.fueOpinadaPor(participante)).thenReturn(false);
		when(muestra.isVerificada()).thenReturn(false);
		when(muestra.soloOpinaronBasicos()).thenReturn(true);
		
		
		participante.opinar(muestra, TipoDeOpinion.CHINCHE_FOLIADA, LocalDate.of(2023, 6, 4));

		assertFalse(participante.getRevisiones().isEmpty());
	}
	
	@Test
	public void recolectarTest() {
		
		assertTrue(participante.getEnvios().isEmpty());

		participante.recolectar(TipoDeOpinion.CHINCHE_FOLIADA, LocalDate.of(2023, 6, 4), this.ubicacion);

		assertFalse(participante.getEnvios().isEmpty());
	}

}
