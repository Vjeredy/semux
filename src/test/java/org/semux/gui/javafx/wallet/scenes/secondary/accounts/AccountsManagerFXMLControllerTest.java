/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.accounts;

import com.sun.javafx.application.PlatformImpl;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class AccountsManagerFXMLControllerTest extends ApplicationTest {

    private static Stage testAccountsStage;

    @BeforeClass
    public static void loadExportKeyGUI() {
        PlatformImpl.setImplicitExit(false);
        PlatformImpl.startup(() -> {
            try {
                testAccountsStage = new Stage();
                Parent mainAccountsNode = FXMLLoader
                        .load(AccountsManagerFXMLController.class.getResource("AccountsManagerFXML.fxml"));
                testAccountsStage.setScene(new Scene(mainAccountsNode));
                testAccountsStage.show();
                testAccountsStage.toFront();
                testAccountsStage.requestFocus();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

    @Test
    public void testRenameAccountButton() {
        Button renameAccountButton = GuiTest.find("#renameAccountButton");
        assertThat(renameAccountButton.getText(), is("Rename Account"));
        assertTrue(renameAccountButton.isVisible());
        clickOn(renameAccountButton);
    }

    @Test
    public void testNewAccountButton() {
        Button newAccountButton = GuiTest.find("#newAccountButton");
        assertThat(newAccountButton.getText(), is("New Account"));
        assertTrue(newAccountButton.isVisible());
        clickOn(newAccountButton);
    }

    @Test
    public void testDeleteAccountButton() {
        Button deleteAccountButton = GuiTest.find("#deleteAccountButton");
        assertThat(deleteAccountButton.getText(), is("Delete Account"));
        assertTrue(deleteAccountButton.isVisible());
        clickOn(deleteAccountButton);
    }

    @Test
    public void testAccountsTableView() {
        TableView accountsTableView = GuiTest.find("#accountsTableView");
        assertTrue(accountsTableView.isVisible());
        clickOn(accountsTableView);
        Node numberAccountsTableColumn = GuiTest.find("#numberAccountsTableColumn");
        assertTrue(numberAccountsTableColumn.isVisible());
        clickOn(numberAccountsTableColumn);
        Node nameAccountsTableColumn = GuiTest.find("#nameAccountsTableColumn");
        assertTrue(nameAccountsTableColumn.isVisible());
        clickOn(nameAccountsTableColumn);
        Node addressAccountsTableColumn = GuiTest.find("#addressAccountsTableColumn");
        assertTrue(addressAccountsTableColumn.isVisible());
        clickOn(addressAccountsTableColumn);
        Node availableAccountsTableColumn = GuiTest.find("#availableAccountsTableColumn");
        assertTrue(availableAccountsTableColumn.isVisible());
        clickOn(availableAccountsTableColumn);
        Node lockedAccountsTableColumn = GuiTest.find("#lockedAccountsTableColumn");
        assertTrue(lockedAccountsTableColumn.isVisible());
        clickOn(lockedAccountsTableColumn);
    }

    @After
    public void closeAccountsManagerPopupWindows() {
        press(KeyCode.ESCAPE);
    }

    @AfterClass
    public static void tearDownAccountsStage() {
        PlatformImpl.runLater(() -> {
            try {
                testAccountsStage.hide();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

}