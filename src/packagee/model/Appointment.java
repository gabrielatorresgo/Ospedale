package packagee.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import packagee.model.enums.AppointmentStatus;
import packagee.model.enums.Specialty;

public class Appointment {

    private final String id;
    private Patient patient;
    private Doctor doctor;
    private Specialty specialty;
    private LocalDateTime datetime;
    private String reason;
    private boolean type;
    private final ArrayList<Prescription> prescriptions;
    private AppointmentStatus status;
    private String diagnosis;
    private String observations;
    private String recommendedTreatment;
    private String followUp;

    public Appointment(String id, Patient patient, Doctor doctor, Specialty specialty,
                       LocalDateTime datetime, String reason, boolean type) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.specialty = specialty;
        this.datetime = datetime;
        this.reason = reason;
        this.type = type;
        this.status = AppointmentStatus.REQUESTED;
        this.prescriptions = new ArrayList<>();

        if (patient != null) {
            patient.addAppointment(this);
        }
        if (doctor != null) {
            doctor.addAppointment(this);
        }
    }

    public String getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getRecommendedTreatment() {
        return recommendedTreatment;
    }

    public void setRecommendedTreatment(String recommendedTreatment) {
        this.recommendedTreatment = recommendedTreatment;
    }

    public String getFollowUp() {
        return followUp;
    }

    public void setFollowUp(String followUp) {
        this.followUp = followUp;
    }

    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public List<Prescription> getPrescriptionsReadOnly() {
        return Collections.unmodifiableList(prescriptions);
    }

    public boolean addPrescription(Prescription prescription) {
        if (prescription == null || prescriptions.contains(prescription)) {
            return false;
        }
        return prescriptions.add(prescription);
    }

    public void accept() {
        this.status = AppointmentStatus.PENDING;
    }

    public void complete() {
        this.status = AppointmentStatus.COMPLETED;
    }

    public void cancel() {
        this.status = AppointmentStatus.CANCELED;
    }

    public void reschedule(LocalDateTime newDateTime, String rescheduleReason) {
        this.datetime = newDateTime;
        if (rescheduleReason != null && !rescheduleReason.trim().isEmpty()) {
            this.reason = this.reason + " | Reschedule reason: " + rescheduleReason;
        }
    }
}
