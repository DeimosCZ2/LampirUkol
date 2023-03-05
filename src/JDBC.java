import java.io.*;
import java.sql.*;
import java.util.Scanner; //import věcí
import java.math.*;
public class JDBC {
    public static void main(String[] args) {
        try {




            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection( // připojení
                    "jdbc:mysql://localhost:3306/jdbclampukol", "root", "" //připojení k DB a přihlášení rootu
            );
            System.out.println("Zadej IP adresu");
            Scanner scan = new Scanner(System.in);
            String ip = scan.nextLine() ;

      /*    String[] ipMod = ip.split("[.]", 0);;
            long ip1 = Integer.parseInt(ipMod [0]);
            long ip2 = Integer.parseInt(ipMod [1]);
            long ip3 =Integer.parseInt(ipMod [2]);
            long ip4 = Integer.parseInt(ipMod [3]);
            long inetIp = ip1* (1<<24) + ip2* (1<<16) + ip3* (1<<8) + ip4;
            System.out.println(inetIp);*/

            String query = "SELECT city FROM dbip_lookup_educa_modified_iii where inet_aton('"+ip+"') >= ip_startM order by ip_startM desc limit 1;";
            long clock1 = System.nanoTime();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            long clock2 = System.nanoTime();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1)); //vypsání výsledku

               long Res1 = clock2 - clock1;
                System.out.println(Res1/1000000 + " ms");

            }
            connection.close(); // konec připojení
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
