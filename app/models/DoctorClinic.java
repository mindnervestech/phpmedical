package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.ebean.Model;
@Entity
public class DoctorClinic extends Model {

		@Id
		public Integer doctorClinicId;
		
		@ManyToOne
		public Person person;
		
		@ManyToOne
		public Clinic clinic;
		
		public Integer daysOfWeek;
		public Date timeToStart;
		public Date timeToStop;
		public Integer numberOfPatients;
		public Integer percentageOverbook;
}
