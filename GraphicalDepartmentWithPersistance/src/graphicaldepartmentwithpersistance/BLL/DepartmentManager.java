/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.BLL;

import graphicaldepartmentwithpersistance.BE.Department;
import graphicaldepartmentwithpersistance.DAL.DepartmentPersistanceManager;
import graphicaldepartmentwithpersistance.util.FileTypeFactory.PersistanceType;
import graphicaldepartmentwithpersistance.util.DepartmentException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jeppe
 */
public class DepartmentManager {

    private static final String FILE_NAME = "Departments";
    private final DepartmentPersistanceManager dpm;

    public DepartmentManager() throws DepartmentException {
        dpm = new DepartmentPersistanceManager(FILE_NAME);
    }

    public void setFileType(PersistanceType type) throws DepartmentException {
        dpm.setFileType(type);
    }

    public void clearAll() throws DepartmentException {
        try {
            dpm.clearAll();
        }
        catch (IOException ioex) {
            throw new DepartmentException("Cannot clear all departments", ioex);
        }
    }

    public void addDepartment(Department d) throws DepartmentException {
        try {
            dpm.addDepartment(d);
        }
        catch (IOException ioex) {
            throw new DepartmentException("Cannot add department", ioex);
        }
    }

    public void addAll(List<Department> depts) throws DepartmentException {
        try {
            dpm.addAll(depts);
        }
        catch (IOException ioex) {
            throw new DepartmentException("Cannot add all departments", ioex);
        }
    }

    public List<Department> getAll() throws DepartmentException {
        try {
            return dpm.getAll();
        }
        catch (IOException ioex) {
            throw new DepartmentException("Cannot get all departments", ioex);
        }
    }

    public Department getById(int departmentId) throws DepartmentException {
        try {
            return dpm.getById(departmentId);
        }
        catch (IOException ioex) {
            throw new DepartmentException("Cannot get department from id: " + departmentId, ioex);
        }
    }

    public void delete(Department d) throws DepartmentException {
        try {
            dpm.deleteById(d.getId());
        }
        catch (IOException ioex) {
            throw new DepartmentException(
                    "Cannot department" + d.getName() + " with id: " + d.getId(), ioex);
        }
    }
}
