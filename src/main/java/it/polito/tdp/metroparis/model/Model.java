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
	
	private MetroDAO dao;
	
	private Graph<Fermata, DefaultWeightedEdge> graph;
	private List<Fermata> fermate;
	private Map<Integer, Fermata> fermateIdMap;
	private List<Linea> linee;
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
		
		this.graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		
		dao = new MetroDAO();
		
		this.fermate = dao.getAllFermate();
		fermateIdMap = new HashMap<Integer, Fermata>();
		for (Fermata f : this.fermate) {
			fermateIdMap.put(f.getIdFermata(),f);
		}
		
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

	public List<StampType> getPercorso(Fermata p, Fermata a) {
		
		List <StampType> BOX = new ArrayList<StampType>();
	
		GraphPath<Fermata, DefaultWeightedEdge> gp = DijkstraShortestPath.findPathBetween(graph, p, a);
		
		//System.out.println(gp);
		
		List<Fermata> FermataShortestPath = gp.getVertexList();
		
		//qui dentro ho nome fermata e le coordinate x,y ---> le metto nella lista BOX
		for (DefaultWeightedEdge dwe : gp.getEdgeList()) {
			
			int IdLineaTrovata = dao.QualeLinea(graph.getEdgeSource(dwe),graph.getEdgeTarget(dwe));
			
			Linea LineaInCuiMiTrovo = lineeIdMap.get(IdLineaTrovata);
			
			
			StampType s = new StampType(
					graph.getEdgeWeight(dwe)-LineaInCuiMiTrovo.getVelocita(),
					graph.getEdgeWeight(dwe)-LineaInCuiMiTrovo.getIntervallo(),
					LineaInCuiMiTrovo.getNome(),
					"",
					"",
					dwe.toString(),
					LineaInCuiMiTrovo.getColore());
			
			BOX.add(s);
			
		}
	
		
		for (int i=0; i<FermataShortestPath.size()-1; i++) {
			BOX.get(i).setX(
					FermataShortestPath.get(i).getX().toString()+
					" -> "+
					FermataShortestPath.get(i+1).getX().toString());
			
		}
		
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
