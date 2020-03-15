package finalProject.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class readwrite {
    private String diaChi;

    public readwrite(String diachi){
        this.diaChi = diachi;
    }

    public String read() throws IOException {
        String fileName = "src\\finalProject\\data\\"+diaChi+".txt";

        FileReader frr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(frr);
        String data=new String(Files.readAllBytes(Paths.get(fileName)));

        return data;
    }

    public void write(String data) throws IOException {
        String fileName = "src\\finalProject\\data\\"+diaChi+".txt";

        FileWriter fw = new FileWriter(fileName,true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(data);
        bw.write("\n");
        bw.flush();
        bw.close();
    }

}
