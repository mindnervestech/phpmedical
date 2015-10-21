package viewmodel;

import java.util.List;

public class ClinicVM {
	public String clinicName;
	public Long landLineNumber;
	public Long mobileNumber;
	public String address;
	public String location;
	public String doctorEmail;
	public String email;
	public String doctorId;
	public String parameter;
	public boolean selected;
	public String onlineAppointment;
	public String speciality;
	public String idClinic;
	public List<Schedule> schedules;
	
	public static class Schedule {

		public String day;
		public String shift;
		public String from;
		public String to;

	}
}
