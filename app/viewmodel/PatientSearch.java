package viewmodel;

public class PatientSearch {
	public PatientSearch(String string, String name2, Long mobileNumber2,
			String location2) {
		this.patientId = string;
		this.name = name2;
		this.mobileNumber = mobileNumber2;
		this.location = location2;
	}
	public String patientId;
	public String name;
	public Long mobileNumber;
	public String location;
}
