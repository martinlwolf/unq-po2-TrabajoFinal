package nivelesParticipantes;

import java.time.LocalDate;

import muestra.Muestra;
import opinion.Opinion;
import opinion.OpinionBasica;
import opinion.TipoDeOpinion;
import participantes.Participante;

public class NivelBasico extends Nivel {

	@Override
	public void actualizarNivel(Participante participante) {
		//SI NO SE CUMPLE LA CONDICION PARA CONTINUAR, EL NIVEL CAMBIA
		if(this.debeCambiarSuNivel(participante)) {
			participante.setNivel(new NivelExperto());
		}
	} 

	@Override
	protected boolean puedeOpinar(Muestra m) {
		return m.soloOpinaronBasicos();
	}

	public boolean cumpleEnviosSuficientesParaCambiar(Participante participante) {
		return participante.cantEnviosDeLosUltimos30Dias(LocalDate.now()) > 10;
	}

	public boolean cumpleRevisionesSuficientesParaCambiar(Participante participante) {
		return participante.cantRevisionesDeLosUltimos30Dias(LocalDate.now()) > 20;
	}

	@Override
	public Opinion crearOpinion(TipoDeOpinion tipoDeOpinion, Participante p, LocalDate fechaDeCreacion) {
		return new OpinionBasica(tipoDeOpinion, p, fechaDeCreacion);
	}
	
}
