/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL;

import graphicaldepartmentwithpersistance.util.FileTypeFactory;
import graphicaldepartmentwithpersistance.DAL.filetypes.*;
import graphicaldepartmentwithpersistance.BE.*;
import graphicaldepartmentwithpersistance.util.FileTypeFactory.PersistanceType;
import static graphicaldepartmentwithpersistance.util.FileTypeFactory.PersistanceType.*;
import graphicaldepartmentwithpersistance.util.DepartmentException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jeppe
 */
public final class DepartmentPersistanceManager {

    private DALReadWrite fileHandler;
    private String fileName;

    public void setFileType(PersistanceType type) throws DepartmentException {
        fileHandler = FileTypeFactory.getInstance().create(
                type, fileName);
    }

    public DepartmentPersistanceManager(String fileName) throws DepartmentException {
        this(fileName, SERIALIZED);
    }

    public DepartmentPersistanceManager(String fileName, PersistanceType type) throws DepartmentException {
        this.fileName = fileName;
        setFileType(type);
    }

    public void addDepartment(Department d) throws IOException {
        fileHandler.addDepartment(d);
    }

    public List<Department> getAll() throws IOException {
        return fileHandler.getAll();
    }

    public Department getById(int departmentId) throws IOException {
        return fileHandler.getById(departmentId);
    }

    public void deleteById(int id) throws IOException {
        fileHandler.deleteById(id);
    }

    public void clearAll() throws IOException {
        fileHandler.clearAll();
    }

    public void addAll(List<Department> depts) throws IOException {
        fileHandler.saveAll(depts);
    }
}
