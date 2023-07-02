package zonaCobertura;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;

import muestra.Muestra;
import organizacion.Organizacion;
import sistemaVinchucas.SistemaVinchucas;
import ubicacion.Ubicacion;

public class ZonaCobertura {
	private String nombre;
	private Ubicacion epicentro;
	private int radio;
	private List<Muestra> muestrasReportadas;
	private List<Organizacion> organizaciones;

	public ZonaCobertura(String nombre, Ubicacion epicentro, int radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.muestrasReportadas = new ArrayList<>();
		this.organizaciones = new ArrayList<>();
		SistemaVinchucas.instanciaUnica().agregarZonaAlSistema(this);
	}

	public String getNombre() {
		return nombre;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public int getRadio() {
		return radio;
	}

	public List<Muestra> getMuestrasReportadas() {
		return muestrasReportadas;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void addOrganizacion(Organizacion org) {
		this.organizaciones.add(org);
	}

	public void removeOrganizacion(Organizacion org) {
		this.organizaciones.remove(org);
	}

	public List<ZonaCobertura> zonasQueSeSolapan() {
		Stream<ZonaCobertura> zonasExistentes = SistemaVinchucas.instanciaUnica().getListaDeZonasExistentes().stream()
				.filter(zona -> !this.equals(zona));
		return zonasExistentes.filter(zona -> zona.seSolapaCon(this)).toList();
	}

	public boolean seSolapaCon(ZonaCobertura zonaB) {
		return this.epicentro.distanciaA(zonaB.getEpicentro()) < this.radio + zonaB.getRadio();
	}

	public void seCreoLaMuestra(Muestra muestra) {
		if (this.leCorrespondeMuestra(muestra)) {
			this.muestrasReportadas.add(muestra);
			this.organizaciones.stream().forEach(organizacion -> organizacion.cargaRealizada(this, muestra));
		}
		
	}

	public void seVerificoLaMuestra(Muestra muestra) {
		if (this.leCorrespondeMuestra(muestra)) {
			this.organizaciones.stream().forEach(organizacion -> organizacion.validacionRealizada(this, muestra));
		}
	}

	public boolean leCorrespondeMuestra(Muestra muestra) {
		return this.getEpicentro().laUbicacionSeEncuentraAMenosDe(muestra.getUbicacion(), this.getRadio());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZonaCobertura other = (ZonaCobertura) obj;
		return Objects.equals(epicentro, other.epicentro) && Objects.equals(nombre, other.nombre)
				&& radio == other.radio;
	}
	
	
	
	

}
