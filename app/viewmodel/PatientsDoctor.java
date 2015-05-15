package viewmodel;

public class PatientsDoctor {
	
	public String doctorId;
	public String name;
	public String speciality;
	public String emailID;
	public Long mobileNumber;
	public String location;
	public String dateOfBirth;
	public String gender;
	public Integer type;
	public String bookDate;
	public String bookTime;
	public String shift;
	public Integer clinicId;
	public String lastVisited;
	
	public PatientsDoctor(){}
	
	public PatientsDoctor(String string, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, String dateOfBirth, 
			String gender, Integer type, String bookDate, String bookTime, String shift, Integer clinicId, String lastVisited) {
		this.doctorId = string;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.type = type;
		this.bookDate = bookDate;
		this.bookTime = bookTime;
		this.shift = shift;
		this.clinicId = clinicId;
		this.lastVisited = lastVisited;
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
}
