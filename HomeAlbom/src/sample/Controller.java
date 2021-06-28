package sample;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;


public class Controller  {


    @FXML
    private AnchorPane anchor;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private Pagination pagination;

    @FXML
    private Button buttonDelete;

    File filesJpg[];

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Stage stage = (Stage) anchor.getScene().getWindow();
        openDirectoryChooser(stage);
        pagination.setPageFactory(new Callback<Integer, Node>() {
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });

    }

    private void openDirectoryChooser(Stage parent) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(parent);

        if (selectedDirectory != null) {
            FilenameFilter filterJpg = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".jpg");
                }
            };

            filesJpg = selectedDirectory.listFiles(filterJpg);

        }
    }

    private VBox createPage(int index) {

        ImageView imageView = new ImageView();

        File file = filesJpg[index];
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            imageView.setFitWidth(800);
            imageView.setFitHeight(550);
            imageView.setSmooth(true);
            imageView.setCache(true);
        } catch (IOException ex) {

        }

        VBox pageBox = new VBox();
        pageBox.getChildren().add(imageView);

        return pageBox;
    }

    private void initialize() {
        buttonDelete.setOnAction(event ->{
            VBox.getChildren().clear();




        });

    }

}

