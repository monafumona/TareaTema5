package org.example.tareatema5;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Artistas implements Initializable {
    private Connection conn;
    @FXML
    private Button botonSalir;

    @FXML
    private ListView<String> listView;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        String urlcon="jdbc:sqlite:E:/Descargas Chrome/chinook.db"; //Conexión a la base de datos

            try {
                conn = DriverManager.getConnection(urlcon);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM artists");

                while (rs.next()) {
                    listView.getItems().add(rs.getString("Name"));
                }
                listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observableValue, Object o, Object t1) { //Cada vez que se selecciona un artista se genera su informe
                        String fichero="informes/artistas2.jasper";
                        //InputStream in = MainController.class.getResourceAsStream(fichero);
                        JasperPrint jasperPrint= null;
                        java.util.HashMap parametros = new java.util.HashMap();
                        parametros.put("nombreartista",t1);
                        try {
                            jasperPrint = JasperFillManager.fillReport(fichero, parametros ,conn);
                            System.out.println("Informe lanzado");
                        } catch (JRException e) {
                            throw new RuntimeException(e);
                        }
                        JasperViewer.viewReport(jasperPrint, false);
                    }
                });

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        }



    @FXML
    private void accionSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /* //Esto lo iba a usar en un principio
    @FXML
    private void generar(ActionEvent event) throws IOException {
        try {
            String fichero2="informes/PiratasInformaticos.jasper";
            java.util.HashMap parametros = new java.util.HashMap();

            JasperPrint jasperPrint = JasperFillManager.fillReport(fichero2, parametros, conn);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            System.out.println(ex.getMessage());
        }
    }*/

}


