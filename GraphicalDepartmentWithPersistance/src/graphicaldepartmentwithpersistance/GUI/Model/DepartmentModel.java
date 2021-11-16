/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.GUI.Model;

import graphicaldepartmentwithpersistance.BE.Department;
import graphicaldepartmentwithpersistance.BLL.DepartmentManager;
import graphicaldepartmentwithpersistance.util.FileTypeFactory.PersistanceType;
import graphicaldepartmentwithpersistance.util.DepartmentException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jeppjleemoritzled
 */
public class DepartmentModel {

    private final ObservableList<Department> deptList;
    private final DepartmentManager manager;

    public DepartmentModel() throws DepartmentException {
        manager = new DepartmentManager();
        this.deptList = FXCollections.observableArrayList();
    }

    public ObservableList<Department> getDepartmentList() {
        return deptList;
    }

    private void setDepartments(List<Department> depts) {
        deptList.clear();
        deptList.addAll(depts);
    }

    public void loadDepartments() throws DepartmentException {
        setDepartments(manager.getAll());
    }

    public void setFileType(PersistanceType selectedItem) throws DepartmentException {
        manager.setFileType(selectedItem);
    }

    public void addAll(ObservableList<Department> items) throws DepartmentException {
        manager.addAll(new ArrayList(deptList));
    }

    public void delete(Department selected) throws DepartmentException {
        manager.delete(selected);
        deptList.remove(selected);
    }
}
