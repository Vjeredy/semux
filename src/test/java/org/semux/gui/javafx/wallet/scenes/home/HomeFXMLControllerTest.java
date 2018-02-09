/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet.scenes.home;

import com.sun.javafx.application.PlatformImpl;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import org.junit.BeforeClass;
import org.junit.Test;
import org.semux.gui.javafx.wallet.GUITestHelper;
import org.testfx.framework.junit.ApplicationTest;

public class HomeFXMLControllerTest extends ApplicationTest {

    private static Stage testHomeStage;
    private static GUITestHelper testHomeHelper = new GUITestHelper();

    @BeforeClass
    public static void loadHomeGUI() {
        PlatformImpl.setImplicitExit(true);
        PlatformImpl.startup(() -> {
            testHomeHelper.checkLoadedStages();
            testHomeStage = new Stage();
            try {
                Parent mainHomeNode = FXMLLoader.load(HomeFXMLController.class.getResource("HomeFXML.fxml"));
                testHomeStage.setScene(new Scene(mainHomeNode));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            testHomeStage.show();
            testHomeStage.toFront();
            testHomeStage.requestFocus();
        });
    }

    @Test
    public void testAccountNameHomeTextField() {
        testHomeHelper.testCharTextField("accountNameTextField");
    }

    @Test
    public void testBlockNumberHomeTextField() {
        testHomeHelper.testCharTextField("blockNumberTextField");
    }

    @Test
    public void testBlockTimeHomeTextField() {
        testHomeHelper.testCharTextField("blockTimeTextField");
    }

    @Test
    public void testCoinbaseHomeTextField() {
        testHomeHelper.testCharTextField("coinbaseTextField");
    }

    @Test
    public void testStatusHomeTextField() {
        testHomeHelper.testCharTextField("statusTextField");
    }

    @Test
    public void testAvailableHomeTextField() {
        testHomeHelper.testCharTextField("availableTextField");
    }

    @Test
    public void testLockedHomeTextField() {
        testHomeHelper.testCharTextField("lockedTextField");
    }

    @Test
    public void testTotalBalanceHomeTextField() {
        testHomeHelper.testCharTextField("totalBalanceTextField");
    }

    @Test
    public void testTransactionsHomeListView() {
        testHomeHelper.testListView("transactionsListView");
    }

}