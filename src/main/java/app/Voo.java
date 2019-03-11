package app;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) 
@Table(name="tb_airplane")
public class Voo {

	  @Id
	  @GeneratedValue
	  @Column(nullable = false, unique = false, name = "idDB")
	  private int idDB;

	  @Column(nullable = true, unique = false, name = "icao24")
	  private String icao24;
	  @Column(nullable = false, unique = false, name = "numero")
	  private String numero;
	  @Column(nullable = false, unique = false, name = "pais")
	  private String pais;


	  public Voo() {
		  
	  }
	public Voo(final List line) {
		this.icao24 = (String)line.get(0);
	    this.numero = (String)line.get(1);
	    this.pais = (String)line.get(2);
	  }


	  public int getIdDB() {
		return idDB;
	}

	public void setIdDB(int idDB) {
		this.idDB = idDB;
	}

	public String getIcao24() {
		return icao24;
	}

	public void setIcao24(String icao24) {
		this.icao24 = icao24;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	 public String toString() {
	   return String.format(
	     "{idDB: %i, icao24: %s, number: %s, country: %s }",
	     this.idDB,
	     this.icao24,
	     this.numero,
	     this.pais);
	 }
}
