package muestraTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import muestra.EstadoMuestraBasico;
import muestra.EstadoMuestraExperto;
import muestra.Muestra;
import opinion.Opinion;
import opinion.OpinionBasica;
import opinion.OpinionExperta;
import opinion.TipoDeOpinion;
import participantes.Participante;
import ubicacion.Ubicacion;

public class EstadoMuestraBasicoTestCase {
	private EstadoMuestraBasico estadoBasico;
	@Mock private Muestra muestra;
	@Mock private Opinion op1;
	@Mock private Opinion op2;
	@Mock private OpinionExperta op3;
	@Mock private Opinion op4;
	@Mock private Opinion op5;
	@Mock private Ubicacion ubicacion;
	
	@BeforeEach
	public void setUp() {
		this.estadoBasico = new EstadoMuestraBasico();
		this.ubicacion = mock(Ubicacion.class);
		this.muestra = mock(Muestra.class);
		this.op1 = mock(Opinion.class);
		this.op2 = mock(Opinion.class);
		this.op3 = mock(OpinionExperta.class);
		this.op4 = mock(OpinionBasica.class);
		this.op5 = mock(Opinion.class);
	}
	
	@Test
	public void testElEstadoSeActualizaDeBasicoAExperto() {
		Muestra muestraParaTestear = new Muestra(mock(Participante.class), ubicacion); //Se creo una instancia particular de Muestra para poder testear el cambio de estado de forma dinamica
		this.estadoBasico.verificarOActualizar(muestraParaTestear, op3);
		verify(op3, times(1)).actualizarMuestra(muestraParaTestear);
	}
	@Test
	public void testElEstadoNoSeActualizaDeBasicoAExperto() {
		Muestra muestraParaTestear = new Muestra(mock(Participante.class), ubicacion); //Se creo una instancia particular de Muestra para poder testear el cambio de estado de forma dinamica
		this.estadoBasico.verificarOActualizar(muestraParaTestear, op4); // llega una opinion basica por tanto no debe actualizar el estado
		assertFalse(muestraParaTestear.getEstado() instanceof EstadoMuestraExperto);
	}
	
	@Test
	public void testTipoDeOpinionConMasCoincidencias() {
		when(this.op1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
		when(this.op4.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op5.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
		List<Opinion> streamDeOpiniones = Arrays.asList(op1,op2,op3,op4,op5);
		assertEquals(TipoDeOpinion.VINCHUCA_GUASAYANA, this.estadoBasico.tipoConMasCoincidencia(streamDeOpiniones));
	}
	
	@Test
	public void testTipoDeOpinionConMasCoincidenciasDaEmpate() {
		when(this.op1.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op2.getTipoDeOpinion()).thenReturn(TipoDeOpinion.NINGUNA);
		when(this.op3.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
		when(this.op4.getTipoDeOpinion()).thenReturn(TipoDeOpinion.VINCHUCA_GUASAYANA);
		when(this.op5.getTipoDeOpinion()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);
		List<Opinion> streamDeOpiniones = Arrays.asList(op1,op2,op3,op4,op5);
		assertNull(this.estadoBasico.tipoConMasCoincidencia(streamDeOpiniones)); //En caso de empate, devuelve null
	}
}
