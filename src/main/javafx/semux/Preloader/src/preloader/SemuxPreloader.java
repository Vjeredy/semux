/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package preloader;

import javafx.application.Preloader;
import javafx.application.Preloader.StateChangeNotification;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SemuxPreloader extends Preloader {
    
    private Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Scene scenePreloader = new Scene(FXMLLoader.load(getClass().getResource("SemuxPreloaderFXML.fxml")));
        scenePreloader.setFill(Color.TRANSPARENT);
        stage.setScene(scenePreloader);
        stage.initStyle(StageStyle.TRANSPARENT);
        Image logo = new Image(getClass().getResourceAsStream("logo.png"));
        stage.getIcons().add(logo);
        stage.show();
        }
    
    @Override
    public void handleStateChangeNotification(StateChangeNotification scn) {
        if (scn.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
            }
        }

    }