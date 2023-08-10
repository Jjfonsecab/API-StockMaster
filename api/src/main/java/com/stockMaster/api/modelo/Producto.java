package com.stockMaster.api.modelo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "productos")
@Getter
@Setter
public class Producto {
    @Id
    @Column("codigo")
    private int codigo;
    @Column("producto")
    private String nombre;
    @Column("precio")
    private int precio;
    @Column("cantidad")
    private int inventario;
    @Column("proveedor")
    private String proveedor;


    public Producto() {
    }

    public Producto(String nombre, int precio, int inventario, String proveedor) {
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.proveedor = proveedor;
    }

    public Producto(int codigo, String nombre, int precio, int inventario, String proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
        this.proveedor = proveedor;
    }

}
