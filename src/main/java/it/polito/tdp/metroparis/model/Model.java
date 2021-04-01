package it.polito.tdp.metroparis.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import it.polito.tdp.metroparis.StampType;
import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	//dichiaro il dao
	private MetroDAO dao;
	
	//dichiaro il grafo
	private Graph<Fermata, DefaultWeightedEdge> graph;
	
	//dichiro la lista di fermate (nodi)
	private List<Fermata> fermate;
	//dichiaro la mappatura dei nodi
	private Map<Integer, Fermata> fermateIdMap;
	
	//dischiaro la lista di linee
	private List<Linea> linee;
	//dichiaro la mappatura delle linee
	private Map<Integer, Linea> lineeIdMap;
	
	public Graph<Fermata, DefaultWeightedEdge> getGraph() {
		return graph;
	}

	public void setGraph(DirectedWeightedMultigraph<Fermata, DefaultWeightedEdge> graph) {
		this.graph = graph;
	}

	public List<Fermata> getFermate() {
		return fermate;
	}

	public void setFermate(List<Fermata> fermate) {
		this.fermate = fermate;
	}

	public Map<Integer, Fermata> getFermateIdMap() {
		return fermateIdMap;
	}

	public void setFermateIdMap(Map<Integer, Fermata> fermateIdMap) {
		this.fermateIdMap = fermateIdMap;
	}

	public Model() {
		
		//definisco il grafo
		this.graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		//definisco il dao
		dao = new MetroDAO();
		
		//mi salvo i dati sulle fermate ricevuti dal database
		this.fermate = dao.getAllFermate();
		fermateIdMap = new HashMap<Integer, Fermata>();
		for (Fermata f : this.fermate) {
			fermateIdMap.put(f.getIdFermata(),f);
		}
		
		//mi salvo i dati sulle linee ricevuti dal database
		this.linee = dao.getAllLinee();
		lineeIdMap = new HashMap<Integer, Linea>();
		for (Linea l : this.linee) {
			lineeIdMap.put(l.getIdLinea(),l);
		}
		
		//creazione vertici
		Graphs.addAllVertices(this.graph, this.fermate);
		
		//creazione archi
		List<Coppia> CF = dao.getAllEdges(fermateIdMap);
		for (Coppia c : CF) {
			Graphs.addEdge(graph, c.getPartenza(), c.getArrivo(), c.getPeso()); 
		}
		
		
	}

	//funzione principale: data una partenza ed un arrivo, 
	//ritorna i dati da stampare sulla tabella che ho nel controller
	public List<StampType> getPercorso(Fermata p, Fermata a) {
		
		//lista di servizio che andrò a popolare
		List <StampType> BOX = new ArrayList<StampType>();
	
		//chiamo Dijkstra sul grafo partendo ed arrivato da ciò che l'utente mi ha detto
		//questa funzione restituisce un percorso
		GraphPath<Fermata, DefaultWeightedEdge> gp = DijkstraShortestPath.findPathBetween(graph, p, a);
		
		//prendo tutte le fermate del percorso che ho trovato
		List<Fermata> FermataShortestPath = gp.getVertexList();
		
		//ora devo popolare la mia lista BOX con i dati che ho trovato ed ho
		for (DefaultWeightedEdge dwe : gp.getEdgeList()) {
			
			//dato un arco pesato...in quale linea mi trovo? Lo chiedo al dao!
			int IdLineaTrovata = dao.QualeLinea(graph.getEdgeSource(dwe),graph.getEdgeTarget(dwe));
			
			//recupero la linea tramite l'id che mi ha dato il dao
			Linea LineaInCuiMiTrovo = lineeIdMap.get(IdLineaTrovata);
			
			//genero l'oggetto StampType che andrò poi ad inserire nel BOX
			StampType s = new StampType(
					//Intervallo = peso dell'arco - velocità
					graph.getEdgeWeight(dwe)-LineaInCuiMiTrovo.getVelocita(),
					//Velocità = peso dell'arco - intervallo
					graph.getEdgeWeight(dwe)-LineaInCuiMiTrovo.getIntervallo(),
					//nome della linea
					LineaInCuiMiTrovo.getNome(),
					//X ed Y verranno inserite successivamente
					"",
					"",
					//Arco...e quindi i due vertici fermata che mi servono!
					dwe.toString(),
					//colore della linea in caso si voglia colorare la tabella con CSS
					LineaInCuiMiTrovo.getColore());
			
			BOX.add(s);
			
		}
	
		//generazione delle X -> differenza in linea d'aria tra X di partenza e arrivo
		for (int i=0; i<FermataShortestPath.size()-1; i++) {
			BOX.get(i).setX(
					FermataShortestPath.get(i).getX().toString()+
					" -> "+
					FermataShortestPath.get(i+1).getX().toString());
			
		}
		
		//generazione delle Y -> differenza in linea d'aria tra Y di partenza e arrivo
		for (int i=0; i<FermataShortestPath.size()-1; i++) {
			BOX.get(i).setY(
					FermataShortestPath.get(i).getY().toString()+
					" -> "+
					FermataShortestPath.get(i+1).getY().toString());
			
		}
		//-----------------------------------------------------------------------------
		

		return BOX;
	}
	

}
