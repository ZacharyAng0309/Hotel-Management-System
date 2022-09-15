package room.booking.system;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ZacharyAZY
 */
public abstract class FileOperator {
    private static final Path DIR = Paths.get("").toAbsolutePath();
    private static final String SEPERATOR = "~";
    private final Path filePath;
    private final ArrayList<String> allData = new ArrayList<>();

    public FileOperator(String f) throws Exception {
        this.filePath = Paths.get(DIR + "\\" + f + ".txt");
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public void readOperation() throws Exception {
        String readData;
        BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
        while ((readData = br.readLine()) != null) {
            if (!(readData.isBlank())){
                allData.add(readData);
            }
        }
        br.close();
    }

    protected void writeOperation(String wd) throws Exception {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath.toString())));
        pw.write(wd);
        pw.close();
    }

    public int getDataLength() {
        if (allData.isEmpty()) return 0;
        return allData.size();
    }

    protected ArrayList<String> readSingleData(int val) {
        String data = allData.get(val);
        data.replace ("\n","");
        String[] splitData = data.split(SEPERATOR);
        ArrayList<String> sanitizedData = new ArrayList<>(Arrays.asList(splitData));
        return sanitizedData;
    }

    public abstract void getData() throws Exception;
    public abstract void modifyData() throws Exception;
}
