package models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class Customtamplate_Templatefield extends Model {
	@ManyToOne
	@JoinColumn(name="idCustomTemplate")
	public CustomTemplate customTemplate;
	
	@ManyToOne
	@JoinColumn(name="idTemplateField")
	public TemplateField templateField;
	public Integer sequence;
}
