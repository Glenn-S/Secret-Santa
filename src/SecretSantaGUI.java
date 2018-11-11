/***********************************************
 * Back up GUI version of application
 *
 ***********************************************/

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import java.time.LocalDateTime;


public class SecretSantaGUI extends Application {
    private final String appName = "Secret Santa " + LocalDateTime.now().format("yyyy");
    private int winWidth = 300, winHeight = 300;

    public static void main(String[] args) {
        launch(args);
    }

    public void init() {

    }

    @Override
    public void start(Stage myStage) {
        myStage.setTitle("");

        FlowPane fp = new FlowPane();

        Scene myScene = new Scene(fp, winWidth, winHeight);
        myStage.setScene(myScene);

        myStage.show();

    }

    public void stop() {
        // shutdown
    }

}
