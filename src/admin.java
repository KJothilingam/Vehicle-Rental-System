import java.util.Scanner;

public class admin {

    public void admin_page() {
        Scanner sc=new Scanner(System.in);
        String st="y";
        do{
            System.out.println();
            System.out.println();
            System.out.println(" ADMIN LOGIN- SUCCESSFULL ");
            System.out.println();
            System.out.println("Press 1: Add New Vehicles ");
            System.out.println("Press 2: Modify Vehicles");
            System.out.println("Press 3: List of All Vehicles ");
            System.out.println("Press 4: ACCOUNTS_SECTION");
            System.out.println("Press 5: ADD_security_DEPOSIT ");
            System.out.println("Press 6: TO VIEW PENDING VEHICLE FOR SERVICE ");
            System.out.println("Press 7: FOR SERVICEING VEHICLE");
            int in=sc.nextInt();
            usermanager db=new usermanager();
            // db.createTables();
            switch(in){
                    case 1:
                    db.addVehicles();
                    break;
                    case 2:
                    db.modify();
                    break;
                    case 3:
                    db.listit();
                    break;
                    case 4:
                    db.seecash();
                    break;
                    case 5:
                    db.addsecurity();
                    break;

                    case 6:
                    db.showpending();
                    break;

                    case 7:
                    db.doservice();
                    break;
                }
            System.out.println("Do you want to do Anything else Y/N ?");
            String nxt=sc.next();
            st=nxt;
            
        }while(st.equals("Y") || st.equals("y"));
    }
    
}
