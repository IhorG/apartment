package com.ishe;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by igor on 25.10.2017.
 */
public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/apartDB";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Apartments apartments = new Apartments();

        try {
            try {
                apartments.conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
                apartments.initDB();

                while (true) {
                    System.out.println("1: Add apartment");
                    System.out.println("2: Delete apartment");
                    System.out.println("3: View apartment");
                    System.out.println("4: Choice by parameter");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            apartments.addApartment(sc);
                            break;
                        case "2":
                            apartments.deleteApartment(sc);
                            break;
                        case "3":
                            apartments.viewApartment();
                            break;
                        case "4":
                            System.out.println("\t\t" + "1: parameter district");
                            System.out.println("\t\t" + "2: parameter addres");
                            System.out.println("\t\t" + "3: parameter namberOfrooms");
                            System.out.println("\t\t" + "4: parameter squareOfApartment");
                            System.out.println("\t\t" + "5: parameter price");
                            System.out.print("\t\t" + "-> ");
                            String x = sc.nextLine();
                            switch (x) {
                                case "1":
                                    apartments.faind(sc, "district", "disrict");
                                    break;
                                case "2":
                                    apartments.faind(sc, "addres", "addres");
                                    break;
                                case "3":
                                    apartments.faind(sc, "namberOfrooms", "namber of rooms");
                                    break;
                                case "4":
                                    apartments.faind(sc, "squareOfApartment", "square of apartment");
                                    break;
                                case "5":
                                    apartments.faind(sc, "price", "price");
                                    break;
                                default:
                                    return;
                            }
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                if (apartments.conn != null) apartments.conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }


}
