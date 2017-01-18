package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import bean.Situazione;

public class Dao {
	
	public List<String> getCitta(){
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

	

	
	public List<Situazione> getAllSituazioniOrdinate (String citta){
		String query="select *  from situazione s  where s.Localita=?  order by s.`Data` asc";
		Connection conn = DBConnect.getConnection();
		List<Situazione> sit = new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,  citta);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Situazione s = new Situazione (rs.getString("Localita"),
						rs.getDate("Data").toLocalDate(), rs.getInt("Tmedia"),
						rs.getInt("Tmin"), rs.getInt("Tmax"),
						rs.getInt("Puntorugiada"), rs.getInt("Umidita"),
						rs.getInt("Visibilita"), rs.getInt("Ventomedia"),
						rs.getInt("Ventomax"), rs.getInt("Raffica"),
						rs.getInt("Pressioneslm"), rs.getInt("Pressionemedia"),
						rs.getInt("Pioggia"), rs.getString("Fenomeni"));
				sit.add(s);
			}
			conn.close();
			return sit;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//tmax di oggi e t max di ieri:
	
	//select distinct  oggi.Tmin, ieri.Tmax
   //from situazione oggi, situazione ieri
  //where oggi.Localita='torino' and ieri.Localita='torino' 
 //	and datediff(oggi.`Data`, ieri.`Data`)=1;
	
	//tutte le date di quella citta:
 
	public List<LocalDate> tutteLedateDiQuellaCitta(String city){
		String query="select distinct s.`Data`   from situazione s   where s.Localita=?  order by s.`Data` asc";
		Connection conn = DBConnect.getConnection();
		List<LocalDate> date = new LinkedList<>();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1,  city);
			ResultSet res = st.executeQuery();
			while(res.next()){
				date.add(res.getDate("data").toLocalDate());
			}
			conn.close();
			return date;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
}
