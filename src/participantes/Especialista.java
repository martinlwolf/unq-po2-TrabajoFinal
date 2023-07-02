package participantes;

import nivelesParticipantes.Nivel;
import nivelesParticipantes.NivelExperto;
import ubicacion.Ubicacion;

public class Especialista extends Participante {
	
	public Especialista() {
		super();
		super.setNivel(new NivelExperto());
	}
	
	@Override
	public void setNivel(Nivel nivel) { 
	}

}
