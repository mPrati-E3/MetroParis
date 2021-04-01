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
	
	private Graph<Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	private Map<Integer, Fermata> fermateIdMap;
	
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
		
		this.graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
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
			Graphs.addEdge(graph, c.getPartenza(), c.getArrivo(), c.getPeso()); 
		}
		
		
		
		System.out.println("Graph created: V="+this.graph.vertexSet().size()+" E="+this.graph.edgeSet().size());
		
	}

	public List<StampType> getPercorso(Fermata p, Fermata a) {
		
		DijkstraShortestPath<Fermata, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<Fermata, DefaultWeightedEdge>(graph);
		ArrayList<Fermata> overallShortestPath = new ArrayList<Fermata>();
	
		GraphPath<Fermata, DefaultWeightedEdge> gp = DijkstraShortestPath.findPathBetween(graph, p, a);
		
		System.out.println(gp);
		
		//qui dentro ho nome fermata e le coordinate x,y
		List<Fermata> FermataShortestPath = gp.getVertexList();
		
		/*
		List<Double> PesoShortestPath = new ArrayList<Double>();
		for (DefaultWeightedEdge dwe : gp.getEdgeList()) {
			Double d = Double.parseDouble(dwe.toString());
			PesoShortestPath.add(d);
		}
		*/
		return null;
	}
	

}
