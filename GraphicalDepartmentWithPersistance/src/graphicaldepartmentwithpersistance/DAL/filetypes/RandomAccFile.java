/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicaldepartmentwithpersistance.DAL.filetypes;

import graphicaldepartmentwithpersistance.BE.Department;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jeppjleemoritzled
 */
public class RandomAccFile extends AbstractFile {

    private static final int ID_SIZE = Integer.BYTES;
    private static final int NAME_SIZE = 50;
    private static final int RECORD_SIZE = ID_SIZE + NAME_SIZE;

    public RandomAccFile(String fileName) {
        super(fileName + ".dat");
    }

    @Override
    public void addDepartment(Department d) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            raf.seek(raf.length());  // place the file pointer at the end of the file.
            raf.writeInt(d.getId());
            raf.writeBytes(String.format("%-" + NAME_SIZE + "s", d.getName()).substring(0, NAME_SIZE));
        }
    }

    @Override
    public List<Department> getAll() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            List<Department> departments = new ArrayList<>();

            while (raf.getFilePointer() < raf.length()) {
                departments.add(getOneDepartment(raf));
            }
            return departments;
        }
    }

    @Override
    public Department getById(int departmentId) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            for (int pos = 0; pos < raf.length(); pos += RECORD_SIZE) {
                raf.seek(pos);
                int id = raf.readInt();
                if (id == departmentId) {
                    raf.seek(pos);
                    return getOneDepartment(raf);
                }
            }
            return null;
        }
    }

    @Override
    public void deleteById(int id) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            for (int pos = 0; pos < raf.length(); pos += RECORD_SIZE) {
                raf.seek(pos);
                int currentId = raf.readInt();
                if (currentId == id) {
                    raf.seek(pos);
                    raf.write(new byte[RECORD_SIZE]); // write as many blank bytes as one record
                }
            }
        }
    }

    private Department getOneDepartment(final RandomAccessFile raf) throws IOException {
        byte[] bytes = new byte[NAME_SIZE];
        int id = raf.readInt();
        raf.read(bytes);
        String name = new String(bytes).trim();
        return new Department(id, name);
    }

    @Override
    public void saveAll(List<Department> depts) throws IOException {
        super.clearAll(); // deletes contents of file
        try (RandomAccessFile raf = new RandomAccessFile(new File(fileName), "rw")) {
            for (Department dept : depts) {
                raf.seek(raf.length());  // place the file pointer at the end of the file.
                raf.writeInt(dept.getId());
                raf.writeBytes(String.format("%-" + NAME_SIZE + "s", dept.getName()).substring(0, NAME_SIZE));
            }
        }
    }
}
