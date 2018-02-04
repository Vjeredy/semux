/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet.controls.navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import wallet.ApplicationLoader;
import wallet.DialogBuilder;
import wallet.StageBuilder;
import wallet.WindowSizeStateWatcher;

import static wallet.Options.INIT_UNLOCK_WIDTH;

/**
 * Custom navigation buttons bar controller. Can be imported .jar packed along with .fxml file into Scene Builder as custom control.
 */
public class NavigationBarFXMLController extends HBox {
    
    private int buttonColumn;
    private boolean isLocked = false;
    private final List<Button> navigationButtonsList = new ArrayList<>();
    
    @FXML
    private Button homeNavigationButton;
    @FXML
    private Button sendNavigationButton;
    @FXML
    private Button receiveNavigationButton;
    @FXML
    private Button transactionsNavigationButton;
    @FXML
    private Button delegatesNavigationButton;
    @FXML
    private Button lockNavigationButton;
    @FXML
    private GridPane gridPane;
    
    /**
     * Constructor loads .fxml file and allows navigation bar to be imported with controller.
     */
    public NavigationBarFXMLController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NavigationBarFXML.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        //Add a handler to each of navigation buttons 
        navigationButtonsList.addAll(Arrays.asList(homeNavigationButton, sendNavigationButton, receiveNavigationButton, 
                transactionsNavigationButton, delegatesNavigationButton));
        navigationButtonsList.stream().forEach((button) -> {
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, new NavigationEventHandler());
            button.addEventHandler(KeyEvent.KEY_PRESSED, new NavigationEventHandler());
        });
    }
    
    /**
     * Handles mouse click and key press events, captures pushed navigation button column in the grid pane.
     */
    private class NavigationEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            if (GridPane.getColumnIndex(((Node) evt.getSource())) == null) {
                buttonColumn = 0;
            }
            else {
                buttonColumn = GridPane.getColumnIndex(((Node) evt.getSource()));
            }
        }
    }
    
    /**
     * Handles mouse click on any place of stage event, calls unlock method.
     */
    private class LockEventHandler implements EventHandler<Event> {
        @Override
        public void handle(Event evt) {
            unlock(evt);
        }
    }
    
    /**
     * Shows unlock dialog and unlocks wallet if password is correct.
     */
    private void unlock(Event evt) {
        if (isLocked == true) {
            //Dialog creation
            final Dialog dialogUnlock = new Dialog();
            dialogUnlock.setTitle("Unlock the Wallet");
            dialogUnlock.setHeaderText("");
            dialogUnlock.setContentText("Please enter the password");
            dialogUnlock.initModality(Modality.APPLICATION_MODAL);
            DialogBuilder dialogBuilder = new DialogBuilder(dialogUnlock, INIT_UNLOCK_WIDTH);
            ButtonType acceptButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialogUnlock.getDialogPane().getButtonTypes().addAll(acceptButtonType, ButtonType.CANCEL);
            //Pane with controls
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(15);
            grid.setPadding(new Insets(50, 10, 10, 10));
            final PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("Password");
            passwordField.setMinWidth(dialogUnlock.getDialogPane().getMinWidth() - 20);
            grid.add(new Label("Password"), 0, 0);
            grid.add(passwordField, 1, 0);
            //Accept button binding
            final Node acceptButton = dialogUnlock.getDialogPane().lookupButton(acceptButtonType);
            BooleanBinding disable = passwordField.textProperty().isEmpty();
            acceptButton.disableProperty().bind(disable);
            //Inputs validation
            ValidationSupport validation = new ValidationSupport();
            validation.registerValidator(passwordField, Validator.createEmptyValidator("Password is Required."));
            //Show the dialog window
            dialogUnlock.getDialogPane().setContent(grid);
            passwordField.requestFocus();
            dialogUnlock.show();
            //On confirmation
            acceptButton.addEventFilter(ActionEvent.ACTION, event -> {
                if (true) {
                    Stage stage = ApplicationLoader.getStage();
                    Scene scene = gridPane.getParent().getScene();
                    scene.getRoot().setDisable(false);
                    StageBuilder stageBuilder = new StageBuilder(stage, scene);
                    Stage lockedStage = (Stage) evt.getSource();
                    WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(stage, lockedStage);
                    lockedStage.hide();
                    isLocked = false;
                    lockNavigationButton.getStyleClass().remove("navigation-button-active");    
                } else {
                    unlock(evt);
                    Notifications.create()
                        .title("Wrong password.")
                        .text("Please enter correct password.")
                        .position(Pos.CENTER)
                        .showWarning();
                }
            });
        }
    }
    
    //Changes scene when navigation button pressed by mouse.
    @FXML
    private void changeScene() {
        navigationButtonsList.get(0).requestFocus();
        Stage stage = ApplicationLoader.getStage();
        boolean isMaximized = stage.isMaximized();
        double stageWidth = stage.getWidth();
        double stageHeight = stage.getHeight();
        Platform.runLater(() -> {
            try {
                stage.setScene(ApplicationLoader.getScenesList().get(buttonColumn));
            } catch (Exception exception) {
                System.out.println("Exception" + exception);
            }
        });
        WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(stage, isMaximized, stageWidth, stageHeight);
    }
    
    //Changes scene when navigation button pressed by enter key.
    @FXML
    private void changeSceneOnEnterKeyPressed(KeyEvent key) {
        if(key.getCode().equals(KeyCode.ENTER)) {
            changeScene();
        }
    }
    
    //Invokes locked wallet stage on mouse click.
    @FXML
    private void lockScreen() {
        if (isLocked == false) {
            Stage lockedStage = new Stage();
            lockedStage.addEventFilter(MouseEvent.MOUSE_CLICKED, new LockEventHandler());
            lockNavigationButton.getStyleClass().add("navigation-button-active");
            Stage stage = ApplicationLoader.getStage();
            Scene lockedScene = gridPane.getParent().getScene();
            lockedStage.initStyle(StageStyle.TRANSPARENT);
            lockedScene.setFill(Color.TRANSPARENT);
            lockedScene.getRoot().setDisable(true);
            lockedStage.setTitle("Semux Wallet - Locked");
            StageBuilder builder = new StageBuilder(lockedStage, lockedScene);
            WindowSizeStateWatcher watcher = new WindowSizeStateWatcher(lockedStage, stage);
            stage.setMaximized(false);
            stage.hide();
            isLocked = true;
        }
    }
    
}