package packagee.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Patient extends User {

    private String email;
    private LocalDate birthdate;
    private boolean gender;
    private long phone;
    private String address;
    private final ArrayList<Appointment> appointments;
    private final ArrayList<Hospitalization> hospitalizations;

    public Patient(long id, String username, String firstname, String lastname, String password,
                   String email, LocalDate birthdate, boolean gender, long phone, String address) {
        super(id, username, firstname, lastname, password);
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.appointments = new ArrayList<>();
        this.hospitalizations = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Hospitalization getHospitalization() {
        if (hospitalizations.isEmpty()) {
            return null;
        }
        return hospitalizations.get(hospitalizations.size() - 1);
    }

    public void setHospitalization(Hospitalization hospitalization) {
        addHospitalization(hospitalization);
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
}

