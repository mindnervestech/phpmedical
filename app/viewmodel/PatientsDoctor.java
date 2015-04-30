package viewmodel;

public class PatientsDoctor {
	public PatientsDoctor(){}
	public PatientsDoctor(String string, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, String dateOfBirth, String gender, Integer type) {
		this.doctorId = string;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.type = type;
	}
	public PatientsDoctor(String string, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, Integer type) {
		this.doctorId = string;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.type = type;
	}
	public String doctorId;
	public String name;
	public String speciality;
	public String emailID;
	public Long mobileNumber;
	public String location;
	public String dateOfBirth;
	public String gender;
	public Integer type;
}
