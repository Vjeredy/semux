/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet.controls.footer;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * Custom footer bar controller. Can be imported .jar packed along with .fxml file into Scene Builder as custom control.
 */
public class FooterFXMLController extends HBox {
    
    @FXML
    private TextField peersSyncTextField;
    
    /**
     * Constructor loads .fxml file and allows footer to be imported with controller.
     */
    public FooterFXMLController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FooterFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
}