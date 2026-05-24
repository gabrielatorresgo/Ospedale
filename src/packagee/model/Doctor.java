package packagee.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import packagee.model.enums.Specialty;

public class Doctor extends User {

    private Specialty specialty;
    private String licenceNumber;
    private String assignedOffice;
    private final ArrayList<Appointment> appointments;
    private final ArrayList<Hospitalization> hospitalizations;

    public Doctor(long id, String username, String firstname, String lastname, String password,
                  Specialty specialty, String licenceNumber, String assignedOffice) {
        super(id, username, firstname, lastname, password);
        this.specialty = specialty;
        this.licenceNumber = licenceNumber;
        this.assignedOffice = assignedOffice;
        this.appointments = new ArrayList<>();
        this.hospitalizations = new ArrayList<>();
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsReadOnly() {
        return Collections.unmodifiableList(appointments);
    }

    public boolean addAppointment(Appointment appointment) {
        if (appointment == null || appointments.contains(appointment)) {
            return false;
        }
        return appointments.add(appointment);
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public String getAssignedOffice() {
        return assignedOffice;
    }

    public void setAssignedOffice(String assignedOffice) {
        this.assignedOffice = assignedOffice;
    }

    public ArrayList<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }

    public List<Hospitalization> getHospitalizationsReadOnly() {
        return Collections.unmodifiableList(hospitalizations);
    }

    public boolean addHospitalization(Hospitalization hospitalization) {
        if (hospitalization == null || hospitalizations.contains(hospitalization)) {
            return false;
        }
        return hospitalizations.add(hospitalization);
    }

    public boolean removeHospitalization(Hospitalization hospitalization) {
        return hospitalization != null && hospitalizations.remove(hospitalization);
    }
}
