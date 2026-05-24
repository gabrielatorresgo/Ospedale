package packagee.model;

import java.time.LocalDate;
import packagee.model.enums.HospitalizationStatus;
import packagee.model.enums.RoomType;


public class Hospitalization {

    private final String id;
    private Patient patient;
    private Doctor doctor;
    private LocalDate date;
    private String reason;
    private RoomType roomType;
    private String observations;
    private HospitalizationStatus status;

    public Hospitalization(String id, Patient patient, Doctor doctor, LocalDate date,
                           String reason, RoomType roomType, String observations) {
        this(id, patient, doctor, date, reason, roomType, observations, HospitalizationStatus.REQUESTED);
    }

    public Hospitalization(String id, Patient patient, Doctor doctor, LocalDate date,
                           String reason, RoomType roomType, String observations,
                           HospitalizationStatus status) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.reason = reason;
        this.roomType = roomType;
        this.observations = observations;
        this.status = status;

        if (patient != null) {
            patient.addHospitalization(this);
        }
        if (doctor != null) {
            doctor.addHospitalization(this);
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

    public void setDoctor(Doctor doctor) {
        if (doctor == null || this.doctor == doctor) {
            return;
        }
        if (this.doctor != null) {
            this.doctor.removeHospitalization(this);
        }
        this.doctor = doctor;
        doctor.addHospitalization(this);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public HospitalizationStatus getStatus() {
        return status;
    }

    public void setStatus(HospitalizationStatus status) {
        this.status = status;
    }

    public void approve() {
        this.status = HospitalizationStatus.ONGOING;
    }

    public void deny() {
        this.status = HospitalizationStatus.CANCELED;
    }
}
