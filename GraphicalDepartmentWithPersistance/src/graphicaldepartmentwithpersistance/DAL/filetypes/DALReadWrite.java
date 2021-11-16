/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL.filetypes;

import graphicaldepartmentwithpersistance.BE.Department;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public interface DALReadWrite {
    
    public abstract void saveAll(List<Department> depts) throws IOException;

    public abstract void addDepartment(Department d) throws IOException;

    public abstract void deleteById(int id) throws IOException;

    public abstract Department getById(int departmentId) throws IOException;

    public abstract List<Department> getAll() throws IOException;

    public void clearAll() throws IOException;
}
