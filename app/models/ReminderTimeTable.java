package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.db.ebean.Model;

@Entity
public class ReminderTimeTable extends Model {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;
	
	public String alarmDate;
	public String time1;
	public String time2;
	public String time3;
	public String time4;
	public String time5;
	public String time6;
	
	@ManyToOne(fetch = FetchType.LAZY)
	public ReminderData reminderData;
	
	public static Finder<Integer,ReminderTimeTable> find = new Finder<>(Integer.class,ReminderTimeTable.class);
	
	public static ReminderTimeTable getreminderTimeTableById(long id){
		return find.where().eq("id", id).findUnique() ;
	}
	
	public static List<ReminderTimeTable> getAllReminderTimeTableById(long id){
		return find.where().eq("reminder_data_id", id).findList();
	}
	
		
}
