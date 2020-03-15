package finalProject.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public abstract class action {
    public String sdt;
    public boolean checksdt, checklock;
    public String nameCheck (String nameInput){
        String a,b;
        nameInput = nameInput.trim().replaceAll("\\s+", " ");
        String newNameInput[] = nameInput.split("\\s");
        for (int i = 0; i < newNameInput.length;i++) {
            a = newNameInput[i].toUpperCase();
            b = newNameInput[i].toLowerCase();
            b=b.substring(1);
            newNameInput[i]=(String.valueOf(a.charAt(0))).concat(String.valueOf(b));
        }

        nameInput="";
        for(int i = 0; i<newNameInput.length;i++){
            nameInput=nameInput.concat(newNameInput[i]);
            if(i<newNameInput.length-1){
                nameInput=nameInput.concat(" ");
            }
        }

        return nameInput;
    }

    public void sdt(){
        Scanner temp = new Scanner(System.in);
        String pattern = "0[35789][0-9]{8}";
        System.out.println("-----------------------------");
        System.out.print("Nhap so dien thoai : ");
        sdt=temp.nextLine();
        if(sdt.matches(pattern)){
            checksdt=true;
        }else{
            System.out.println("-----------------------------");
            System.out.println("Sai cu phap!");
            System.out.println("(1) Nhap lai dien thoai\n" +
                    "(*) Thoat");
            System.out.print("Nhap lua chon : ");
            if(temp.nextLine().equals("1")){
                sdt();
            }else {
                checksdt=false;
            }
        }
    }

    public void lock(int index) throws IOException {
        readNwrite lock = new readNwrite("customer");
        String acclist[] = lock.read().split("\n");
        String lockAcc = acclist[index];
        String info[] = lockAcc.split("#");
        String newinfo = info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"
                +info[4]+"#"+info[5]+"#"+info[6]+"#"+info[7]+"#"+info[8]+"#b#"+info[10]+"#"+info[11];
        String temp = lock.read().replaceAll(lockAcc,newinfo);
        lock.overwrite(temp);
    }

    public void accDel(int index) throws IOException {
        readNwrite lock = new readNwrite("customer");
        String acclist[] = lock.read().split("\n");
        String lockAcc = acclist[index];
        String info[] = lockAcc.split("#");
        String newinfo = info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"
                +info[4]+"#"+info[5]+"#"+info[6]+"#"+info[7]+"#"+info[8]+"#c#"+info[10]+"#"+info[11];
        String temp = lock.read().replaceAll(lockAcc,newinfo);
        lock.overwrite(temp);
    }

    public void open(int index) throws IOException {
        readNwrite lock = new readNwrite("customer");
        String acclist[] = lock.read().split("\n");
        String lockAcc = acclist[index];
        String info[] = lockAcc.split("#");
        String newinfo = info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"
                +info[4]+"#"+info[5]+"#"+info[6]+"#"+info[7]+"#"+info[8]+"#a#"+info[10]+"#"+info[11];
        String temp = lock.read().replaceAll(lockAcc,newinfo);
        lock.overwrite(temp);
    }

    public String Time(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public void loginTime(int index, boolean admin) throws IOException {
        String role = (admin==true)? "admin" : "customer";
        readNwrite lock = new readNwrite(role);
        String acclist[] = lock.read().split("\n");
        String lockAcc = acclist[index];
        String info[] = lockAcc.split("#");
        String newinfo = (admin==false)? info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"+info[4]+"#"
                +info[5]+"#"+info[6]+"#"+info[7]+"#"+info[8]+"#"+info[9]+"#"+Time()+"#"+info[11] : info[0]+"#"+info[1]+"#"+info[2]+"#"+Time()+"#"+info[4];
        String temp = lock.read().replaceAll(lockAcc,newinfo);
        lock.overwrite(temp);
    }

    public void logoutTime(int index, boolean admin) throws IOException {
        String role = (admin==true)? "admin" : "customer";
        readNwrite lock = new readNwrite(role);
        String acclist[] = lock.read().split("\n");
        String lockAcc = acclist[index];
        String info[] = lockAcc.split("#");
        String newinfo = (admin==false)? info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"+info[4]+"#"
                +info[5]+"#"+info[6]+"#"+info[7]+"#"+info[8]+"#"+info[9]+"#"+info[10]+"#"+Time() : info[0]+"#"+info[1]+"#"+info[2]+"#"+info[3]+"#"+Time();
        String temp = lock.read().replaceAll(lockAcc,newinfo);
        lock.overwrite(temp);
    }

    public void checkDel() throws IOException {
        readNwrite tool = new readNwrite("customer");
        String listAcc[] = tool.read().split("\n");
        for(int i = 0; i<listAcc.length;i++){
            String info[] = listAcc[i].split("#");
            if(info[9].equals("b")){
                String a[] = Time().split("\\s");
                String a1[] = a[0].split("/");
                int yearIn = Integer.parseInt(a1[0]);
                int monthIn = Integer.parseInt(a1[1]);
                int dayIn = Integer.parseInt(a1[2]);
                String a2[] = a[1].split(":");
                int timeIn = Integer.parseInt(a2[0])*60*60+Integer.parseInt(a2[1])*60+Integer.parseInt(a2[2]);
                String b[] = info[11].split("\\s");
                String b1[] = b[0].split("/");
                int yearOut = Integer.parseInt(b1[0]);
                int monthOut = Integer.parseInt(b1[1]);
                int dayOut = Integer.parseInt(b1[2]);
                String b2[] = b[1].split(":");
                int timeOut = Integer.parseInt(b2[0])*60*60+Integer.parseInt(b2[1])*60+Integer.parseInt(b2[2]);

                if(yearIn==yearOut&&monthIn==monthOut&&dayIn==dayOut){
                }else if(yearIn==yearOut&&monthIn==monthOut){
                    if((dayIn-dayOut)<2){
                    }else if((dayIn-dayOut)==2){
                        if(86400-timeOut+timeIn<86400){
                        }else{
                            accDel(i);
                        }
                    }else{
                        accDel(i);
                    }
                }else if(yearIn==yearOut){
                    if((monthIn-monthOut)==1){
                        if(monthIn==3||monthIn==5||monthIn==7||monthIn==8||monthIn==10||monthIn==12){
                            if(monthIn==3){
                                if(yearIn%4==0){
                                    if((29-dayOut+dayIn)==1){
                                    }else if((29-dayOut+dayIn)==2){
                                        if(86400-timeOut+timeIn<86400){
                                        }else{
                                            accDel(i);
                                        }
                                    }else{
                                        accDel(i);
                                    }
                                }else{
                                    if((28-dayOut+dayIn)==1){
                                    }else if((28-dayOut+dayIn)==2){
                                        if(86400-timeOut+timeIn<86400){
                                        }else{
                                            accDel(i);
                                        }
                                    }else{
                                        accDel(i);
                                    }
                                }
                            }else{
                                if((30-dayOut+dayIn)==1){
                                }else if((30-dayOut+dayIn)==2){
                                    if(86400-timeOut+timeIn<86400){
                                    }else{
                                        accDel(i);
                                    }
                                }else{
                                    accDel(i);
                                }
                            }
                        }else{
                            if((31-dayOut+dayIn)==1){
                            }else if((31-dayOut+dayIn)==2){
                                if(86400-timeOut+timeIn<86400){
                                }else{
                                    accDel(i);
                                }
                            }else{
                                accDel(i);
                            }
                        }
                    }else{
                        accDel(i);
                    }
                }else if((yearIn-yearOut)==1){
                    if(monthIn==1&&monthOut==12){
                        if((31-dayOut+dayIn)==1){
                        }else if((31-dayOut+dayIn)==2){
                            if(86400-timeOut+timeIn<86400){
                            }else{
                                accDel(i);
                            }
                        }else{
                            accDel(i);
                        }
                    }
                }else{
                    accDel(i);
                }
            }
        }

    }

    public abstract void find()throws IOException;
    public abstract void view()throws IOException;
    public abstract void edit(String choice)throws IOException;

}
