/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet.scenes.secondary.accounts;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import wallet.DialogBuilder;

import static wallet.Options.INIT_NEW_ACCOUNT_WIDTH;
import static wallet.Options.INIT_RENAME_ACCOUNT_WIDTH;
import static wallet.Options.MAX_NAME_SIZE;
import static wallet.Options.MIN_NAME_SIZE;

/**
 * User accounts manager controller. Allows to add new, rename or delete existing accounts.
 */
public class AccountsManagerFXMLController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableView<?> accountsTableView;
    @FXML
    private TableColumn<?, ?> numberTableColumn;
    @FXML
    private TableColumn<?, ?> nameTableColumn;
    @FXML
    private TableColumn<?, ?> addressTableColumn;
    @FXML
    private TableColumn<?, ?> availableTableColumn;
    @FXML
    private TableColumn<?, ?> lockedTableColumn;
    @FXML
    private Button renameButton;
    @FXML
    private Button newButton;
    @FXML
    private Button deleteButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    /**
     * Renames selected account on mouse click.
     */
    @FXML
    private void rename() {
        //Dialog creation
        TextInputDialog dialogRename = new TextInputDialog("");
        dialogRename.setTitle("Rename account");
        dialogRename.setHeaderText("");
        dialogRename.setContentText("New name");
        DialogBuilder builder = new DialogBuilder(dialogRename, INIT_RENAME_ACCOUNT_WIDTH);
        //Accept button binding
        final Button acceptButton = (Button) dialogRename.getDialogPane().lookupButton(ButtonType.OK);
        BooleanProperty nameSizeCorrect = new SimpleBooleanProperty(false);
        dialogRename.getEditor().textProperty().addListener((observable, initialValue, newValue) -> {
            nameSizeCorrect.set((newValue.length() < MIN_NAME_SIZE) || (newValue.length() > MAX_NAME_SIZE));
        });
        BooleanBinding disableButton = dialogRename.getEditor().textProperty().isEmpty()
                .or(nameSizeCorrect);
        acceptButton.disableProperty().bind(disableButton);
        //Inputs validation
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(dialogRename.getEditor(), Validator.createEmptyValidator("Name is Required."));
        //Show the dialog window
        dialogRename.show();
        //On confirmation
        acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (true) {
                
            } else {
                rename();
                Notifications.create()
                    .title("Wrong name.")
                    .text("Please enter correct name.")
                    .position(Pos.CENTER)
                    .showWarning();
            }
        });
    }
    
    /**
     * Renames selected account on enter key pressed.
     */
    @FXML
    private void renameOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            rename();
        }
    }
    
    /**
     * Adds new account on mouse click.
     */
    @FXML
    private void add() {
        //Dialog creation
        TextInputDialog dialogRename = new TextInputDialog("");
        dialogRename.setTitle("Add account");
        dialogRename.setHeaderText("");
        dialogRename.setContentText("New account name");
        DialogBuilder builder = new DialogBuilder(dialogRename, INIT_NEW_ACCOUNT_WIDTH);
        //Accept button binding
        final Button acceptButton = (Button) dialogRename.getDialogPane().lookupButton(ButtonType.OK);
        BooleanProperty nameSizeCorrect = new SimpleBooleanProperty(false);
        dialogRename.getEditor().textProperty().addListener((observable, initialValue, newValue) -> {
            nameSizeCorrect.set((newValue.length() < MIN_NAME_SIZE) || (newValue.length() > MAX_NAME_SIZE));
        });
        BooleanBinding disableButton = dialogRename.getEditor().textProperty().isEmpty()
                .or(nameSizeCorrect);
        acceptButton.disableProperty().bind(disableButton);
        //Inputs validation
        ValidationSupport validation = new ValidationSupport();
        validation.registerValidator(dialogRename.getEditor(), Validator.createEmptyValidator("Name is Required."));
        //Show the dialog window
        dialogRename.show();
        //On confirmation
        acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
            if (true) {
                
            } else {
                add();
                Notifications.create()
                    .title("Wrong name.")
                    .text("Please enter correct name.")
                    .position(Pos.CENTER)
                    .showWarning();
            }
        });
    }
    
    /**
     * Adds new account on enter key pressed.
     */
    @FXML
    private void addOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            add();
        }
    }
    
    /**
     * Deletes selected account on mouse click.
     */
    @FXML
    private void delete() {
        System.out.println("Delete");
    }
    
    /**
     * Deletes selected account on enter key pressed.
     */
    @FXML
    private void deleteOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            delete();
        }
    }
    
}