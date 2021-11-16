/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.util;

import graphicaldepartmentwithpersistance.DAL.filetypes.DB.DBPersistance;
import graphicaldepartmentwithpersistance.DAL.filetypes.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jeppjleemoritzled
 */
public class FileTypeFactory {

    public enum PersistanceType {
        TEXTFILE, RANDOM_BINARY, SERIALIZED, NIOTEXT, DB
    }

    private static FileTypeFactory instance;

    private FileTypeFactory() {
    }

    // Singleton pattern
    public static FileTypeFactory getInstance() {
        if (instance == null) {
            instance = new FileTypeFactory();
        }
        return instance;
    }

    public DALReadWrite create(PersistanceType persistanceType, String fileName) throws DepartmentException {
        switch (persistanceType) {
            case TEXTFILE:
                return new TextFile(fileName);
            case RANDOM_BINARY:
                return new RandomAccFile(fileName);
            case SERIALIZED:
                return new SerializedFile(fileName);
            case NIOTEXT:
                return new NewIOTextFile(fileName);
            case DB:
        {
            try {
                // Filename ignored, looks like Liskovs principle is violated
                return new DBPersistance();
            }
            catch (IOException ex) {
                throw new DepartmentException("Problems with DB",ex);
            }
        } 
            default:
                throw new DepartmentException("No such filetype implemented");
        }
    }
}
