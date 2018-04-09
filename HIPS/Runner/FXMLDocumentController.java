package HIPS.Runner;
import HIPS.backend.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * Controls the buttons and actions for all buttons.
 *
 * @author JARK
 */
public class FXMLDocumentController implements Initializable {

    private String path = "Images/2.png";

    @FXML
    private Label title;
    @FXML
    private Button openFileButton;
    @FXML
    private Label pathToFileLabel;
    @FXML
    private TextField pathToFileTextField;
    @FXML
    private Label encryptLabel;
    @FXML
    private Label textHereLabel;
    @FXML
    private ScrollPane encryptTextScrollPane;
    @FXML
    private TextArea encryptTextArea;
    @FXML
    private Button encryptButton;
    @FXML
    private Label imageTitle;
    @FXML
    private ScrollPane imvScrollPane;
    @FXML
    private ImageView imageViewSpot;
    @FXML
    private Button saveImageButton;
    @FXML
    private Label decryptTitle;
    @FXML
    private Label decryptedTextLabel;
    @FXML
    private TextArea decryptedTextArea;
    @FXML
    private Button decryptButton;

    @FXML
    private Button chooseImageButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Opens a file chooser dialog to choose which image to encrypt on. Sets the
     * path instance variable.
     *
     * @param event ActionEvent
     */
    @FXML
    private void handleOpenFileButtonAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (file.getPath().contains(".png") || file.getPath().contains(".jpg")) {
            pathToFileTextField.setText(file.getPath());

        } else {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            Label errorMsg = new Label("Not a Valid File :(");
            dialogVbox.getChildren().add(errorMsg);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }

    }

    /**
     * Takes the information from encryptTextArea and encrypts it into image of
     * specified path.
     *
     * @param event ActionEvent
     */
    @FXML
    private void encryptButtonAction(ActionEvent event) {

        String message = encryptTextArea.getText();

        try {

            String use = this.path;
            System.out.println(use);

            Encryptor myEncryptor = new Encryptor(use, message);
            if (myEncryptor.isLengthValid()) {
                if (myEncryptor.isCharacterValid()) {
                    myEncryptor.encrypt();
                    Image disp = new Image("hidden.png");
                    imageViewSpot.setImage(disp);
                } else {
                    final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogVbox = new VBox(20);
                    Label errorMsg = new Label("Please input basic Ascii text. ");
                    dialogVbox.getChildren().add(errorMsg);
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            } else {
                final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                Label errorMsg = new Label("Message Length and Image are Not Compatible :(");
                dialogVbox.getChildren().add(errorMsg);
                Scene dialogScene = new Scene(dialogVbox, 300, 200);
                dialog.setScene(dialogScene);
                dialog.show();
            }

        } catch (IOException ex) {

        }
    }

    /**
     * Opens a file chooser dialog to choose which directory to save the image
     * to.
     *
     * @param event ActionEvent
     */
    @FXML
    private void saveFileAction(ActionEvent event) {
        File img = new File("hidden.png");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        try {
            BufferedImage disp = ImageIO.read(img);
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                try {
                    ImageIO.write(disp, "png", file);
                } catch (IOException ex) {

                }
            }
        } catch (IOException ex) {
            System.out.println("Not a Valid File");
        }
    }

    /**
     * Takes the path from the pathToFileTextField and decrypts the image.
     *
     * @param event ActionEvent
     */
    @FXML
    private void decryptAction(ActionEvent event) {

        String path = "hidden.png";

        if (!pathToFileTextField.getText().equals("")) {
            path = pathToFileTextField.getText();

        }
        Decryptor myDecryptor = new Decryptor(path);
        decryptedTextArea.setText(myDecryptor.decrypt());
    }

    /**
     * Opens a file chooser dialog to choose which image to open and decrypt.
     *
     * @param event ActionEvent
     */
    @FXML
    private void handleOpenImgButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file.getPath().contains(".png") || file.getPath().contains(".jpg")) {
            this.path = file.getPath();

        } else {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            Label errorMsg = new Label("Not a Valid File :(");
            dialogVbox.getChildren().add(errorMsg);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }

    }

}
