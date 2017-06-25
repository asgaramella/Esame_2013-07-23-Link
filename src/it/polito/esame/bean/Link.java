package it.polito.esame.bean;

import org.jgrapht.graph.DefaultEdge;

public class Link extends DefaultEdge{
	
	private Corso corso;
	
	public Link() {
		super();
		
	}

	/**
	 * @return the corso
	 */
	public Corso getCorso() {
		return corso;
	}

	/**
	 * @param corso the corso to set
	 */
	public void setCorso(Corso corso) {
		this.corso = corso;
	}

	
	
	
	


	@Override
	public String toString() {
		return corso.toString();
	}
	
	
	
	
	

}
