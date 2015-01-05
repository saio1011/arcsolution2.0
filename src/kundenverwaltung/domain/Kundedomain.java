package kundenverwaltung.domain;

import util.reuse.Adresa;

/**
 * @author mihaisava
 *		private attribute	"Id"
 *		public attribute 	"id"
 */
public class Kundedomain {
	private int Id;
	private String DenumireClient;
	private String NrContract;
	private String ActeAditionale;
	private String ValabilitateCtr;
	private String ContactClient;
	private String Cui;
	private String NrONRC;
	private Adresa Adresa;
	
	public Kundedomain(int id, String name, String nrContract, String acteAditionale,
			String valabilitateCrt, String contactCl, String cui, String nrONRC, Adresa adresa) {
		super();
		this.Id = id;
		this.DenumireClient = name;
		this.NrContract = nrContract;
		this.ActeAditionale = acteAditionale;
		this.ValabilitateCtr = valabilitateCrt;
		this.ContactClient = contactCl;
		this.Cui = cui;
		this.NrONRC = nrONRC;
		this.Adresa = adresa;
//		this.Adresa = new Adresa(adresa.getStrada(), adresa.getNummer(), adresa.getCodPostal(), adresa.getOras(), adresa.getCountry());
	}
	public Kundedomain(String name, String nrContract, String acteAditionale,
			String valabilitateCrt, String contactCl, String cui, String nrONRC, Adresa adresa) {
		super();
		this.DenumireClient = name;
		this.NrContract = nrContract;
		this.ActeAditionale = acteAditionale;
		this.ValabilitateCtr = valabilitateCrt;
		this.ContactClient = contactCl;
		this.Cui = cui;
		this.NrONRC = nrONRC;
		this.Adresa = new Adresa(adresa.getStrada(), adresa.getNummer(), adresa.getCodPostal(), adresa.getOras(), adresa.getCountry());
	}
	
	public String getDenumireClient() {
		return DenumireClient;
	}
	public void setDenumireClient(String denumireClient) {
		DenumireClient = denumireClient;
	}
	public String getNrContract() {
		return NrContract;
	}
	public void setNrContract(String nrContract) {
		NrContract = nrContract;
	}
	public String getActeAditionale() {
		return ActeAditionale;
	}
	public void setActeAditionale(String acteAditionale) {
		ActeAditionale = acteAditionale;
	}
	public String getValabilitateCtr() {
		return ValabilitateCtr;
	}
	public void setValabilitateCtr(String valabilitateCtr) {
		ValabilitateCtr = valabilitateCtr;
	}
	public String getContactClient() {
		return ContactClient;
	}
	public void setContactClient(String contactClient) {
		ContactClient = contactClient;
	}
	public String getCui() {
		return Cui;
	}
	public void setCui(String cui) {
		Cui = cui;
	}
	public String getNrONRC() {
		return NrONRC;
	}
	public void setNrONRC(String nrONRC) {
		NrONRC = nrONRC;
	}
	public int getId() {
		return Id;
	}
	
	//TODO 
	//to check if is correct implemented and if is needed 
	public Adresa  getAdresa() {
		return Adresa;
	}
	public void setAdresa(Adresa adresa) {
		Adresa = adresa;
	}
	@Override
	public String toString() {
		return "DenumireClient=" + DenumireClient
				+ ", NrContract=" + NrContract + ", ActeAditionale="
				+ ActeAditionale + ", ValabilitateCtr=" + ValabilitateCtr
				+ ", ContactClient=" + ContactClient + ", Cui=" + Cui
				+ ", NrONRC=" + NrONRC;
	}
	
}
