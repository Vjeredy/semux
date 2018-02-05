/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static wallet.Options.DEFAULT_STYLES_PATH;
import static wallet.Options.INIT_MAIN_SCENE_HEIGHT;
import static wallet.Options.INIT_MAIN_SCENE_WIDTH;
import static wallet.Options.MAIN_PACKAGE_NAME;
import static wallet.Options.MIN_MAIN_STAGE_HEIGHT;
import static wallet.Options.MIN_MAIN_STAGE_WIDTH;

/**
 * Application Class. Loads main scenes, sets up main stage with home scene.
 */
public class ApplicationLoader extends Application {

    private static Stage stageMain;
    private static WalletSession currentSession;
    private static final List<Scene> scenesList = new ArrayList();

    /**
     * Startup options.
     * 
     * @param args
     *            options
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Dont need to add new account and list if loaded from preloader, just for
        // example
        WalletAccount accountFromPreloader = new WalletAccount();
        List<WalletAccount> accountsListFromPreloader = new ArrayList();
        accountsListFromPreloader.add(accountFromPreloader);
        WalletSession newSession = new WalletSession(accountFromPreloader, accountsListFromPreloader);
        currentSession = newSession;
        ApplicationLoader.stageMain = stage;
        // Loading main scenes
        Scene sceneHome = new Scene(FXMLLoader.load(getClass()
                .getResource("scenes/home/HomeFXML.fxml")), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        Scene sceneSend = new Scene(FXMLLoader.load(getClass()
                .getResource("scenes/send/SendFXML.fxml")), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        Scene sceneReceive = new Scene(FXMLLoader.load(getClass()
                .getResource("scenes/receive/ReceiveFXML.fxml")), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        Scene sceneTransactions = new Scene(FXMLLoader.load(getClass()
                .getResource("scenes/transactions/TransactionsFXML.fxml")), INIT_MAIN_SCENE_WIDTH,
                INIT_MAIN_SCENE_HEIGHT);
        Scene sceneDelegates = new Scene(FXMLLoader.load(getClass()
                .getResource("scenes/delegates/DelegatesFXML.fxml")), INIT_MAIN_SCENE_WIDTH, INIT_MAIN_SCENE_HEIGHT);
        scenesList.addAll(Arrays.asList(sceneHome, sceneSend, sceneReceive, sceneTransactions, sceneDelegates));
        scenesList.stream().forEach((scene) -> {
            StylesLoader stylesLoader = new StylesLoader(scene, MAIN_PACKAGE_NAME + DEFAULT_STYLES_PATH);
        });
        // Prepare and show main stage
        stage.setTitle("Semux Wallet");
        stage.setMinWidth(MIN_MAIN_STAGE_WIDTH);
        stage.setMinHeight(MIN_MAIN_STAGE_HEIGHT);
        StageBuilder builder = new StageBuilder(stageMain, sceneHome);
        // Update values in new thread
        WalletUpdater updater = new WalletUpdater(newSession);
    }

    /**
     * Stops all threads when application closed.
     */
    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Get main scenes list.
     * 
     * @return list of main scenes
     */
    public static List<Scene> getScenesList() {
        return scenesList;
    }

    /**
     * Get main stage.
     * 
     * @return
     */
    public static Stage getStage() {
        return stageMain;
    }

    /**
     * Get current session.
     * 
     * @return the currentSession
     */
    public static WalletSession getCurrentSession() {
        return currentSession;
    }

}