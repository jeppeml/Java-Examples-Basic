/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL.filetypes.DB;

import graphicaldepartmentwithpersistance.BE.Department;
import graphicaldepartmentwithpersistance.DAL.filetypes.DALReadWrite;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeppjleemoritzled
 */
public class DBPersistance implements DALReadWrite{

    ConnectionManager cm;

    public DBPersistance() throws IOException {
        cm = new ConnectionManager();
    }
    
    @Override
    public void saveAll(List<Department> depts) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public void addDepartment(Department d) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(int id) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Department getById(int departmentId) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Department> getAll() throws IOException {
        List<Department> allDepts = new ArrayList();
        try (Connection con = cm.getConnection()) {
            String sqlcommandSelect = "SELECT * FROM Departments";
            PreparedStatement pstmtSelect = con.prepareStatement(sqlcommandSelect);
            ResultSet rs = pstmtSelect.executeQuery();
            while(rs.next())
            {
                allDepts.add(new Department(
                        rs.getInt("id"), 
                        rs.getString("name"))
                );
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(DBPersistance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allDepts;
    }

    @Override
    public void clearAll() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
