package lexical.analizer;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Definición de tokens y patrones
 */
public class Tokens {
    public static final String reset = "\u001B[0m";
    public static final String rojo = "\u001B[31m";

    ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList("sellado","abstracto","clase", "imprimir", "ent", "escribir", "booleano", "const", "raiz",
                    "flota","doble","bit", "si", "then", "demas", "durante", "hacer", "mientras", "publico", "privado", "interno", "protegido", "vacia","vacio", "larg","cad","."));
    //aquí el punto se utiliza para marcar el final de una línea de código
    ArrayList<String> delimitadores = new ArrayList<>(Arrays.asList(";",".","",",","{","}"));
    ArrayList<String> comparadores = new ArrayList<>(Arrays.asList("=:=","!=",">","<",">=","<=","++","&&"));
    ArrayList<String> operadoresM = new ArrayList<>(Arrays.asList("+","-","*","/"));
    ArrayList<String> simbolo = new ArrayList<>(Arrays.asList("#","%","$"));
    ArrayList<String> parentesis = new ArrayList<>(Arrays.asList("(",")"));
    ArrayList<String> booleanos = new ArrayList<>(Arrays.asList("verdadero", "falso"));

    String asignacion = "=";
    String identificador = "[@]+([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)*";
    String textoString = "[\"]([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)*[\"]";
    String DigitosInt = "[0-9]+";
    String DigitosFloat = "[0-9]+[.][0-9]+";

    ArrayList<String> myProgram = null;

    public Tokens(ArrayList<String> myArrayList) {
        this.myProgram = myArrayList;
    }
    public int lexicAnalizer() {
        for(int i = 0; i < this.myProgram.size(); i++) {
            String line = discardComments(this.myProgram.get(i));
            String[] splited = line.split("\\s+");
            // revisa el programa línea por línea
            for (int j = 0; j < splited.length; j++) {
                String token = splited[j];
                String result = checkToken(token);
                if(!result.equals("desconocido (1)")) {
                    System.out.println(token + " | "+ result + " |" + i);
                }
                else {
                    String subToken = token.substring(0, token.length() - 1);
                    result = checkToken(subToken);
                    if(!result.equals("desconocido (2)")) {
                        System.out.println(result + " : " +subToken);
                        //System.out.println(subToken + " | "+ result + " |" + i);
                        String lastToken = token.substring(token.length() - 1, token.length());
                        result = checkToken(lastToken);
                        if(!result.equals("desconocido (3)")) {
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
        }
        return 1;
    }
    private String discardComments(String line) {
        return line.replaceAll("\\{(?s).*?}", "");
    }
    private String checkToken(String split) {
        if (this.palabrasReservadas.contains(split)) {
            return "Palabra reservada";
        } else if (split.matches(this.textoString)) {
            return "cadena de texto";
        }else if (this.parentesis.contains(split)) {
            return "parentesis";
        } else if (this.asignacion.contains(split)) {
            return "asignación";
        } else if (this.delimitadores.contains(split)) {
            return "Delimitador";
        } else if (this.comparadores.contains((split))) {
            return "Comparador";
        } else if (this.operadoresM.contains(split)) {
            return "Operación arimética";
        } else if (this.simbolo.contains(split)) {
            return "caracter especial";
        } else if (split.matches(this.DigitosInt)){
            return "Entero";
        } else if (split.matches(this.DigitosFloat)) {
            return "Decimal";
        } else if (split.matches(this.identificador)) {
            if (this.booleanos.contains(split)) {
                return "Booleano";
            }
            return "Identificador";
        } else if (split.matches("\\{(?s).*?")) {
            return "error: no pertenece al lenguaje/n";
        } else{
        return rojo+"error: token desconocido"+reset;
        }
    }
}