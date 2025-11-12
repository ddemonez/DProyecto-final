package controlador;

import modelo.Reparacion;
import modelo.ReparacionDAO;
import vista.VistaReparaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ControladorReparaciones implements ActionListener {
    private VistaReparaciones vista;
    private ReparacionDAO dao;

    public ControladorReparaciones(VistaReparaciones vista) {
        this.vista = vista;
        this.dao = new ReparacionDAO();

        vista.btnAgregar.addActionListener(this);
        vista.btnActualizar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnLimpiar.addActionListener(this);
        vista.btnCargar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregar();
        } else if (e.getSource() == vista.btnActualizar) {
            actualizar();
        } else if (e.getSource() == vista.btnEliminar) {
            eliminar();
        } else if (e.getSource() == vista.btnCargar) {
            cargarTabla();
        } else if (e.getSource() == vista.btnLimpiar) {
            limpiarCampos();
        }
    }

    private void agregar() {
        try {
            Reparacion r = new Reparacion();
            r.setNombre_cliente(vista.txtNombre.getText());
            r.setModelo_moto(vista.txtModelo.getText());
            r.setTipo_servicio(vista.txtTipo.getText());
            r.setCosto(Double.parseDouble(vista.txtCosto.getText()));
            r.setFecha_ingreso(vista.txtFecha.getText());

            if (dao.insertar(r)) {
                JOptionPane.showMessageDialog(vista, "Registro agregado correctamente.");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al agregar registro.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Datos inválidos: " + ex.getMessage());
        }
    }

    private void actualizar() {
        try {
            Reparacion r = new Reparacion();
            r.setCodigo_servicio(Integer.parseInt(vista.txtCodigo.getText()));
            r.setNombre_cliente(vista.txtNombre.getText());
            r.setModelo_moto(vista.txtModelo.getText());
            r.setTipo_servicio(vista.txtTipo.getText());
            r.setCosto(Double.parseDouble(vista.txtCosto.getText()));
            r.setFecha_ingreso(vista.txtFecha.getText());

            if (dao.actualizar(r)) {
                JOptionPane.showMessageDialog(vista, "Registro actualizado correctamente.");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al actualizar registro.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }

    private void eliminar() {
        try {
            int codigo = Integer.parseInt(vista.txtCodigo.getText());
            if (dao.eliminar(codigo)) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado.");
                cargarTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Debe ingresar un código válido.");
        }
    }

    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        modelo.setRowCount(0);
        List<Reparacion> lista = dao.listar();
        for (Reparacion r : lista) {
            modelo.addRow(new Object[]{
                    r.getCodigo_servicio(),
                    r.getNombre_cliente(),
                    r.getModelo_moto(),
                    r.getTipo_servicio(),
                    r.getCosto(),
                    r.getFecha_ingreso()
            });
        }
    }

    private void limpiarCampos() {
        vista.txtCodigo.setText("");
        vista.txtNombre.setText("");
        vista.txtModelo.setText("");
        vista.txtTipo.setText("");
        vista.txtCosto.setText("");
        vista.txtFecha.setText("");
    }
}
