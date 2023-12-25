import java.util.Scanner;
 
public class login_page{
    public static void main(String[] args) throws Exception {

        System.out.println();
        System.out.println();
        String sq="y";
        do{
                System.out.println();
                System.out.println(" Welcome to the XploreXpress Rental Services ");
                System.out.println();
                System.out.println("Press 1 for Sign-Up");
                System.out.println("Press 2 for Sign-in");

                Scanner sc=new Scanner (System.in);
            usermanager db=new usermanager();
            int input=sc.nextInt();
            switch(input){
                case 1:
                db.createdata();
                System.out.println(" Account Created Successfully ");
                System.out.println();
                System.out.print("DO YOU WANT TO LOGIN IN - Y/N");
                System.out.println();
                String lg=sc.next();
                sq=lg;
                break;
                
                case 2:
                db.check();
                }
        }while(sq.equals("Y") || sq.equals("y"));
    }
}
