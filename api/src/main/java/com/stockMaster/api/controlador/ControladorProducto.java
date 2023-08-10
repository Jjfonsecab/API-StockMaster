package com.stockMaster.api.controlador;

import com.stockMaster.api.modelo.Producto;
import com.stockMaster.api.modelo.RepositorioProducto;
import com.stockMaster.api.vista.Principal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ControladorProducto implements ActionListener {

    RepositorioProducto repoProducto ;
    Principal vista;
    DefaultTableModel defaultTableModel;

    private int codigo;
    private String nombre;
    private int precio;
    private int inventario;
    private String proveedor;

    public ControladorProducto() {
        super();
    }
    public ControladorProducto(RepositorioProducto repoProducto, Principal vista) {
        super();
        this.repoProducto = repoProducto;
        this.vista = vista;
        vista.setVisible(true);
        agregarEventos();
        listarTabla();
    }

    private void agregarEventos(){
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnActualizar().addActionListener(this);
        vista.getBtnBorrar().addActionListener(this);
        vista.getBtnInforme().addActionListener(this);
        vista.getTblTabla().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }
    public void listarTabla(){
        String[] titulos = new String[]{"id","Nombre","Precio","Inventario","Proveedor"};
        defaultTableModel = new DefaultTableModel(titulos, 0);
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();
        for (Producto producto : listaProductos) {
            defaultTableModel.addRow(new Object[]{producto.getCodigo(),
            producto.getNombre(),producto.getPrecio(), producto.getInventario(), producto.getProveedor()});
        }
        vista.getTblTabla().setModel(defaultTableModel);
        vista.getTblTabla().setPreferredSize(new Dimension(350, defaultTableModel.getRowCount()*16));
    }
    private void llenarCampos(MouseEvent e){
        JTable target = (JTable) e.getSource();
        vista.getTxtCodigo().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 0).toString());
        vista.getTxtNombre().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vista.getTxtPrecio().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vista.getTxtInventario().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        vista.getTxtProveedor().setText(vista.getTblTabla().getModel().getValueAt(target.getSelectedRow(), 4).toString());
    }

    private boolean validarDatos(){
        if ("".equals(vista.getTxtNombre().getText()) || "".equals(vista.getTxtPrecio().getText()) || "".equals(vista.getTxtInventario().getText()) || "".equals(vista.getTxtProveedor().getText())){
            JOptionPane.showMessageDialog(null, "Los campos no pueden ser vacios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    private boolean cargarDatos(){
        try {
            codigo = Integer.parseInt("".equals(vista.getTxtCodigo().getText()) ? "0" : vista.getTxtCodigo().getText());
            nombre = vista.getTxtNombre().getText();
            precio = Integer.parseInt(vista.getTxtPrecio().getText());
            inventario = Integer.parseInt(vista.getTxtInventario().getText());
            proveedor = vista.getTxtProveedor().getText();
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private void limpiarCampos(){
        vista.getTxtCodigo().setText("");
        vista.getTxtNombre().setText("");
        vista.getTxtPrecio().setText("");
        vista.getTxtInventario().setText("");
        vista.getTxtProveedor().setText("");
    }

    private void agregarProducto(){
        try {
            if (validarDatos()){
                if (cargarDatos()){
                    Producto producto = new Producto(nombre, precio, inventario, proveedor);
                    repoProducto.save(producto);
                    JOptionPane.showMessageDialog(null, "Producto agregado con exito.!");
                    limpiarCampos();
                }else {
                    JOptionPane.showMessageDialog(null,"Los campos precio e inventario deben ser numericos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (Exception e){ //Ojo puede ser necesario cambiar el Exception por un DbActionExecutionException
            JOptionPane.showMessageDialog(null, "El producto ya existe.!");
        }finally {
            listarTabla();
        }
    }
    private void actualizarProducto(){
        try {
            if (validarDatos()){
                if (cargarDatos()){
                    Producto producto = new Producto(codigo, nombre, precio, inventario, proveedor);
                    repoProducto.save(producto);
                    JOptionPane.showMessageDialog(null, "Producto actualizado con exito.!");
                    limpiarCampos();
                }else {
                    JOptionPane.showMessageDialog(null,"Los campos precio e inventario deben ser numericos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (Exception e){ //Ojo puede ser necesario cambiar el Exception por un DbActionExecutionException
            JOptionPane.showMessageDialog(null, "Ocurrio un error, el producto ya existe!");
        }finally {
            listarTabla();
        }
    }
    private void borrarProducto(){
        try {
            if (validarDatos()){
                if (cargarDatos()){
                    Producto producto = new Producto(codigo, nombre,  precio, inventario, proveedor);
                    repoProducto.delete(producto);
                    JOptionPane.showMessageDialog(null, "Producto borrado con exito.!");
                    limpiarCampos();
                }
            }
        }catch (Exception e){ //Ojo puede ser necesario cambiar el Exception por un DbActionExecutionException
            JOptionPane.showMessageDialog(null, "Ocurrio un error, no se pudo eliminar.!");
        }finally {
            listarTabla();
        }
    }
    private void generarInforme(){
        String info = "";
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();

        double precioMayor = Double.MIN_VALUE;
        String nombreMayor = "";

        double precioMenor = Double.MAX_VALUE;
        String nombreMenor = "";

        double sumaPrecios = 0;

        for (Producto producto : listaProductos) {
            if (producto.getPrecio() > precioMayor) {
                precioMayor = producto.getPrecio();
                nombreMayor = producto.getNombre();
            }

            if (producto.getPrecio() < precioMenor) {
                precioMenor = producto.getPrecio();
                nombreMenor = producto.getNombre();
            }

            sumaPrecios += producto.getPrecio();
        }

        double promedio = sumaPrecios / listaProductos.size();

        info += "Producto con el precio más alto: " + nombreMayor + " ($" + precioMayor + ")\n";
        info += "Producto con el precio más bajo: " + nombreMenor + " ($" + precioMenor + ")\n";
        info += "Promedio de precios: $" + promedio + "\n";
        info += "Valor total del inventario: $" + totalInvetnario();

        JOptionPane.showMessageDialog(null, info, "Informe de Productos", JOptionPane.INFORMATION_MESSAGE);
    }

    private String precioMayor(){
        String nombre = "";
        double precioAux = 0;
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();
        for (Producto producto: listaProductos) {
            if (producto.getPrecio() > precioAux){
                nombre = producto.getNombre();
                precioAux = producto.getPrecio();
            }
        }
        return nombre;
    }
    private String precioMenor(){
        String nombre = "";
        double precioAux = Double.MAX_VALUE;
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();
        for (Producto producto: listaProductos) {
            if (producto.getPrecio() < precioAux){
                nombre = producto.getNombre();
                precioAux = producto.getPrecio();
            }
        }
        return nombre;
    }
    private String precioPromedio(){
        double suma = 0;
        double resultado = 0;
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();
        for (Producto producto: listaProductos) {
            suma += producto.getPrecio();
        }
        resultado = suma / listaProductos.size();
        return String.format("%.2f", resultado);
    }
    private String totalInvetnario() {
        double suma = 0;
        double resultado = 0;
        List<Producto> listaProductos = (List<Producto>) repoProducto.findAll();
        for (Producto producto: listaProductos) {
            suma = producto.getPrecio() * producto.getInventario();
            resultado += suma;
        }
        return String.format("%.2f", resultado);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnAgregar()){
            agregarProducto();
        }
        if (ae.getSource() == vista.getBtnActualizar()){
            actualizarProducto();
        }
        if (ae.getSource() == vista.getBtnBorrar()){
            borrarProducto();
        }
        if (ae.getSource() == vista.getBtnInforme()){
            generarInforme();
        }
    }
}
