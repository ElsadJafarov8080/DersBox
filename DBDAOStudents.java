package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Scanner;

public class DBDAOStudents {
    static HikariConfig config = new HikariConfig();
    static Scanner scanner = new Scanner(System.in);
    private static String adFinal = "";
    private static int idFinal = 0;


    private static void Config() {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres123");
        config.setMaximumPoolSize(15);
    }

    private static HikariDataSource getHikariDataSource() {
        Config();
        HikariDataSource ds = null;
        try {
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            System.out.println("Xeta: " + e.getMessage());
        }
        return ds;
    }//FOR STUDENTS

    public void student_davamiyeti() {//adi otur
        String sql = "SELECT f.davamiyet " + "FROM N_S_FN t " + "JOIN davamiyet f ON t.id = f.student_id " + "WHERE t.name = ?";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, adFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String status = rs.getString("davamiyet");
                System.out.println("STATUS-> " + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }

    }

    public void student_bal() {//adi otur
        String sql = "SELECT f.bal " + "FROM N_S_FN t " + "JOIN sinaq_ballari f ON t.id = f.student_id " + "WHERE t.name=? ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, adFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int status = rs.getInt("bal");
                System.out.println("Bal-> " + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public void student_fen() {
        String sql = "SELECT f.fenn_adi " + "FROM N_S_FN t " + "JOIN sinaq_ballari s ON t.id = s.student_id " + "JOIN Fenler f ON s.id = f.id " + "WHERE t.name =? ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, adFinal);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String status = rs.getString("fenn_adi");
                System.out.println("FENN-> " + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public boolean user_pass_check() {
        String sql = "SELECT EXISTS ( " + "    SELECT 1 " + "    FROM N_S_FN " + "    WHERE username = ? AND password = ? " + ")";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            System.out.println("Adinizi daxil edin");
            adFinal = scanner.next();
            System.out.println("Usernanme: ");
            String username = scanner.next();
            System.out.println("Password: ");
            String password = scanner.next();
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getBoolean(1)) {
                System.out.println("daxil oldunuz");
                return true;
            } else {
                //System.out.println("sizin hesabiniz yoxdur");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }

        return false;
    }

    private void id_find() {
        HikariDataSource ds = getHikariDataSource();
        String sql = "select N_S_FN.id from N_S_FN " + "where name=? ";
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, adFinal);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                idFinal = rs.getInt("id");
//               System.out.println(idFinal);
            }
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public boolean register_user_pass_check(String ad, String username, String password) {
        String sql = "SELECT EXISTS ( " + "    SELECT 1 " + "    FROM N_S_FN " + "    WHERE username = ? AND password = ? " + ")";
        HikariDataSource ds = getHikariDataSource();
        adFinal = ad;
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getBoolean(1)) {//?
                return true;
            } else {
//                System.out.println("sizin hesabiniz yoxdur");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());

        }
        return false;


    }

    public void register_user_pass_addDB(String ad, String soyad, String f_name, String username, String password) {
        String sql = "insert into N_S_FN(name,surname,f_name,username,password)values(?,?,?,?,?) ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql);) {
            stm.setString(1, ad);
            stm.setString(2, soyad);
            stm.setString(3, f_name);
            stm.setString(4, username);
            stm.setString(5, password);
            stm.executeUpdate();
            System.out.println("Ugurla qeydiyatdan kecdiniz.");
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }

    }

    public void Derslerik_linkini_goster() {
        String sql = "select * from lesson;";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection(); PreparedStatement stm = con.prepareStatement(sql); ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String lesson = rs.getString("link");
                System.out.println("Ders linki -> " + lesson);
            }
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

}
