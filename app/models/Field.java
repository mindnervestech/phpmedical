package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
@Entity
public class Field extends Model{
	@Id
	public Integer idField;
	public Integer type;
	public String description;
}
