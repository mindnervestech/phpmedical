package viewmodel;

public class totalInvoiceVM {
	public Long id;
	public String doctorId;
	public String patientId;
	public String appointmentDate;
	public String appointmentTime;
	public String grandTotal;
	public String discount;
	public String taxValue;
	public String percentageDiscount;
	public String percentageTax;
	public String advance;
	public String totalDue;
	public Integer shareWithPatient;
	
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    
	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
}
