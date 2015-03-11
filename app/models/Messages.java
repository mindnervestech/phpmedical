package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;
@Entity
public class Messages extends Model {
	@Id
	public Integer messageId; 
	@ManyToOne
	@JoinColumn(name="idSender")
	public Person sperson;
	
	@ManyToOne
	@JoinColumn(name="idRecipient")
	public Person tperson;
	
	public String massage;
	public Date date;
	public String isRead;
}
