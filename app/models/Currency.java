package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Currency extends Model {
	@Id
	public Integer idCurrency;
	public String Currencycol;
	public String currencySymbol;
}
