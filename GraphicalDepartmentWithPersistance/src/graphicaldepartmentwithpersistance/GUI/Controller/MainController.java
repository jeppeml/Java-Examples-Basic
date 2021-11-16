/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.GUI.Controller;

import graphicaldepartmentwithpersistance.BE.Department;
import graphicaldepartmentwithpersistance.util.FileTypeFactory.PersistanceType;
import graphicaldepartmentwithpersistance.GUI.Model.DepartmentModel;
import graphicaldepartmentwithpersistance.util.DepartmentException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author jeppjleemoritzled
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Department> tableDepartment;
    @FXML
    private TableColumn<Department, Integer> columnId;
    @FXML
    private TableColumn<Department, String> columnName;
    @FXML
    private TextField textId;
    @FXML
    private TextField textName;
    @FXML
    private ComboBox<PersistanceType> comboFileType;

    //private final DepartmentManager manager = new DepartmentManager();
    private DepartmentModel model;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            model = new DepartmentModel();
        }
        catch (DepartmentException ex) {
            showAndLogError(ex);
        }
        columnName.setCellValueFactory(
                new PropertyValueFactory("name"));
        columnId.setCellValueFactory(
                new PropertyValueFactory("id"));

        comboFileType.setItems(FXCollections.observableArrayList(PersistanceType.values()));
        comboFileType.getSelectionModel().selectFirst();

        tableDepartment.setItems(model.getDepartmentList());
    }

    @FXML
    private void clickAdd(ActionEvent event) {
        model.getDepartmentList().add(
                new Department(Integer.parseInt(textId.getText()), textName.getText()));
        textId.clear();
        textName.clear();
    }

    @FXML
    private void comboSelectFileType(ActionEvent event) {
        try {
            model.setFileType(comboFileType.getSelectionModel().getSelectedItem());
        }
        catch (DepartmentException ex) {
            showAndLogError(ex);
        }
    }

    @FXML
    private void clickLoad(ActionEvent event) {
        try {
            model.loadDepartments();
        }
        catch (DepartmentException ex) {
            showAndLogError(ex);
        }
    }

    @FXML
    private void clickSave(ActionEvent event) {
        try {
            model.addAll(tableDepartment.getItems());
        }
        catch (DepartmentException ex) {
            showAndLogError(ex);
        }
    }

    private static void showAndLogError(DepartmentException ex) {
        Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

        Alert alert = new Alert(AlertType.ERROR,
                ex.getMessage()
                + String.format("%n")
                + "See error log for technical details."
        );
        alert.showAndWait();
    }

    @FXML
    private void clickDeleteDepartment(ActionEvent event) {
        Department selected = 
                tableDepartment.getSelectionModel().getSelectedItem();
        try {
            model.delete(selected);
        }
        catch (DepartmentException ex) {
            showAndLogError(ex);
        }
    }

    @FXML
    private void clickGetDepartment(ActionEvent event) {
        Department selected = 
                tableDepartment.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.INFORMATION,
                selected.toString()
        );
        alert.showAndWait();
    }
}
