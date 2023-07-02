package sistemaVinchucas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import muestra.Muestra;
import zonaCobertura.ZonaCobertura;

public class SistemaVinchucas {
	private static SistemaVinchucas instanciaUnicaDeSistema;
	private List<ZonaCobertura> listaDeZonasExistentes;
	private List<Muestra> muestrasReportadas;

	public SistemaVinchucas() { // Sabemos que el constructor deberia ser privada, pero lo cambiamos a publico
								// para poder testear.
		this.listaDeZonasExistentes = new ArrayList<ZonaCobertura>();
		this.muestrasReportadas = new ArrayList<>();
	}

	public static SistemaVinchucas instanciaUnica() {
		if (instanciaUnicaDeSistema == null) {
			instanciaUnicaDeSistema = new SistemaVinchucas();
		}
		return instanciaUnicaDeSistema;
	}

	public void muestraCreada(Muestra muestra) {
		this.notificarNuevaMuestraAZonasSiCorresponde(muestra);
		this.muestrasReportadas.add(muestra);
	}

	private void notificarNuevaMuestraAZonasSiCorresponde(Muestra muestra) {
	this.listaDeZonasExistentes.stream().forEach(zonaInteresada -> zonaInteresada.seCreoLaMuestra(muestra)); 
	}

	public void muestraVerificada(Muestra muestra) {
		this.notificarMuestraVerificadaAZonasSiCorresponde(muestra);
	}

	private void notificarMuestraVerificadaAZonasSiCorresponde(Muestra muestra) {
		this.listaDeZonasExistentes.stream().forEach(zonaInteresada -> zonaInteresada.seVerificoLaMuestra(muestra));
	}

	public void agregarZonaAlSistema(ZonaCobertura zona) {
		
		if(this.listaDeZonasExistentes.stream().allMatch(zonaSistema->!zonaSistema.equals(zona))){
			this.listaDeZonasExistentes.add(zona);
		}

	}

	public void removerZonaDelSistema(ZonaCobertura zona) {
		this.listaDeZonasExistentes.remove(zona);
	}

	public List<ZonaCobertura> getListaDeZonasExistentes() {
		return listaDeZonasExistentes;
	}
	
	public void reset() {
		this.listaDeZonasExistentes.clear();
	}

}
