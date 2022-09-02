package controlalumnosfx;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class aluViewController implements Initializable {
    
    Modelo model = new Modelo();
    
    //
    @FXML
    private TextField edadLabel;

    @FXML
    private TextField semLabel;

    @FXML
    private Label resLabel;

    @FXML
    private Label estado;

    @FXML
    private TextField espLabel;

    @FXML
    private TextField capLabel;

    @FXML
    private TextField sexLabel;

    @FXML
    private TextField nctlLabel;

    @FXML
    private TextField carLabel;

    @FXML
    private Label renLabel;

    @FXML
    private TextField proLabel;

    @FXML
    private Button backButton;

    @FXML
    private BorderPane aluViewPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Button editButton;
    //
    
    public BorderPane getaluViewPane(){
        return aluViewPane;
    }
    
    @FXML
    void onBackEdit(ActionEvent event) {
        System.out.println("Auch");
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }

    public Button getBackButton() {
        return backButton;
    }

    boolean edit = false;

    @FXML
    void onEdit(ActionEvent event) {

        if (!edit) {
            
            nctlLabel.setEditable(true);
            sexLabel.setEditable(true);
            edadLabel.setEditable(true);
            semLabel.setEditable(true);
            capLabel.setEditable(true);
            carLabel.setEditable(true);
            proLabel.setEditable(true);
            espLabel.setEditable(true);
            edit = true;
            
            nctlLabel.setText(alumno.getnCtrl());
            sexLabel.setText(alumno.getSexo());
            edadLabel.setText(alumno.getEdad());
            semLabel.setText(alumno.getSem());
            capLabel.setText(alumno.getCrApr());
            carLabel.setText(alumno.getCarga());
            proLabel.setText(alumno.getPromedio());
            espLabel.setText(alumno.getEspecialidad());

        } else {
            if(nctlLabel.getText().equals("") ||
               sexLabel.getText().equals("") ||
               edadLabel.getText().equals("") ||
               semLabel.getText().equals("") ||
               capLabel.getText().equals("") ||
               carLabel.getText().equals("") ||
               proLabel.getText().equals("") ||
               espLabel.getText().equals("")){
                return;
            }
            alumno.setnCtrl(new SimpleStringProperty((String) nctlLabel.getText()));
            alumno.setSexo(new SimpleStringProperty((String) sexLabel.getText()));
            alumno.setEdad(new SimpleStringProperty((String) edadLabel.getText()));
            alumno.setSem(new SimpleStringProperty((String) semLabel.getText()));
            alumno.setCrApr(new SimpleStringProperty((String) capLabel.getText()));
            alumno.setCarga(new SimpleStringProperty((String) carLabel.getText()));
            alumno.setPromedio(new SimpleStringProperty((String) proLabel.getText()));
            alumno.setEspecialidad(new SimpleStringProperty((String) espLabel.getText()));
            
            //alumno;
            nctlLabel.setText("N.Control: " + alumno.getnCtrl());
            sexLabel.setText("Sexo: " + alumno.getSexo());
            edadLabel.setText("Edad: " + alumno.getEdad());
            semLabel.setText("Semestre: " + alumno.getSem());
            capLabel.setText("Cr. Aprox: " + alumno.getCrApr());
            carLabel.setText("Carga: " + alumno.getCarga());
            proLabel.setText("Promedio: " + alumno.getPromedio());
            espLabel.setText("Especialidad: " + alumno.getEspecialidad());
            nameLabel.setText(alumno.getNombre());
            renLabel.setText("Rendimiento: " + Integer.toString(Integer.parseInt(alumno.getCrApr()) + Integer.parseInt(alumno.getCarga())));
            double resta = Double.parseDouble(alumno.getCrApr()) / 22;

            resLabel.setText(String.format("Resta: %2.2f", resta));
            double selectEstado = Integer.parseInt(alumno.getSem()) - resta;
            System.out.println(selectEstado);
            if (selectEstado <= 1) {
                estado.setStyle("-fx-background-color: #0a7935;");
            } else if (selectEstado <= 2) {
                estado.setStyle("-fx-background-color: #caba27;");
            } else if (selectEstado <= 3) {
                estado.setStyle("-fx-background-color: #b4611d;");
            } else {
                estado.setStyle("-fx-background-color: #8a120e;");
            }
            
            
            
            nctlLabel.setEditable(false);
            sexLabel.setEditable(false);
            edadLabel.setEditable(false);
            semLabel.setEditable(false);
            capLabel.setEditable(false);
            carLabel.setEditable(false);
            proLabel.setEditable(false);
            espLabel.setEditable(false);
            edit = false;
            
        }
        System.out.println("edit");

        //nctlLabel.setText("N.Control: 18071392");
    }

    @FXML
    void leftButton(ActionEvent event) {
        System.out.println("Left");

        //nctlLabel.setText("N.Control: 18071392");
    }

    private ArrayList<Alumnos> alumnosArrayList = new ArrayList<>();
    private Alumnos alumno;

    public void setAlumnos(ArrayList<Alumnos> alus) {
        alumnosArrayList = alus;
    }
    
    public Alumnos getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumnos in) {
        alumno = in;
        nctlLabel.setText("N.Control: " + alumno.getnCtrl());
        sexLabel.setText("Sexo: " + alumno.getSexo());
        edadLabel.setText("Edad: " + alumno.getEdad());
        semLabel.setText("Semestre: " + alumno.getSem());
        capLabel.setText("Cr. Aprox: " + alumno.getCrApr());
        carLabel.setText("Carga: " + alumno.getCarga());
        proLabel.setText("Promedio: " + alumno.getPromedio());
        espLabel.setText("Especialidad: " + alumno.getEspecialidad());
        nameLabel.setText(alumno.getNombre());
        renLabel.setText("Rendimiento: " + Integer.toString(Integer.parseInt(alumno.getCrApr()) + Integer.parseInt(alumno.getCarga())));
        double resta = Double.parseDouble(alumno.getCrApr()) / 22;

        resLabel.setText(String.format("Resta: %2.2f", resta));
        double selectEstado = Integer.parseInt(alumno.getSem()) - resta;
        System.out.println(selectEstado);
        if (selectEstado <= 1) {
            estado.setStyle("-fx-background-color: #0a7935;");
        } else if (selectEstado <= 2) {
            estado.setStyle("-fx-background-color: #caba27;");
        } else if (selectEstado <= 3) {
            estado.setStyle("-fx-background-color: #b4611d;");
        } else {
            estado.setStyle("-fx-background-color: #8a120e;");
        }
        //estado.setStyle("-fx-back");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
