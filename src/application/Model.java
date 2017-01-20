package application;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import bean.CoppieDate;
import bean.IeriOggi;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private DefaultDirectedGraph<LocalDate, DefaultEdge> grafo;
	
	public List<String> getCitta(){
		List<String> citta = dao.getCitta();
		return citta;
	}
	
	public List<IeriOggi> getElenco(String localita){
		List<IeriOggi> elenco = dao.getCoppie(localita);
		return elenco;
	}
	
	public List<CoppieDate> getCoppieDate(String localita){
		List<CoppieDate> coppieDate = dao.getCoppieDate(localita);
		return coppieDate;
	}
		
	public List<LocalDate> getVertici(String localita){
		List<LocalDate> vertici = new LinkedList<>();
		List<IeriOggi> elenco = getElenco(localita);
		for(IeriOggi e : elenco){
			if(!vertici.contains(e.getIeri())){
				vertici.add(e.getIeri());
			}
			if(!vertici.contains(e.getOggi())){
				vertici.add(e.getOggi());
			}
		}
		System.out.println(vertici.toString());
		return vertici;
	}
	
	//il grafo non serve
	//ho come vertici le date di quella citta
	//collego le due date se ieri ha avuto una temp piu bassa di 2 rispetto a t di oggi
	
	public void buildGraph(String localita){                                             //parte
		grafo = new DefaultDirectedGraph<LocalDate, DefaultEdge>(DefaultEdge.class);
		Graphs.addAllVertices(grafo,  getVertici(localita));
		List<IeriOggi> elenco = getElenco(localita);
		for(IeriOggi e : elenco){
			if(e.getIeri_t() <= e.getOggi_t()+2){
				grafo.addEdge(e.getIeri(),  e.getOggi());
			}
		}
		System.out.println(grafo.toString());
	}
	
	
	
	public static void main(String [] args){
		Model m = new Model();
		//m.getVertici("torino");
		m.buildGraph("torino");
	}
			
//	public List<LocalDate> getVerticiDate(String city){
//		List<LocalDate> all= dao.tutteLedateDiQuellaCitta(city);
//		return all;
//	}
//
//	public List<Situazione> getAllSit(String citta){
//		List<Situazione> allS= dao.getAllSituazioniOrdinate(citta);
//		return allS;
//	}
//	
//	public void buildGraph(String city){
//		grafo = new DefaultDirectedGraph<Integer, DefaultEdge> (DefaultEdge.class);
//		List<Situazione> sit = getAllSit(city);   //tutte le situazioni di quella citta
//		for(int i =0; i<sit.size(); i++){
//			grafo.addVertex(sit.get(i).gettMax());
//		}
//		
//		for(Integer vertice : grafo.vertexSet()){
//			for(int i = 0; i<sit.size()-1; i++){
//			    Integer primo =   sit.get(i).gettMax();       //ieri
//			    Integer secondo = sit.get(i+1).gettMax();    //oggi   //non è corretto 
//			    if(primo!=secondo){
//		    	if(primo <= secondo + 2 ){  
//		    		if(!grafo.containsEdge(primo, secondo)){
//		    			grafo.addEdge(primo,  secondo);
//		    		}
//			     }}
//		
//			}
//			}
//		System.out.println(grafo.toString());
//	}

//	public static void main(String [] args){
//		Model m = new Model();
//			m.buildGraph("Torino");
//	}

}
