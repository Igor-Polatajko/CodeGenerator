package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private static SceneController obj = new SceneController();
    private Stage stage;
    private SceneStates currentSceneState;

    private SceneController() {
    }

    public static SceneController getObj() {
        return obj;
    }

    public void init(Stage stage) {
        this.stage = stage;
        stage.setResizable(false);
    }

    public void setCurrentSceneState(SceneStates currentSceneState) {
        this.currentSceneState = currentSceneState;
        handleSceneState();
    }

    private void handleSceneState() {

        switch (currentSceneState) {
            case MAIN_SCENE:
                Scene mainScene = getMainScene();
                stage.setScene(mainScene);
                break;
        }
        stage.show();
    }

    private Scene getMainScene(){
        VBox root = null;
        try {
           root = (VBox) FXMLLoader.load(getClass().getResource("/view/mainScene.fxml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        return scene;
    }
}
