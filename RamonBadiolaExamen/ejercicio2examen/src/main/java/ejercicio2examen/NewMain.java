/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2examen;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAW
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       

        try {
            File archivo = new File("esteaparcamiento.txt");

            Scanner lector_fichero = new Scanner(archivo);
            
            String datos;
            
            ArrayList<String> lista_datos = new ArrayList();
            
            while (lector_fichero.hasNext()){
                
                datos = lector_fichero.nextLine();
                             
                lista_datos.add(datos);
                
            }
            
            for (int i = 0; i<lista_datos.size(); i++){
                
                System.out.println(lista_datos.get(i));
                
            }
            
            
            
            String codigo_parking;
            String calle;
            String tipo;
            int numerodeplazas;            
            int rotacion=0;
            int residente=0;
            int disuasorio=0;
            int mixto=0;
            String distrito;
            
            for(int i= 0; i<lista_datos.size();i++){
                
                String[] parte = lista_datos.get(i).split(" ");
                
                for (int j =0; j<parte.length;j++){
                    
                    codigo_parking=parte[0];
                    
                    calle = parte[1];
                    
                    if (parte[2]=="MIXTO"| parte[2]=="ROTACION"| parte[2]=="RESIDENTES"| parte[2]=="DISUASORIO"){
                        disuasorio++;
                        tipo=parte[2];
                        
                        numerodeplazas = Integer.parseInt(parte[3]);
                        
                        if (parte[4].matches("[0-9]*")){ 
                                                        
                            numerodeplazas = numerodeplazas+Integer.parseInt(parte[4]);
                       
                        }
                        
                        
                        
                    } else {
                        calle = calle + parte[2];                                               
                    }
                    
                    
                        
                    
                    
                    
                    
                    
                }
                
                
                
                
            }
            
            
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NO ENCUENTRA EL FICHERO");
        }

    }

}
