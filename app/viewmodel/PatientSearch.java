package viewmodel;

public class PatientSearch {
	public PatientSearch(String string, String name2, Long mobileNumber2,
			String location2, String emailID) {
		this.patientId = string;
		this.name = name2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.emailID = emailID;
	}
	public String patientId;
	public String name;
	public Long mobileNumber;
	public String location;
	public String emailID;
	public String status;
	public String accessLevel;
	public String doctorId; 
	public String gender;
	public String blood_group;
	public String allergic_to;
	public Integer type;
	public String bookDate;
	public String bookTime;
	public String shift;
	public Integer clinicId;
	public String lastVisited;
	public String lastVisitedTime;
	public String appointmentDate;
	public String appointmentTime;
	public String appointmentType;
	public PatientSearch(String string, String name2, Long mobileNumber2,
			String location2, String emailID, String status, String accessLevel) {
		this.patientId = string;
		this.name = name2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.emailID = emailID;
		this.accessLevel = accessLevel;
		this.status = status;
	}
	public PatientSearch(){}
}
