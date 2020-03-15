package finalProject.service;

import finalProject.model.address;
import finalProject.model.customer;

import java.io.IOException;
import java.util.Scanner;

public class regist {
    customer ob = new customer();
    boolean checkuser,checkpass,checksdt,checkemail,checkten, checkadmin;
    String nhapUsername, nhapPassword, nhapTen, nhapEmail, nhapSdt;

    public void CentralUnit() throws IOException {
        readNwrite count = new readNwrite("customer");
        String a[] = count.read().split("\n");
        int num = a.length;

        if(username()==true){
            if(password()==true){
                if(email()==true){
                    if(sdt()==true){
                        if(name()==true){
                            address AD = new address();
                            AD.CentralUnit(num,a[0].equals(""));
                            String info = ob.toString(AD.getId());
                            readNwrite tool2 = new readNwrite("customer");
                            tool2.write(info);
                        }else{
                            checkuser=false;
                            checkpass=false;
                            checkemail=false;
                            checksdt=false;
                            checkten=false;
                        }
                    }else{
                        checkuser=false;
                        checkpass=false;
                        checkemail=false;
                        checksdt=false;
                    }
                }else{
                    checkuser=false;
                    checkpass=false;
                    checkemail=false;
                }
            }else{
                checkuser=false;
                checkpass=false;
            }
        }else{
            checkuser=false;
        }
    }

    public boolean username() throws IOException {
        Scanner tmp = new Scanner(System.in);
        readNwrite checkData = new readNwrite("admin");
        String accountList[] = checkData.read().split("\n");
        int num = accountList.length;

        readNwrite checkData2 = new readNwrite("customer");
        String accountList2[] = checkData2.read().split("\n");
        int num2 = accountList2.length;

        String pattern = "((?=.*[a-zA-Z])(?!.*[-+#^\\(\\)@$!%*?&~*_|`:;',./\"=\\[\\]\\\\{\\}]))";
        System.out.println("-----------------------------");
        System.out.print("Nhap username : ");
        nhapUsername = tmp.nextLine();
        if(num==1&&num2==1&&accountList[0].equals("")&&accountList2[0].equals("")) {
            checkuser=true;
            ob.setUsername(nhapUsername);
        }else {
            for (int i = 0; i < accountList.length; i++) {
                String usernameTest[] = accountList[i].split("#");
                if (usernameTest[0].equals(nhapUsername)) {
                    checkadmin = true;
                    System.out.println("-----------------------------");
                    System.out.println("Da ton tai tai khoan \"" + nhapUsername + "\"");
                    System.out.println("(1) Nhap lai username\n" +
                            "(*) Thoat");
                    System.out.print("Nhap lua chon : ");
                    if(tmp.nextLine().equals("1")){
                        checkadmin=false;
                        username();
                    }else{
                        checkuser=false;
                    }
                    break;
                }else{
                    checkuser=true;
                    ob.setUsername(nhapUsername);
                }
            }

            if(checkadmin==false){
                for (int i = 0; i < accountList2.length; i++) {
                    String usernameTest[] = accountList2[i].split("#");
                    if (usernameTest[0].equals(nhapUsername)) {
                        System.out.println("-----------------------------");
                        System.out.println("Da ton tai tai khoan \"" + nhapUsername + "\"");
                        System.out.println("(1) Nhap lai username\n" +
                                "(*) Thoat");
                        System.out.print("Nhap lua chon : ");
                        if(tmp.nextLine().equals("1")){
                            username();
                        }else{
                            checkuser=false;
                        }
                        break;
                    }else{
                        checkuser=true;
                        ob.setUsername(nhapUsername);
                    }
                }
            }
        }

        return checkuser;
    }

    public boolean password(){
        Scanner temp = new Scanner(System.in);
        String pattern = "((?=.*[0-9])(?=.*[a-zA-Z])(?=.*[-+#^\\\\(\\\\)@$!%*?&~*_|`:;',./\\\"=\\\\[\\\\]\\\\\\\\{\\\\}])).{8,}";
        System.out.println("-----------------------------");
        System.out.print("Nhap password : ");
        nhapPassword=temp.nextLine();
        if(nhapPassword.matches(pattern)){
            checkpass=true;
            ob.setPassword(nhapPassword);
        }else{
            System.out.println("-----------------------------");
            System.out.println("Sai cu phap!");
            System.out.println("(1) Nhap lai password\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(temp.nextLine().equals("1")){
                password();
            }
        }
        return checkpass;
    }

    public boolean sdt(){
        Scanner temp = new Scanner(System.in);
        String pattern = "0[35789][0-9]{8}";
        System.out.println("-----------------------------");
        System.out.print("Nhap so dien thoai : ");
        nhapSdt=temp.nextLine();
        if(nhapSdt.matches(pattern)){
            checksdt=true;
            ob.setSdt(nhapSdt);
        }else{
            System.out.println("-----------------------------");
            System.out.println("Sai cu phap!");
            System.out.println("(1) Nhap lai so dien thoai\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(temp.nextLine().equals("1")){
                sdt();
            }
        }
        return checksdt;
    }

    public boolean email(){
        Scanner temp = new Scanner(System.in);
        String pattern = "0[35789][0-9]{8}";
        System.out.println("-----------------------------");
        System.out.print("Nhap email : ");
        nhapEmail=temp.nextLine();
        checkemail=true;
        ob.setEmail(nhapEmail);

        return checkemail;
    }

    public boolean name(){
        String a,b;
        Scanner temp = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.print("Nhap ho va ten : ");
        nhapTen=temp.nextLine();
        nhapTen = nhapTen.trim().replaceAll("\\s+", " ");
        String newNameInput[] =nhapTen.split("\\s");
        for (int i = 0; i < newNameInput.length;i++) {
            a = newNameInput[i].toUpperCase();
            b = newNameInput[i].toLowerCase();
            b=b.substring(1);
            newNameInput[i]=(String.valueOf(a.charAt(0))).concat(String.valueOf(b));
        }

        nhapTen="";
        for(int i = 0; i<newNameInput.length;i++){
            nhapTen=nhapTen.concat(newNameInput[i]);
            if(i<newNameInput.length-1){
                nhapTen=nhapTen.concat(" ");
            }
        }

        ob.setName(nhapTen);
        checkten=true;
        return checkten;
    }

}

