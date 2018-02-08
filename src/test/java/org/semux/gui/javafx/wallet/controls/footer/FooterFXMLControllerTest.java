/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.footer;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class FooterFXMLControllerTest extends ApplicationTest {

    private static Stage testFooterStage;

    @BeforeClass
    public static void loadFooterGUI() {
        PlatformImpl.setImplicitExit(false);
        PlatformImpl.startup(() -> {
            try {
                testFooterStage = new Stage();
                FXMLLoader footerLoader = new FXMLLoader(FooterFXMLController.class.getResource("FooterFXML.fxml"));
                HBox rootFooterHbox = new HBox();
                footerLoader.setRoot(rootFooterHbox);
                FooterFXMLController footerController = new FooterFXMLController();
                footerLoader.setController(footerController);
                Parent rootFooter = footerLoader.load();
                testFooterStage.setScene(new Scene(rootFooter));
                testFooterStage.show();
                testFooterStage.toFront();
                testFooterStage.requestFocus();
            } catch (Exception exception) {
            }
        });
    }

    @Test
    public void testPeersSyncTextField() {
        TextField peersSyncTextField = GuiTest.find("#peersSyncTextField");
        assertThat(peersSyncTextField.getText(), is(""));
        assertTrue(peersSyncTextField.isVisible());
        clickOn(peersSyncTextField);
        peersSyncTextField.setText("Test");
        assertThat(peersSyncTextField.getText(), is("Test"));
    }

    @AfterClass
    public static void tearDownFooterStage() {
        PlatformImpl.runLater(() -> {
            try {
                testFooterStage.hide();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

}