/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.praktikumadpl3_database.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abedann
 */
public class Alat {
    private final String kode;
    private final String nama;
    private final int jumlah;
    private final String kondisi;
    
    public Alat(
        String kode, String nama, int jumlah, String kondisi
    ) {
        this.kode = kode;
        this.nama = nama;
        this.jumlah = jumlah;
        this.kondisi = kondisi;
    }
    
    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public String getKondisi() {
        return kondisi;
    }
    //Tambah data
    public void insert() throws SQLException{
        String sql = "INSERT INTO alat_lab (kode_alat, nama_alat, jumlah, kondisi)"
                + "VALUES (?, ?, ?, ?)";
        
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setInt(3, jumlah);
            ps.setString(4, kondisi);
            ps.executeUpdate();
        } 
    }
    //Lihat Data
    public static List<Alat> getAll() throws SQLException {
        String sql = "SELECT kode_alat, nama_alat, jumlah, kondisi "
                + "FROM alat_lab ORDER BY kode_alat";
        List<Alat> daftarAlat = new ArrayList<>();
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                daftarAlat.add(new Alat(
                        rs.getString("kode_alat"),
                        rs.getString("nama_alat"),
                        rs.getInt("jumlah"),
                        rs.getString("kondisi")));
            }
        }
        return daftarAlat;
    }
    //Ubah Data
    public void update() throws SQLException {
        String sql = "UPDATE alat_lab SET nama_alat = ?, jumlah = ?, kondisi = ? "
                + "WHERE kode_alat = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nama);
            ps.setInt(2, jumlah);
            ps.setString(3, kondisi);
            ps.setString(4, kode);
            ps.executeUpdate();
        }
    }
    //Hapus Data
    public static void delete(String kode) throws SQLException {
        String sql = "DELETE FROM alat_lab WHERE kode_alat = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        }
    }
}
