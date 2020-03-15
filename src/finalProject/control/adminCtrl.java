package finalProject.control;

import finalProject.model.address;
import finalProject.service.action;
import finalProject.service.readNwrite;

import java.io.IOException;
import java.util.Scanner;

public class adminCtrl extends action {
    Scanner tmp = new Scanner(System.in);
    private String user,pass;
    private int index;
    private boolean checkuser, checkpass, checkrepeat, checkadminChange;
    readNwrite tool = new readNwrite("admin");
    readNwrite tool2 = new readNwrite("customer");
    address o = new address();

    public adminCtrl(String username,String password, int index){
        this.user=username;
        this.pass=password;
        this.index=index;
    }

    public void display() throws IOException {
        System.out.println("-----------------------------");
        System.out.println("(1) Xem thong tin tai khoan\n" +
                "(2) Tim kiem\n" +
                "(3) Quan li tai khoan nguoi dung\n" +
                "(*) Thoat");
        System.out.print("Nhap lua chon : ");
        String choice = tmp.nextLine();
        switch (choice){
            case "1":
                view();
                display();
                break;
            case "2":
                find();
                display();
                break;
            case "3":
                quanLy();
                display();
                break;
            default:
                break;

        }
    }

    public void quanLy() throws IOException {
        String status;
        System.out.println("-----------------------------");
        System.out.println("Index\tUsername\tStatus");
        readNwrite tool = new readNwrite("customer");
        String listAcc[] = tool.read().split("\n");
        for(int i = 0;i<listAcc.length;i++){
            String listInfo[]=listAcc[i].split("#");
            if(listInfo[9].equals("a")){
                status="[UNLOCKED]";
            }else if(listInfo[9].equals("b")){
                status="[LOCKED]";
            }else{
                status="[DELETED]";
            }
            System.out.println((i+1)+"\t\t"+listInfo[0]+"\t\t"+status);
        }
        System.out.println("(1) Chinh sua tai khoan\n" +
                "(*) Thoat");
        System.out.print("Nhap lua chon : ");
        String choice = tmp.nextLine();
        if(choice.equals("1")){
            editStatus();
        }
    }

    public void editStatus() throws IOException {
        System.out.println("-----------------------------");
        System.out.print("Nhap tai khoan theo INDEX : ");
        String choice = tmp.nextLine();
        try {
            int num = Integer.parseInt(choice)-1;
            readNwrite tool = new readNwrite("customer");
            String listAcc[] = tool.read().split("\n");
            String listInfo[]=listAcc[num].split("#");
            String accUsername= listInfo[0];
            if(num>=0&&num<listAcc.length){
                System.out.println("-----------------------------");
                System.out.println("(1) UNLOCK\n(2) LOCK\n(3) DELETE\n(*) Thoat");
                System.out.print("Nhap lua chon : ");
                switch (tmp.nextLine()){
                    case "1":
                        open(num);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong !");
                        System.out.println(accUsername+" -> [UNLOCKED]");
                        break;
                    case "2":
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong !");
                        System.out.println(accUsername+" -> [LOCKED]");
                        lock(num);
                        break;
                    case "3":
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong !");
                        System.out.println(accUsername+" -> [DELETED]");
                        accDel(num);
                        break;
                    default:
                        break;
                }
            }else{
                System.out.println("-----------------------------");
                System.out.println("Nhap sai index!");
                System.out.println("(1) Nhap lai\n" +
                        "(*) Thoat");
                System.out.print("Nhap lua chon : ");
                if(tmp.nextLine().equals("1")){
                    editStatus();
                }
            }


        }catch (NumberFormatException e){
            System.out.println("-----------------------------");
            System.out.println("Nhap sai index!");
            System.out.println("(1) Nhap lai\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(tmp.nextLine().equals("1")){
                editStatus();
            }
        }
    }

    public boolean checkPass(){
        checkpass=false;
        System.out.print("Xac nhan password: ");
        String input = tmp.nextLine();
        if(input.equals(pass)){
            checkpass=true;
        }else{
            System.out.println("-----------------------------");
            System.out.println("Sai password!\n" +
                    "(1) Nhap lai password\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(tmp.nextLine().equals("1")){
                checkPass();
            }else {
                checkpass=false;
            }
        }
        return checkpass;
    }

    @Override
    public void find() {

    }

    @Override
    public void view() throws IOException {
        String listAcc[] = tool.read().split("\n");
        String listInfo[] = listAcc[index].split("#");
        readNwrite toolAd = new readNwrite("address");
        String listAddress[] = toolAd.read().split("\n");
        String regex="";String address="";
        for(int i = 0; i<listAddress.length; i++){
            String listIndex[] = listAddress[i].split("#");
            if(listIndex[0].equals(listInfo[4])){
                regex=listIndex[0]+"#";
                address=listAddress[i];
                break;
            }
        }
        address=address.replaceAll(regex,"");
        address=address.replaceAll("#"," ,");

        System.out.println("-----------------------------");
        System.out.println("ADMIN ACCOUNT INFORMATION");
        System.out.println("(1) Ho va Ten : "+listInfo[2]);
        System.out.println("(2) Username : "+listInfo[0]);
        System.out.println("(3) Password");
        System.out.println("(*) Thoat");
        System.out.print("Chinh sua : ");

        String choice = tmp.nextLine();
        switch (choice){
            case "1":
                edit("1");
                break;
            case "2":
                checkrepeat=false;
                edit("2");
                break;
            case "3":
                edit("3");
            default:
                break;
        }

    }

    @Override
    public void edit(String choice) throws IOException {
        switch (choice){
            case "1":
                System.out.println("-----------------------------");
                System.out.print("Nhap thay doi : ");
                String thaydoi = o.fixAd(tmp.nextLine());
                String listAcc[] = tool.read().split("\n");
                String listInfo[] = listAcc[index].split("#");
                String temp1 = tool.read();
                String oldInfo1 = listAcc[index];
                String newInfo1 = listInfo[0]+"#"+listInfo[1]+"#"+thaydoi+"#"+listInfo[3]+"#"+listInfo[4];
                temp1=temp1.replaceAll(oldInfo1,newInfo1);
                tool.overwrite(temp1);
                System.out.println("-----------------------------");
                System.out.println("Thay doi thanh cong !");
                break;
            case "2":
                System.out.println("-----------------------------");
                System.out.print("Nhap thay doi : ");
                String thaydoi2 = tmp.nextLine();
                if(thaydoi2.equals(user)){
                    System.out.println("-----------------------------");
                    System.out.println("Lap lai username ban dau?\n" +
                            "(1) Nhap lai username\n" +
                            "(*) Thoat");
                    System.out.print("Nhap lua chon : ");
                    if(tmp.nextLine().equals("1")){
                        edit("2");
                    }else{
                        checkrepeat=true;
                    }
                }else{
                    String listAcc2[] = tool.read().split("\n");
                    for(int i = 0; i<listAcc2.length; i++){
                        String listUsername[] = listAcc2[i].split("#");
                        if(thaydoi2.equals(listUsername[0])){
                            checkadminChange = true;
                            checkuser=true;
                            break;
                        }
                    }

                    if(checkadminChange==false){
                        String listAcc2b[] = tool2.read().split("\n");
                        for(int i = 0; i<listAcc2b.length; i++){
                            String listUsername[] = listAcc2b[i].split("#");
                            if(thaydoi2.equals(listUsername[0])){
                                checkuser=true;
                                break;
                            }
                        }
                    }
                }

                if(checkuser==true){
                    System.out.println("-----------------------------");
                    System.out.println("Da ton tai tai khoan '"+thaydoi2+"'");
                    System.out.println("(1) Nhap username khac\n" +
                            "(*) Thoat");
                    System.out.print("Nhap lua chon : ");
                    if(tmp.nextLine().equals("1")){
                        checkuser=false;
                        edit("2");
                    }
                }else if(checkrepeat==false&&checkuser==false){
                    if(checkPass()){
                        String listAcc2[] = tool.read().split("\n");
                        String listInfo2[] = listAcc2[index].split("#");
                        String temp2 = tool.read();
                        String oldInfo2 = listAcc2[index];
                        String newInfo2 = thaydoi2+"#"+listInfo2[1]+"#"+listInfo2[2]+"#"+listInfo2[3]+"#"+listInfo2[4];
                        temp2=temp2.replaceAll(oldInfo2,newInfo2);
                        tool.overwrite(temp2);
                        checkrepeat=true;
                        user=thaydoi2;
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong !");
                    }
                }
                break;

            case "3":
                if(checkPass()==true){
                    Scanner temp = new Scanner(System.in);
                    String pattern = "((?=.*[0-9])(?=.*[a-zA-Z])(?=.*[-+#^\\\\(\\\\)@$!%*?&~*_|`:;',./\\\"=\\\\[\\\\]\\\\\\\\{\\\\}])).{8,}";
                    System.out.println("-----------------------------");
                    System.out.print("Nhap password moi : ");
                    String thaydoi6 =temp.nextLine();
                    if(thaydoi6.matches(pattern)){
                        String listAcc6[] = tool.read().split("\n");
                        String listInfo6[] = listAcc6[index].split("#");
                        String temp6 = tool.read();
                        String oldInfo6 = listAcc6[index];
                        String newInfo6 = listInfo6[0]+"#"+thaydoi6+"#"+listInfo6[2]+"#"+listInfo6[3]+"#"+listInfo6[4];
                        temp6=temp6.replaceAll(oldInfo6,newInfo6);
                        tool.overwrite(temp6);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong !");
                    }else{
                        System.out.println("-----------------------------");
                        System.out.println("Sai cu phap!");
                        System.out.println("(1) Nhap lai password\n" +
                                "(*) Thoat");
                        System.out.print("Nhap lua chon : ");
                        if(temp.nextLine().equals("1")){
                            edit("3");
                        }
                    }
                }
                break;
        }

    }
}
