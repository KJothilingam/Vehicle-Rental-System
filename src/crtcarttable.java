import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class crtcarttable {
    
    usermanager db=new usermanager();
    static String url = "jdbc:mysql://localhost:3306/";
    static   String dbname="vehicles_Rental_System";
    static    String userName = "root";
    static   String password = "root";
    Scanner sc=new Scanner(System.in);

    public void addcarttable(String name) {
        try {
            String tableName = name+"_CART";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "CREATE TABLE "+tableName+"(VEHICLE_TYPE VARCHAR(50),VEHICLE_NAME VARCHAR(50) ,VEHICLE_NUMBER VARCHAR(50),RENTAL_CHARGES_PER_DAY VARCHAR(50))"  ;
    
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void addtocart() {
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
                        if(rs.getString(3).equals("READY")){

                            System.out.println();
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

                            System.out.println();
                            System.out.println("BIKE_NAME : "+rs.getString(1));
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


        System.out.println(); 

        System.out.println();
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
                            String ty=rs.getString(1);
                            String reqn=rs.getString(2);
                            String rent=rs.getString(4);
                            System.out.println();
                            db.addcartnew(qq,ty,reqn,rent);
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
            boolean fgc=false;
                try {
                    String tableName = "BIKE_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        if((rs.getString(1).equals(bsec)) || rs.getString(2).equals(bsec)){
                            System.out.println("BIKE_NAME : "+rs.getString(1));
                            System.out.println("NUMBER_PLATE : "+rs.getString(2));
                            System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                            System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                            System.out.println("LAST_SERVICE : "+rs.getString(5));
                            System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                            String ty=rs.getString(1);
                            String reqn=rs.getString(2);
                            String rent=rs.getString(4);
                            System.out.println();
                            db.addcartnew(qq,ty,reqn,rent);
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
            else{
                System.out.println("INVALID INPUT ");
            }

    }

    public void newrent() {
        System.out.println();
        System.out.println("WHICH VEHICLE WOULD YOU LIKE TO RENT CAR / BIKE ");
        String wh=sc.next();
        //----------------------------------------------------------------------------------------------
        System.out.println();
            if(wh.equals("car") || wh.equals("CAR")){
                System.out.println("CAR_INVENTORY");
                    try {
                        String tableName = "CAR_INVENTORY";
                        
                Connection connection = DriverManager.getConnection(url+dbname,userName, password);
        
                String sql = "SELECT * FROM CAR_INVENTORY";
    
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
                else if(wh.equals("BIKE") || wh.equals("bike")){
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
          
            System.out.print("ENTER THE NAME OF THE VEHICLE TO RENT : ");
            String rentname=sc.next();
            //---------------------------------------------------------------------------------------------------
              boolean fgc2=false;
              if(wh.equals("car") || wh.equals("CAR")){
                try {

                        String tableName = "CAR_INVENTORY";
                        Connection connection = DriverManager.getConnection(url+dbname,userName, password);
                
                        String sql = "SELECT * FROM CAR_INVENTORY";

                        Statement stm= connection.createStatement();
                            
                        ResultSet rs= stm.executeQuery(sql);
                                while(rs.next()){
                                                if((rs.getString(1).equals(rentname))){
                                                    System.out.println();
                                        System.out.println("CAR_NAME : "+rs.getString(1));
                                        System.out.println("NUMBER_PLATE : "+rs.getString(2));
                                        System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                                        System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getInt(4));
                                        System.out.println("LAST_SERVICE : "+rs.getInt(5));
                                        System.out.println("TOTAL_KMS_RUN : "+rs.getInt(6));
                                        System.out.println("CURRENT_STATUS : "+rs.getString(7));
                                        String vehiname=rs.getString(1);
                                        String reqn=rs.getString(2);
                                        String sercon=rs.getString(3);
                                        int rent=rs.getInt(4);
                                        int lastser=rs.getInt(5);
                                        int tkms=rs.getInt(6);
                                        String inornot=rs.getString(7);
                                        db.canrentit(wh,vehiname,reqn,sercon,rent,lastser,tkms,inornot);
                                        System.out.println();
                                        fgc2=true;
                                            }
                                        }
                    System.out.println();
                    stm.execute(sql);
                    if(!fgc2){
                        System.out.println("NO SUCH VEHICLE FOUND ");
                    }  
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
            else if(wh.equals("BIKE") || wh.equals("bike")){
              System.out.println();
            boolean fgb2=false;
                try {
                    String tableName = "BIKE_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = "SELECT * FROM BIKE_INVENTORY";

            Statement stm= connection.createStatement();
                
            ResultSet rs= stm.executeQuery(sql);
                    while(rs.next()){
                        if((rs.getString(1).equals(rentname))){
                            System.out.println();
                            System.out.println("BIKE_NAME : "+rs.getString(1));
                            System.out.println("NUMBER_PLATE : "+rs.getString(2));
                            System.out.println("SERVICE_CONDITION : "+rs.getString(3));
                            System.out.println("RENTAL_CHARGES_PER_DAY : "+rs.getString(4));
                            System.out.println("LAST_SERVICE : "+rs.getString(5));
                            System.out.println("TOTAL_KMS_RUN : "+rs.getString(6));
                            String vehiname=rs.getString(1);
                            String reqn=rs.getString(2);
                            String sercon=rs.getString(3);
                            int rent=rs.getInt(4);
                            int lastser=rs.getInt(5);
                            int tkms=rs.getInt(6);
                            String inornot=rs.getString(7);
                            db.canrentit(wh,vehiname,reqn,sercon,rent,lastser,tkms,inornot);
                            System.out.println();
                            fgb2=true;
                        }
                    } 
                    System.out.println();
                    stm.execute(sql);
                    if(!fgb2){
                        System.out.println("NO SUCH VEHICLE FOUND ");
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }




        }

    public void newhistable(String name) {
         try {
            String tn = name+"_HISTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql ="CREATE TABLE "+tn+" (VEHI_TYPE VARCHAR(10),VEHICLE_NAME VARCHAR(50),VEHICLE_NUMBER VARCHAR(30), VEHICLE_RENT INT(10),LAST_SER INT(10),TOTAL_KMS INT(10),RENT_DATE VARCHAR(30),RETURN_DATE VARCHAR(30) )";
    
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    public void history(String personusing,String wh, String vehiname, String reqn, int rent, int lastser, int tkms) {
        System.out.println("ENTER TODAYS DATE: IN THIS FORMAT DATE/MONTH/YEAR -> Ex-12/04/2023");
        String date1=sc.next();
        try {
            String tbne2 = personusing+"_HISTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
            String sql = "INSERT INTO "+tbne2+"(VEHI_TYPE,VEHICLE_NAME,VEHICLE_NUMBER,VEHICLE_RENT,LAST_SER,TOTAL_KMS,RENT_DATE,RETURN_DATE) VALUES (?,?,?,?,?,?,?,?) "  ;
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,wh);
            pstm.setString(2,vehiname);
            pstm.setString(3,reqn);
            pstm.setInt(4,rent);
            pstm.setInt(5,lastser);
            pstm.setInt(6,tkms);
            pstm.setString(7,date1);
            pstm.setString(8,"-");
            pstm.execute();
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uptable(String vehiname,String wh) {
         try {
            String tn = wh+"_INVENTORY";
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);

            String sql = "UPDATE "+tn+" SET CURRENT_STATUS='STOCK_OUT' WHERE "+wh+"_NAME='"+vehiname+"'";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.close();
      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnupdate(String rettype, String retname, String retkms, int damage_fine, String retdate, int befrent,
            int beflast_ser, int beftotal_kms, String befdate) {
                System.out.println();
                // System.out.println(rettype);
                // System.out.println(retname);
                // System.out.println(retkms);
                // System.out.println(damage_fine);
                // System.out.println(retdate);
                // System.out.println(befrent);
                // System.out.println(beflast_ser);
                // System.out.println(beftotal_kms);
                // System.out.println(befdate);
                // System.out.println();

                String arrdate[]=befdate.split("/");
                String arrdate2[]=retdate.split("/");

                int no_of_days=Integer.parseInt(arrdate2[0])-Integer.parseInt(arrdate[0]);
                int actualrent=befrent*no_of_days;

                actualrent=actualrent+damage_fine;
                
                int riderkms=Integer.parseInt(retkms)-beftotal_kms;
                double kmsfine=(15/100.0)*befrent;
                int kmsfine_2=(int)kmsfine;

                if(riderkms>500){
                    actualrent=actualrent+kmsfine_2;
                }
                // System.out.println(actualrent);
                // System.out.println("500 fine  "+kmsfine);
                // System.out.println("after_fine  "+actualrent);
                // System.out.println("days "+arrdate2[0]);
                // System.out.println("return date  "+arrdate[0]);
                
                // System.out.println("days "+no_of_days);
                // System.out.println("Ammount "+actualrent);
                // System.out.println("pre_kms "+riderkms);

                //billing 
                usermanager bl=new usermanager();
                bl.finalbill(actualrent);
                bl.vehitableupdate(rettype,retname,retkms,riderkms,beflast_ser);
                bl.upreturndate(retdate,retname);
                bl.lastser_totalkmsupdate(beflast_ser,riderkms,retkms,rettype,retname);

    }

    public void upaccount(String personusing) {
        try {
            String tname="security_deposit";
            int amt=0;
            
            Connection connection = DriverManager.getConnection(url+dbname,userName, password);
    
            String sql = " UPDATE security_deposit SET Ammount="+amt+" WHERE USER_NAME='"+personusing+"'";
    
            Statement statement = connection.createStatement();
            
            statement.executeUpdate(sql);
            statement.close();
    
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
