/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Builder for stages. Loads logo, binds scene to stage, shows stage.
 */
public class StageBuilder implements org.semux.gui.javafx.wallet.Options {

    /**
     * Builder constructor for stages. Loads logo, binds scene to stage, shows
     * stage.
     * 
     * @param stage
     *            stage to build
     * @param scene
     *            scene to bind
     */
    public StageBuilder(Stage stage, Scene scene) {
        try {
            stage.setScene(scene);
            Image logo = new Image(getClass().getResourceAsStream(LOGO_ICON_PATH));
            stage.getIcons().add(logo);
            Platform.runLater(() -> stage.show());
            stage.requestFocus();
        } catch (Exception exception) {
            System.out.println("Exception:" + exception);
        }
    }

}