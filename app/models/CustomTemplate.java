package models;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class CustomTemplate extends Model{
	@Id
	public Integer idCustomerTemplate;
	public String name;
	public String icon;
	public Integer category;
	public Integer Type;
	
	@ManyToOne
	@JoinColumn(name="idPerson")
	public Person person;
	
	@ManyToOne
	@JoinColumn(name="idParentTemplate")
	public CustomTemplate customTemplate;
}
