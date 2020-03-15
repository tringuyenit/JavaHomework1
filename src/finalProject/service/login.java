package finalProject.service;

import finalProject.control.adminCtrl;
import finalProject.control.customCtrl;
import java.io.*;
import java.util.Scanner;

public class login extends action{
    boolean admin, tieptuc, login, checklock;
    int countuser=0;
    int found;
    String nhapUsername, nhapPassword;

    public void centralUnit() throws IOException {
        checkDel();
        if(checkUser()){
            if(checkPass(found)){

                if(admin==true){
                    loginTime(found,admin);
                    adminCtrl acc = new adminCtrl(nhapUsername, nhapPassword, found);
                    acc.display();
                    logoutTime(found,admin);
                }else {
                    open(found);
                    loginTime(found,admin);
                    customCtrl acc = new customCtrl(nhapUsername, nhapPassword, found);
                    acc.display();
                    logoutTime(found,admin);
                }

            }
        }
    }

    public boolean checkUser() throws IOException {
        Scanner tmp = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.print("Nhap user name : ");
        nhapUsername = tmp.nextLine();

        readNwrite checkAdmin = new readNwrite("admin");
        String AdminList[] = checkAdmin.read().split("\n");
        for (int i = 0; i < AdminList.length; i++) {
            String usernameTest[] = AdminList[i].split("#");
            if (usernameTest[0].equals(nhapUsername)) {
                found=i;
                admin=true;
                checklock=false;
                countuser=0;
                tieptuc=true;
                break;
            }else{
                countuser++;
            }
        }

        if(admin==false){
            readNwrite checkCustomer = new readNwrite("customer");
            String CustomerList[] = checkCustomer.read().split("\n");
            for (int i = 0; i < CustomerList.length; i++) {
                String usernameTest[] = CustomerList[i].split("#");
                if (usernameTest[0].equals(nhapUsername)) {
                    found=i;
                    if(usernameTest[9].equals("a")){
                        checklock=false;
                        countuser=0;
                        tieptuc=true;
                        break;
                    }else if(usernameTest[9].equals("b")){
                        checklock=false;
                        countuser=0;
                        tieptuc=true;
                        break;

                    }else{
                        countuser=0;
                        checklock=true;
                        break;
                    }
                }else{
                    countuser++;
                }
            }
        }



        if(countuser!=0){
            admin=false;
            tieptuc=false;
            countuser=0;
            System.out.println("-----------------------------");
            System.out.println("Khong ton tai tai khoan \"" + nhapUsername + "\"");
            System.out.println("(1) Nhap lai username\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(tmp.nextLine().equals("1")){
                checkUser();
            }
        }

        if(checklock==true){
            System.out.println("-----------------------------");
            System.out.println("Tai khoan \""+nhapUsername+"\" da bi xoa :((");
            accDel(found);
            admin=false;
            tieptuc=false;
            checklock=false;
            countuser=0;
        }

        return tieptuc;
    }

    public boolean checkPass(int username) throws IOException {
        Scanner tmp = new Scanner(System.in);
        System.out.print("Nhap password : ");
        nhapPassword = tmp.nextLine();

        String AdminOrCustomer = (admin == true) ? "admin" : "customer";
        readNwrite checkData = new readNwrite(AdminOrCustomer);
        String accountList[] = checkData.read().split("\n");
        String usernameTest[] = accountList[username].split("#");

        if (!usernameTest[1].equals(nhapPassword)){
            System.out.println("-----------------------------");
            System.out.println("Sai password!");
            System.out.println("(1) Nhap lai password\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(tmp.nextLine().equals("1")){
                checkPass(found);
            }else{
                tieptuc=false;
                countuser=0;
            }
        }else{
            login=true;
            tieptuc=false;
            countuser=0;
        }
        return login;
    }

    @Override
    public void find() throws IOException {

    }
    @Override
    public void view() throws IOException {

    }
    @Override
    public void edit(String choice) throws IOException {

    }
}
