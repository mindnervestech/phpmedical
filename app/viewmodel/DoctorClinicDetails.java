package viewmodel;

public class DoctorClinicDetails {
	
	
	public String doctorId;
	public String doctorName;
	public String clinicId;
	public String clinicName;
	public String clinicLocation;
	public String contactNumber;
	public TimeTable slot1;
	public TimeTable slot2;
	public TimeTable slot3;
	
	public DoctorClinicDetails(){}
	
	public DoctorClinicDetails(String doctorId, String doctorName, String clinicId, String clinicName,
							   String clinicLocation, String contactNumber, TimeTable slot1,
							   TimeTable slot2,TimeTable slot3) {
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.clinicId = clinicId;
		this.clinicName = clinicName;
		this.clinicLocation = clinicLocation;
		this.contactNumber = contactNumber;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
	}	
}
