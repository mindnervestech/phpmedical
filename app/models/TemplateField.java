package models;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class TemplateField extends Model {
	@Id
	public Integer idTemplateField;
	@ManyToOne
	@JoinColumn(name="idField")
	public Field field;

	public String fieldName;
	public String value;
	public String valueQuery;
	public String listQuery;
	public String updateQuery;
	
	@ManyToOne
	@JoinColumn(name="idPerson")
	public Person person;
	
}
