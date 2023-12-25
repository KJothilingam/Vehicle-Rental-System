import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.mysql.cj.protocol.Resultset;

public class usermanager {
    
                 static String url = "jdbc:mysql://localhost:3306/";
                 static   String dbname="vehicles_Rental_System";
                 static    String userName = "root";
                 static   String password = "root";
                 static String personusing="";
    
    
    Scanner sc=new Scanner(System.in);
    public void createDatabases() {      
        try {
            String url = "jdbc:mysql://localhost:3306/";
            
            String databaseName = "Vehicles_Rental_System";
            String userName = "root";
            String password = "root";
            
            Connection connection = DriverManager.getConnection(url,userName, password);
    
            String sql = "CREATE DATABASE " + databaseName;
    
            Statement statement = connection.createStatement();
            
            statement.executeUpdate(sql);
            statement.close();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try {
            String tableName = "BIKE_INVENTORY";
    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "CREATE TABLE BIKE_INVENTORY(BIKE_NAME VARCHAR(50),NUMBER_PLATE VARCHAR(20),Service_conditon varchar(10))"  ;
    
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public static void alterTables() {
        try {
            String tableName = "CAR_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "ALTER TABLE"+tableName+"ADD RENTAL_CHARGES_PER_DAY  VARCHAR(10),ADD LAST_SERVICE int(10),ADD TOTAL_KMS_RUN INT(10) "  ;
    
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createdata() {
        
        try {
            String tableName = "LOGIN_DETAILS";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "INSERT INTO LOGIN_DETAILS  (USER_NAME,PASSWORD ) VALUES (?,?) "  ;
            PreparedStatement pstm = connection.prepareStatement(sql);
            System.out.println(" Enter User Name");
            String name=sc.next();
            pstm.setString(1,name);
            System.out.println(" Enter User Password");
            pstm.setString(2,sc.next());

            
            pstm.execute();
            // System.out.println("DONE INSERTING ");
            connection.close();
            crtcarttable ct=new crtcarttable();
            ct.newhistable(name);
            ct.addcarttable(name);
      
        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
    
    public void check() {
        System.out.println("Enter User Name:");
        String usr=sc.next();
        personusing=usr;
        System.out.println("Enter Password");
        String pass=sc.next();
        boolean ch=false;
        boolean ad=false;
        
        try {
            String tableName = "LOGIN_DETAILS";
    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM "+tableName  ;
    
            Statement stm= connection.createStatement();
          
            ResultSet rs= stm.executeQuery(sql);
            while(rs.next()){
                String n=rs.getString(1);
                String p=rs.getString(2);
                if(n.equals(usr) && p.equals(pass)){
                    if(n.equals("LINGAM") && p.equals("LINGAM@1234")){
                        ad=true;
                    }
                    ch=true;
                }
            }
            stm.execute(sql);
            if(!ch){
                System.out.println("Invalid User id or Password ");
                return ;
            }
            admin adm=new admin();
            borrower br=new borrower();
            if(ad){
                adm.admin_page();
            }
            else{
                br.borrower_page();
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addsecurity() {
         
        try {
            String tableName = "security_deposit";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "INSERT INTO security_deposit (USER_NAME,AMMOUNT,FINE ) VALUES (?,?,?) "  ;
            PreparedStatement pstm = connection.prepareStatement(sql);
            System.out.println(" Enter User Name");
            pstm.setString(1,sc.next());
            System.out.println(" Enter Security Amount ");
            pstm.setInt(2,sc.nextInt());
            System.out.println(" Enter Fine Amount ");
            pstm.setInt(3,sc.nextInt());

            pstm.execute();
            System.out.println("DONE INSERTING ");
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addVehicles() { 
        System.out.println("what do you want to add CAR or BIKE");
        String st=sc.next();
        if(st.equals("car")  || st.equals("CAR")){
             try {
            String tableName = "car_inventory";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "INSERT INTO car_inventory (CAR_NAME,NUMBER_PLATE,SERVICE_CONDITON,RENTAL_CHARGES_PER_DAY,LAST_SERVICE,TOTAL_KMS_RUN,CURRENT_STATUS ) VALUES (?,?,?,?,?,?,?) "  ;
            PreparedStatement pstm = connection.prepareStatement(sql);
            System.out.println(" Enter CAR Name");
            pstm.setString(1,sc.next());
            System.out.println(" Enter NUMBER_PLATE");
            pstm.setString(2,sc.next());
            System.out.println(" Enter the serivice_condition;");
            pstm.setString(3,sc.next());

            System.out.println(" Enter the RENTAL CHARGES PER DAY");
            pstm.setInt(4,sc.nextInt());
            System.out.println(" Enter the LAST SERVICE DONE BEFORE");
            pstm.setInt(5,sc.nextInt());
            System.out.println(" Enter the TOTAL KMS RUN");
            pstm.setInt(6,sc.nextInt());
            System.out.println(" Enter the  CURRENT_STATUS");
            pstm.setString(7,sc.next());

            pstm.execute();
            System.out.println("DONE INSERTING ");
            connection.close();
        
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        else if(st.equals("bike")  || st.equals("BIKE")){
                try {
            String tableName = "bike_inventory";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "INSERT INTO bike_inventory (BIKE_NAME,NUMBER_PLATE,SERVICE_CONDITON ,RENTAL_CHARGES_PER_DAY,LAST_SERVICE,TOTAL_KMS_RUN,CURRENT_STATUS) VALUES (?,?,?,?,?,?,?) "  ;
            PreparedStatement pstm = connection.prepareStatement(sql);
            System.out.println(" Enter BIKE Name");
            pstm.setString(1,sc.next());
            System.out.println(" Enter NUMBER_PLATE");
            pstm.setString(2,sc.next());
            System.out.println(" Enter the serivice_condition;");
            pstm.setString(3,sc.next());
            System.out.println(" Enter the RENTAL CHARGES PER DAY");
            pstm.setInt(4,sc.nextInt());
            System.out.println(" Enter the LAST SERVICE DONE BEFORE");
            pstm.setInt(5,sc.nextInt());
            System.out.println(" Enter the TOTAL KMS RUN");
            pstm.setInt(6,sc.nextInt());
            
            System.out.println(" Enter the  CURRENT_STATUS");
            pstm.setString(7,sc.next());

            pstm.execute();
            System.out.println("DONE INSERTING ");
            connection.close();
        
            } catch (Exception e) {
                e.printStackTrace();
            }

            }
            else{
                System.out.println("INVALID INPUT");
                
            }
        }

    public void modify() {
        System.out.println();
        System.out.println("VEHICLES_MODIFICATION");
        System.out.println("Press 1: TO EDIT AVALIABLE_VEHICLES_LIST ");
        System.out.println("Press 2: REMOVE_VEHICLES");
        System.out.println();
        int w=sc.nextInt();
        switch(w){
            case 1:
            System.out.println("WHICH VEHICLE  YOU WANT TO MODIFY CAR/BIKE");
            String ee=sc.next();
            if(ee.equals("car") || ee.equals("CAR")){
                System.out.println("CAR_INVENTORY");
                try {
                    String tableName = "CAR_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM CAR_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("CAR_NAME : "+rs.getString(1));
                        System.out.println("NUMBER_PLATE : "+rs.getString(2));
                        System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                        System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                        System.out.println("LAST_SERVICE : "+rs.getString(5));
                        System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                        System.out.println("CURRENT_STATUS : "+rs.getString(7));
                        System.out.println();
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                System.out.println("ENTER NAME OF THE CAR TO CHANGE THE SERVICE_CONDITION ");
                String crn=sc.next();
                System.out.println("CHANGE THE SERVICE_CONDITION TO READY/NOT_READY ");
                String cha=sc.next();
                 try {
                    String tname="car_inventory";
                    
                    Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            
                    String sql = " UPDATE car_inventory SET Service_conditon= '"+cha+"' WHERE car_name='"+crn+"'";
            
                    Statement statement = connection.createStatement();
                    
                    statement.executeUpdate(sql);
                    statement.close();
            
                } catch (Exception e) {
                    e.printStackTrace();
                }
         }
         else if(ee.equals("bike") || ee.equals("BIKE")){
            System.out.println();
            System.out.println("BIKE_INVENTORY");
            System.out.println();
                try {
                    String tableName = "BIKE_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("BIKE_NAME : "+rs.getString(1));
                        System.out.println("NUMBER_PLATE : "+rs.getString(2));
                        System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                        System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                        System.out.println("LAST_SERVICE : "+rs.getString(5));
                        System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                        System.out.println("CURRENT_STATUS : "+rs.getString(7));
                        System.out.println();
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                System.out.println("ENTER NAME OF THE BIKE TO CHANGE THE SERVICE_CONDITION ");
                String crn=sc.next();
                System.out.println("CHANGE THE SERVICE_CONDITION TO READY/NOT_READY ");
                String cha=sc.next();
                 try {
                    String tname="BIKE_inventory";
                    
                    Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            
                    String sql = " UPDATE bike_inventory SET Service_conditon= '"+cha+"' WHERE bike_name='"+crn+"'";
            
                    Statement statement = connection.createStatement();
                    
                    statement.executeUpdate(sql);
                    statement.close();
            
                } catch (Exception e) {
                    e.printStackTrace();
                }
         }




            break;
            case 2:
            System.out.println();
            System.out.println("WHICH VEHICLE DO YOU WANT TO REMOVE CAR/BIKE ");
            String q=sc.next();
            if(q.equals("car") || q.equals("CAR")){
                System.out.println("ENTER THE NAME OF THE CAR TO REMOVE");
                String cname=sc.next();
                System.out.println();
                 try {
                            String tableName = "CAR_INVENTORY";
                            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                            String sql = "DELETE FROM CAR_INVENTORY where CAR_NAME='"+cname+"'"  ;
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(sql);
                            System.out.println("DELETED");
                            System.out.println();
                            connection.close();
                    
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
            }
            else if(q.equals("bike") || q.equals("BIKE")){
                System.out.println("ENTER THE NAME OF THE BIKE TO REMOVE");
                String bname=sc.next();
                System.out.println();
                 try {
                            String tableName = "BIKE_INVENTORY";
                            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                            String sql = "DELETE FROM BIKE_INVENTORY where BIKE_NAME='"+bname+"'"  ;
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(sql);
                            System.out.println("DELETED");
                            System.out.println();
                            connection.close();
                    
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

            }
            break;
        }

    }

    public void listit() {
        System.out.println();
        System.out.println("WHICH VEHICLE LIST DO YOU WANT TO SEE : ");
        String qq=sc.next();
        if(qq.equals("car") || qq.equals("CAR")){
                System.out.println("CAR_INVENTORY");
                try {
                    String tableName = "CAR_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM CAR_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("CAR_NAME : "+rs.getString(1));
                        System.out.println("NUMBER_PLATE : "+rs.getString(2));
                        System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                        System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                        System.out.println("LAST_SERVICE : "+rs.getString(5));
                        System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                        System.out.println("CURRENT_STATUS : "+rs.getString(7));
                        System.out.println();
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(qq.equals("BIKE") || qq.equals("bike")){
                System.out.println("BIKE_INVENTORY");
                try {
                    String tableName = "BIKE_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("CAR_NAME : "+rs.getString(1));
                        System.out.println("NUMBER_PLATE : "+rs.getString(2));
                        System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                        System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                        System.out.println("LAST_SERVICE : "+rs.getString(5));
                        System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                        System.out.println("CURRENT_STATUS : "+rs.getString(7));
                        System.out.println();
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("INVALID INPUT TRY AGAIN ");
            }
            
            

    }

    public void seecash() {
        System.out.println();
        System.out.println("ACCOUNTS_DETAILS");
        System.out.println();
                try {
                    String tableName = "security_deposit";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM SECURITY_DEPOSIT";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        System.out.println("NAME : "+rs.getString(1));
                        System.out.println("SECURITY_DEPOSIT : "+rs.getInt(2));
                        System.out.println("FINE : "+rs.getInt(3));
                        System.out.println();
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            
    }
  

    //BORROWER CLASS 
     public void listitforbro() {
        System.out.println();
        System.out.println("WHICH VEHICLE LIST DO YOU WANT TO SEE : ");
        String qq=sc.next();
        if(qq.equals("car") || qq.equals("CAR")){
            System.out.println("CAR_INVENTORY");
                try {
                    String tableName = "CAR_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM CAR_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        if(rs.getString(3).equals("READY") ){
                            System.out.println("CAR_NAME : "+rs.getString(1));
                            System.out.println("NUMBER_PLATE : "+rs.getString(2));
                            System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                            System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                            System.out.println("LAST_SERVICE : "+rs.getString(5));
                            System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                            System.out.println("CURRENT_STATUS : "+rs.getString(7));
                            System.out.println();
                        }
                    }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(qq.equals("BIKE") || qq.equals("bike")){
                System.out.println("BIKE_INVENTORY");
                try {

                    String tableName = "BIKE_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        if(rs.getString(3).equals("READY")){

                            System.out.println("CAR_NAME : "+rs.getString(1));
                            System.out.println("NUMBER_PLATE : "+rs.getString(2));
                            System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                            System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                            System.out.println("LAST_SERVICE : "+rs.getString(5));
                            System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                            System.out.println("CURRENT_STATUS : "+rs.getString(7));
                            System.out.println();
                        }
                     }
                    stm.execute(sql);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("INVALID INPUT TRY AGAIN ");
            }
            
            

    }

    public void searchit() {
        System.out.println();
        System.out.println("WHICH VEHICLE WOULD YOU LIKE TO SEARCH CAR / BIKE : ");
        String qq=sc.next();
        if(qq.equals("car") || qq.equals("CAR")){
            System.out.println();
            System.out.println("ENTER CAR NAME OR NUMBER_PLATE_NO ");
            String carsec=sc.next();
            boolean fgc=false;
                try {
                    String tableName = "CAR_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM CAR_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        if((rs.getString(1).equals(carsec)) || rs.getString(2).equals(carsec)){
                            System.out.println("CAR_NAME : "+rs.getString(1));
                            System.out.println("NUMBER_PLATE : "+rs.getString(2));
                            System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                            System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                            System.out.println("LAST_SERVICE : "+rs.getString(5));
                            System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                            System.out.println("CURRENT_STATUS : "+rs.getString(7));
                            System.out.println();
                            fgc=true;
                        }
                    }
                    System.out.println();
                    stm.execute(sql);
                    if(!fgc){
                        System.out.println("NO SUCH VEHICLE FOUND ");
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(qq.equals("BIKE") || qq.equals("bike")){
                System.out.println();
                System.out.println("ENTER BIKE NAME OR NUMBER_PLATE_NO ");
                String bsec=sc.next();
                boolean fgb=false;
                try {

                    String tableName = "BIKE_INVENTORY";
                    
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
            while(rs.next()){
                if(rs.getString(1).equals(bsec) || rs.getString(2).equals(bsec)){
                    
                    System.out.println("BIKE_NAME : "+rs.getString(1));
                    System.out.println("NUMBER_PLATE : "+rs.getString(2));
                    System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                    System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                    System.out.println("LAST_SERVICE : "+rs.getString(5));
                    System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                    System.out.println("CURRENT_STATUS : "+rs.getString(7));
                    System.out.println();
                    fgb=true;
                }
            }
            stm.execute(sql);
            System.out.println();
            if(!fgb){
                        System.out.println("NO SUCH VEHICLE FOUND ");
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("INVALID INPUT ");
            }
            
            


    }

    public void addcartnew(String qq, String ty, String reqn, String rent) {
                 try {
            String tbne = personusing+"_cart";

            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "INSERT INTO "+tbne+"(VEHICLE_TYPE,VEHICLE_NAME,VEHICLE_NUMBER,RENTAL_CHARGES_PER_DAY) VALUES (?,?,?,?) "  ;

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,qq);
            pstm.setString(2,ty);
            pstm.setString(3,reqn);
            pstm.setString(4,rent);

            pstm.execute();
            connection.close();
            
      
        } catch (Exception e) {
            e.printStackTrace();
        }
            // System.out.println("222222222222");
            // System.out.println(personusing);

    }

    public void cartlist() {
            try {
                String tableName = personusing+"_cart";
                
        Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
        String sql = "SELECT * FROM "+tableName;
    
        Statement stm= connection.createStatement();
            
        ResultSet rs= stm.executeQuery(sql);
                while(rs.next()){

                    System.out.println();
                    System.out.println("VEHICLE_TYPE: "+rs.getString(1));
                    System.out.println("VEHICLE_NAME: "+rs.getString(2));
                    System.out.println("VEHICLE_NUMBER : "+rs.getString(3));
                    System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getString(4));
                    System.out.println();
                }
                stm.execute(sql);
                
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    public void canrentit(String wh,String vehiname, String reqn, String sercon, int rent, int lastser, int tkms,String inornot) {
        
            System.out.println();

            if((sercon.equals("READY") && inornot.equals("AVAILABLE"))){
                System.out.println();
                System.out.println("VEHICLE AVAILABLE TO CONFIRM BOOKING TYPE CONFIRM ");
                String cbook=sc.next();
                System.out.println();
                if(cbook.equals("CONFIRM") || cbook.equals("CONFIRM")){
                    crtcarttable ss=new crtcarttable();
                    ss.history(personusing,wh,vehiname,reqn,rent,lastser,tkms);
                    crtcarttable up=new crtcarttable();
                    up.uptable(vehiname,wh);
                }
                else{
                    System.out.println("TRY AGAIN");
                }



            }
            else if(inornot.equals("STOCK_OUT")){
                System.out.println("CURRENTLY NOT AVAILABLE ");
            }
            else{
                System.out.println("VEHICLE IS NOT YET READY FOR RENTING  ");
            }

        }

    public void giveitback() {
         try {
                String tb3 = personusing+"_HISTORY";
                            
                    Connection connection = DriverManager.getConnection(url+dbname,userName, password);
                
                    String sql = "SELECT * FROM "+tb3;
                
                    Statement stm= connection.createStatement();
                        
                    ResultSet rs= stm.executeQuery(sql);
                            while(rs.next()){

                                System.out.println();
                                System.out.println("VEHICLE_TYPE: "+rs.getString(1));
                                System.out.println("VEHICLE_NAME: "+rs.getString(2));
                                System.out.println("VEHICLE_NUMBER : "+rs.getString(3));
                                System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                System.out.println("TOTAL_KMS : "+rs.getInt(6));
                                System.out.println("RENT_DATE : "+rs.getString(7));
                                System.out.println();
                            }
                            stm.execute(sql);
                            
                        } catch (Exception e) {
                            e.printStackTrace();
              }


        System.out.println();
        System.out.println("WHICH VEHICLE DO YOU WANT TO RETURN : ");
        String rettype=sc.next();
        System.out.println("ENTER THE VEHICLE NAME : ");
        String retname=sc.next();
        System.out.println("ENTER THE CURENNT TOTAL KMS RIDE : ");
        String retkms=sc.next();
        int damage_fine=0;

        if(rettype.equals("car") || rettype.equals("CAR")){
            System.out.println("IS THERE ANY DAMANGE TO THE CAR Y/N");
            String retdam=sc.next();
            if(retdam.equals("y") || retdam.equals("Y")){
                System.out.println("ENTER THE DAMAGE CATEGORY :");
                System.out.println("PRESS 1 : FOR LOW DAMAGE ");
                System.out.println("PRESS 2 : FOR MEDIUM DAMAGE ");
                System.out.println("PRESS 3 : FOR HIGH DAMAGE ");
                int fine1=6000;
                int fine2=15000;
                int fine3=22500;
                int retdanty=sc.nextInt();
                switch(retdanty){
                    case 1:
                    damage_fine=fine1;
                    // System.out.println(damage_fine+" 1");
                    break;
                    
                    case 2:
                   damage_fine=fine2;
                    // System.out.println(damage_fine+" 2");
                    break;
                    
                    case 3:
                     damage_fine=fine3;
                    //  System.out.println(damage_fine+" 3");
                    break;
                    
                }
                //  System.out.println(damage_fine);

            }
        }
        System.out.println("ENTER RETURN_DATE : IN THIS FORMAT DATE/MONTH/YEAR -> Ex:12/03/2022");
        String retdate=sc.next();
        boolean fg4=false;
        int befrent=0;
        int beflast_ser=0;
        int beftotal_kms=0;
        String befdate="";
        try {
                String tb3 = personusing+"_HISTORY";
                            
                    Connection connection = DriverManager.getConnection(url+dbname,userName, password);
                
                    String sql = "SELECT * FROM "+tb3;
                
                    Statement stm= connection.createStatement();
                        
                    ResultSet rs= stm.executeQuery(sql);
                            while(rs.next()){
                                if(retname.equals(rs.getString(2))){
                                    befrent=rs.getInt(4);
                                    beflast_ser=rs.getInt(5);
                                    beftotal_kms=rs.getInt(6);
                                    befdate=rs.getString(7);
                                    fg4=true;
                                }

                            }
                        if(!fg4){
                            System.out.println("VEHICLE NOT FOUND  ");
                        }
                            stm.execute(sql);
                        
                        } catch (Exception e) {
                            e.printStackTrace();
        }
        crtcarttable po =new crtcarttable();
        po.returnupdate(rettype,retname,retkms,damage_fine,retdate,befrent,beflast_ser,beftotal_kms,befdate);



    }

    public void finalbill(int actualrent) {
        crtcarttable bil=new crtcarttable();
        if(actualrent>30000){
            int togive=actualrent-30000;
            System.out.println("YOU HAVE TO PAY :-> "+togive);
            bil.upaccount(personusing);
        }
        else{
            int toback=30000-actualrent;
            System.out.println("YOUR BALANCE IS :-> "+toback);
            bil.upaccount(personusing);
        }
    }

    public void vehitableupdate(String rettype, String retname, String retkms, int riderkms,int beflast_ser) {
        if(rettype.equals("car") || rettype.equals("CAR")){
            String con="";
            int pre=beflast_ser+riderkms;
            if(pre>3000){
                con="NOT_READY";
            }
            else{
                con="READY";
            }
            try {
                String tn = rettype+"_INVENTORY";
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
                String sql = "UPDATE "+tn+" SET CURRENT_STATUS='AVAILABLE' WHERE "+rettype+"_NAME='"+retname+"'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.close();
          
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String tn = rettype+"_INVENTORY";
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
                String sql = "UPDATE "+tn+" SET Service_conditon='"+con+"' WHERE "+rettype+"_NAME='"+retname+"'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.close();
          
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
              String con="";
            int pre=beflast_ser+riderkms;
            if(pre>1500){
                con="NOT_READY";
            }
            else{
                con="READY";
            }

            // System.out.println(pre);
            // System.out.println(con);
          try {
                String tn = rettype+"_INVENTORY";
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
                String sql = "UPDATE "+tn+" SET CURRENT_STATUS='AVAILABLE' WHERE "+rettype+"_NAME='"+retname+"'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.close();
          
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                String tn = rettype+"_INVENTORY";
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
                String sql = "UPDATE "+tn+" SET Service_conditon='"+con+"' WHERE "+rettype+"_NAME='"+retname+"'";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                connection.close();
          
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void upreturndate(String retdate,String retname) {

        try {
            String tn = personusing+"_HISTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
              
            String sql = "UPDATE "+tn+" SET RETURN_DATE= '"+retdate+"' WHERE VEHICLE_NAME= '"+retname+"'" ;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lastser_totalkmsupdate(int beflast_ser,int riderkms, String retkms,String rettype,String retname) {
        int present_lastser=beflast_ser+riderkms;
        int present_totalkms=Integer.parseInt(retkms);
        try {
            String tn = rettype+"_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "UPDATE " + tn + " SET LAST_SERVICE = " + present_lastser + " WHERE " + rettype + "_NAME = '" + retname + "'";
               
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            String tn = rettype+"_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            String sql = "UPDATE " + tn + " SET TOTAL_KMS_RUN = " + present_totalkms + " WHERE " + rettype + "_NAME = '" + retname + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showthehistory() {
           try {
                String tableName = personusing+"_HISTORY";
                
        Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
        String sql = "SELECT * FROM "+tableName;
    
        Statement stm= connection.createStatement();
            
        ResultSet rs= stm.executeQuery(sql);
                while(rs.next()){

                    System.out.println();
                    System.out.println("VEHICLE_TYPE: "+rs.getString(1));
                    System.out.println("VEHICLE_NAME: "+rs.getString(2));
                    System.out.println("VEHICLE_NUMBER : "+rs.getString(3));
                    System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getString(4));
                    System.out.println("RENT_DATE : "+rs.getString(7));
                    System.out.println("RETUN_DATE : "+rs.getString(8));
                    System.out.println();
                }
                stm.execute(sql);
                
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void showpending() {
        System.out.println("CAR OR BIKE");
        String whty=sc.next();
        if(whty.equals("car") || whty.equals("CAR")){
            try {
                        String tableName = whty+"_INVENTORY";
                        
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                String sql = "SELECT * FROM "+tableName;

                Statement stm= connection.createStatement();
                
                ResultSet rs= stm.executeQuery(sql);
                        while(rs.next()){
                            if(rs.getString(3).equals("NOT_READY")){

                                System.out.println();
                                System.out.println("CAR_NAME: "+rs.getString(1));
                                System.out.println("NUMBER_PLATE : "+rs.getString(2));
                                System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                                System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                System.out.println("TOTAL_KMS_RUN : "+rs.getInt(6));
                                System.out.println("CURRENT_STATUS : "+rs.getString(7));
                                System.out.println();
                            }
                        }
                        stm.execute(sql);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
         }
         else if(whty.equals("bike") || whty.equals("BIKE")){
             try {
                        String tableName = whty+"_INVENTORY";
                        
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                String sql = "SELECT * FROM "+tableName;

                Statement stm= connection.createStatement();
                
                ResultSet rs= stm.executeQuery(sql);
                        while(rs.next()){
                            if(rs.getString(3).equals("NOT_READY")){

                                System.out.println();
                                System.out.println("BIKE_NAME: "+rs.getString(1));
                                System.out.println("NUMBER_PLATE : "+rs.getString(2));
                                System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                                System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                System.out.println("TOTAL_KMS_RUN : "+rs.getInt(6));
                                System.out.println("CURRENT_STATUS : "+rs.getString(7));
                                System.out.println();
                            }
                        }
                        stm.execute(sql);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                 

         }
         else{
            System.out.print("INVALID INPUT");
         }
                
            }

    public void doservice() {
        System.out.println("CAR OR BIKE");
        String whty=sc.next();
        int numq=0;
        if(whty.equals("car") || whty.equals("CAR")){
            try {
                        String tableName = whty+"_INVENTORY";
                        
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                String sql = "SELECT * FROM "+tableName;

                Statement stm= connection.createStatement();
                
                ResultSet rs= stm.executeQuery(sql);
                        while(rs.next()){
                            if(rs.getString(3).equals("NOT_READY")){

                                System.out.println();
                                System.out.println("CAR_NAME: "+rs.getString(1));
                                System.out.println("NUMBER_PLATE : "+rs.getString(2));
                                System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                                System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                System.out.println("TOTAL_KMS_RUN : "+rs.getInt(6));
                                System.out.println("CURRENT_STATUS : "+rs.getString(7));
                                System.out.println();
                            }
                        }
                        stm.execute(sql);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
         }
         else if(whty.equals("bike") || whty.equals("BIKE")){
             try {
                        String tableName = whty+"_INVENTORY";
                        
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);

                String sql = "SELECT * FROM "+tableName;

                Statement stm= connection.createStatement();
                
                ResultSet rs= stm.executeQuery(sql);
                        while(rs.next()){
                            if(rs.getString(3).equals("NOT_READY")){

                                System.out.println();
                                System.out.println("BIKE_NAME: "+rs.getString(1));
                                System.out.println("NUMBER_PLATE : "+rs.getString(2));
                                System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                                System.out.println("RENATAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                System.out.println("TOTAL_KMS_RUN : "+rs.getInt(6));
                                System.out.println("CURRENT_STATUS : "+rs.getString(7));
                                System.out.println();
                            }
                        }
                        stm.execute(sql);
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                 

         }
         else{
            System.out.print("INVALID INPUT");
         }
      
        System.out.println("ENTER THE NAME OF VECHILE FOR SERVICE");
        String str=sc.next();
        
        try {
            String tn = whty+"_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
              
            String sql = "UPDATE "+tn+" SET Service_conditon= 'READY' WHERE "+whty+"_NAME='"+str+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            String tn = whty+"_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            String sql = "UPDATE " + tn + " SET LAST_SERVICE = " + numq + " WHERE " + whty + "_NAME = '" + str + "'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
        
    }

        




