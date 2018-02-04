/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet.scenes.delegates;

import java.awt.Robot;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import static wallet.Options.DEFAULT_FEE;
import static wallet.Options.MAX_NAME_SIZE;
import static wallet.Options.MIN_NAME_SIZE;

import wallet.TextFieldFormatter;
import wallet.controls.navigation.NavigationButtonsActivator;

/**
 * Delegates scene controller. Processes votes, unvotes, delegates registrations.
 */
public class DelegatesFXMLController implements Initializable {
    
    private TextFieldFormatter votesFormatter;
    private TextFieldFormatter unvotesFormatter;
    private double votes;
    private double unvotes;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TableColumn<?, ?> nameTableColumn;
    @FXML
    private TableColumn<?, ?> addressTableColumn;
    @FXML
    private TableView<?> delegatesTableView;
    @FXML
    private TableColumn<?, ?> rankTableColumn;
    @FXML
    private TableColumn<?, ?> votesTableColumn;
    @FXML
    private TableColumn<?, ?> votesFromMeTableColumn;
    @FXML
    private TableColumn<?, ?> statusTableColumn;
    @FXML
    private TableColumn<?, ?> rateTableColumn;
    @FXML
    private TextField delegateTextField;
    @FXML
    private ChoiceBox<?> addressChoiceBox;
    @FXML
    private TextField votesTextField;
    @FXML
    private Button voteButton;
    @FXML
    private TextField unvotesTextField;
    @FXML
    private Button unvoteButton;
    @FXML
    private TextField registerNameTextField;
    @FXML
    private Button registerButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Navigation buttons initialization
        NavigationButtonsActivator activator = new NavigationButtonsActivator(anchorPane, "delegates");
        //Vote binding
        BooleanProperty votesAmountCorrect = new SimpleBooleanProperty(false);
        votesTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            votes = (double) votesFormatter.getConverter().fromString(votesTextField.getText());
            votesAmountCorrect.set(votes <= DEFAULT_FEE);
        });
        BooleanBinding disableVotes = votesTextField.textProperty().isEmpty()
                .or(votesAmountCorrect);
        voteButton.disableProperty().bind(disableVotes);
        //Unvote binding
        BooleanProperty unvotesAmountCorrect = new SimpleBooleanProperty(false);
        unvotesTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            unvotes = (double) unvotesFormatter.getConverter().fromString(unvotesTextField.getText());
            unvotesAmountCorrect.set(unvotes <= DEFAULT_FEE);
        });
        BooleanBinding disableUnvotes = unvotesTextField.textProperty().isEmpty()
                .or(unvotesAmountCorrect);
        unvoteButton.disableProperty().bind(disableUnvotes);
        //Register binding
        BooleanProperty nameSizeCorrect = new SimpleBooleanProperty(false);
        registerNameTextField.textProperty().addListener((observable, initialValue, newValue) -> {
            nameSizeCorrect.set((newValue.length() < MIN_NAME_SIZE) || (newValue.length() > MAX_NAME_SIZE));
        });
        BooleanBinding disableButton = registerNameTextField.textProperty().isEmpty()
                .or(nameSizeCorrect);
        registerButton.disableProperty().bind(disableButton);
        //Votes formatter
        votesFormatter = new TextFieldFormatter(votesTextField);
        votesFormatter.initFormatter(DEFAULT_FEE);
        //Unvotes formatter
        unvotesFormatter = new TextFieldFormatter(unvotesTextField);
        unvotesFormatter.initFormatter(DEFAULT_FEE);
    } 
    
    /**
    * Votes for delegate on mouse click.
    */
    @FXML
    private void vote() {
        votesTextField.clear();
        delegatesTableView.requestFocus();
    }
    
    /**
    * Votes for delegate on enter key pressed.
    */
    @FXML
    private void voteOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            vote();
        }
    }
    
    /**
    * Unvotes delegate on mouse click.
    */
    @FXML
    private void unvote() {
        unvotesTextField.clear();
        delegatesTableView.requestFocus();
    }
    
    /**
    * Unvotes delegate on enter key pressed.
    */
    @FXML
    private void unvoteOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            unvote();
        }
    }
    
    /**
    * Registers delegate on mouse click.
    */
    @FXML
    private void register() {
        registerNameTextField.clear();
        delegatesTableView.requestFocus();
    }
    
    /**
    * Registers delegate on enter key pressed.
    */
    @FXML
    private void registerOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            register();
        }
    }
    
    /**
    * Focuses on next element.
    */
    @FXML
    private void fireTabOnEnter(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER) {
            try {
                Robot bot = new Robot();
                bot.keyPress(java.awt.event.KeyEvent.VK_TAB);
            }
            catch (Exception e) {
            }
        }    
    }
    
}