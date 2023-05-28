package lexical.analizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
/*
*Definición de tokens y patrones
*/
public class Tokens{
    File salida = new File("src/lexical/analizer/SalidaSin");
    PrintStream stream;
    //declaración de tokens
    ArrayList<String> palabrasReservadas = new ArrayList<>(Arrays.asList("jclase","jestatico","jvoid","sellado","abstracto","clase", "jEscribir", "jent","jent[]","booleano", "const", "raiz",
                    "flota","jcad[]","jcad","jconst","jdouble","bit","arg", "si", "then", "demas","jparacada","jsi", "jcada_vez", "hacer", "mientras", "publico", "enteros", "iniciales", "protegido", "vacia","vacio", "larg","cada"));
    //aquí el punto se utiliza para marcar el final de una línea de código
    ArrayList<String> delimitadores = new ArrayList<>(Arrays.asList(";",":",",","{","}"));
    ArrayList<String> comparadores = new ArrayList<>(Arrays.asList("=:=","!=",">","<",">=","<=","++","&&"));
    ArrayList<String> operadoresM = new ArrayList<>(Arrays.asList("+","-","*","/","++"));
    ArrayList<String> simbolo = new ArrayList<>(Arrays.asList("#","§","%","$","?","¿","!","¡"));
    ArrayList<String> fin = new ArrayList<>(Arrays.asList("."));
    ArrayList<String> booleanos = new ArrayList<>(Arrays.asList("verdadero", "falso"));
    String asignacion = "=";
    String main = "jprincipal()";
    String clase = "([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]||[a-z]+|[A-Z]|_)*+[()]";
    String comentarios = "[//]([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]|[a-z]+|[A-Z]|_)*";
    String identificador = "[@]+([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)*";
    String textoString = "[\"]([ ]|[á,é,í,ó,ú]|[:]|[0-9]|[a-z]+|[A-Z]|_)*[\"]";
    String condicion = "[(]([á,é,í,ó,ú]|[a-z]|[A-Z])([á,é,í,ó,ú]|[0-9]|[@]|[ ]|[a-z]+|[A-Z]|_)*[)]";
    String array = "[{]([0-9]+[,][0-9]+)*[}]";
    String DigitosInt = "[0-9]+";
    String DigitosFloat = "[0-9]+[.][0-9]+";
    //reglas sintácticas
    String claseP;

    ArrayList<String> myProgram = null;
    public Tokens(ArrayList<String> myArrayList) throws IOException{
        this.myProgram = myArrayList;
        this.stream = new PrintStream(salida);
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
                if (null != result){
                if(!result.equals("unknown")){
                    System.out.println(token + " | "+ result + " |" + i + " : " + token);
                }
                else {
                    String subToken = token.substring(0, token.length() - 1);
                    result = checkToken(subToken);
                    if(!result.equals("unknown")) {
                        System.out.println(subToken+" | "+result+" |"+i+ " : "+subToken+"hola2");
                        String lastToken = token.substring(token.length() - 1, token.length());
                        result = checkToken(lastToken);
                        if(!result.equals("unknown")) {
                            //System.out.println(result + " : " +lastToken);
                            System.out.println(lastToken+" | "+result+" | "+i+"hola");
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
                //System.out.print(token + " | "+ result + " |" + i);
                }
} return 1;
    }
    private String discardComments(String line) {
        return line.replaceAll("//(([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)).*?", "");
    }
    private String checkToken(String split) {
        if (this.palabrasReservadas.contains(split)) {
           System.out.print("palabra reservada: ");
        } else if (split.matches(this.textoString)) {
           System.out.print("texto string: ");
        } else if (split.matches(this.clase)) {
           System.out.print("clase: ");
        } else if (this.asignacion.contains(split)) {
           System.out.print("asignación: ");
        } else if (this.main.contains(split)) {
           System.out.print("clase principal: ");
        } else if (this.delimitadores.contains(split)) {
           System.out.print("delimitador: ");
        } else if (this.comparadores.contains((split))) {
           System.out.print("comparador: ");
        } else if (this.operadoresM.contains(split)) {
           System.out.print("operador arimético: ");
        } else if (this.fin.contains(split)) {
           System.out.print("fin de sentencia: ");
        } else if (split.matches(this.DigitosInt)){
           System.out.print("entero: ");
        } else if (split.matches(this.DigitosFloat)) {
            System.out.print("decimal: ");
        } else if (split.matches(this.array)) {
            System.out.print("arreglo: ");
        } else if (split.matches(this.condicion)) {
            System.out.print("condicion: ");
        } else if (split.matches(this.identificador)) {
            if (this.booleanos.contains(split)) {
            System.out.print("booleano: ");
            }
            System.out.print("identificador ");
        } else if (split.matches("\\{(?s).*?")) {
            System.out.print("error: no pertenece al lenguaje: ");
        } else{
            System.out.print("error: palabra reservada mal escrita: ");
        }
        return "lectura exitosa";
    }
}