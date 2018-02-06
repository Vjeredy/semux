/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.receive;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import org.semux.gui.javafx.wallet.ApplicationLoader;
import org.semux.gui.javafx.wallet.WalletAccount;
import org.semux.gui.javafx.wallet.WalletSession;
import org.semux.gui.javafx.wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Receive scene controller. Shows accounts table, QR-code.
 */
public class ReceiveFXMLController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<?, ?> numberTableColumn;
    @FXML
    private TableColumn<WalletAccount, String> nameTableColumn;
    @FXML
    private TableColumn<WalletAccount, String> addressTableColumn;
    @FXML
    private TableColumn<WalletAccount, Double> availableTableColumn;
    @FXML
    private TableColumn<WalletAccount, Double> lockedTableColumn;
    @FXML
    private ImageView qrCodeImageView;
    @FXML
    private Button copyButton;
    @FXML
    private TableView<WalletAccount> accountsTableView;

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "receive");
        // Set accounts properties as TableView items
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        availableTableColumn.setCellValueFactory(new PropertyValueFactory<>("available"));
        lockedTableColumn.setCellValueFactory(new PropertyValueFactory<>("locked"));
        final WalletSession currentSession = ApplicationLoader.getCurrentSession();
        accountsTableView.setItems(currentSession.getObservableAccountsList());
    }

    /**
     * Copies account`s address to clipboard on mouse click.
     */
    @FXML
    private void copy() {
        System.out.println("Copy");
    }

    /**
     * Copies account`s address to clipboard on enter key pressed.
     */
    @FXML
    private void copyOnEnterKeyPressed(KeyEvent key) {
        if (key.getCode().equals(KeyCode.ENTER)) {
            copy();
        }
    }

}