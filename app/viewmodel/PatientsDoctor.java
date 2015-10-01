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
	public String blood_group;
	public String allergic_to;
	public Integer type;
	public String bookDate;
	public String bookTime;
	public String shift;
	public Integer clinicId;
	public String lastVisited;
	public String previousAppointment;
	public String previousDate;
	
	public PatientsDoctor(){}
	
	public PatientsDoctor(String doctorId, String name, String speciality2,
			String email2, Long mobileNumber2, String location2, String dateOfBirth, 
			String gender, Integer type, String bookDate, String bookTime, String shift, Integer clinicId, String lastVisited) {
		this.doctorId = doctorId;
		this.name = name;
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
	
	public PatientsDoctor(String doctorId, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, String dateOfBirth, 
			String gender, String blood_group, String allergic_to, Integer type, String bookDate, 
			String bookTime, String shift, Integer clinicId, String lastVisited) {
		this.doctorId = doctorId;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.blood_group = blood_group;
		this.allergic_to = allergic_to;
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
	public PatientsDoctor(String string, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, Integer type,String gender) {
		this.doctorId = string;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.type = type;
		this.gender = gender;
		
	}
	public PatientsDoctor(String doctorId, String name2, String speciality2,
			String email2, Long mobileNumber2, String location2, String dateOfBirth, 
			String gender, String blood_group, String allergic_to, Integer type, String bookDate, 
			String bookTime, String shift, Integer clinicId, String lastVisited,String previousAppointment,String previousDate) {
		this.doctorId = doctorId;
		this.name = name2;
		this.speciality = speciality2;
		this.emailID = email2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.blood_group = blood_group;
		this.allergic_to = allergic_to;
		this.type = type;
		this.bookDate = bookDate;
		this.bookTime = bookTime;
		this.shift = shift;
		this.clinicId = clinicId;
		this.lastVisited = lastVisited;
		this.previousAppointment = previousAppointment;
		this.previousDate = previousDate;
	}
}
