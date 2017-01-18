package application;

import java.util.List;

import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import bean.Situazione;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private DefaultDirectedGraph<Integer, DefaultEdge> grafo;
	
	public List<String> getCitta(){
		List<String> citta = dao.getCitta();
		return citta;
	}
	
//	public List<LocalDate> getVerticiDate(String city){
//		List<LocalDate> all= dao.tutteLedateDiQuellaCitta(city);
//		return all;
//	}

	public List<Situazione> getAllSit(String citta){
		List<Situazione> allS= dao.getAllSituazioniOrdinate(citta);
		return allS;
	}
	
	public void buildGraph(String city){
		grafo = new DefaultDirectedGraph<Integer, DefaultEdge> (DefaultEdge.class);
		List<Situazione> sit = getAllSit(city);   //tutte le situazioni di quella citta
		for(int i =0; i<sit.size(); i++){
			grafo.addVertex(sit.get(i).gettMax());
		}
		
		for(Integer vertice : grafo.vertexSet()){
			for(int i = 0; i<sit.size()-1; i++){
			    Integer primo =   sit.get(i).gettMax();       //ieri
			    Integer secondo = sit.get(i+1).gettMax();    //oggi   //non è corretto 
			    if(primo!=secondo){
		    	if(primo <= secondo + 2 ){  
		    		if(!grafo.containsEdge(primo, secondo)){
		    			grafo.addEdge(primo,  secondo);
		    		}
			     }}
		
			}
			}
		System.out.println(grafo.toString());
	}
	
	
	
	
	public static void main(String [] args){
		Model m = new Model();
			m.buildGraph("Torino");
	}
	
	
	
}
