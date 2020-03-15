package finalProject.control;

import finalProject.model.address;
import finalProject.service.action;
import finalProject.service.readNwrite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class customCtrl extends action {
    Scanner tmp = new Scanner(System.in);
    private String user,pass;
    private int index;
    private boolean checkuser, checkpass, checkrepeat, checkadmin;
    readNwrite tool = new readNwrite("customer");
    readNwrite tool2 = new readNwrite("admin");
    address o = new address();

    public customCtrl(String username,String password, int index){
        this.user=username;
        this.pass=password;
        this.index=index;
    }

    public void display() throws IOException {
        System.out.println("-----------------------------");
        System.out.println("(1) Xem thong tin tai khoan\n" +
                "(2) Tim kiem\n" +
                "(3) Khoa tai khoan\n" +
                "(4) Xoa tai khoan\n" +
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
                if(checkPass()==true){
                    lock(index);
                    System.out.println("-----------------------------");
                    System.out.println("KHOA TAI KHOAN THANH CONG !");
                    System.out.println("\tHEN GAP LAI <3");
                }else {
                    display();
                }
                break;
            case "4":
                if(checkPass()==true){
                    accDel(index);
                    System.out.println("-----------------------------");
                    System.out.println("XOA TAI KHOAN THANH CONG !");
                }else {
                    display();
                }
                break;
            default:
                break;

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
    public void find() throws IOException {
        System.out.println("-----------------------------");
        System.out.println("SEARCH");
        System.out.println("(1) Theo ten\n" +
                "(2) Theo username\n" +
                "(3) Theo so dien thoai\n" +
                "(4) Theo email\n" +
                "(5) Theo thanh pho\n" +
                "(*) Thoat");
        System.out.print("Nhap lua chon : ");
        String choice = tmp.nextLine();
        switch (choice){
            case "1":
                System.out.println("-----------------------------");
                System.out.print("Nhap ten : ");
                String ten = o.fixAd( tmp.nextLine());
                ten = ".*"+nameCheck(ten)+".*";
                List<String> newlist = new ArrayList<>();
                newlist.add("Ho va ten\t\t\tUsername");
                String listAcc[] = tool.read().split("\n");
                for(int i = 0; i<listAcc.length;i++){
                    String listInfo[] = listAcc[i].split("#");
                    if(listInfo[9].equals("a")){
                        if(listInfo[2].matches(ten)){
                            String info = listInfo[2]+"\t\t"+listInfo[0];
                            newlist.add(info);
                        }
                    }
                }
                if(newlist.size()==1){
                    System.out.println("-----------------------------");
                    System.out.println("Khong co ket qua");
                    find();
                }else{
                    System.out.println("-----------------------------");
                    System.out.println("KET QUA");
                    for (String a: newlist) {
                        System.out.println(a);
                    }
                    find();
                }
                break;

            case "2":
                System.out.println("-----------------------------");
                System.out.print("Nhap username : ");
                String username = tmp.nextLine();
                username = ".*"+username+".*";
                List<String> newlist2 = new ArrayList<>();
                newlist2.add("Ho va ten\t\t\tUsername");
                String listAcc2[] = tool.read().split("\n");
                for(int i = 0; i<listAcc2.length;i++){
                    String listInfo[] = listAcc2[i].split("#");
                    if(listInfo[9].equals("a")){
                        if(listInfo[0].matches(username)){
                            String info = listInfo[2]+"\t\t"+listInfo[0];
                            newlist2.add(info);
                            break;
                        }
                    }
                }
                if(newlist2.size()==1){
                    System.out.println("-----------------------------");
                    System.out.println("Khong co ket qua");
                    find();
                }else{
                    System.out.println("-----------------------------");
                    System.out.println("KET QUA");
                    for (String a: newlist2) {
                        System.out.println(a);
                    }
                    find();
                }
                break;

            case "3":
                List<String> newlist3 = new ArrayList<>();
                sdt();
                if(checksdt==true){
                    newlist3.add("Ho va ten\t\t\tUsername");
                    String listAcc3[] = tool.read().split("\n");
                    for(int i = 0; i<listAcc3.length;i++){
                        String listInfo[] = listAcc3[i].split("#");
                        if(listInfo[9].equals("a")){
                            if(listInfo[7].equals("1")){
                                if(listInfo[5].matches(sdt)){
                                    String info = listInfo[2]+"\t\t"+listInfo[0];
                                    newlist3.add(info);
                                }
                            }
                        }
                    }
                }

                if(newlist3.size()==1){
                    System.out.println("-----------------------------");
                    System.out.println("Khong co ket qua");
                    find();
                }else{
                    System.out.println("-----------------------------");
                    System.out.println("KET QUA");
                    for (String a: newlist3) {
                        System.out.println(a);
                    }
                    find();
                }
                break;
            case "4":
                System.out.println("-----------------------------");
                System.out.print("Nhap email : ");
                String email = tmp.nextLine();
                List<String> newlist4 = new ArrayList<>();
                newlist4.add("Ho va ten\t\t\tUsername");
                String listAcc4[] = tool.read().split("\n");
                for(int i = 0; i<listAcc4.length;i++){
                    String listInfo[] = listAcc4[i].split("#");
                    if(listInfo[9].equals("a")){
                        if(listInfo[6].equals("1")){
                            if(listInfo[3].equals(email)){
                                String info = listInfo[2]+"\t\t"+listInfo[0];
                                newlist4.add(info);
                            }
                        }
                    }
                }

                if(newlist4.size()==1){
                    System.out.println("-----------------------------");
                    System.out.println("Khong co ket qua");
                    find();
                }else{
                    System.out.println("-----------------------------");
                    System.out.println("KET QUA");
                    for (String a: newlist4) {
                        System.out.println(a);
                    }
                    find();
                }
                break;
            case "5":
                System.out.println("-----------------------------");
                System.out.print("Nhap thanh pho : ");
                String thanhpho = o.fixAd(tmp.nextLine());
                thanhpho = ".*"+nameCheck(thanhpho)+".*";
                List<String> newlist5 = new ArrayList<>();
                newlist5.add("Ho va ten\t\t\tUsername\t\tThanh pho");
                String listAcc5[] = tool.read().split("\n");
                for(int i = 0; i<listAcc5.length;i++){
                    String listInfo[] = listAcc5[i].split("#");
                    if(listInfo[9].equals("a")){
                        if(listInfo[8].equals("1")){
                            readNwrite tool2 = new readNwrite("address");
                            String listAddress[]=tool2.read().split("\n");
                            for(int x = 0; x<listAddress.length;x++){
                                String listThanhpho[]=listAddress[x].split("#");
                                if(listThanhpho[0].equals(listInfo[4])){
                                    if(listThanhpho[4].matches(thanhpho)){
                                        String info = listInfo[2]+"\t\t"+listInfo[0]+"\t\t"+listThanhpho[4];
                                        newlist5.add(info);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(newlist5.size()==1){
                    System.out.println("-----------------------------");
                    System.out.println("Khong co ket qua");
                    find();
                }else{
                    System.out.println("-----------------------------");
                    System.out.println("KET QUA");
                    for (String a: newlist5) {
                        System.out.println(a);
                    }
                    find();
                }
                break;
        }
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
        address=address.replaceAll("#",", ");

        System.out.println("-----------------------------");
        System.out.println("ACCOUNT INFORMATION");
        System.out.println("(1) Ho va Ten : "+listInfo[2]);
        System.out.println("(2) Username : "+listInfo[0]);
        System.out.println("(3) Sdt : "+listInfo[5]);
        System.out.println("(4) Email : "+listInfo[3]);
        System.out.println("(5) Dia chi : "+address);
        System.out.println("(6) Password");
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
                System.out.println("-----------------------------");
                System.out.println("(1) Sua so dien thoai\n" +
                        "(2) Public\n" +
                        "(3) Private\n" +
                        "(*) Thoat");
                System.out.print("Nhap lua chon : ");
                String choice3 = tmp.nextLine();
                switch (choice3){
                    case "1":
                        edit("3");
                        break;
                    case "2":
                        String temp32 = tool.read();
                        String oldInfo32 = listAcc[index];
                        String newInfo32 = listInfo[0]+"#"+listInfo[1]+"#"+listInfo[2]+"#"+listInfo[3]+"#" +listInfo[4]+"#"+listInfo[5]
                                +"#"+listInfo[6]+"#1#"+listInfo[8]+"#"+listInfo[9]+"#"+listInfo[10]+"#"+listInfo[11];
                        temp32=temp32.replaceAll(oldInfo32,newInfo32);
                        tool.overwrite(temp32);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : So dien thoai -> [Public]");
                        break;
                    case "3":
                        String temp33 = tool.read();
                        String oldInfo33 = listAcc[index];
                        String newInfo33 = listInfo[0]+"#"+listInfo[1]+"#"+listInfo[2]+"#"+listInfo[3]+"#" +listInfo[4]+"#"+listInfo[5]
                                +"#"+listInfo[6]+"#0#"+listInfo[8]+"#"+listInfo[9]+"#"+listInfo[10]+"#"+listInfo[11];
                        temp33=temp33.replaceAll(oldInfo33,newInfo33);
                        tool.overwrite(temp33);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : So dien thoai -> [Private]");
                        break;
                }
                break;
            case "4":
                System.out.println("-----------------------------");
                System.out.println("(1) Sua email\n" +
                        "(2) Public\n" +
                        "(3) Private\n" +
                        "(*) Thoat");
                System.out.print("Nhap lua chon : ");
                String choice4 = tmp.nextLine();
                switch (choice4) {
                    case "1":
                        edit("4");
                        break;
                    case "2":
                        String temp42 = tool.read();
                        String oldInfo42 = listAcc[index];
                        String newInfo42 = listInfo[0] + "#" + listInfo[1] + "#" + listInfo[2] + "#" + listInfo[3] + "#" + listInfo[4] + "#" + listInfo[5]
                                + "#1#" + listInfo[7] + "#" + listInfo[8]+ "#" + listInfo[9]+ "#" + listInfo[10]+ "#" + listInfo[11];
                        temp42 = temp42.replaceAll(oldInfo42, newInfo42);
                        tool.overwrite(temp42);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : Email -> [Public]");
                        break;
                    case "3":
                        String temp43 = tool.read();
                        String oldInfo43 = listAcc[index];
                        String newInfo43 = listInfo[0] + "#" + listInfo[1] + "#" + listInfo[2] + "#" + listInfo[3] + "#" + listInfo[4] + "#" + listInfo[5]
                                + "#0#" + listInfo[7] + "#" + listInfo[8]+ "#" + listInfo[9]+ "#" + listInfo[10]+ "#" + listInfo[11];
                        temp43 = temp43.replaceAll(oldInfo43, newInfo43);
                        tool.overwrite(temp43);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : Email -> [Private]");
                        break;
                }
                break;
            case "5":
                System.out.println("-----------------------------");
                System.out.println("(1) Sua dia chi\n" +
                        "(2) Public\n" +
                        "(3) Private\n" +
                        "(*) Thoat");
                System.out.print("Nhap lua chon : ");
                String choice5 = tmp.nextLine();
                switch (choice5) {
                    case "1":
                        edit("5");
                        break;
                    case "2":
                        String temp52 = tool.read();
                        String oldInfo52 = listAcc[index];
                        String newInfo52 = listInfo[0] + "#" + listInfo[1] + "#" + listInfo[2] + "#" + listInfo[3] + "#" + listInfo[4] + "#" + listInfo[5]
                                + "#"+ listInfo[6] +"#"+ listInfo[7] + "#1#"+ listInfo[9]+"#"+ listInfo[10]+"#"+ listInfo[11];
                        temp52 = temp52.replaceAll(oldInfo52, newInfo52);
                        tool.overwrite(temp52);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : Dia chi -> [Public]");
                        break;
                    case "3":
                        String temp53 = tool.read();
                        String oldInfo53 = listAcc[index];
                        String newInfo53 = listInfo[0] + "#" + listInfo[1] + "#" + listInfo[2] + "#" + listInfo[3] + "#" + listInfo[4] + "#" + listInfo[5]
                                + "#"+ listInfo[6] +"#"+ listInfo[7] + "#0#"+ listInfo[9]+"#"+ listInfo[10]+"#"+ listInfo[11];
                        temp53 = temp53.replaceAll(oldInfo53, newInfo53);
                        tool.overwrite(temp53);
                        System.out.println("-----------------------------");
                        System.out.println("Thay doi thanh cong ! : Dia chi -> [Private]");
                        break;
                }
                break;
            case "6":
                edit("6");
                break;
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
                String newInfo1 = listInfo[0]+"#"+listInfo[1]+"#"+thaydoi+"#"+listInfo[3]+"#"+listInfo[4]+"#"+listInfo[5]
                        +"#"+listInfo[6]+"#"+listInfo[7]+"#"+listInfo[8]+"#"+listInfo[9]+"#"+listInfo[10]+"#"+listInfo[11];
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
                    String listAcc2[] = tool2.read().split("\n");
                    for(int i = 0; i<listAcc2.length; i++){
                        String listUsername[] = listAcc2[i].split("#");
                        if(thaydoi2.equals(listUsername[0])){
                            checkadmin=true;
                            checkuser=true;
                            break;
                        }
                    }

                    if(checkadmin==false){
                        String listAcc2b[] = tool.read().split("\n");
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
                        checkadmin=false;
                        checkuser=false;
                        edit("2");
                    }
                }else if(checkrepeat==false&&checkuser==false){
                    if(checkPass()){
                        String listAcc2[] = tool.read().split("\n");
                        String listInfo2[] = listAcc2[index].split("#");
                        String temp2 = tool.read();
                        String oldInfo2 = listAcc2[index];
                        String newInfo2 = thaydoi2+"#"+listInfo2[1]+"#"+listInfo2[2]+"#"+listInfo2[3]+"#"+listInfo2[4]+"#"+listInfo2[5]
                                +"#"+listInfo2[6]+"#"+listInfo2[7]+"#"+listInfo2[8]+"#"+listInfo2[9]+"#"+listInfo2[10]+"#"+listInfo2[11];
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
                sdt();
                if(checksdt==true){
                    String thaydoi3 = sdt;
                    String listAcc3[] = tool.read().split("\n");
                    String listInfo3[] = listAcc3[index].split("#");
                    String temp3 = tool.read();
                    String oldInfo3 = listAcc3[index];
                    String newInfo3 = listInfo3[0]+"#"+listInfo3[1]+"#"+listInfo3[2]+"#"+listInfo3[3]+"#"+listInfo3[4]+"#"+thaydoi3
                            +"#"+listInfo3[6]+"#"+listInfo3[7]+"#"+listInfo3[8]+"#"+listInfo3[9]+"#"+listInfo3[10]+"#"+listInfo3[11];
                    temp3=temp3.replaceAll(oldInfo3,newInfo3);
                    tool.overwrite(temp3);
                    System.out.println("-----------------------------");
                    System.out.println("Thay doi thanh cong !");
                }
                break;
            case "4":
                System.out.println("-----------------------------");
                System.out.print("Nhap thay doi : ");
                String thaydoi4 = tmp.nextLine();
                String listAcc4[] = tool.read().split("\n");
                String listInfo4[] = listAcc4[index].split("#");
                String temp4 = tool.read();
                String oldInfo4 = listAcc4[index];
                String newInfo4 = listInfo4[0]+"#"+listInfo4[1]+"#"+listInfo4[2]+"#"+thaydoi4+"#"+listInfo4[4]+"#"+listInfo4[5]
                        +"#"+listInfo4[6]+"#"+listInfo4[7]+"#"+listInfo4[8]+"#"+listInfo4[9]+"#"+listInfo4[10]+"#"+listInfo4[11];
                temp4=temp4.replaceAll(oldInfo4,newInfo4);
                tool.overwrite(temp4);
                System.out.println("-----------------------------");
                System.out.println("Thay doi thanh cong !");
                break;
            case "5":
                String oldAddress="";
                String listAcc5[] = tool.read().split("\n");
                String listInfo5[] = listAcc5[index].split("#");
                readNwrite oldAd = new readNwrite("address");
                String listAd[] = oldAd.read().split("\n");
                for(int i = 0; i<listAd.length;i++){
                    String listIndex[]=listAd[i].split("#");
                    if(listIndex[0].equals(listInfo5[4])){
                        oldAddress=listAd[i];
                        break;
                    }
                }
                address newAD = new address();
                newAD.CentralUnit2(Integer.parseInt(listInfo5[4]),oldAddress);
                System.out.println("Thay doi thanh cong !");
                break;
            case "6":
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
                        String newInfo6 = listInfo6[0]+"#"+thaydoi6+"#"+listInfo6[2]+"#"+listInfo6[3]+"#"+listInfo6[4]+"#"+listInfo6[5]
                                +"#"+listInfo6[6]+"#"+listInfo6[7]+"#"+listInfo6[8]+"#"+listInfo6[9]+"#"+listInfo6[10]+"#"+listInfo6[11];
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
                            edit("6");
                        }
                    }
                }
                break;
        }

    }

}
