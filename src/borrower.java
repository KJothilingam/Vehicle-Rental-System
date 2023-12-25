import java.util.Scanner;

public class borrower {

    public void borrower_page() {
        String st;
         
       do{
            System.out.println();
            System.out.println(" BORROWER LOGIN- SUCCESSFULL ");
            System.out.println();
            System.out.println("Press 1 : TO VIEW THE LIST OF VEHICLES");
            System.out.println("Press 2 : TO SEARCH A VEHICLE ");
            System.out.println("Press 3 : ADD VEHICLE TO CART ");
            System.out.println("Press 4 : TO VIEW CART_LIST ");
            System.out.println("Press 5 : TO RENT A VEHICLE ");
            System.out.println("Press 6 : TO RETURN A VEHICLE ");
            System.out.println("Press 7 : TO VIEW PREVIOUS RENTED VEHICLE LIST ");
            System.out.println();
            Scanner sc=new Scanner(System.in);
                    int inp=sc.nextInt();
                    usermanager br=new usermanager();
                    crtcarttable ct=new crtcarttable();
                    
                    switch(inp){
                        case 1:
                        br.listitforbro();
                        break;

                        case 2:
                        br.searchit();
                        break;

                        case 3:
                        ct.addtocart();
                        break;
                        
                        case 4:
                        br.cartlist();
                        break;

                        case 5:
                        ct.newrent();
                        break;

                        case 6:
                        br.giveitback();
                        break;

                        case 7:
                        br.showthehistory();
                        break;
                        
                    }
                    
                System.out.println("Do you want to do Anything else Y/N ?");
                String nxt=sc.next();
                st=nxt;
    
        
            } while(st.equals("y") || st.equals("Y"));

    }
    
}
