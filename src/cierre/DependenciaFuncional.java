package cierre;

public class DependenciaFuncional {

	private String implicante;
	private String implicado;

	public DependenciaFuncional(String implicante, String implicado) {
		super();
		this.implicante = implicante.toUpperCase();
		this.implicado = implicado.toUpperCase();
	}


	/**
	 * @return the implicante
	 */
	public String getImplicante() {
		return implicante;
	}


	/**
	 * @param implicante the implicante to set
	 */
	public void setImplicante(String implicante) {
		this.implicante = implicante;
	}


	/**
	 * @return the implicado
	 */
	public String getImplicado() {
		return implicado;
	}


	/**
	 * @param implicado the implicado to set
	 */
	public void setImplicado(String implicado) {
		this.implicado = implicado;
	}


	@Override
	public String toString() {
		return implicante +" -> " +implicado;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((implicado == null) ? 0 : implicado.hashCode());
		result = prime * result + ((implicante == null) ? 0 : implicante.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DependenciaFuncional other = (DependenciaFuncional) obj;
		if (implicado == null) {
			if (other.implicado != null)
				return false;
		} else if (!implicado.equals(other.implicado))
			return false;
		if (implicante == null) {
			if (other.implicante != null)
				return false;
		} else if (!implicante.equals(other.implicante))
			return false;
		return true;
	}




}
