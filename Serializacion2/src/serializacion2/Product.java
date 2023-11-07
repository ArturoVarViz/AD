/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializacion2;

import java.io.Serializable;

/**
 *
 * @author arturo
 */
public class Product implements Serializable{
     String cod;
    String desc;
    int prezo;

    // Constructor de la clase Product
    
    public Product() {
    }

    Product(String cod, String desc, int prezo) {
        this.cod = cod;
        this.desc = desc;
        this.prezo = prezo;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrezo() {
        return prezo;
    }

    public void setPrezo(int prezo) {
        this.prezo = prezo;
    }

    // Método toString para imprimir los detalles del producto
    public String toString() {
        return "Código: " + cod + ", Descripción: " + desc + ", Precio: " + prezo;
    }
}
