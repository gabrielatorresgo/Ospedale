package packagee.app;

import packagee.controller.AppointmentController;
import packagee.controller.AuthController;
import packagee.controller.DoctorController;
import packagee.controller.HospitalizationController;
import packagee.controller.PatientController;
import packagee.controller.TableController;
import packagee.repository.AppointmentRepository;
import packagee.observer.ModelEventPublisher;
import packagee.repository.DataStore;
import packagee.repository.HospitalizationRepository;
import packagee.repository.UserRepository;
import packagee.service.AppointmentService;
import packagee.service.AuthService;
import packagee.service.DoctorService;
import packagee.service.HospitalizationService;
import packagee.service.PatientService;
import packagee.util.IdGenerator;
import packagee.util.JsonLoadResult;
import packagee.util.JsonLoader;
import packagee.util.Serializer;
import packagee.validation.AppointmentValidator;
import packagee.validation.DoctorValidator;
import packagee.validation.HospitalizationValidator;
import packagee.validation.PatientValidator;
import packagee.validation.PrescriptionValidator;


public class ApplicationContext {

    private final DataStore dataStore;
    private final UserRepository userRepository;
    private final AppointmentRepository appointmentRepository;
    private final HospitalizationRepository hospitalizationRepository;
    private final ModelEventPublisher modelEventPublisher;

    private final JsonLoader jsonLoader;
    private final Serializer serializer;
    private final IdGenerator idGenerator;

    private final PatientValidator patientValidator;
    private final DoctorValidator doctorValidator;
    private final AppointmentValidator appointmentValidator;
    private final HospitalizationValidator hospitalizationValidator;
    private final PrescriptionValidator prescriptionValidator;

    private final AuthService authService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final HospitalizationService hospitalizationService;

    private final AuthController authController;
    private final PatientController patientController;
    private final DoctorController doctorController;
    private final AppointmentController appointmentController;
    private final HospitalizationController hospitalizationController;
    private final TableController tableController;

    private JsonLoadResult jsonLoadResult;

    public ApplicationContext() {
        this.dataStore = new DataStore();
        this.userRepository = new UserRepository(dataStore);
        this.appointmentRepository = new AppointmentRepository(dataStore);
        this.hospitalizationRepository = new HospitalizationRepository(dataStore);
        this.modelEventPublisher = new ModelEventPublisher();

        this.jsonLoader = new JsonLoader(userRepository, appointmentRepository, hospitalizationRepository);
        this.modelEventPublisher.addObserver(jsonLoader);
        this.serializer = new Serializer();
        this.idGenerator = new IdGenerator(appointmentRepository, hospitalizationRepository);

        this.patientValidator = new PatientValidator();
        this.doctorValidator = new DoctorValidator();
        this.appointmentValidator = new AppointmentValidator();
        this.hospitalizationValidator = new HospitalizationValidator();
        this.prescriptionValidator = new PrescriptionValidator();

        this.authService = new AuthService(userRepository, serializer);
        this.patientService = new PatientService(userRepository, patientValidator, serializer, modelEventPublisher);
        this.doctorService = new DoctorService(userRepository, doctorValidator, serializer, modelEventPublisher);
        this.appointmentService = new AppointmentService(
                appointmentRepository,
                userRepository,
                appointmentValidator,
                prescriptionValidator,
                idGenerator,
                serializer,
                modelEventPublisher
        );
        this.hospitalizationService = new HospitalizationService(
                hospitalizationRepository,
                appointmentRepository,
                userRepository,
                hospitalizationValidator,
                idGenerator,
                serializer,
                modelEventPublisher
        );


        this.authController = new AuthController(authService);
        this.patientController = new PatientController(patientService);
        this.doctorController = new DoctorController(doctorService);
        this.appointmentController = new AppointmentController(appointmentService);
        this.hospitalizationController = new HospitalizationController(hospitalizationService);
        this.tableController = new TableController(
                patientController,
                doctorController,
                appointmentController,
                hospitalizationController
        );
    }

    public void initialize() {
        this.jsonLoadResult = jsonLoader.loadDefaultUsers();
        printInitializationSummary();
    }

    private void printInitializationSummary() {
        if (jsonLoadResult == null) {
            System.out.println("Aplicacion iniciada sin resultado de carga JSON.");
            return;
        }

        System.out.println("Carga JSON completada: " + jsonLoadResult);
        System.out.println("Persistencia JSON activa: los cambios se guardaran en json/users.json.");

        if (jsonLoadResult.hasErrors()) {
            System.err.println("Advertencias durante la carga JSON:");
            for (String error : jsonLoadResult.getErrors()) {
                System.err.println("- " + error);
            }
        }
    }

    public DataStore getDataStore() {
        return dataStore;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public AppointmentRepository getAppointmentRepository() {
        return appointmentRepository;
    }

    public HospitalizationRepository getHospitalizationRepository() {
        return hospitalizationRepository;
    }

    public JsonLoadResult getJsonLoadResult() {
        return jsonLoadResult;
    }

    public ModelEventPublisher getModelEventPublisher() {
        return modelEventPublisher;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public AppointmentService getAppointmentService() {
        return appointmentService;
    }

    public HospitalizationService getHospitalizationService() {
        return hospitalizationService;
    }

    public AuthController getAuthController() {
        return authController;
    }

    public PatientController getPatientController() {
        return patientController;
    }

    public DoctorController getDoctorController() {
        return doctorController;
    }

    public AppointmentController getAppointmentController() {
        return appointmentController;
    }

    public HospitalizationController getHospitalizationController() {
        return hospitalizationController;
    }

    public TableController getTableController() {
        return tableController;
    }
}

