/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package limpiarexamenes;
import java.io.*;
/**
 *
 * @author Usuario
 */
public class LimpiarExamenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        deleteLinesStartingWith("tests.txt", "Pregunta", "1 / 1 pts");
        
        try {
            // Abrir el archivo tests.txt
            File inputFile = new File("tests.txt");
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // Crear un archivo temporal para escribir las líneas actualizadas
            File tempFile = new File("temp.txt");
            FileWriter fileWriter = new FileWriter(tempFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            String line;
            boolean deleteLine = false; // Indica si la línea actual debe ser eliminada completamente
            
            // Leer cada línea del archivo
            while ((line = bufferedReader.readLine()) != null) {
                // Si la línea actual contiene "¡Correcto!", marcar para eliminarla
                if (line.contains("¡Correcto!")) {
                    deleteLine = true;
                }
                // Si la línea actual no debe ser eliminada, escribirla en el archivo temporal
                else {
                    // Si la línea anterior debe ser eliminada, sustituir el carácter de la primera columna por un punto
                    if (deleteLine) {
                        String updatedLine = "." + line.substring(1);
                        bufferedWriter.write(updatedLine);
                        deleteLine = false;
                    }
                    // Si la línea anterior no debe ser eliminada, escribir la línea tal cual en el archivo temporal
                    else {
                        bufferedWriter.write(line);
                    }
                    bufferedWriter.newLine();
                }
            }
            
            // Cerrar los archivos
            bufferedReader.close();
            bufferedWriter.close();
            
            // Reemplazar el archivo original con el archivo temporal
            if (!inputFile.delete()) {
                System.out.println("Error al eliminar el archivo original");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error al renombrar el archivo temporal");
                return;
            }
            
            System.out.println("Archivo actualizado exitosamente");
        } catch (IOException e) {
            System.out.println("Error al leer/escribir el archivo: " + e.getMessage());
        }
    }
    public static void deleteLinesStartingWith(String filename, String prefix1, String prefix2) {
    try {
        // Abrir el archivo original
        File inputFile = new File(filename);
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        // Crear un archivo temporal para escribir las líneas actualizadas
        File tempFile = new File("temp.txt");
        FileWriter fileWriter = new FileWriter(tempFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        String line;
        
        // Leer cada línea del archivo
        while ((line = bufferedReader.readLine()) != null) {
            // Si la línea actual no comienza con ninguno de los prefijos, escribirla en el archivo temporal
            if (!line.startsWith(prefix1) && !line.startsWith(prefix2)) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
        
        // Cerrar los archivos
        bufferedReader.close();
        bufferedWriter.close();
        
        // Reemplazar el archivo original con el archivo temporal
        if (!inputFile.delete()) {
            System.out.println("Error al eliminar el archivo original");
            return;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Error al renombrar el archivo temporal");
            return;
        }
        
        System.out.println("Líneas eliminadas exitosamente");
    } catch (IOException e) {
        System.out.println("Error al leer/escribir el archivo: " + e.getMessage());
    }
}
}
