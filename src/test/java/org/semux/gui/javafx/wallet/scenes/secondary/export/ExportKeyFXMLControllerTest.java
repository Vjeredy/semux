/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.export;

import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ExportKeyFXMLControllerTest extends ApplicationTest {

    private static Stage testExportKeyStage;

    @BeforeClass
    public static void loadExportKeyGUI() {
        PlatformImpl.startup(() -> {
            try {
                testExportKeyStage = new Stage();
                Parent mainExportKeyNode = FXMLLoader
                        .load(ExportKeyFXMLController.class.getResource("ExportKeyFXML.fxml"));
                testExportKeyStage.setScene(new Scene(mainExportKeyNode));
                testExportKeyStage.show();
                testExportKeyStage.toFront();
                testExportKeyStage.requestFocus();
            } catch (Exception exception) {
            }
        });
    }

    @AfterClass
    public static void tearDownStage() {
        Platform.runLater(() -> {
            testExportKeyStage.hide();
        });
    }

    @Test
    public void testCopyKeyButton() {
        Button copyKeyButton = GuiTest.find("#copyKeyButton");
        assertThat(copyKeyButton.getText(), is("Copy private key"));
        assertTrue(copyKeyButton.isVisible());
        clickOn(copyKeyButton);
        press(KeyCode.ESCAPE);
    }

    @Test
    public void testExportKeyTableView() {
        TableView exportKeyTableView = GuiTest.find("#exportKeyTableView");
        assertTrue(exportKeyTableView.isVisible());
        clickOn(exportKeyTableView);
        clickOn("#numberExportKeyTableColumn");
        clickOn("#addressExportKeyTableColumn");
        clickOn("#nameExportKeyTableColumn");
        clickOn("#keyExportKeyTableColumn");
    }

}