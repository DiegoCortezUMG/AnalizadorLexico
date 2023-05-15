package lexical.analizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
/**
 * Definición de tokens y patrones
 */
public class Tokens{
    File salida = new File("src/lexical/analizer/SalidaLex");
    PrintStream stream;
    
    ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList("sellado","abstracto","clase", "imprimir", "jent","jent[]", "escribir", "booleano", "const", "raiz",
                    "flota","jconst","jdoble","bit", "si", "then", "demas", "jcada_vez", "hacer", "mientras", "publico", "enteros", "iniciales", "protegido", "vacia","vacio", "larg","cada"));
    //aquí el punto se utiliza para marcar el final de una línea de código
    ArrayList<String> delimitadores = new ArrayList<>(Arrays.asList(";",":",",","{","}"));
    ArrayList<String> comparadores = new ArrayList<>(Arrays.asList("=:=","!=",">","<",">=","<=","++","&&"));
    ArrayList<String> operadoresM = new ArrayList<>(Arrays.asList("+","-","*","/","++"));
    ArrayList<String> simbolo = new ArrayList<>(Arrays.asList("#","%","$","?","¿","!","¡"));
    ArrayList<String> fin = new ArrayList<>(Arrays.asList("."));
    ArrayList<String> booleanos = new ArrayList<>(Arrays.asList("verdadero", "falso"));

    String asignacion = "=";
    String main = "jPrincipal()";
    String clase = "([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]||[a-z]+|[A-Z]|_)*+[()]";
    String comentarios = "[//]([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]|[a-z]+|[A-Z]|_)*";
    String identificador = "[@]+([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)*";
    String textoString = "[\"]([ ]|[á,é,í,ó,ú]|[:]|[0-9]|[a-z]+|[A-Z]|_)*[\"]";
    String condicion = "[(]([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]|[@]|[ ]|[a-z]+|[A-Z]|_)*[)]";
    String array = "[{]([0-9]+[,][0-9]+)*[}]";
    String DigitosInt = "[0-9]+";
    String DigitosFloat = "[0-9]+[.][0-9]+";

    ArrayList<String> myProgram = null;

    public Tokens(ArrayList<String> myArrayList) throws IOException{
        this.stream = new PrintStream(salida);
        this.myProgram = myArrayList;
        System.setOut(stream);
    }
    public int lexicAnalizer() {
        for(int i = 0; i < this.myProgram.size(); i++) {
            String line = discardComments(this.myProgram.get(i));
            String[] splited = line.split("\\s+");
            // revisa el programa línea por línea
            for (int j = 0; j < splited.length; j++) {
                String token = splited[j];
                String result = checkToken(token);
                if (null != result) {
                if(!result.equals("desconocido)")) {
                    System.out.println(token + " | "+ result + " |" + i);
                }
                else {
                    String subToken = token.substring(0, token.length() - 1);
                    result = checkToken(subToken);
                    if(!result.equals("desconocido")) {
                        System.out.println(result + " : " +subToken);
                        //System.out.println(subToken + " | "+ result + " |" + i);
                        String lastToken = token.substring(token.length() - 1, token.length());
                        result = checkToken(lastToken);
                        if(!result.equals("desconocido")) {
                            System.out.println(result + " : " +lastToken);
                            //System.out.println(lastToken+ " | "+ result + " |" + i);
                        }
                        else {
                            System.err.println("Error: No pertenece al lenguage");
                            //System.err.println("Linea: "+i+", Estado: "+result+" -> "+subToken);
                            return 0;
                        }
                    }
                    else {
                        System.err.println("Error: No pertenece al languaje");
                        //System.err.println("Linea: "+i+", Estado: " + result + " -> "+subToken+"");
                        return 0;
                    }
                }
        }
                System.out.print(token + " | "+ result + " |" + i);
                }
}
        return 1;
    }
    private String discardComments(String line) {
        return line.replaceAll("//(([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)).*?", "");
    }
    private String checkToken(String split) {
        if (this.palabrasReservadas.contains(split)) {
            System.out.println("palabra reservada/n");
        } else if (split.matches(this.textoString)) {
            System.out.println("texto string/n");
        } else if (split.matches(this.clase)) {
            System.out.println("clase/n");
        } else if (this.asignacion.contains(split)) {
            System.out.println("asignación/n");
        } else if (this.main.contains(split)) {
            System.out.println("clase principal/n");
        } else if (this.delimitadores.contains(split)) {
            System.out.println("delimitador/n");
        } else if (this.comparadores.contains((split))) {
            System.out.println("comparador/n");
        } else if (this.operadoresM.contains(split)) {
            System.out.println("operador arimético/n");
        } else if (this.fin.contains(split)) {
            System.out.println("fin de sentencia/n");
        } else if (split.matches(this.DigitosInt)){
            System.out.println("entero/n");
        } else if (split.matches(this.DigitosFloat)) {
            System.out.println("decimal/n");
        } else if (split.matches(this.array)) {
            System.out.println("arreglo/n");
        } else if (split.matches(this.condicion)) {
            System.out.println("condicion/n");
        } else if (split.matches(this.identificador)) {
            if (this.booleanos.contains(split)) {
                System.out.println("booleano/n");
            }
            System.out.println("identificador/n");
        } else if (split.matches("\\{(?s).*?")) {
            System.out.println("error: no pertenece al lenguaje/n");
        } else{
            System.out.println("error: token desconocido");
        }
        return "token";
    }
}