package muestra;

import java.util.List;

import opinion.Opinion;
import opinion.TipoDeOpinion;

public class EstadoMuestraBasico extends EstadoDeMuestra {

	@Override
	public TipoDeOpinion resultadoActual(Muestra muestra) {
		
		List<Opinion> opiniones = muestra.getOpiniones(); // toma todas las opiniones de la muestra (solo hay basicos)
		
		return tipoConMasCoincidencia(opiniones);
	}

	@Override
	public void verificarOActualizar(Muestra muestra, Opinion opinion) {
		// actualiza el estado de la muestra si puede (opinion es de experto)
		opinion.actualizarMuestra(muestra);
		}

	@Override
	public boolean soloOpinaronBasicos() {
		return true;
	}
	

}