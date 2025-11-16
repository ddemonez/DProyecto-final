package vista;

import controlador.ControladorReparaciones;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaReparaciones extends JFrame {

    public JTextField txtCodigo, txtNombre, txtModelo, txtTipo, txtCosto, txtFecha;
    public JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnCargar, btnReporte;
    public JTable tabla;

    public VistaReparaciones() {
        setTitle("Moto Reparaciones Calel");
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // === Fondo general ===
        getContentPane().setBackground(new Color(245, 245, 245));

        // === Título ===
        JLabel lblTitulo = new JLabel("Taller Mecánico - Moto Reparaciones Calel", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(30, 30, 70));
        lblTitulo.setBounds(150, 10, 500, 30);
        add(lblTitulo);

        // === Panel formulario ===
        JPanel panelForm = new JPanel();
        panelForm.setLayout(null);
        panelForm.setBackground(new Color(255, 255, 255));
        panelForm.setBorder(new TitledBorder("Datos de Reparación"));
        panelForm.setBounds(20, 60, 350, 250);
        add(panelForm);

        // === Campos ===
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(20, 30, 100, 25);
        panelForm.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(130, 30, 180, 25);
        panelForm.add(txtCodigo);

        JLabel lblNombre = new JLabel("Cliente:");
        lblNombre.setBounds(20, 65, 100, 25);
        panelForm.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 65, 180, 25);
        panelForm.add(txtNombre);

        JLabel lblModelo = new JLabel("Modelo moto:");
        lblModelo.setBounds(20, 100, 100, 25);
        panelForm.add(lblModelo);

        txtModelo = new JTextField();
        txtModelo.setBounds(130, 100, 180, 25);
        panelForm.add(txtModelo);

        JLabel lblTipo = new JLabel("Tipo servicio:");
        lblTipo.setBounds(20, 135, 100, 25);
        panelForm.add(lblTipo);

        txtTipo = new JTextField();
        txtTipo.setBounds(130, 135, 180, 25);
        panelForm.add(txtTipo);

        JLabel lblCosto = new JLabel("Costo:");
        lblCosto.setBounds(20, 170, 100, 25);
        panelForm.add(lblCosto);

        txtCosto = new JTextField();
        txtCosto.setBounds(130, 170, 180, 25);
        panelForm.add(txtCosto);

        JLabel lblFecha = new JLabel("Fecha ingreso:");
        lblFecha.setBounds(20, 205, 100, 25);
        panelForm.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(130, 205, 180, 25);
        panelForm.add(txtFecha);

        // === Panel botones ===
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(6, 1, 5, 8)); // Aumentamos a 6 filas
        panelBotones.setBounds(400, 70, 150, 260);
        panelBotones.setBackground(new Color(245, 245, 245));
        add(panelBotones);

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        btnCargar = new JButton("Cargar datos");

        JButton[] botones = {btnAgregar, btnActualizar, btnEliminar, btnLimpiar, btnCargar};
        for (JButton b : botones) {
            b.setBackground(new Color(66, 133, 244));
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 13));
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            panelBotones.add(b);
        }

        // === Botón de reporte ===
        btnReporte = new JButton("Generar Reporte PDF");
        btnReporte.setBackground(new Color(255, 0, 0)); // rojo
        btnReporte.setForeground(Color.WHITE);
        btnReporte.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnReporte.setFocusPainted(false);
        btnReporte.setBorderPainted(false);


        try {
            btnReporte.setIcon(new ImageIcon(getClass().getResource("/imagenes/pdf_icon.png")));
            btnReporte.setHorizontalAlignment(SwingConstants.LEFT);
        } catch (Exception e) {
            System.out.println("No se encontró el icono PDF (opcional).");
        }

        panelBotones.add(btnReporte);

        // === Tabla ===
        tabla = new JTable(new DefaultTableModel(
                new Object[]{"Código", "Cliente", "Modelo", "Servicio", "Costo", "Fecha"}, 0
        ));
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tabla.setRowHeight(25);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(220, 220, 220));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 330, 740, 160);
        add(scroll);

        // === Controlador ===
        new ControladorReparaciones(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaReparaciones().setVisible(true));
    }
}
