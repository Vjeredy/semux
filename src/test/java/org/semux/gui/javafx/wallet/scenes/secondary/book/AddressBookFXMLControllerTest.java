/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.secondary.book;

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

public class AddressBookFXMLControllerTest extends ApplicationTest {

    private static Stage testAddressBookStage;

    @BeforeClass
    public static void loadAddressBookGUI() {
        PlatformImpl.setImplicitExit(false);
        PlatformImpl.startup(() -> {
            try {
                testAddressBookStage = new Stage();
                Parent mainAddressBookNode = FXMLLoader
                        .load(AddressBookFXMLController.class.getResource("AddressBookFXML.fxml"));
                testAddressBookStage.setScene(new Scene(mainAddressBookNode));
                testAddressBookStage.show();
                testAddressBookStage.toFront();
                testAddressBookStage.requestFocus();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

    @Test
    public void testAddAddressButton() {
        Button addAddressButton = GuiTest.find("#addAddressButton");
        assertThat(addAddressButton.getText(), is("Add"));
        assertTrue(addAddressButton.isVisible());
        clickOn(addAddressButton);
    }

    @Test
    public void testCopyAddressButton() {
        Button copyAddressButton = GuiTest.find("#copyAddressButton");
        assertThat(copyAddressButton.getText(), is("Copy"));
        assertTrue(copyAddressButton.isVisible());
        clickOn(copyAddressButton);
    }

    @Test
    public void testDeleteAddressButton() {
        Button deleteAddressButton = GuiTest.find("#deleteAddressButton");
        assertThat(deleteAddressButton.getText(), is("Delete"));
        assertTrue(deleteAddressButton.isVisible());
        clickOn(deleteAddressButton);
    }

    @Test
    public void testAddressBookTableView() {
        TableView addressBookTableView = GuiTest.find("#addressBookTableView");
        assertTrue(addressBookTableView.isVisible());
        clickOn(addressBookTableView);
        Node numberAddressBookTableColumn = GuiTest.find("#numberAddressBookTableColumn");
        assertTrue(numberAddressBookTableColumn.isVisible());
        clickOn(numberAddressBookTableColumn);
        Node nameAddressBookTableColumn = GuiTest.find("#nameAddressBookTableColumn");
        assertTrue(nameAddressBookTableColumn.isVisible());
        clickOn(nameAddressBookTableColumn);
        Node addressAddressBookTableColumn = GuiTest.find("#addressAddressBookTableColumn");
        assertTrue(addressAddressBookTableColumn.isVisible());
        clickOn(addressAddressBookTableColumn);
    }

    @After
    public void closeAddressBookPopupWindows() {
        press(KeyCode.ESCAPE);
    }

    @AfterClass
    public static void tearDownAddressBookStage() {
        PlatformImpl.runLater(() -> {
            try {
                testAddressBookStage.hide();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

}