package packagee.app;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import packagee.LoginView;


public class Main {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        configureLookAndFeel();
        initializeApplicationContext();
        launchLoginView();
    }

    private static void configureLookAndFeel() {
        System.setProperty("flatlaf.useNativeLibrary", "false");
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("No se pudo inicializar FlatLaf: " + ex.getMessage());
        }
    }

    private static void initializeApplicationContext() {
        applicationContext = new ApplicationContext();
        applicationContext.initialize();
    }


    private static void launchLoginView() {
        java.awt.EventQueue.invokeLater(() -> {
            LoginView loginView = new LoginView(applicationContext);
            loginView.setVisible(true);
        });
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
