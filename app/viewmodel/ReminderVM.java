package viewmodel;

import java.util.List;

public class ReminderVM {
	
	public Long id;
	public Integer doctorId;
	public String patientId;
	public String appointmentDate;
	public String appointmentTime;
	public String medicinName;
	public String startDate;
	public String endDate;
	public Integer duration;
	public Integer numberOfDoses;
	public String schedule;
	public String doctorInstruction;
	
	public String visitDate;
	public String visitType;
	public String referredBy;
	public String symptoms;
	public String diagnosis;
	public String medicinePrescribed;
	public String testsPrescribed;
	
	public List<AlarmReminderVM> alarmReminderVMList;
}
