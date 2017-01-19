package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import bean.CoppieDate;
import bean.IeriOggi;
import bean.Situazione;

public class Dao {
	
	public List<String> getCitta(){             //x la combo
		String query="select distinct s.Localita   from situazione s";
		Connection conn = DBConnect.getConnection();
		List<String> citta = new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				citta.add(res.getString("localita"));
			}
			conn.close();
			return citta;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

	// x grafo
	public List<IeriOggi> getCoppie(String localita){
		String query="select distinct ieri.`Data` as ieri , ieri.Tmax as ieri_t  ,oggi.`Data` as oggi, oggi.Tmax as oggi_t "
				+ " from situazione oggi, situazione ieri   "
				+ "where oggi.`Data`<> ieri.`Data`   "
				+ "and oggi.Localita=ieri.Localita and oggi.Localita=?   "
				+ "and DATEDIFF(oggi.`Data`, ieri.`Data`)= 1";
		List<IeriOggi> elenco = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,  localita);
			ResultSet res = st.executeQuery();
			while(res.next()){
				IeriOggi e = new IeriOggi(res.getDate("ieri").toLocalDate(), res.getInt("ieri_t"), res.getDate("oggi").toLocalDate(), res.getInt("oggi_t"));
				elenco.add(e);
			}
			conn.close();
			return elenco;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	//x primo 
	public List<CoppieDate> getCoppieDate(String localita){
		String query="select distinct ieri.`Data` as ieri , ieri.Tmax as ieri_t  ,oggi.`Data` as oggi, oggi.Tmax as oggi_t  "
				+ "from situazione oggi, situazione ieri  "
				+ "where  oggi.Localita=ieri.Localita and oggi.Localita=?  "
				+ "and DATEDIFF(oggi.`Data`, ieri.`Data`)= 1  "
				+ "and ieri.Tmax<= oggi.Tmax+2";
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			List<CoppieDate> coppieDate = new LinkedList<>();
			st.setString(1,  localita);
			ResultSet res = st.executeQuery();
			while(res.next()){
				CoppieDate e = new CoppieDate (res.getDate("ieri").toLocalDate(), res.getDate("oggi").toLocalDate());
				coppieDate.add(e);
			}
			conn.close();
			return coppieDate;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
		
	}
}
