package kundenverwaltung.service;

import java.sql.*;
import java.util.ArrayList;
import kundenverwaltung.domain.Kundedomain;
import util.DBverbindung;
import util.reuse.Adresa;

/**
 *
 * @author mihaisava
 */
public class Kundeservice {
	
	/**
	 * get all customers
	 * @return array with all customers
	 */
	public ArrayList<Kundedomain> getAllKunden(){
		Statement st = null;
		Statement stAdr = null;
		ArrayList<Kundedomain> kunden = new ArrayList<Kundedomain>();
//		DBverbindung.dbconnect();
		try{
			st = DBverbindung.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM kunde"); 
			while (res.next()) {
				int iD = res.getInt("ID"); 
				String name = res.getString("Name");
				String valabilitateCtr = res.getString("Valabilitatectr");
				String kontraktNr = res.getString("Kontraktnr");
				String actaditional = res.getString("Actaditional");
				String contactCl = res.getString("ContactClient");
				String cui = res.getString("Cui");
				String nrONRC = res.getString("NrONRC");
				Adresa adresa = new Adresa();
				
				stAdr = DBverbindung.getConn().createStatement();
				ResultSet resAdresa = stAdr.executeQuery("SELECT * FROM adresaClient where ID_Client = \"" + iD + "\"");
				while(resAdresa.next()){
					adresa = new Adresa(resAdresa.getString("Strada"), resAdresa.getString("Nummer"), resAdresa.getString("CodPostal"), 
							resAdresa.getString("Oras"), resAdresa.getString("Country"));
				}
				stAdr.close();
				
				Kundedomain tmp = new Kundedomain(iD, name, kontraktNr, actaditional, valabilitateCtr, contactCl, cui, nrONRC, adresa);		
				kunden.add(tmp);
			}
			st.close();
		}catch (Exception ex){
			try {
				st.close();
				stAdr.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ex.getMessage());
		}
		
//		DBverbindung.dbdisconect();
		return kunden;
	}
	
	/**
	 * find customer by name or CUI
	 * @param spalte
	 * @param nameField
	 * @return array with customers
	 */
	public ArrayList<Kundedomain> findKundenByName(String spalte, String nameField){
		Statement st = null;
		Statement stAdr = null;
		ArrayList<Kundedomain> kunden = new ArrayList<Kundedomain>();
//		DBverbindung.dbconnect();
		try{
			st = DBverbindung.getConn().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM kunde where "+ spalte + " like\""+ nameField + "%\""); 
			while (res.next()) {
				int iD = res.getInt("ID"); 
				String name = res.getString("Name");
				String valabilitateCtr = res.getString("Valabilitatectr");
				String kontraktNr = res.getString("Kontraktnr");
				String actaditional = res.getString("Actaditional");
				String contactCl = res.getString("ContactClient");
				String cui = res.getString("Cui");
				String nrONRC = res.getString("NrONRC");
				Adresa adresa = new Adresa();
				
				stAdr = DBverbindung.getConn().createStatement();
				ResultSet resAdresa = stAdr.executeQuery("SELECT * FROM adresaClient where ID_Client = \"" + iD + "\"");
				while(resAdresa.next()){
					adresa = new Adresa(resAdresa.getString("Strada"), resAdresa.getString("Nummer"), resAdresa.getString("CodPostal"), 
							resAdresa.getString("Oras"), resAdresa.getString("Country"));
				}
				stAdr.close();
			
				Kundedomain tmp = new Kundedomain(iD, name, kontraktNr, actaditional, valabilitateCtr, contactCl, cui, nrONRC, adresa);
				kunden.add(tmp);
			}
			st.close();
		}catch (Exception ex){
			try {
				st.close();
				stAdr.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ex.getMessage());
		}
		
//		DBverbindung.dbdisconect();
		return kunden;
	}
	
	/**
	 * create kunde
	 * @param kunde
	 * @return resAdr - it must be 1 in success case
	 */
	public int createKunde(Kundedomain kunde){
		java.sql.Connection connection = null;
		Statement st = null;
		int resInsertKunde = -1;
		int resAdr = -1;
//		DBverbindung.dbconnect();
		try{
			connection = DBverbindung.getConn();
			connection.setAutoCommit(false);
			st = connection.createStatement(Statement.CLOSE_ALL_RESULTS, Statement.RETURN_GENERATED_KEYS);
			resInsertKunde = st.executeUpdate("INSERT INTO kunde (`Name`, `Kontraktnr`, `Actaditional`, `Valabilitatectr`, `ContactClient`, `Cui`, `NrONRC`) VALUES "
					+ "('"+kunde.getDenumireClient().toString()+"', '"+ kunde.getNrContract().toString()+"', '" + kunde.getActeAditionale().toString() 
					+ "', '"+kunde.getValabilitateCtr().toString()+"', '"+ kunde.getContactClient().toString()+"', '"+ kunde.getCui().toString()+"', '"+ kunde.getNrONRC().toString()+"')",Statement.RETURN_GENERATED_KEYS);
		
			int counter = 0;
			int idKunde = -1;
			ResultSet generatedKeys = st.getGeneratedKeys();
		        if(generatedKeys.next()){
		        	idKunde = generatedKeys.getInt(1);
		        	counter++;
		            System.out.println("ID nou: " + generatedKeys.getInt(1));    
		        }
			
			if(resInsertKunde == 1) {
				if(counter == 1 && idKunde != -1){
					resAdr = st.executeUpdate("INSERT INTO adresaCLient (`Strada`, `Nummer`, `CodPostal`, `Oras`, `Country`, `ID_Client`) VALUES "
							+ "('" +kunde.getAdresa().getStrada()+"', '"+kunde.getAdresa().getNummer()+"', '"+ kunde.getAdresa().getCodPostal()
							+"', '"+kunde.getAdresa().getOras()+"', '"+kunde.getAdresa().getCountry()+"', '"+idKunde+"')");
				}else{
					throw new Exception("Create customer failed");
				}
			}else{
				throw new Exception("Create customer failed");
			}
			connection.commit();
			st.close();
			
		}catch (Exception ex){
			try {
				connection.rollback();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(ex.getMessage());
			System.out.println(st.toString());
		}
		
//		DBverbindung.dbdisconect();
		return resAdr;
	}
	
	
	/**
	 * update customer
	 * @param id
	 * @param kunde
	 * @return resAdr - it must be 1 in success case 
	 */
	public int updateKunde(int id,Kundedomain kunde){
		java.sql.Connection connection = null;
		Statement st = null;
		int res = -1;
		int resAdr = -1;
		try{
			connection = DBverbindung.getConn();
			connection.setAutoCommit(false);
			st = connection.createStatement();
			res = st.executeUpdate("UPDATE kunde "
										+ "SET Name = '" + kunde.getDenumireClient() + "', "
										+ 	"Kontraktnr = '"+ kunde.getNrContract() + "', "
										+	"Actaditional = '"+ kunde.getActeAditionale() + "', "
										+	"Valabilitatectr = '" + kunde.getValabilitateCtr() + "', "
										+	"ContactClient = '" + kunde.getContactClient() + "', "
										+	"Cui = '" + kunde.getCui() + "', "
										+	"NrONRC = '" + kunde.getNrONRC() + "'"
											+ " WHERE ID = " + id);
			if(res == 1){
				resAdr = st.executeUpdate("UPDATE adresaClient "
											+ "SET Strada = '" + kunde.getAdresa().getStrada() + "', "
											+ 	"Nummer = '" + kunde.getAdresa().getNummer() + "', "
											+	"CodPostal = '" + kunde.getAdresa().getCodPostal() + "', "
											+	"Oras = '" + kunde.getAdresa().getOras() + "', "
											+	"Country = '" + kunde.getAdresa().getCountry() + "'"
												+ " WHERE ID_Client = " + id);
				if(resAdr != 1){
					throw new Exception("Update customer failed");
				}
			}else{
				throw new Exception("Update customer failed");
			}
			connection.commit();
			st.close();
		}catch(Exception e){
			try {
				connection.rollback();
				st.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(e.getMessage());
			System.out.println(st.toString());
		} 
		
//		System.out.println("update kunde mit id "+id+" aufgerufen; resAdr = " + resAdr);
		return resAdr;
	}
}
