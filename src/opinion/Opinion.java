package opinion;

import java.time.LocalDate;

import muestra.Muestra;
import participantes.Participante;

public abstract class Opinion {
	private TipoDeOpinion tipoDeOpinion;
	private Participante autor;
	private LocalDate fechaDeCreacion;

	public Opinion(TipoDeOpinion tipoDeOpinion, Participante autor, LocalDate fechaDeCreacion) {
		this.tipoDeOpinion = tipoDeOpinion;
		this.autor = autor;
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public Participante getAutor() {
		return autor;
	}

	public LocalDate getFecha() {
		return this.fechaDeCreacion;
	}

	public TipoDeOpinion getTipoDeOpinion() {
		return tipoDeOpinion;
	}

	public abstract void actualizarMuestra(Muestra muestra);

}
