package it.polito.tdp.metroparis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import it.polito.tdp.metroparis.model.Coppia;
import it.polito.tdp.metroparis.model.Fermata;
import it.polito.tdp.metroparis.model.Linea;

//classe dedicata alle interrogazioni sul database
public class MetroDAO {

	//chiede al database i dati su tutte le fermate
	public List<Fermata> getAllFermate() {

		final String sql = "SELECT id_fermata, nome, coordx, coordy FROM fermata ORDER BY nome ASC";
		List<Fermata> fermate = new ArrayList<Fermata>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Fermata f = new Fermata(
						rs.getInt("id_Fermata"), 
						rs.getString("nome"),
						rs.getDouble("coordx"), 
						rs.getDouble("coordy"));
				fermate.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return fermate;
	}

	//chiede al database i dati su tutte le linee
	public List<Linea> getAllLinee() {
		final String sql = "SELECT id_linea, nome, velocita, intervallo, colore FROM linea ORDER BY nome ASC";

		List<Linea> linee = new ArrayList<Linea>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Linea f = new Linea(
						rs.getInt("id_linea"), 
						rs.getString("nome"), 
						rs.getDouble("velocita"),
						rs.getDouble("intervallo"),
						rs.getString("colore"));
				linee.add(f);
			}

			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}

		return linee;
	}
	
	//chiede al database i dati su tutti gli archi
	public List<Coppia> getAllEdges(Map<Integer, Fermata> fermateIdMap) {
		
		final String sql = "SELECT id_stazP, id_stazA, linea.velocita+linea.intervallo AS peso\n"
				+ "FROM connessione\n"
				+ "LEFT JOIN linea\n"
				+ "ON linea.id_linea=connessione.id_linea";

		List<Coppia> C = new ArrayList<Coppia>();

		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int p = rs.getInt("id_stazP");
				int a = rs.getInt("id_stazA");
				Double peso = rs.getDouble("peso");
				
				Coppia c = new Coppia(fermateIdMap.get(p),fermateIdMap.get(a),peso);
				
				C.add(c);
			}

			st.close();
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
		return C;

		
	}

	//data la fermata di inizio e fine, ritrovo in quale linea mi trovo
	public int QualeLinea(Fermata edgeSource, Fermata edgeTarget) {
		
		final String sql = "SELECT id_linea\n"
				+ "FROM connessione\n"
				+ "WHERE id_stazP=? AND id_stazA=?";
		
		int ritorno = 0;

		try {
			
			Connection conn = DBConnect.getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, edgeSource.getIdFermata());
			st.setInt(2, edgeTarget.getIdFermata());
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				ritorno = rs.getInt("id_linea");
				
			}

			st.close();
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore di connessione al Database.");
		}
		
		return ritorno;
	}
	




}
