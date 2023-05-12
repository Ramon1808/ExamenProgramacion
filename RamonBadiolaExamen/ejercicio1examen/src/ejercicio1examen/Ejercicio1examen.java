/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1examen;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author DAW
 */
public class Ejercicio1examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        try {

            //Primero de todo cargamos el driver que vamos a utilizar.
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            //Realizamos la conexión con la base de datos creada
            Connection conexion = DriverManager.getConnection("jdbc:mysql://10.230.108.207:3306/mysql?serverTimezone=UTC", "root", "");

            //Comprobamos si hemos establecido bien la conexión.
            if (conexion.isClosed()) {
                System.out.println("No se ha establecido la conexión");
            }
        

            //Aquí crearemos el menú para la agenda.
            int salir = 0;
            
            //int numero_clave=1;

            Scanner lector = new Scanner(System.in);

            while (salir == 0) {

                System.out.println("Bienvenido a su agenda, ¿en qué puedo ayudarle?\n"
                        + "Ejecute \"mostrar\" para mostrar sus contactos.\n"
                        + "Ejecute \"agendar\" para añadir un contacto.\n"
                        + "Ejecute \"editar\" para editar un contacto.\n"
                        + "Ejecute \"borrar\" para eliminar un contacto.\n"
                        + "Ejecute \"salir\" para salir de la agenda.");
                
                

                String opcion = lector.nextLine();

                switch (opcion) {
                    case "mostrar": {

                        //Creamos el objeto Statement que nos permitirá ejecutar las sentencias.
                        Statement ejecutor = conexion.createStatement();

                        boolean ex = ejecutor.execute("USE AGENDA;");
                        

                        //Realizamos ya la consulta que nos va a dar los datos de la tabla.
                        ResultSet consulta = ejecutor.executeQuery("select * from contactos;");

                        while (consulta.next()) {
                            int clave = consulta.getInt("id");
                            String nombre = consulta.getString("nombre");
                            int telefono = consulta.getInt("telefono");
                            System.out.println("id: " + clave + " nombre: " + nombre + ", teléfono: " + telefono);
                        }
                        
                        ejecutor.close();
                        consulta.close();

                        break;
                    }
                    
                    
                    case "agendar": {
                        
                        //Creamos el objeto Statement que nos permitirá registrar nuevas filas.
                        Statement ejecutor = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        
                        boolean ex = ejecutor.execute("USE AGENDA;");
                        
                        
                        //Ejecutamos un select para obtener todos los datos.
                        
                        String tablaentera = "select * from contactos";
                        
                        
                        //Realizamos ya la consulta que nos va a dar los datos de los id usados.
                        ResultSet consulta = ejecutor.executeQuery("select * from contactos;");

                        while (consulta.next()) {
                            int clave = consulta.getInt("id");                            
                            System.out.println("id usados: " + clave);
                        }
                        
                        //comprobamos el último id para poner el siguiente:
                        
                        
                        
                        //Creamos el nuevo registro y lo insertamos;
                        
                        int clave;
                        String nombre;
                        int numero;
                        
                        lector.nextLine();
                        System.out.println("Indique la clave del contacto");
                        
                        clave=lector.nextInt();
                        
                        lector.nextLine();

                        System.out.println("Indique el nombre del contacto:");
                        
                        nombre=lector.nextLine();
                        
                        lector.nextLine();
                        System.out.println("Indique el numero del contacto:");
                        
                        numero = lector.nextInt();
                        
                        ResultSet resultados = ejecutor.executeQuery(tablaentera);
                        
                        resultados.moveToCurrentRow();
                        resultados.moveToInsertRow();
                        resultados.updateInt(1, clave);
                        resultados.updateString(2, nombre);
                        resultados.updateInt(3, numero);
                        resultados.insertRow();
                        resultados.moveToCurrentRow();
                        
                        
                        
                        consulta.close();
                        ejecutor.close();
                        

                        break;
                    }
                    
                    
                    case "editar": {
                        
                        //Creamos el objeto Statement que nos permitirá modificar las filas.
                        Statement ejecutor = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        
                        boolean ex = ejecutor.execute("USE AGENDA;");
                        
                        
                        //Ejecutamos un select para obtener todos los datos.
                        
                        String tablaentera = "select * from contactos";
                        ResultSet resultados = ejecutor.executeQuery(tablaentera);
                        
                        //Creamos el nuevo registro y lo insertamos;
                        
                       
                        String nombre;
                        int numero;
                        
                        System.out.println("Indique el numero de fila a editar.");
                        
                        int numero_editar=lector.nextInt();
                        lector.nextLine();                        
                        System.out.println("Indique el nombre del contacto:");
                        lector.nextLine();
                        nombre = lector.nextLine();
                        lector.nextLine();
                        System.out.println("Indique el numero del contacto:");
                        
                        numero = lector.nextInt();
                        lector.nextLine();
                        
                        resultados.absolute(numero_editar);                        
                        resultados.updateString(2, nombre);
                        resultados.updateInt(3, numero);
                        resultados.updateRow();
                                          
                                              
                        ejecutor.close();                                        
                        
                                                
                        break;
                    }
                    
                    case "borrar": {
                        
                        //Creamos el objeto Statement que nos permitirá borrar las filas.
                        Statement ejecutor = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        
                        boolean ex = ejecutor.execute("USE AGENDA;");
                        
                        
                        //Ejecutamos un select para obtener todos los datos.
                        
                        String tablaentera = "select * from contactos";
                        ResultSet resultados = ejecutor.executeQuery(tablaentera);
                        
                        //Eliminamos el registro.
                        
                       
                        String nombre;
                        int numero;
                        
                        System.out.println("Indique el numero de fila a eliminar.");
                        
                        int numero_eliminar=lector.nextInt();
                                                                       
                        System.out.println("Indique el nombre del contacto:");
                                              
                        nombre = lector.nextLine();
                        
                        System.out.println("Indique el numero del contacto:");
                        
                        numero = lector.nextInt();
                        
                        resultados.absolute(numero_eliminar);                   
                        
                        resultados.deleteRow();
                                                                                        
                        ejecutor.close();
                        
                        
                        
                        break;
                        
                    }
                    case "salir": {
                        salir = 1;
                        System.out.println("Gracias por confiar en nosotros.");
                        break;
                    }
                }

            }


        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

}
