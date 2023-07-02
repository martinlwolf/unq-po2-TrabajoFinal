package muestra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import opinion.Opinion;
import opinion.OpinionExperta;
import opinion.TipoDeOpinion;
import participantes.Participante;
import sistemaVinchucas.SistemaVinchucas;
import ubicacion.Ubicacion;

public class Muestra {
	private boolean verificada;
	private Ubicacion ubicacion;
	private Participante autor;
	private List<Opinion> opiniones;
	private LocalDate fecha;
	private EstadoDeMuestra estado;

	// CONSTRUCTORES
	public Muestra(Participante autor, LocalDate fecha, Ubicacion ubicacion) {
		this.verificada = false;
		this.opiniones = new ArrayList<Opinion>();
		this.setAutor(autor);
		this.setUbicacion(ubicacion);
		this.fecha = fecha;
		this.estado = new EstadoMuestraBasico();
		SistemaVinchucas.instanciaUnica().muestraCreada(this);
	}

	public Muestra(Participante autor, Ubicacion ubicacion) {
		this.verificada = false;
		this.opiniones = new ArrayList<Opinion>();
		this.setAutor(autor);
		this.setUbicacion(ubicacion);
		this.fecha = LocalDate.now();
		this.estado = new EstadoMuestraBasico();
	}

////GETS Y SETS

	private void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	private void setAutor(Participante autor) {
		this.autor = autor;
	}

	public Participante getAutor() {
		return autor;
	}

	public boolean isVerificada() {
		return verificada;
	}

	public List<Opinion> getOpiniones() {
		return this.opiniones;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setVerificada() {
		this.verificada = true;
		SistemaVinchucas.instanciaUnica().muestraVerificada(this);
	}

	public void setEstado(EstadoDeMuestra estado) {
		this.estado = estado;
	}

	public EstadoDeMuestra getEstado() {
		return estado;
	}

//// MENSAJES PRINCIPALES

	public TipoDeOpinion resultadoActual() {
		return this.estado.resultadoActual(this);
	}

	public void agregarOpinion(Opinion opinion) {

		this.estado.verificarOActualizar(this, opinion);
		this.opiniones.add(opinion);
	}

	public boolean fueOpinadaPor(Participante participante) {
		return this.getOpiniones().stream().anyMatch(o -> o.getAutor().equals(participante)); 
	}

	public boolean soloOpinaronBasicos() {
		return this.getEstado().soloOpinaronBasicos();
	}

	public List<Opinion> getOpinionesExpertas() {
		return this.opiniones.stream().filter(opinion -> opinion instanceof OpinionExperta).toList();
	}

	public List<Muestra> muestrasQueEstenAUnaDistanciaMenorA(List<Muestra> listaDeMuestras, double kilometros) {
		return listaDeMuestras.stream().filter(muestra -> muestra.laMuestraSeEncuentraAMenosDe(this, kilometros))
				.toList();
	}

	public boolean laMuestraSeEncuentraAMenosDe(Muestra muestra, double kilometros) {
		return this.ubicacion.laUbicacionSeEncuentraAMenosDe(muestra.getUbicacion(), kilometros);
	}

}
