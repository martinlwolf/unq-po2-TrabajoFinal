package muestra;

import java.util.List;

import opinion.Opinion;
import opinion.TipoDeOpinion;

public class EstadoMuestraExperto extends EstadoDeMuestra {

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		
		List<Opinion> opiniones = muestra.getOpinionesExpertas();
		
		return tipoConMasCoincidencia(opiniones);
	}

	@Override
	public void verificarOActualizar(Muestra muestra, Opinion opinion) {
		// Verifica la muestra en caso de que se pueda.
		boolean anyMatch = muestra.getOpinionesExpertas().stream().anyMatch(opMuestra->opMuestra.getTipoDeOpinion().equals(opinion.getTipoDeOpinion()));
		if(anyMatch) {
			muestra.setVerificada();
		}
	}

	@Override
	public boolean soloOpinaronBasicos() {
		return false;
	} 

}
