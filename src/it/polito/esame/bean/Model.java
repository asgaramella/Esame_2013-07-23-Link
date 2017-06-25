package it.polito.esame.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.Multigraph;

import it.polito.esame.dao.IscrittiDAO;

public class Model {
	
	private List<Studente> studenti;
	private List<Corso> corsi;
	private CorsoIdMap corsoIdMap;
	private StudenteIdMap studenteIdMap;
	private IscrittiDAO dao;
	private UndirectedGraph<Studente,Link> graph;
	private int max;
	
	public Model() {
		super();
		corsoIdMap=new CorsoIdMap();
		studenteIdMap=new StudenteIdMap();
		dao=new IscrittiDAO();
	}
	
	public UndirectedGraph<Studente,Link> getGrafo(){
		if(this.graph==null)
			this.creaGrafo();
		
		return this.graph;
	}
	
	


	private void creaGrafo() {
		this.graph= new Multigraph<>(Link.class);
		
		Graphs.addAllVertices(graph, this.getStudenti());
		for(Corso ctemp:this.getCorsi()){
			for(Studente studente: ctemp.getIscritti()){
				for(Studente collega: ctemp.getIscritti()){
					if(!studente.equals(collega)){
					Link ltemp=graph.addEdge(studente, collega);
					if(ltemp!=null)
						ltemp.setCorso(ctemp);
					}
				}
				
			}
		}
		
	}
	
	
	public int getMaxCorsi(Studente s){
		int max=0;
		List<Studente> colleghi = Graphs.neighborListOf(this.getGrafo(), s);
		for(Studente stemp: colleghi){
			if(max<graph.getAllEdges(s, stemp).size())
				max=graph.getAllEdges(s, stemp).size();
		}
		this.max=max;
		return max/2;
	}
	
	public Set<Studente> getSimili(Studente s){
		Set<Studente> ltemp=new HashSet();
		List<Studente> colleghi=Graphs.neighborListOf(this.getGrafo(), s);
		for(Studente stemp:colleghi){
			if(graph.getAllEdges(s, stemp).size()==max)
				ltemp.add(stemp);
			
		}
		return ltemp;
	}

	public List<Studente> getStudenti(){
		if(studenti==null){
			studenti=dao.getAllStudente(studenteIdMap);
			for(Studente stemp: studenti){
				stemp.getCaricodidattico().addAll(dao.getCorsiByStudente(stemp, corsoIdMap));
			}
			
		}
		return studenti;
	}
	
	
	
	public List<Corso> getCorsi(){
		if(corsi==null){
			corsi=dao.getAllCorso(corsoIdMap);
			for(Corso ctemp:corsi){
				ctemp.getIscritti().addAll(dao.getStudentiByCorso(ctemp, studenteIdMap));
			}
		}
		
		return corsi;
	}


	
	
	
	
	
	public List<Corso> getCorsiStudente(int matricola) {
		List<Corso> ltemp=new ArrayList<Corso>(studenteIdMap.get(matricola).getCaricodidattico());
		return ltemp;
	}

	public Studente getStudente(int matricola) {
		this.getStudenti();
		return studenteIdMap.get(matricola);
	}

	

	

	
}
