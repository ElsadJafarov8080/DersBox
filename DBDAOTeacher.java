package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.util.Scanner;

public class DBDAOTeacher {
    static HikariConfig config = new HikariConfig();
    static Scanner scanner = new Scanner(System.in);

    public void Config() {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres123");
        config.setMaximumPoolSize(15);
    }

    public HikariDataSource getHikariDataSource() {
        Config();
        HikariDataSource ds = null;
        try {
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            System.out.println("Xeta: " + e.getMessage());
        }
        return ds;
    }

    public void all_students() {
        HikariDataSource ds = getHikariDataSource();
        String sql = "select * from N_S_FN";
        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String ad = rs.getString("name");
                String soyad = rs.getString("surname");
                String ata_adi = rs.getString("f_name");
                System.out.println(ad + "-" + soyad + "-" + ata_adi);
            }
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }


    public void davamiyet_qeydi() {
        HikariDataSource ds = getHikariDataSource();
        System.out.println("Davamiyyet qeyd edin: ");
        String satatus = scanner.next();
        System.out.println("Telebenin id-si: ");
        int id = scanner.nextInt();
        String sql = "insert into davamiyet(DAVAMIYET, STUDENT_ID) values(?,?)";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, satatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public void fenleri_goster() {
        String sql = "SELECT n.name, " +
                "       n.surname, " +
                "       n.f_name, " +
                "       f.fenn_adi " +
                "FROM N_S_FN n " +
                "         JOIN Fenler f ON f.student_id = n.id ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String ad = rs.getString("name");
                String soyad = rs.getString("surname");
                String ata_adi = rs.getString("f_name");
                String status = rs.getString("fenn_adi");
                System.out.println(ad + "-" + soyad + "-" + ata_adi + "->" + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public void davamiyetleri_goster() {
        String sql = "SELECT n.name, " +
                "       n.surname, " +
                "       n.f_name, " +
                "       d.davamiyet " +
                "FROM N_S_FN n " +
                "JOIN davamiyet d ON d.student_id = n.id ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String ad = rs.getString("name");
                String soyad = rs.getString("surname");
                String ata_adi = rs.getString("f_name");
                String status = rs.getString("davamiyet");
                System.out.println(ad + "-" + soyad + "-" + ata_adi + "->" + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public void bali_daxilET() {
        HikariDataSource ds = getHikariDataSource();
        System.out.println("Bali daxil edin: ");
        int bal = scanner.nextInt();
        System.out.println("Telebenin id-si: ");
        int id = scanner.nextInt();
        String sql = "INSERT INTO sinaq_ballari(BAL, student_id) VALUES (?,?);";
        try (Connection con = ds.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, bal);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }


    public void ballari_goster() {
        String sql = "SELECT n.name, " +
                "       n.surname, " +
                "       n.f_name, " +
                "       s.bal " +
                "FROM N_S_FN n " +
                "JOIN sinaq_ballari s ON s.student_id = n.id ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String ad = rs.getString("name");
                String soyad = rs.getString("surname");
                String ata_adi = rs.getString("f_name");
                int status = rs.getInt("bal");
                System.out.println(ad + "-" + soyad + "-" + ata_adi + "->" + status);
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public boolean user_pass_check() {
        String sql = "SELECT EXISTS ( " +
                "    SELECT 1 " +
                "    FROM teacher " +
                "    WHERE username = ? AND password = ? " +
                ")";
        HikariDataSource ds = getHikariDataSource();
        System.out.println("Usernanme: ");
        String username = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getBoolean(1)) {
                System.out.println("daxil oldunuz");
                return true;
            } else {
                System.out.println("sizin hesabiniz yoxdur");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());

        }
        return false;


    }

    public boolean register_user_pass_check(String ad, String username, String password) {
        String sql = "SELECT EXISTS ( " +
                "    SELECT 1 " +
                "    FROM teacher " +
                "    WHERE username = ? AND password = ? " +
                ")";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next() && rs.getBoolean(1)) {
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

    public void register_user_pass_addDB(String ad, String soyad, String username, String password) {
        String sql = "insert into teacher(name,surname,username,password)values(?,?,?,?) ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
        ) {
            stm.setString(1, ad);
            stm.setString(2, soyad);
            stm.setString(3, username);
            stm.setString(4, password);
            stm.executeUpdate();
            System.out.println("Ugurla qeydiyatdan kecdiniz.");
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }

    }

    public void Derslerik_linkini_goster() {
        String sql = "select * from lesson;";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);
             ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                String lesson = rs.getString("link");
                System.out.println("Ders linki -> " + lesson);
            }
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }

    public void Derslerik_linkini_ELAVE_ET() {
        String sql = "insert into lesson(link)values(?) ";
        HikariDataSource ds = getHikariDataSource();
        try (Connection con = ds.getConnection();
             PreparedStatement stm = con.prepareStatement(sql);) {
            String link = scanner.next();
            stm.setString(1, link);
        } catch (SQLException e) {
            System.out.println("Xeta: " + e.getMessage());
        }
    }


}
