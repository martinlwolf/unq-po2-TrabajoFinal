package muestraTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import muestra.EstadoMuestraExperto;
import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoDeOpinion;

public class EstadoMuestraExpertoTestCase {
	private EstadoMuestraExperto estadoExperto;
	@Mock private Muestra muestra;
	@Mock private Opinion op1;
	@Mock private Opinion op2;
	@Mock private Opinion op3;
	
	@BeforeEach
	public void setUp() {
		this.estadoExperto = new EstadoMuestraExperto();
		this.muestra = mock(Muestra.class);
		this.op1 = mock(Opinion.class);
		this.op2 = mock(Opinion.class);
		this.op3 = mock(Opinion.class);
	}
	
	@Test
	public void testElEstadoMuestraExpertoVerificaLaMuestra() {
		//Se encontro un tipo de opinion igual
		when(this.muestra.getOpinionesExpertas()).thenReturn(Arrays.asList(this.op1));
		when(this.op1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		when(this.op3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		this.estadoExperto.verificarOActualizar(muestra, op3);
		verify(this.muestra, times(1)).setVerificada();
	}
	
	@Test
	public void testElEstadoMuestraExpertoNoVerificaLaMuestra() {
		//No se encontro un tipo de opinion igual
		when(this.muestra.getOpinionesExpertas()).thenReturn(Arrays.asList(this.op1));
		when(this.op1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		this.estadoExperto.verificarOActualizar(muestra, op3);
		verify(this.muestra, times(0)).setVerificada();
	}
	//La existencia de al menos una opinion experta esta asegurada por el mismo estado, que de no ser porque se agrego una opinion basica, nunca se actualizaria a experto.
}
