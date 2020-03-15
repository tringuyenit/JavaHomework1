package finalProject.service;

import java.io.IOException;
import java.util.Scanner;

public class intro {
    public void introduction() throws IOException {
        Scanner tmp = new Scanner(System.in);
        regist REG = new regist();
        login LOG = new login();

        System.out.println("-----------------------------");
        System.out.println("\t\tMENU");
        System.out.println("(1) Tao tai khoan\n" +
                "(2) Dang nhap\n" +
                "(*) Thoat");
        System.out.print("Nhap lua chon : ");
        String choice = tmp.nextLine();
        switch (choice){
            case "1":
                REG.CentralUnit();
                introduction();
                break;
            case "2":
                LOG.centralUnit();
                introduction();
                break;
            default:
                System.out.println("-----------------------------");
                System.out.println("\t\tCLOSED");
                System.out.println("#######################");
                break;
        }

    }

}
