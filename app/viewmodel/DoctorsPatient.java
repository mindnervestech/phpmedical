package viewmodel;

public class DoctorsPatient {
	public DoctorsPatient(String string, String name2, String emailID2,
			Long mobileNumber2, String location2,Integer type) {
		// TODO Auto-generated constructor stub
		this.patientId = string;
		this.name = name2;
		this.emailID = emailID2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.type = type;
		
	}
	public String patientId;
	public String name;
	public String emailID;
	public Long mobileNumber;
	public String location;
	public Integer type;
}
