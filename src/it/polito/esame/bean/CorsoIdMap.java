package it.polito.esame.bean;

import java.util.HashMap;
import java.util.Map;



public class CorsoIdMap {
	private Map<String,Corso> map;

	public CorsoIdMap() {
		map=new HashMap<>();
	}
	
	public Corso get(String codins){
		return map.get(codins);
	}
	
	public Corso put(Corso c){
		Corso old=map.get(c.getCodins());
		if(old==null){
			map.put(c.getCodins(), c);
			return c;
		}
		else
			return old;
	}

}
