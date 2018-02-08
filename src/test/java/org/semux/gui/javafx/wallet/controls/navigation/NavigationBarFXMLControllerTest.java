/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.navigation;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;
import org.semux.gui.javafx.wallet.Options;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationBarFXMLControllerTest extends ApplicationTest implements Options {

    private static Stage testNavigationStage;
    private static Scene testNavigationScene;
    private static Scene newNavigationTestScene;
    private static NavigationBarFXMLController navigationController;

    @BeforeClass
    public static void loadNavigationGUI() {
        PlatformImpl.setImplicitExit(false);
        PlatformImpl.startup(() -> {
            try {
                testNavigationStage = new Stage();
                newNavigationTestScene = new Scene(new HBox());
                FXMLLoader navigationLoader = new FXMLLoader(
                        NavigationBarFXMLController.class.getResource("NavigationBarFXML.fxml"));
                HBox rootNavigationHbox = new HBox();
                navigationLoader.setRoot(rootNavigationHbox);
                navigationController = new NavigationBarFXMLController();
                navigationLoader.setController(navigationController);
                Parent rootNavigation = navigationLoader.load();
                testNavigationScene = new Scene(rootNavigation);
                testNavigationStage.setScene(testNavigationScene);
                testNavigationStage.show();
                testNavigationStage.toFront();
                testNavigationStage.requestFocus();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

    @Test
    public void testHomeNavigationButton() {
        Button homeNavigationButton = GuiTest.find("#homeNavigationButton");
        assertThat(homeNavigationButton.getText(), is("Home"));
        assertTrue(homeNavigationButton.isVisible());
        navigationController.setCurrentSceneHome(newNavigationTestScene);
        clickOn(homeNavigationButton);
    }

    @Test
    public void testSendNavigationButton() {
        Button sendNavigationButton = GuiTest.find("#sendNavigationButton");
        assertThat(sendNavigationButton.getText(), is("Send"));
        assertTrue(sendNavigationButton.isVisible());
        navigationController.setCurrentSceneSend(newNavigationTestScene);
        clickOn(sendNavigationButton);
    }

    @Test
    public void testReceiveNavigationButton() {
        Button sendNavigationButton = GuiTest.find("#receiveNavigationButton");
        assertThat(sendNavigationButton.getText(), is("Receive"));
        assertTrue(sendNavigationButton.isVisible());
        navigationController.setCurrentSceneReceive(newNavigationTestScene);
        clickOn(sendNavigationButton);
    }

    @Test
    public void testTransactionsNavigationButton() {
        Button transactionsNavigationButton = GuiTest.find("#transactionsNavigationButton");
        assertThat(transactionsNavigationButton.getText(), is("Transactions"));
        assertTrue(transactionsNavigationButton.isVisible());
        navigationController.setCurrentSceneTransactions(newNavigationTestScene);
        clickOn(transactionsNavigationButton);
    }

    @Test
    public void testDelegatesNavigationButton() {
        Button delegatesNavigationButton = GuiTest.find("#delegatesNavigationButton");
        assertThat(delegatesNavigationButton.getText(), is("Delegates"));
        assertTrue(delegatesNavigationButton.isVisible());
        navigationController.setCurrentSceneDelegates(newNavigationTestScene);
        clickOn(delegatesNavigationButton);
    }

    @Test
    public void testLockNavigationButton() {
        Button lockNavigationButton = GuiTest.find("#lockNavigationButton");
        assertThat(lockNavigationButton.getText(), is("Lock"));
        assertTrue(lockNavigationButton.isVisible());
        clickOn(lockNavigationButton);

    }

    @After
    public void bringBackTestScene() {
        PlatformImpl.runLater(() -> {
            if (testNavigationScene.getRoot().isDisabled()) {
                navigationController.getCurrentLockedStage().hide();
                testNavigationScene.getRoot().setDisable(false);
                testNavigationStage.setScene(testNavigationScene);
                testNavigationStage.show();
            }
            testNavigationStage.setScene(testNavigationScene);
        });
    }

    @AfterClass
    public static void tearDownNavigationStage() {
        PlatformImpl.runLater(() -> {
            try {
                testNavigationStage.hide();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

}