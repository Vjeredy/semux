/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.transactions;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Transactions scene controller. Shows transactions table.
 */
public class TransactionsFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> transactionsTableView;
    @FXML
    private TableColumn<?, ?> typeTableColumn;
    @FXML
    private TableColumn<?, ?> destinationTableColumn;
    @FXML
    private TableColumn<?, ?> valueTableColumn;
    @FXML
    private TableColumn<?, ?> timeTableColumn;
    @FXML
    private TableColumn<?, ?> statusTableColumn;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "transactions");
    }

}