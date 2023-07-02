package opinion;

import java.time.LocalDate;

import muestra.EstadoMuestraExperto;
import muestra.Muestra;
import participantes.Participante;

public class OpinionExperta extends Opinion {

	public OpinionExperta(TipoDeOpinion tipoDeOpinion, Participante autor, LocalDate fechaDeCreacion) {
		super(tipoDeOpinion, autor, fechaDeCreacion);
	}

	@Override
	public void actualizarMuestra(Muestra muestra) {
		muestra.setEstado(new EstadoMuestraExperto());
	}

}
