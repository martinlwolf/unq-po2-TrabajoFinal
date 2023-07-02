package organizacion;

import muestra.Muestra;
import ubicacion.Ubicacion;
import zonaCobertura.ZonaCobertura;

public class Organizacion {
	
	private Ubicacion ubicacion;
	private TipoOrganizacion tipo;
	private int cantEmpleados;
	private FuncionalidadExterna funcCarga;
	private FuncionalidadExterna funcValidacion;

	public Organizacion(Ubicacion ubicacion, TipoOrganizacion tipo, int cantEmpleados, FuncionalidadExterna funcCarga,
			FuncionalidadExterna funcValidacion) {
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cantEmpleados = cantEmpleados;
		this.funcCarga = funcCarga;
		this.funcValidacion = funcValidacion;
	}

	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public TipoOrganizacion getTipo() {
		return tipo;
	}

	public int getCantEmpleados() {
		return cantEmpleados;
	}

	/////////////////////////////////////////////
	
	public void setFuncCarga(FuncionalidadExterna funcCarga) {
		this.funcCarga = funcCarga;
	}

	public void setFuncValidacion(FuncionalidadExterna funcValidacion) {
		this.funcValidacion = funcValidacion;
	}
	
	public void cargaRealizada(ZonaCobertura zona, Muestra muestra) {
		this.funcCarga.nuevoEvento(this, zona, muestra);
	}
	
	public void validacionRealizada(ZonaCobertura zona, Muestra muestra) {
		this.funcValidacion.nuevoEvento(this, zona, muestra);
	}
	
	public void suscribirseAZona(ZonaCobertura zona) {
		zona.addOrganizacion(this);
	}
	
	public void desuscribirseDeZona(ZonaCobertura zona) {
		zona.removeOrganizacion(this);
	}

}
