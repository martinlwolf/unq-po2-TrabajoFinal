package opinionTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import muestra.EstadoMuestraExperto;
import muestra.Muestra;
import opinion.Opinion;
import opinion.OpinionBasica;
import opinion.OpinionExperta;
import opinion.TipoDeOpinion;
import participantes.Participante;

public class OpinionTestCase {
	private Opinion opinionBasica;
	private Opinion opinionExperta;
	@Mock
	private Participante participante;
	@Mock
	private Muestra muestra;
	private ArgumentCaptor<EstadoMuestraExperto> argumentCaptor;

	@BeforeEach
	public void setUp() {
		this.argumentCaptor = ArgumentCaptor.forClass(EstadoMuestraExperto.class);
		this.participante = mock(Participante.class);
		this.opinionBasica = new OpinionBasica(TipoDeOpinion.VINCHUCA_INFESTANS, participante,
				LocalDate.of(2020, 3, 10));
		this.opinionExperta = new OpinionExperta(TipoDeOpinion.VINCHUCA_INFESTANS, participante,
				LocalDate.of(2020, 3, 10));
		this.muestra = mock(Muestra.class);
	}

	@Test
	public void testConstructor() {
		assertEquals(TipoDeOpinion.VINCHUCA_INFESTANS, this.opinionBasica.getTipoDeOpinion());
		assertEquals(this.participante, this.opinionBasica.getAutor());
		assertEquals(LocalDate.of(2020, 3, 10), this.opinionBasica.getFecha());
	}

	@Test
	public void testActualizarMuestraConOpinionBasica() {
		this.opinionBasica.actualizarMuestra(this.muestra);
		verifyZeroInteractions(this.muestra);
	}

	@Test
	public void testActualizarMuestraConOpinionExperta() {
		this.opinionExperta.actualizarMuestra(this.muestra);
		verify(muestra, times(1)).setEstado(argumentCaptor.capture());
	}
}
