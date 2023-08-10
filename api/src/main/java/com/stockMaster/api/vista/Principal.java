package com.stockMaster.api.vista;

import lombok.Getter;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

@Getter
public class Principal extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtInventario;
    private JTextField txtProveedor;
    private JButton btnAgregar;
    private JTable tblTabla;
    private JButton btnInforme;
    private JButton btnBorrar;
    private JButton btnActualizar;
    private JPanel panel;

    public Principal() {
        setTitle("StockMaster");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initComponents() {
        // Crear los componentes
        int textFieldWidth = 10;
        txtCodigo = new JTextField(textFieldWidth);
        txtNombre = new JTextField(textFieldWidth);
        txtPrecio = new JTextField(textFieldWidth);
        txtInventario = new JTextField(textFieldWidth);
        txtProveedor = new JTextField(textFieldWidth);
        btnAgregar = new JButton("Agregar");
        tblTabla = new JTable();
        btnInforme = new JButton("Generar Informe");
        btnBorrar = new JButton("Borrar");
        btnActualizar = new JButton("Actualizar");

        class UpperCaseDocumentFilter extends DocumentFilter {
            @Override
            public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
                super.insertString(fb, offset, text.toUpperCase(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                super.replace(fb, offset, length, text.toUpperCase(), attrs);
            }
        }

        txtCodigo.setEnabled(false);

        DocumentFilter upperCaseFilter = new UpperCaseDocumentFilter();

        ((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(upperCaseFilter);
        ((AbstractDocument) txtProveedor.getDocument()).setDocumentFilter(upperCaseFilter);

        // Crear el panel principal y establecer el diseño
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(tblTabla);

        // Agregar los componentes al panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Código:", JLabel.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Nombre:", JLabel.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Precio:", JLabel.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(txtPrecio, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Cantidad:", JLabel.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(txtInventario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Proveedor:", JLabel.RIGHT), gbc);
        gbc.gridx = 1;
        formPanel.add(txtProveedor, gbc);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnAgregar);
        buttonPanel.add(btnInforme);
        buttonPanel.add(btnBorrar);
        buttonPanel.add(btnActualizar);

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);



        // Agregar el panel principal a la ventana
        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Principal();
        });
    }
}
