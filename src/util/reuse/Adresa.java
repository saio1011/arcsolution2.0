package util.reuse;

/**
 *
 * @author mihaisava
 */
public class Adresa {
	
	private int ID_Adresa; 
	private String Strada; 
	private String Nummer; 
	private String CodPostal;
	private String Oras; 
	private String Country; 
	
	public Adresa(int iD_Adresa, String strada, String nummer,
			String codPostal, String oras,  String country) {
		super();
		ID_Adresa = iD_Adresa;
		Strada = strada;
		Nummer = nummer;
		CodPostal = codPostal;
		Oras = oras;
		Country = country;
	}
	public Adresa(String strada, String nummer,
			String codPostal, String oras,  String country) {
		super();
		Strada = strada;
		Nummer = nummer;
		CodPostal = codPostal;
		Oras = oras;
		Country = country;
	}
	public Adresa(){
		super();
	}
	
	public int getID_Adresa() {
		return ID_Adresa;
	}
	public void setID_Adresa(int iD_Adresa) {
		ID_Adresa = iD_Adresa;
	}
	public String getStrada() {
		return Strada;
	}
	public void setStrada(String strada) {
		Strada = strada;
	}
	public String getNummer() {
		return Nummer;
	}
	public void setNummer(String nummer) {
		Nummer = nummer;
	}
	public String getCodPostal() {
		return CodPostal;
	}
	public void setCodPostal(String codPostal) {
		CodPostal = codPostal;
	}
	public String getOras() {
		return Oras;
	}
	public void setOras(String oras) {
		Oras = oras;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}

	@Override
	public String toString() {
		return "Adresa [ID_Adresa=" + ID_Adresa + ", Strada=" + Strada
				+ ", Nummer=" + Nummer + ", CodPostal=" + CodPostal + ", Oras="
				+ Oras + ", Country=" + Country + "]";
	} 
}
