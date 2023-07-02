package nivelesParticipantes;

import java.time.LocalDate;

import muestra.Muestra;
import opinion.Opinion;
import opinion.OpinionExperta;
import opinion.TipoDeOpinion;
import participantes.Participante;

public class NivelExperto extends Nivel {

	@Override
	public void actualizarNivel(Participante participante) {
		if (this.debeCambiarSuNivel(participante)) {
			participante.setNivel(new NivelBasico());
		}
	}

	public boolean cumpleEnviosSuficientesParaCambiar(Participante participante) {
		return participante.cantEnviosDeLosUltimos30Dias(LocalDate.now()) < 10;
	}

	public boolean cumpleRevisionesSuficientesParaCambiar(Participante participante) {
		return participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now()) < 20;
	}

	@Override
	public boolean puedeOpinar(Muestra m) {
		return true;
	}

	@Override
	public Opinion crearOpinion(TipoDeOpinion tipoDeOpinion, Participante p, LocalDate fechaDeCreacion) {
		return new OpinionExperta(tipoDeOpinion, p, fechaDeCreacion);
	}
	
	public boolean debeCambiarSuNivel(Participante participante) {
		return cumpleRevisionesSuficientesParaCambiar(participante)
				|| cumpleEnviosSuficientesParaCambiar(participante);
	}

}
