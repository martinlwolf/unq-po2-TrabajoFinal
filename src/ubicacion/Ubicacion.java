package ubicacion;

import java.util.List;

public class Ubicacion {

	private double latitud;
	private double longitud;

	public Ubicacion(Double latitud, Double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double distanciaA(Ubicacion ubicacion) {
		double radioDeLaTierraEnKilometros = 6371;
		double deltaLatitud = Math.toRadians(this.latitud - ubicacion.getLatitud());
		double deltaLongitud = Math.toRadians(this.longitud - ubicacion.getLongitud());
		double a = Math.pow(Math.sin(deltaLatitud / 2), 2) + Math.cos(Math.toRadians(this.getLatitud()))
				* Math.cos(Math.toRadians(ubicacion.getLatitud())) * Math.pow(Math.sin(deltaLongitud / 2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = radioDeLaTierraEnKilometros * c;
		return distance;
	}

	public List<Ubicacion> ubicacionesQueSeEncuentranAMenosDe(List<Ubicacion> listaDeUbicaciones, double kilometros) {
		return listaDeUbicaciones.stream()
				.filter(ubicacion -> this.laUbicacionSeEncuentraAMenosDe(ubicacion, kilometros)).toList();
	}

	public boolean laUbicacionSeEncuentraAMenosDe(Ubicacion ubicacion, double kilometros) {
		return this.distanciaA(ubicacion) < kilometros;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

}
