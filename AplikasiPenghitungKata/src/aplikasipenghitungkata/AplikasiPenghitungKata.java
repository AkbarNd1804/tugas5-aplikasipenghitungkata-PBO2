/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasipenghitungkata;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AplikasiPenghitungKata extends JFrame {
    
    // Komponen GUI
    private JTextArea textArea;
    private JLabel labelJumlahKata;
    private JLabel labelJumlahKarakter;
    private JLabel labelJumlahKalimat;
    private JLabel labelJumlahParagraf;
    private JButton btnHitung;
    private JButton btnSimpan;
    private JButton btnClear;
    private JTextField txtCariKata;
    private JButton btnCari;
    private JLabel labelHasilPencarian;
    private JCheckBox chkRealTime;
    
    public AplikasiPenghitungKata() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Aplikasi Penghitung Kata");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        
        // Panel Utama
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(52, 152, 219));
        JLabel lblTitle = new JLabel("APLIKASI PENGHITUNG KATA");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        headerPanel.add(lblTitle);
        
        // Panel Input Teks
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Teks"));
        
        textArea = new JTextArea(15, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        inputPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel Kontrol
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnHitung = new JButton("Hitung");
        btnHitung.setFont(new Font("Arial", Font.BOLD, 12));
        btnHitung.setBackground(new Color(46, 204, 113));
        btnHitung.setForeground(Color.WHITE);
        btnHitung.setFocusPainted(false);
        
        btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.BOLD, 12));
        btnClear.setBackground(new Color(231, 76, 60));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        
        btnSimpan = new JButton("Simpan ke File");
        btnSimpan.setFont(new Font("Arial", Font.BOLD, 12));
        btnSimpan.setBackground(new Color(52, 152, 219));
        btnSimpan.setForeground(Color.WHITE);
        btnSimpan.setFocusPainted(false);
        
        chkRealTime = new JCheckBox("Hitung Real-Time");
        chkRealTime.setFont(new Font("Arial", Font.PLAIN, 12));
        
        controlPanel.add(btnHitung);
        controlPanel.add(btnClear);
        controlPanel.add(btnSimpan);
        controlPanel.add(chkRealTime);
        inputPanel.add(controlPanel, BorderLayout.SOUTH);
        
        // Panel Hasil
        JPanel hasilPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        hasilPanel.setBorder(BorderFactory.createTitledBorder("Hasil Perhitungan"));
        hasilPanel.setBackground(Color.WHITE);
        
        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font hasilFont = new Font("Arial", Font.PLAIN, 13);
        
        JLabel lblKata = new JLabel("Jumlah Kata:");
        lblKata.setFont(labelFont);
        labelJumlahKata = new JLabel("0");
        labelJumlahKata.setFont(hasilFont);
        labelJumlahKata.setForeground(new Color(52, 152, 219));
        
        JLabel lblKarakter = new JLabel("Jumlah Karakter:");
        lblKarakter.setFont(labelFont);
        labelJumlahKarakter = new JLabel("0");
        labelJumlahKarakter.setFont(hasilFont);
        labelJumlahKarakter.setForeground(new Color(52, 152, 219));
        
        JLabel lblKalimat = new JLabel("Jumlah Kalimat:");
        lblKalimat.setFont(labelFont);
        labelJumlahKalimat = new JLabel("0");
        labelJumlahKalimat.setFont(hasilFont);
        labelJumlahKalimat.setForeground(new Color(52, 152, 219));
        
        JLabel lblParagraf = new JLabel("Jumlah Paragraf:");
        lblParagraf.setFont(labelFont);
        labelJumlahParagraf = new JLabel("0");
        labelJumlahParagraf.setFont(hasilFont);
        labelJumlahParagraf.setForeground(new Color(52, 152, 219));
        
        hasilPanel.add(lblKata);
        hasilPanel.add(labelJumlahKata);
        hasilPanel.add(lblKarakter);
        hasilPanel.add(labelJumlahKarakter);
        hasilPanel.add(lblKalimat);
        hasilPanel.add(labelJumlahKalimat);
        hasilPanel.add(lblParagraf);
        hasilPanel.add(labelJumlahParagraf);
        
        // Panel Pencarian
        JPanel pencarianPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pencarianPanel.setBorder(BorderFactory.createTitledBorder("Pencarian Kata"));
        
        JLabel lblCari = new JLabel("Cari Kata:");
        lblCari.setFont(labelFont);
        txtCariKata = new JTextField(20);
        txtCariKata.setFont(new Font("Arial", Font.PLAIN, 12));
        
        btnCari = new JButton("Cari");
        btnCari.setFont(new Font("Arial", Font.BOLD, 12));
        btnCari.setBackground(new Color(241, 196, 15));
        btnCari.setFocusPainted(false);
        
        labelHasilPencarian = new JLabel("Hasil: 0 kata ditemukan");
        labelHasilPencarian.setFont(hasilFont);
        labelHasilPencarian.setForeground(new Color(52, 73, 94));
        
        pencarianPanel.add(lblCari);
        pencarianPanel.add(txtCariKata);
        pencarianPanel.add(btnCari);
        pencarianPanel.add(labelHasilPencarian);
        
        hasilPanel.add(pencarianPanel);
        
        // Panel Bawah (Hasil + Pencarian)
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.add(hasilPanel, BorderLayout.NORTH);
        bottomPanel.add(pencarianPanel, BorderLayout.CENTER);
        
        // Tambahkan ke Main Panel
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Tambahkan ke Frame
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        
        // Event Listeners
        setupEventListeners();
    }
    
    private void setupEventListeners() {
        // ActionListener untuk tombol Hitung
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTeks();
            }
        });
        
        // ActionListener untuk tombol Clear
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAll();
            }
        });
        
        // ActionListener untuk tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanKeFile();
            }
        });
        
        // ActionListener untuk tombol Cari
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariKata();
            }
        });
        
        // DocumentListener untuk perhitungan real-time
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (chkRealTime.isSelected()) {
                    hitungTeks();
                }
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                if (chkRealTime.isSelected()) {
                    hitungTeks();
                }
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                if (chkRealTime.isSelected()) {
                    hitungTeks();
                }
            }
        });
    }
    
    // Logika Program: Manipulasi String dan Ekspresi Reguler
    private void hitungTeks() {
        String teks = textArea.getText();
        
        if (teks.trim().isEmpty()) {
            labelJumlahKata.setText("0");
            labelJumlahKarakter.setText("0");
            labelJumlahKalimat.setText("0");
            labelJumlahParagraf.setText("0");
            return;
        }
        
        // Hitung jumlah kata
        int jumlahKata = hitungKata(teks);
        labelJumlahKata.setText(String.valueOf(jumlahKata));
        
        // Hitung jumlah karakter (dengan spasi)
        int jumlahKarakter = teks.length();
        labelJumlahKarakter.setText(jumlahKarakter + " (dengan spasi)");
        
        // Hitung jumlah kalimat
        int jumlahKalimat = hitungKalimat(teks);
        labelJumlahKalimat.setText(String.valueOf(jumlahKalimat));
        
        // Hitung jumlah paragraf
        int jumlahParagraf = hitungParagraf(teks);
        labelJumlahParagraf.setText(String.valueOf(jumlahParagraf));
    }
    
    private int hitungKata(String teks) {
        // Menggunakan ekspresi reguler untuk menghitung kata
        String trimmedText = teks.trim();
        if (trimmedText.isEmpty()) {
            return 0;
        }
        String[] kata = trimmedText.split("\\s+");
        return kata.length;
    }
    
    private int hitungKalimat(String teks) {
        // Menghitung kalimat berdasarkan tanda baca . ! ?
        Pattern pattern = Pattern.compile("[.!?]+");
        Matcher matcher = pattern.matcher(teks);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
    
    private int hitungParagraf(String teks) {
        // Menghitung paragraf berdasarkan baris kosong
        String[] paragraf = teks.split("\\n\\s*\\n");
        int count = 0;
        for (String p : paragraf) {
            if (!p.trim().isEmpty()) {
                count++;
            }
        }
        return count == 0 && !teks.trim().isEmpty() ? 1 : count;
    }
    
    private void cariKata() {
        String kataCari = txtCariKata.getText().trim();
        String teks = textArea.getText().toLowerCase();
        
        if (kataCari.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Silakan masukkan kata yang ingin dicari!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Mencari kata dengan ekspresi reguler
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(kataCari.toLowerCase()) + "\\b");
        Matcher matcher = pattern.matcher(teks);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        
        labelHasilPencarian.setText("Hasil: " + count + " kata '" + kataCari + "' ditemukan");
    }
    
    private void simpanKeFile() {
        String teks = textArea.getText();
        
        if (teks.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tidak ada teks untuk disimpan!", 
                "Peringatan", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Teks dan Hasil Perhitungan");
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (FileWriter writer = new FileWriter(fileToSave + ".txt")) {
                writer.write("=== TEKS ASLI ===\n");
                writer.write(teks);
                writer.write("\n\n=== HASIL PERHITUNGAN ===\n");
                writer.write("Jumlah Kata: " + labelJumlahKata.getText() + "\n");
                writer.write("Jumlah Karakter: " + labelJumlahKarakter.getText() + "\n");
                writer.write("Jumlah Kalimat: " + labelJumlahKalimat.getText() + "\n");
                writer.write("Jumlah Paragraf: " + labelJumlahParagraf.getText() + "\n");
                
                JOptionPane.showMessageDialog(this, 
                    "File berhasil disimpan!", 
                    "Sukses", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Gagal menyimpan file: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void clearAll() {
        textArea.setText("");
        labelJumlahKata.setText("0");
        labelJumlahKarakter.setText("0");
        labelJumlahKalimat.setText("0");
        labelJumlahParagraf.setText("0");
        txtCariKata.setText("");
        labelHasilPencarian.setText("Hasil: 0 kata ditemukan");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplikasiPenghitungKata().setVisible(true);
            }
        });
    }
}
