package viewmodel;

public class AllPersonData {
	
	public Integer idPerson;
	public String name;
	public String emailId;
	public Long mobileNumber;
	public String gender;
	public String dateOfBirth;
	public String location;
	public String blood_group;
	public String allergic_to;
	public String patientId;
	public String doctorId;
	public String assistentId;
	
	public String speciality;
	public Integer type;
	public String bookDate;
	public String bookTime;
	public String shift;
	public Integer clinicId;
	public String lastVisited;
	
	public AllPersonData(){}
	
	public AllPersonData(String name, String speciality2,
			String emailId, Long mobileNumber, String location, String dateOfBirth, 
			String gender, String blood_group, String allergic_to, Integer type, 
			String bookDate, String bookTime, String shift, Integer clinicId, String lastVisited) {
		
		
		this.name = name;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.location = location;
		this.blood_group = blood_group;
		this.allergic_to = allergic_to;
		
		this.type = type;
		this.bookDate = bookDate;
		this.bookTime = bookTime;
		this.shift = shift;
		this.clinicId = clinicId;
		this.lastVisited = lastVisited;
	}
}
