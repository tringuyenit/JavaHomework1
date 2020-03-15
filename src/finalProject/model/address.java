package finalProject.model;

import finalProject.service.readNwrite;
import finalProject.service.regist;

import java.io.IOException;
import java.util.Scanner;

public class address {
    Scanner sc = new Scanner(System.in);
    private String soNha;
    private String street;
    private String district;
    private String city;
    private int id;

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId() throws IOException {
        readNwrite tool = new readNwrite("customer");
        String a[]=tool.read().split("\n");
        String b[]=a[a.length-1].split("#");
        String c = b[4];
        int num = Integer.parseInt(c);
        num+=1;
        this.id = num;
    }

    public void CentralUnit(int num,boolean flaq) throws IOException {
        System.out.println("-----------------------------");
        System.out.println("Nhap noi o hien tai");
        city();
        district();
        street();
        sonha();
        if(num==1&&flaq==true){
            this.id=10000;
        }else{
            setId();
        }
        readNwrite tool = new readNwrite("address");
        tool.write(toString());
    }

    public void CentralUnit2(int AdID, String oldAd) throws IOException {
        System.out.println("-----------------------------");
        System.out.println("Thay doi noi o hien tai");
        city();
        district();
        street();
        sonha();
        this.id=AdID;
        readNwrite tool = new readNwrite("address");
        String listAd= tool.read();
        String newInfo=toString().replaceAll("\n","");
        listAd=listAd.replaceAll(oldAd,newInfo);
        tool.overwrite(listAd);
    }

    public void city(){
        System.out.print("Thanh pho : ");
        setCity(fixAd(sc.nextLine()));
    }

    public void district(){
        System.out.print("Quan/Huyen : ");
        setDistrict(fixAd(sc.nextLine()));
    }

    public void street(){
        System.out.print("Duong/Pho : ");
        setStreet(fixAd(sc.nextLine()));
    }

    public void sonha(){
        System.out.print("So nha : ");
        setSoNha(fixAd(sc.nextLine()));
    }

    public String fixAd(String smt){
        String a,b;
        smt = smt.trim().replaceAll("\\s+", " ");
        String newNameInput[] =smt.split("\\s");
        for (int i = 0; i < newNameInput.length;i++) {
            a = newNameInput[i].toUpperCase();
            b = newNameInput[i].toLowerCase();
            b=b.substring(1);
            newNameInput[i]=(String.valueOf(a.charAt(0))).concat(String.valueOf(b));
        }

        smt="";
        for(int i = 0; i<newNameInput.length;i++){
            smt=smt.concat(newNameInput[i]);
            if(i<newNameInput.length-1){
                smt=smt.concat(" ");
            }
        }
        return smt;
    }

    @Override
    public String toString() {
        return  id+"#So "+soNha+"#"+street+"#"+district+"#"+city+"\n";
    }
}
