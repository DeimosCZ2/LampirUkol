import java.io.*;
import java.sql.*;
import java.util.Scanner; //import věcí
public class JDBC {
    public static void main(String[] args) {
        try {




            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection( // připojení k driveru
                    "jdbc:mysql://localhost:3306/lampukol", "root", "" //připojení k DB a přihlášení rootu
            );
            System.out.println("Zadej IP adresu");
            Scanner scan = new Scanner(System.in);
            String ip = scan.nextLine() ;

            String[] ipMod = ip.split("[.]", 0);;
            int ip1 = Integer.parseInt(ipMod [0]);
            int ip2 = Integer.parseInt(ipMod [1]);
            int ip3 =Integer.parseInt(ipMod [2]);
            int ip4 = Integer.parseInt(ipMod [3]);
            String query = "SELECT ipp.city FROM dbip_lookup_educa_modified as ipp WHERE ipp.ip_start1 = "+ip1+" AND ipp.ip_start2 = "+ip2+" AND "+ip3+" BETWEEN ipp.ip_start3 AND ipp.ip_end3 AND "+ip4+" BETWEEN ipp.ip_start4 AND ipp.ip_end4;"; //query
            Statement statement = connection.createStatement();
            long clock1 = System.nanoTime();
            ResultSet resultSet = statement.executeQuery(query);
            long clock2 = System.nanoTime();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); //vypsání výsledku
               long Res1 = clock2 - clock1;
                System.out.println(Res1 + " ns");
            }
            connection.close(); // konec připojení
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}