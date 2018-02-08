/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.semux.gui.javafx.wallet;

import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.control.Dialog;

/**
 * Loads .css stylesheet and adds it to scene.
 */
public class StylesLoader implements Options {

    /**
     * Loader constructor for scenes. Loads .css stylesheet and adds it to scene.
     * 
     * @param scene
     *            scene .css file is added to
     * @param stylePath
     *            path to .css file
     */
    public StylesLoader(Scene scene, String stylePath) {
        URL cssURL = getClass().getResource(RUN_PATH + stylePath);
        String css = cssURL.toExternalForm();
        scene.getStylesheets().add(css);
    }

    /**
     * Loader constructor for dialogs. Loads .css stylesheet and adds it to scene.
     * 
     * @param dialog
     *            dialog window .css file is added to
     * @param stylePath
     *            path to .css file
     */
    public StylesLoader(Dialog dialog, String stylePath) {
        dialog.getDialogPane().getStylesheets().add(getClass().getResource(RUN_PATH + stylePath).toExternalForm());
    }

}