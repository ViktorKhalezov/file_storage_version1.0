package client;

import common.AuthMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeWindowController implements Initializable {

    private static final ClientNet clientNet = ClientNet.getClientNet();
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public TextField login;

    @FXML
    public PasswordField password;


    public void enterButton(ActionEvent actionEvent) {
        AuthMessage authMessage = new AuthMessage(login.getText(), password.getText());
        clientNet.sendMessage(authMessage);
        new Thread(() -> {
            while (true) {
                if (clientNet.getAuthCommand() != null) {
                    break;
                }
            }
            Thread.currentThread().interrupt();
        }).start();
        try {
            Thread.currentThread().sleep(200);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        String authCommand = clientNet.getAuthCommand();
        if(authCommand.equals("authConfirmed")) {
            enterMainWindow();
        }
        else {
            try {
                root = FXMLLoader.load(getClass().getResource("/client/authErrorWindow.fxml"));
                stage = AppStarter.getPrimaryStage();
                scene = new Scene(root);
                stage.setScene(scene);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        clientNet.setAuthCommand(null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppStarter.setPreviousWindow("welcomeWindow");
    }


    public void enterMainWindow() {
        try {
            root = FXMLLoader.load(getClass().getResource("/client/mainWindow.fxml"));
            stage = AppStarter.getPrimaryStage();
            scene = new Scene(root);
            stage.setScene(scene);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
