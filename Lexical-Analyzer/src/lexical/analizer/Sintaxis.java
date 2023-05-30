/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lexical.analizer;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author Usuario
 */
public class Sintaxis {
    File salida = new File("src/lexical/analizer/SalidaSin");
    PrintStream stream;
    ArrayList<String> tipos = new ArrayList<>(Arrays.asList("jent","jflota","jdoble","jconst"));
    ArrayList<String> delimitadores = new ArrayList<>(Arrays.asList(";",":",",","{","}"));
    ArrayList<String> comparadores = new ArrayList<>(Arrays.asList("=:=","!=",">","<",">=","<=","++","&&"));
    ArrayList<String> operadoresM = new ArrayList<>(Arrays.asList("+","-","*","/","++"));
    ArrayList<String> simbolo = new ArrayList<>(Arrays.asList("#","%","$","?","¿","!","¡"));
    ArrayList<String> fin = new ArrayList<>(Arrays.asList("."));
    ArrayList<String> booleanos = new ArrayList<>(Arrays.asList("verdadero", "falso"));
    String asignacion = "=";
    String main = "jPrincipal()";
    String clase = "([a-z]|(á,é,í,ó,ú)|[A-Z])([0-9]|(á,é,í,ó,ú)|[a-z]+|[A-Z]|_)*+[()]";
    String comentarios = "[//]([a-z]|(á,é,í,ó,ú)|[A-Z])([0-9]|(á,é,í,ó,ú)|[a-z]+|[A-Z]|_)*";
    String identificador = "[@]+([a-z]|(á,é,í,ó,ú)|[A-Z])([0-9]|(á,é,í,ó,ú)|[a-z]+|[A-Z]|_)*";
    String textoString = "[\"]([a-z]|(á,é,í,ó,ú)|[A-Z])([ ])([0-9]|(á,é,í,ó,ú)|[a-z]+|[A-Z]|_)*[\"]";
    String condicion = "[(]([a-z]|(á,é,í,ó,ú)|[A-Z])([0-9]|(á,é,í,ó,ú)|[@]|[ ]|[a-z]+|[A-Z]|_)*[)]";
    String darray = "[{]([0-9]+[,][0-9]+)*[}]";
    String DigitosInt = "[0-9]+";
    String DigitosFloat = "[0-9]+[.][0-9]+";
    private String codigo;
    private int indice;
    public Sintaxis(String codigo) throws IOException{
        this.codigo = codigo;
        this.indice = 0;
        this.stream = new PrintStream(salida);
        System.setOut(stream);
    }
    public boolean AnSintax() {
        try {
            programa();
            return indice == codigo.length(); // Verificar si se llegó al final del código
        } catch (Exception e) {
            return false;
        }
    }
    private void programa() {
        String[] lineas = codigo.split("\n");
        for (String linea : lineas) {
            linea = quitarComentarios(codigo);
            linea = linea.trim();
            if (!linea.isEmpty()) {
                sentencia(linea);
            }
        }
    }
    private void sentencia(String linea) {
        if (linea.startsWith("jsi")) {
            sentenciaIf(linea);
        } if (linea.startsWith("jEscribir")){
            sentenciaPrint(linea);
        } if(linea.startsWith("jparacada")){
            sentenciaWhile(linea);
        } if(linea.contains((CharSequence) tipos)){
            asignacion(linea);
        }
        // Aquí puedes agregar más reglas para analizar otras sentencias
    }
    private void asignacion(String linea){
        matchAR(tipos,linea);
        match("=",linea);
    }
    private void sentenciaIf(String linea) {
        match("jsi", linea);
        match("(", linea);
        expresion(linea);
        match(")", linea);
        bloque(linea);
    }
    private void sentenciaPrint(String linea) {
        match("jEscribir", linea);
        match("(", linea);
        matchRE(textoString,linea);
        match(")", linea);
        match(".",linea);
    }
    private void sentenciaWhile(String linea){
        match("jparacada", linea);
        match("(", linea);
        expresion(linea);
        match(")", linea);
        bloque(linea);
    }
    private void expresion(String linea) {
        matchRE(identificador,linea);
        matchAR(comparadores,linea);
        matchRE(identificador,linea);
    }
    private void bloquePrin(String linea) {
        match(main,linea);
        matchRE(identificador,linea);
        match("{", linea);
        // Implementa la lógica para analizar el contenido del bloque
        // según tus necesidades
        match("}", linea);
    }
    private void bloque(String linea) {
        match("{", linea);
        // Implementa la lógica para analizar el contenido del bloque
        // según tus necesidades
        match("}", linea);
    }
    private String quitarComentarios(String line) {
        return line.replaceAll("//(([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)).*?", "");
    }
    private boolean match(String token, String linea) {
        if (linea.startsWith(token)) {
            indice += token.length();
            return true;
        }
        return false;
    }
    private boolean matchAR(ArrayList<String> array, String token) {
        if (this.main.contains(token)) {
            indice += token.length();
            return true;
        }
        return false;
    }
    private boolean matchRE(String pattern, String linea) {
    Pattern regex = Pattern.compile(pattern);
    Matcher matcher = regex.matcher(linea);
    if (matcher.lookingAt()) {
        indice += matcher.end();
        return true;
    }
    return false;
}
}