/**
 * Copyright (c) 2017-2018 The Semux Developers
 *
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package wallet;

import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static wallet.Options.DEFAULT_STYLES_PATH;
import static wallet.Options.LOGO_ICON_PATH;
import static wallet.Options.MAIN_PACKAGE_NAME;

/**
 * Builder for dialog windows. Loads logo, sets graphic and .css styles.
 * 
 * @author jeredy
 */
public class DialogBuilder {

    /**
     * Builder constructor dialog windows. Loads logo, sets graphic and .css styles.
     * 
     * @param dialog
     *            dialog window to build
     * @param initialWidth
     *            initial dialog window width
     */
    public DialogBuilder(Dialog dialog, float initialWidth) {
        Image logo = new Image(getClass().getResourceAsStream(LOGO_ICON_PATH));
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(logo);
        ImageView imageView = new ImageView(logo);
        dialog.setGraphic((Node) imageView);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.getDialogPane().setMinWidth(initialWidth);
        StylesLoader stylesLoader = new StylesLoader(dialog, MAIN_PACKAGE_NAME + DEFAULT_STYLES_PATH);
    }

}