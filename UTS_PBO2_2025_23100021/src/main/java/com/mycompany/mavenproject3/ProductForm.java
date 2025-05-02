package com.mycompany.mavenproject3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductForm extends JFrame {
    private JTable productTable;
    private DefaultTableModel tableModel;
    private JTextField codeField;
    private JTextField nameField;
    private JComboBox<String> categoryField;
    private JTextField priceField;
    private JTextField stockField;
    private JButton saveButton;
    private List<Product> productList; // Menyimpan daftar produk

    public ProductForm() {
        productList = new ArrayList<>();
        // Inisialisasi data awal
        productList.add(new Product("P001", "Americano", "Coffee", 18000, 10));
        productList.add(new Product("P002", "Pandan Latte", "Coffee", 15000, 8));

        setTitle("WK. Cuan | Stok Barang");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Tutup hanya jendela ini
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.add(new JLabel("Kode Barang:"));
        codeField = new JTextField();
        formPanel.add(codeField);

        formPanel.add(new JLabel("Nama Barang:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Kategori:"));
        categoryField = new JComboBox<>(new String[]{"Coffee", "Dairy", "Juice", "Soda", "Tea"});
        formPanel.add(categoryField);

        formPanel.add(new JLabel("Harga Jual:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        formPanel.add(new JLabel("Stok Tersedia:"));
        stockField = new JTextField();
        formPanel.add(stockField);

        formPanel.add(new JLabel(""));
        saveButton = new JButton("Simpan");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewProduct();
            }
        });
        formPanel.add(saveButton);

        tableModel = new DefaultTableModel(new String[]{"Kode", "Nama", "Kategori", "Harga Jual", "Stok"}, 0);
        productTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(productTable);

        loadProductData(); // Load data dari productList

        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadProductData() {
        tableModel.setRowCount(0); // Bersihkan tabel sebelum memuat ulang data
        for (Product product : productList) {
            tableModel.addRow(new Object[]{
                    product.getCode(), product.getName(), product.getCategory(), product.getPrice(), product.getStock()
            });
        }
    }

    private void addNewProduct() {
        String code = codeField.getText();
        String name = nameField.getText();
        String category = (String) categoryField.getSelectedItem();
        String priceStr = priceField.getText();
        String stockStr = stockField.getText();

        // Validasi input sederhana
        if (code.isEmpty() || name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            Product newProduct = new Product(code, name, category, price, stock);
            productList.add(newProduct);
            loadProductData(); // Refresh tabel
            clearInputFields();
            JOptionPane.showMessageDialog(this, "Produk berhasil ditambahkan.", "Sukses", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga dan Stok harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearInputFields() {
        codeField.setText("");
        nameField.setText("");
        priceField.setText("");
        stockField.setText("");
        categoryField.setSelectedIndex(0); // Reset ke item pertama
    }
}