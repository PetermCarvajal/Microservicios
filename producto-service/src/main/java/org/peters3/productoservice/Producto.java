package org.peters3.productoservice;

import jakarta.persistence.*;

@Table(name = "productos")
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String precio;
    private String codigo;
    private String vlrUnitario;
    private String vlrBruto;
    private String descuento;
    private String valorTotal;

    private Integer iva;
    private Integer stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getVlrUnitario() {
        return vlrUnitario;
    }

    public void setVlrUnitario(String vlrUnitario) {
        this.vlrUnitario = vlrUnitario;
    }

    public String getVlrBruto() {
        return vlrBruto;
    }

    public void setVlrBruto(String vlrBruto) {
        this.vlrBruto = vlrBruto;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("""
               Producto:
               Id: %d
               Nombre: %s
               Descripcion: %s
               Precio: %s
               Codigo: %s
               VlrUnitario: %s
               VlrBruto: %s
               Descuento: %s
               ValorTotal: %s
               Iva: %d
               Stock: %d
               """,id,nombre,descripcion,precio,codigo,vlrUnitario,vlrBruto,descuento,valorTotal);
    }

}