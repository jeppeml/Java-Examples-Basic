/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL.filetypes;

import graphicaldepartmentwithpersistance.BE.Department;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class SerializedFile extends AbstractFile {

    public SerializedFile(String fileName) {
        super(fileName + ".ser");
    }

    @Override
    public void addDepartment(Department d) throws IOException {
        List<Department> allDepts = getAll();
        // If file is empty create new array
        if (allDepts == null) {
            allDepts = new ArrayList();
        }

        allDepts.add(d);

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(allDepts);
        }
    }

    @Override
    public void deleteById(int id) throws IOException {
        List<Department> allDepts = getAll();
        Department d = getByIdFromList(id,allDepts);
        allDepts.remove(d);
        saveAll(allDepts);
    }
    
    private Department getByIdFromList(
            int departmentId, List<Department> departments){
        for (Department dept : departments) {
            if (dept.getId() == departmentId) {
                return dept;
            }
        }
        return null;
    }

    @Override
    public Department getById(int departmentId) throws IOException {
        return getByIdFromList(departmentId, getAll());
    }

    @Override
    public List<Department> getAll() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(fileName))) {
            return (List<Department>) ois.readObject();
        }
        catch (ClassNotFoundException ex) {
            throw new IOException(ex);
        }
    }

    @Override
    public void saveAll(List<Department> depts) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(fileName))) {
            oos.writeObject(depts);
        }
    }

}
