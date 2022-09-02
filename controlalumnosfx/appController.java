package controlalumnosfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

public class appController implements Initializable {

    @FXML
    private ImageView pastelIcon;

    @FXML
    private ImageView histoIcono;

    @FXML
    private Label semLabel;

    @FXML
    private Label resLabel;

    @FXML
    private Label estado;

    @FXML
    private Label espLabel;

    @FXML
    private Label edadLbel;

    @FXML
    private Label capLabel;

    @FXML
    private Label sexLabel;

    @FXML
    private Label nctlLabel;

    @FXML
    private Label renLabel;

    @FXML
    private Label carLabel;

    @FXML
    private Label proLabel;

    private Modelo model;

    @FXML
    private TableColumn<Alumnos, String> ageCol;

    @FXML
    private TableColumn<Alumnos, String> crPrCol;

    @FXML
    private MenuItem openFile;

    @FXML
    private TableColumn<Alumnos, String> cargaCol;

    @FXML
    private TableView<Alumnos> segTable;

    @FXML
    private TableColumn<Alumnos, String> nCtlCol;

    @FXML
    private TableColumn<Alumnos, String> nameCol;

    @FXML
    private TableColumn<Alumnos, String> PromCol;

    @FXML
    private TableColumn<Alumnos, String> semCol;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TableColumn<Alumnos, String> sexCol;

    @FXML
    private TableColumn<Alumnos, String> espCol;

    @FXML
    private Tab segTab;

    @FXML
    private TabPane tabPaneApp;

    boolean reopen = false;

    @FXML
    void onSaveFile(ActionEvent event) {
        System.out.println("Save as..");
        if (tabPaneApp.getSelectionModel().isSelected(0)) {
            try {
                // parent component of the dialog

                Stage window = new Stage();
                FileChooser fileChooser = new FileChooser();
                //fileChooser.setSelectedExtensionFilter(new ExtensionFilter(".xlsx"));
                fileChooser.setTitle("Guardar excel de seguimiento");
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel book (*.xlsx)", "*.xlsx"));
                File file = fileChooser.showSaveDialog(window);

                model.writeExcel(file);
            } catch (Exception ex) {
                Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    void onOpenFile(ActionEvent event) {
        //

        if (tabPaneApp.getSelectionModel().isSelected(0)) {
            if (!reopen) {
                reopen = true;
                Stage window = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Importar excel de seguimiento");
                File file = fileChooser.showOpenDialog(window);

                //System.out.println(file.getPath());
                //
                try {

                    model.setSEGUIMIENTO_PATH_CHOOSE(file.getPath());
                    segTable.setEditable(true);

                    alumnosArrayList.addAll(model.importExcelSeguimiento());

                    nCtlCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nCtrl"));
                    nameCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("nombre"));
                    sexCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("sexo"));
                    ageCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("edad"));
                    semCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("sem"));
                    crPrCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("crApr"));
                    cargaCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("carga"));
                    PromCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("promedio"));
                    espCol.setCellValueFactory(new PropertyValueFactory<Alumnos, String>("especialidad"));

                    ObservableList<Alumnos> people = observableList();
                    //people.add(new Alumnos());
                    segTable.setItems(people);
                    segTable.setEditable(false);

                } catch (Exception ex) {
                    System.out.println("Shale");
                    Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("Ajio");
                reOpen();
            }

        }
    }
    Stage window;

    public void reOpen() {
        Stage window = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Importar excel de seguimiento");
        File file = fileChooser.showOpenDialog(window);
        //System.out.println(file.getPath());
        //
        try {

            model.setSEGUIMIENTO_PATH_CHOOSE(file.getPath());
            segTable.setEditable(true);
            alumnosArrayList = new ArrayList<Alumnos>();
            alumnosArrayList.addAll(model.importExcelSeguimiento());
            ObservableList<Alumnos> people = FXCollections.observableArrayList();
            people.removeAll();
            //alumnosArrayList.set(index, alum);
            //System.out.println(alumnosArrayList.get(index).getSexo());

            //segTable.setEditable(false);
            people.addAll(alumnosArrayList);
            //people.add(alum);
            //people.add(new Alumnos());

            segTable.setItems(people);
            segTable.setEditable(false);
            segTable.refresh();
            //return people;
        } catch (Exception e) {

        }

    }

    public ObservableList<Alumnos> observableListUpdate(Alumnos alum, int index) {

        ObservableList<Alumnos> people = FXCollections.observableArrayList();
        people.removeAll();
        alumnosArrayList.set(index, alum);
        System.out.println(alumnosArrayList.get(index).getSexo());

        //segTable.setEditable(false);
        people.addAll(alumnosArrayList);
        //people.add(alum);
        people.add(null);
        return people;
    }

    public ObservableList<Alumnos> observableList() {

        ObservableList<Alumnos> people = FXCollections.observableArrayList();
        people.removeAll();
        people.addAll(alumnosArrayList);
        return people;
    }

    ArrayList<Alumnos> alumnosArrayList = new ArrayList<>();

    Stage graphView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        model = new Modelo();
        window = new Stage();
        Image applicationIcon = new Image(getClass().getResourceAsStream("Imagenes/logo.jpg"));
        window.getIcons().add(applicationIcon);
        graphView = new Stage();
        //window.initStyle(StageStyle.UNDECORATED);
        segTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {

                try {

                    //FxmlLoader object = new FxmlLoader();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("alumView.fxml"));
                    Parent root = loader.load();

                    Scene scene = new Scene(root);

                    //loader.setController(this);
                    //nctlLabel.setText(newSelection.getnCtrl());
                    aluViewController controlador = (aluViewController) loader.getController();

                    controlador.setAlumnos(alumnosArrayList);

                    Stage stage = (Stage) controlador.getBackButton().getScene().getWindow();

                    //
                    window.setScene(scene);
                    window.setResizable(false);

                    controlador.setAlumno(newSelection);
                    mainPane.setDisable(true);
                    //segTable.setDisable(true);
                    window.showAndWait();

                    newSelection = controlador.getAlumno();
                    //controlador.getaluViewPane().setClosable(false);

                    int index = segTable.getSelectionModel().getSelectedIndex();
                    segTable.setItems(observableListUpdate(newSelection, index));
                    segTable.refresh();
                    //updateTable(newSelection, index);
//                    segTable.r
                    //segTable.setDisable(false);
                    mainPane.setDisable(false);

                } catch (IOException ex) {
                    Logger.getLogger(appController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ColorAdjust colorAdjust = new ColorAdjust();

        histoIcono.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            colorAdjust.setBrightness(-0.5);
            histoIcono.setEffect(colorAdjust);

        });
        histoIcono.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            histoIcono.setEffect(null);
        });

        histoIcono.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            colorAdjust.setBrightness(-0.75);
            histoIcono.setEffect(colorAdjust);
        });
        histoIcono.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            colorAdjust.setBrightness(-0.5);
            histoIcono.setEffect(null);
        });

        pastelIcon.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            colorAdjust.setBrightness(-0.5);
            pastelIcon.setEffect(colorAdjust);

        });
        pastelIcon.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            pastelIcon.setEffect(null);
        });

        pastelIcon.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            colorAdjust.setBrightness(-0.75);
            pastelIcon.setEffect(colorAdjust);
        });
        pastelIcon.addEventFilter(MouseEvent.MOUSE_RELEASED, e -> {
            colorAdjust.setBrightness(-0.5);
            pastelIcon.setEffect(null);
        });
        


    }

    
    //Stage window;
    @FXML
    void onEditAlu(ActionEvent event) {

    }

    @FXML
    void onCirculoClicked(MouseEvent event) throws IOException {
        System.out.println("histo");
        //FxmlLoader object = new FxmlLoader();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphsViewer.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("Imagenes/logo.jpg"));
        graphView.getIcons().add(applicationIcon);
        

        //loader.setController(this);
        //nctlLabel.setText(newSelection.getnCtrl());
        ImageViewerController controlador = (ImageViewerController) loader.getController();
        //JFreeChart chart = null;
        //
        //
        int countEdo[] = new int[4];
        for (int i = 0; i < alumnosArrayList.size(); i++) {
////            value[i] = Double.parseDouble(alumnosArrayList.
            Alumnos alumno = alumnosArrayList.get(i);
            double resta = Double.parseDouble(alumno.getCrApr()) / 22;

            //resLabel.setText(String.format("Resta: %2.2f", resta));
            double selectEstado = Integer.parseInt(alumno.getSem()) - resta;
            System.out.println(selectEstado);
            if (selectEstado <= 1) {
                countEdo[0]++;
            } else if (selectEstado <= 2) {
                countEdo[1]++;
            } else if (selectEstado <= 3) {
                countEdo[2]++;
            } else {
                countEdo[3]++;
            }
        }

        DefaultPieDataset pieDataset = new DefaultPieDataset();

        pieDataset.setValue("Regular", countEdo[0]);
        pieDataset.setValue("Atraso", countEdo[1]);
        pieDataset.setValue("Rezago", countEdo[2]);
        pieDataset.setValue("No termina", countEdo[3]);

        JFreeChart chart = ChartFactory.createPieChart(
                "Seguimiento",
                pieDataset,
                true,
                true,
                false
        );
        //
        //
        BufferedImage Bffimage = chart.createBufferedImage(500, 340);
        

        controlador.setImage(Bffimage);

        //Stage stage = (Stage) controlador.getBackButton().getScene().getWindow();
        //
        graphView.setScene(scene);

        graphView.setResizable(false);
        mainPane.setDisable(true);
        graphView.showAndWait();
        mainPane.setDisable(false);
    }

    @FXML
    void onHistoClicked(MouseEvent event) throws IOException {
        System.out.println("histo");
        //FxmlLoader object = new FxmlLoader();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphsViewer.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        
        Image applicationIcon = new Image(getClass().getResourceAsStream("Imagenes/logo.jpg"));
        graphView.getIcons().add(applicationIcon);

        //loader.setController(this);
        //nctlLabel.setText(newSelection.getnCtrl());
        ImageViewerController controlador = (ImageViewerController) loader.getController();

        JFreeChart chart = null;
        double[] value = new double[alumnosArrayList.size()];
        for (int i = 0; i < alumnosArrayList.size(); i++) {
////            value[i] = Double.parseDouble(alumnosArrayList.
            value[i] = Double.parseDouble(alumnosArrayList.get(i).getPromedio());
            //System.out.println(Double.parseDouble(alumnosArrayList.get(i).getPromedio()));
        }
        //Random generator = new Random();
        String xaxis = "Promedio";
        String yaxis = "Cantidad Personas";
        String plotTitle = "Histograma";
        boolean show = false;
        boolean toolTips = false;
        boolean urls = false;
        PlotOrientation orientation = PlotOrientation.VERTICAL;

        int number = 4;
        HistogramDataset dataset = new HistogramDataset();
        //dataset.setType(HistogramType.FREQUENCY);
        dataset.addSeries("Histogram", value, number);
        chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
                dataset, orientation, show, toolTips, urls);
////        for (int i = 0; i < alumnosArrayList.size(); i++) {
////            value[i] = Double.parseDouble(alumnosArrayList.get(i).getPromedio());
////            int number = 10;
////            HistogramDataset dataset = new HistogramDataset();
////            dataset.setType(HistogramType.FREQUENCY);
////            dataset.addSeries("Histogram", value, number);
////            chart = ChartFactory.createHistogram(plotTitle, xaxis, yaxis,
////                    dataset, orientation, show, toolTips, urls);
        ////int width = 500;
        ////int height = 300;
        /*
            try {
                //ChartUtilities.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);

            } catch (IOException e) {
            }
         */
        //}

        BufferedImage Bffimage = chart.createBufferedImage(500, 340);
        

        controlador.setImage(Bffimage);

        //Stage stage = (Stage) controlador.getBackButton().getScene().getWindow();
        //
        graphView.setScene(scene);

        graphView.setResizable(false);
        mainPane.setDisable(true);
        graphView.showAndWait();
        mainPane.setDisable(false);
    }

}
