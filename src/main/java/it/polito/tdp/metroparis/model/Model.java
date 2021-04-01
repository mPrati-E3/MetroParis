package it.polito.tdp.metroparis.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import it.polito.tdp.metroparis.db.MetroDAO;

public class Model {
	
	private Graph<Fermata, DefaultEdge> graph;
	private List<Fermata> fermate;
	private Map<Integer, Fermata> fermateIdMap;
	
	public Graph<Fermata, DefaultEdge> getGraph() {
		return graph;
	}

	public void setGraph(Graph<Fermata, DefaultEdge> graph) {
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
		
		this.graph = new SimpleDirectedGraph<>(DefaultEdge.class);
		
		MetroDAO dao = new MetroDAO();
		
		this.fermate = dao.getAllFermate();
		fermateIdMap = new HashMap<Integer, Fermata>();
		for (Fermata f : this.fermate) {
			fermateIdMap.put(f.getIdFermata(),f);
		}
		
		//creazione vertici
		Graphs.addAllVertices(this.graph, this.fermate);
		
		//creazione archi
		List<Coppia> CF = dao.getAllEdges(fermateIdMap);
		for (Coppia c : CF) {
			this.graph.addEdge(c.getPartenza(), c.getArrivo());
		}
		
		
		
		System.out.println("Graph created: V="+this.graph.vertexSet().size()+" E="+this.graph.edgeSet().size());
		
	}
	
	/*public static void main(String[] args) {
		Model M = new Model();
	}*/

}
