/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serializacion2;

// Importas as clases necesarias para a serialización e deserialización de obxectos.
import java.io.*;



public class Serializacion2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Definimos los arrays de datos
        String[] cod = {"p1", "p2", "p3"};
        String[] desc = {"parafusos", "cravos", "tachas"};
        int[] prezo = {3, 4, 5};

        // Creamos un flujo de salida para escribir los datos en un archivo
        FileOutputStream fos = new FileOutputStream("products.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        // Creamos y escribimos los objetos Product en el archivo
        for (int i = 0; i < cod.length; i++) {
            Product product = new Product(cod[i], desc[i], prezo[i]);
            oos.writeObject(product);
        }

        // Escribimos un null en el archivo antes de cerrarlo
        oos.writeObject(null);
        oos.close();

        // Creamos un flujo de entrada para leer los datos del archivo
        FileInputStream fis = new FileInputStream("products.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        // Leemos e imprimimos los objetos Product del archivo hasta que llegamos al null
        Object obj;
        while ((obj = ois.readObject()) != null) {
            System.out.println(obj);
        }

        // Cerramos el flujo de entrada
        ois.close();
    }
}
