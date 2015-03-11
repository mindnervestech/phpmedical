package models;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
public class Languages extends Model {
	@Id
	public Integer idLanguages;
	public String name;
	public String nameInLanguage;
	
	@OneToMany
	public List<CountryLanguage> countryLanguage;
	
	@ManyToOne
	public Person person;

	public static Finder<Integer,Languages> find = new Finder<>(Integer.class,Languages.class);
		public static Languages getLanguagesById(Integer id) {
		return find.byId(id);
	}
}
