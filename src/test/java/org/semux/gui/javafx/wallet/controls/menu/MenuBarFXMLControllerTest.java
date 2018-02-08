/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.controls.menu;

import com.sun.javafx.application.PlatformImpl;
import com.sun.javafx.stage.StageHelper;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.input.KeyCode;
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

import static org.junit.Assert.assertTrue;

public class MenuBarFXMLControllerTest extends ApplicationTest {

    private static Stage testMenuBarStage;

    @BeforeClass
    public static void loadMenuBarGUI() {
        PlatformImpl.setImplicitExit(false);
        PlatformImpl.startup(() -> {
            try {
                testMenuBarStage = new Stage();
                FXMLLoader menuBarLoader = new FXMLLoader(MenuBarFXMLController.class.getResource("MenuBarFXML.fxml"));
                HBox rootMenuBarHbox = new HBox();
                menuBarLoader.setRoot(rootMenuBarHbox);
                MenuBarFXMLController menuBarController = new MenuBarFXMLController();
                menuBarLoader.setController(menuBarController);
                Parent rootMenuBar = menuBarLoader.load();
                Scene testMenuBarScene = new Scene(rootMenuBar);
                testMenuBarStage.setScene(testMenuBarScene);
                testMenuBarStage.show();
                testMenuBarStage.toFront();
                testMenuBarStage.requestFocus();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

    @Test
    public void testMainMenuBar() {
        MenuBar mainMenuBar = GuiTest.find("#menuBar");
        assertTrue(mainMenuBar.isVisible());
        clickOn(mainMenuBar);
    }

    @Test
    public void testFileMenu() {
        Node fileMenu = GuiTest.find("#fileMenu");
        assertTrue(fileMenu.isVisible());
        clickOn(fileMenu);
    }

    @Test
    public void testWalletMenu() {
        Node walletMenu = GuiTest.find("#walletMenu");
        assertTrue(walletMenu.isVisible());
        clickOn(walletMenu);
    }

    @Test
    public void testAccountMenu() {
        Node accountMenu = GuiTest.find("#accountMenu");
        assertTrue(accountMenu.isVisible());
        clickOn(accountMenu);
    }

    @Test
    public void testHelpMenu() {
        Node helpMenu = GuiTest.find("#helpMenu");
        assertTrue(helpMenu.isVisible());
        clickOn(helpMenu);
    }

    @Test
    public void testExitMenuItem() {
        clickOn("#fileMenu").moveBy(0, 10).moveTo("#exitMenuItem");
    }

    @Test
    public void testChangePasswordMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#changePasswordMenuItem");
    }

    @Test
    public void testRecoverAccountMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#recoverAccountsMenuItem");
    }

    @Test
    public void testBackupWalletMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#backupWalletMenuItem");
    }

    @Test
    public void testImportKeyMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#importKeyMenuItem");
    }

    @Test
    public void testExportKeyMenuItem() {
        clickOn("#walletMenu").moveBy(0, 10).clickOn("#exportKeyMenuItem");
    }

    @Test
    public void testAccountsManagerMenuItem() {
        clickOn("#accountMenu").moveBy(0, 10).clickOn("#manageAccountsMenuItem");
    }

    @Test
    public void testAboutMenuItem() {
        clickOn("#helpMenu").moveBy(0, 10).clickOn("#aboutMenuItem");
    }

    @Test
    public void testHelpMenuItem() {
        clickOn("#helpMenu").moveBy(0, 10).clickOn("#helpMenuItem");
    }

    @After
    public void closeMenuBarPopupWindows() {
        press(KeyCode.ESCAPE);
        if (StageHelper.getStages().size() > 1) {
            PlatformImpl.runLater(() -> {
                StageHelper.getStages().get(1).hide();
            });
        }
    }

    @AfterClass
    public static void tearMenuBarStage() {
        PlatformImpl.runLater(() -> {
            try {
                testMenuBarStage.hide();
            } catch (Exception exception) {
                System.out.println(exception);
            }
        });
    }

}