/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL.filetypes;

import graphicaldepartmentwithpersistance.BE.Department;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public abstract class AbstractFile implements DALReadWrite{

    protected final String fileName;

    public AbstractFile(String fileName) {
        this.fileName = fileName;
    }

    public void clearAll() throws IOException {
        // Ereases the content of the file without deleting it.
        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        raf.setLength(0);
    }
}
