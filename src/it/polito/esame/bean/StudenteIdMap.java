package it.polito.esame.bean;

import java.util.HashMap;
import java.util.Map;

public class StudenteIdMap {
	
	private Map<Integer,Studente> map;

	public StudenteIdMap() {
		map=new HashMap<>();
	}
	
	public Studente get(int matricola){
		return map.get(matricola);
	}
	
	public Studente put(Studente s){
		Studente old=map.get(s.getMatricola());
		if(old==null){
			map.put(s.getMatricola(), s);
			return s;
		}
		else
			return old;
	}


}
