/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlalumnosfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author mecob
 */
public class ImageViewerController implements Initializable {

    @FXML
    private ImageView imageMain;
    
    private BufferedImage bffImage;
    public void setImage(BufferedImage Bffimage) {
        bffImage = Bffimage;
        Image image = SwingFXUtils.toFXImage(Bffimage, null);
        imageMain.setImage(image);
        //ImageIO.write(Bffimage, "png", new File("a.png"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        saveImage.addEventHandler(ActionEvent.ACTION, e -> {
            try {
                Stage window = new Stage();
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setSelectedExtensionFilter(new ExtensionFilter(".xlsx"));
                fileChooser.setTitle("Guardar grafica de seguimiento");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagen grafica (*.png)", "*.png"));
                File file = fileChooser.showSaveDialog(window);
                ImageIO.write(bffImage, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(ImageViewerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private Button saveImage;
}
