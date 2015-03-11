package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class Country extends Model {
	@Id
	public Integer idCountry;
	public String coutryName;
	public Integer ISDCode;

	@ManyToOne
	public Person person;
	
	@OneToMany
	public List<CountryLanguage> countryLanguage;
	
	@OneToOne
	@JoinColumn(name="id_currency")
	public Currency currency;
	
	public static Finder<Integer,Country> find = new Finder<>(Integer.class,Country.class);
	
	public static Country getCountryById(Integer id) {
		return find.byId(id);
	}
}
