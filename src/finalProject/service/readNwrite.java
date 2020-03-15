package finalProject.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class readNwrite {
    private String name;
    public readNwrite(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public boolean test(){
        String testPath[]=System.getProperty("user.dir").split("\\\\");
        return testPath[testPath.length-2].equals("production");
    }

    public String read() throws IOException {
        String a="";
        String fileName;

        if(test()){
            fileName = ".\\..\\..\\..\\src\\finalProject\\data\\"+name+".txt";
        }else{
            fileName = "src\\finalProject\\data\\"+name+".txt";
        }

        try {
            a = new String(Files.readAllBytes(Paths.get(fileName)));
        }
        catch(IOException ex) {
        }
        return a;
    }


    public void overwrite(String smt) {
        String fileName;

        if(test()){
            fileName = ".\\..\\..\\..\\src\\finalProject\\data\\"+name+".txt";
        }else{
            fileName = "src\\finalProject\\data\\"+name+".txt";
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(smt);
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

    public void write(String smt) throws IOException {
        String fileName;

        if(test()){
            fileName = ".\\..\\..\\..\\src\\finalProject\\data\\"+name+".txt";
        }else{
            fileName = "src\\finalProject\\data\\"+name+".txt";
        }

        File file = new File(fileName);
        FileWriter fr = new FileWriter(file, true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(smt);

        br.close();
        fr.close();
    }

}

