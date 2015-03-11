package viewmodel;

public class PatientsDoctor {
	public PatientsDoctor(){}
	public PatientsDoctor(String string, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2) {
		this.doctorId = string;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
	}
	public PatientsDoctor(String string, String name2, String emailID2,
			Long mobileNumber2, String location2) {
		this.doctorId = string;
		this.name = name2;
		this.emailID = emailID2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		
	}
	public String doctorId;
	public String name;
	public String speciality;
	public String emailID;
	public Long mobileNumber;
	public String location;
}
