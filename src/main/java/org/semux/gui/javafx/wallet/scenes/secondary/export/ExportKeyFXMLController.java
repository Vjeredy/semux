/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.export;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Private key export scene controller.
 */
public class ExportKeyFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> exportKeyTableView;
    @FXML
    private TableColumn<?, ?> numberTableColumn;
    @FXML
    private TableColumn<?, ?> addressTableColumn;
    @FXML
    private TableColumn<?, ?> nameTableColumn;
    @FXML
    private TableColumn<?, ?> keyTableColumn;
    @FXML
    private Button copyButton;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * Copies selected private key on mouse click.
     */
    @FXML
    private void copy() {
        System.out.println("Copy");
    }

    /**
     * Copies selected private key on enter key pressed.
     */
    @FXML
    private void copyOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            copy();
        }
    }

}