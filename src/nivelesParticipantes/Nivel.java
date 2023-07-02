package nivelesParticipantes;

import java.time.LocalDate;

import muestra.Muestra;
import opinion.Opinion;
import opinion.TipoDeOpinion;
import participantes.Participante;

public abstract class Nivel {
	
	public void opinar(Participante p, Muestra m, TipoDeOpinion tipoDeOpinion, LocalDate fechaDeCreacion) {
		
		if(!m.fueOpinadaPor(p) && this.puedeOpinar(m) && !m.isVerificada()) {
			Opinion nuevaOpinion = this.crearOpinion(tipoDeOpinion, p, fechaDeCreacion);
			p.agregarOpinion(nuevaOpinion); 
			m.agregarOpinion(nuevaOpinion); 
			
			this.actualizarNivel(p); 
		}
	}


	protected abstract Opinion crearOpinion(TipoDeOpinion tipoDeOpinion, Participante p, LocalDate fechaDeCreacion);


	protected abstract void actualizarNivel(Participante p);

	public void recolectar(Participante participante, Muestra muestra) {
		participante.agregarMuestra(muestra); 
		this.actualizarNivel(participante); 
	}
	
	protected abstract boolean puedeOpinar(Muestra m) ;
	
	public boolean debeCambiarSuNivel(Participante participante) {
		return cumpleRevisionesSuficientesParaCambiar(participante)
				&& cumpleEnviosSuficientesParaCambiar(participante);
	}
	
	public abstract boolean cumpleEnviosSuficientesParaCambiar(Participante participante);
	
	public abstract boolean cumpleRevisionesSuficientesParaCambiar(Participante participante);

}

