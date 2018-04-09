
package HIPS.Runner;
import HIPS.backend.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Gui is the graphics user interface. Gui allows the user to visually encrypt
 * and decrypt messages. Gui allows the user to select an image and message for
 * encryption. Gui allows the user to select an image for decryption.
 *
 * @author JARK
 */
public class Gui extends Application {

    /**
     * Entry point for the Gui (Graphical User Interface) for
     * HiddenInPlainSight.
     *
     * @param stage for Gui window.
     * @throws java.lang.Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {

        launch(args);
    }

}
