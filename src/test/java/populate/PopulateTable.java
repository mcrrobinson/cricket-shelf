/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;
import static java.util.UUID.randomUUID;

/**
 *
 * @author Squash
 */
public class PopulateTable {
    public static void main(String args[]) throws Exception {
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        String mysqlUrl = "jdbc:derby://localhost:1527/cricketstore";
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
        PreparedStatement pstmt = con.prepareStatement(
                "insert into BOOKS (title, edition, sales_price, publish_year, thumbnail) VALUES " +
                        "('Ultimate Cricket Superstars', '1st', 6.50, 2022, ?)," +
                        "('Crickets Most Legendary International Matches', '1st', 7.99, 2023, ?)," +
                        "('Fatty Batter: How cricket saved my life (then ruined it)', '1st', 12.09, 2008, ?)," +
                        "('The Cricket Quiz Book: The quintessential quizzing challenge for cricket lovers with over 500 questions', '1st', 4.99, 2022, ?),"
                        +
                        "('Pushing the Boundaries: Cricket in the Eighties', '1st', 20.00, 2018, ?)");

        for (int i = 1; i < 6; i++) {
            UUID randomUUID = randomUUID();
            Path copied = Paths.get("src/main/webapp/img/" + randomUUID.toString() + ".jpg");
            Path originalPath = Paths.get("src/test/java/populate/thumbnail" + i + ".jpg");
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            pstmt.setString(i, randomUUID.toString());
        }

        pstmt.execute();
        System.out.println("Success...");
    }
}