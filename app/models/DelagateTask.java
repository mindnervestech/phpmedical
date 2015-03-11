package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class DelagateTask extends Model {
	@Id
	public Integer delegateId;
	
	@ManyToOne
	@JoinColumn(name="idSource")
	public Person sperson;
	
	@ManyToOne
	@JoinColumn(name="idTarget")
	public Person tperson;
	public Integer accessType;
	public Integer delegateType;
	public String URL;
}
