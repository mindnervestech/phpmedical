package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class CountryLanguage extends Model {

	@Id
	public Integer countryLanguageId;
	@ManyToOne
	public Country country;
	
	@ManyToOne
	public Languages languages;

}
