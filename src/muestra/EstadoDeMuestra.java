package muestra;
import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import opinion.Opinion;
import opinion.TipoDeOpinion;

public abstract class EstadoDeMuestra {

	public abstract TipoDeOpinion resultadoActual(Muestra muestra);

	protected abstract void verificarOActualizar(Muestra muestra, Opinion opinion);
	
	public TipoDeOpinion tipoConMasCoincidencia(List<Opinion> opiniones) {
		
	        Map<TipoDeOpinion, Integer> apariciones = new HashMap<>();
	        // Cuenta las apariciones de cada opinion en la lista
	        for (Opinion opinion : opiniones) {
	            apariciones.put(opinion.getTipoDeOpinion(), apariciones.getOrDefault(opinion.getTipoDeOpinion(), 0) + 1);
	        }

	        int maxApariciones = 0;
	        TipoDeOpinion tipoConMasApariciones = null;
	        boolean huboEmpate = false;

	        // Encuentra la opinion con mas apariciones
	        for (Map.Entry<TipoDeOpinion, Integer> entry : apariciones.entrySet()) {
	            int recuentoActual = entry.getValue();

	            if (recuentoActual > maxApariciones) {
	                maxApariciones = recuentoActual;
	                tipoConMasApariciones = entry.getKey();
	                huboEmpate = false;
	            } else if (recuentoActual == maxApariciones) {
	                huboEmpate = true;
	            }
	        }

	        // Si hubo empate devuelve null
	        if (huboEmpate) {
	        	tipoConMasApariciones = null;
	        }

	        return tipoConMasApariciones;
	    }

	public abstract boolean soloOpinaronBasicos();

}
