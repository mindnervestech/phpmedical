package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Notification extends Model {
	@Id
	public Integer id;
	public String doctorId;
	public String date;
	public String time;
	public String title;
	public static Finder<Integer,Notification> find = new Finder<>(Integer.class,Notification.class);
	
	public static List<Notification> getAllNotificationDoctor(String email){
		return find.where().eq("doctorId", email).findList();
	}
}
