package com.ishe;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by igor on 25.10.2017.
 */
public class Apartments {
    public static Connection conn;
    public static void initDB() throws SQLException {

        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS apartment");
            st.execute("CREATE TABLE apartment (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(20) NOT NULL, " +
                    "addres VARCHAR(20) NOT NULL, namberOfrooms INT NOT NULL, squareOfApartment INT NOT NULL, price INT NOT NULL)");
        } finally {
            st.close();
        }
    }

    public static void addApartment(Scanner sc) throws SQLException {

        System.out.print("Enter District name: ");
        String district = sc.nextLine();

        System.out.print("Enter addres: ");
        String addres = sc.nextLine();

        System.out.print("Enter namber of rooms: ");
        String snamberOfrooms = sc.nextLine();
        int namberOfrooms = Integer.parseInt(snamberOfrooms);

        System.out.print("Enter square Of Apartment: ");
        String ssquareOfApartment = sc.nextLine();
        int squareOfApartment = Integer.parseInt(ssquareOfApartment);

        System.out.print("Enter price: ");
        String sprice = sc.nextLine();
        int price = Integer.parseInt(sprice);



        PreparedStatement ps = conn.prepareStatement("INSERT INTO apartment (district, addres, namberOfrooms," +
                "squareOfApartment, price) VALUES(?, ?, ?, ?, ?)");
        try {
            ps.setString(1, district);
            ps.setString(2, addres);
            ps.setInt(3, namberOfrooms);
            ps.setInt(4, squareOfApartment);
            ps.setInt(5, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    public static void deleteApartment(Scanner sc) throws SQLException {
        System.out.print("Enter district name: ");
        String district = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM apartment WHERE district = ?");
        try {
            ps.setString(1, district);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }


    public static void viewApartment() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM apartment ");
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }

    }



    public static void faind (Scanner sc, String a, String b) throws SQLException {
        System.out.print("Enter  "+ b +" :");
        String param = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM apartment WHERE " + a + " = ?");
        try {
            ps.setString(1, param);
            ResultSet rs = ps.executeQuery();


            try {
                ResultSetMetaData md = rs.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();
                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }
}
