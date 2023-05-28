package lexical.analizer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Usuario
 */
public class Sintaxis {
    String lector="";
    
    File salida = new File("src/lexical/analizer/SalidaSin");
    PrintStream Sstream;
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
    public Sintaxis(ArrayList<String> myArrayList) throws IOException{
        this.myProgram = myArrayList;
        this.Sstream = new PrintStream(salida);
        System.setOut(Sstream);
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
                    //System.out.println(token + " | "+ result + " |" + i);
                }
                else {
                    String subToken = token.substring(0, token.length() - 1);
                    result = checkToken(subToken);
                    if(!result.equals("unknown")) {
                        //System.out.println(result + " : " +subToken);
                        //System.out.println(subToken + " | "+ result + " |" + i);
                        String lastToken = token.substring(token.length() - 1, token.length());
                        result = checkToken(lastToken);
                        if(!result.equals("unknown")) {
                            //System.out.println(result + " : " +lastToken);
                            System.out.println("|" + i);
                        }
                        else {
                            //System.err.println("Error: No pertenece al lenguage");
                            //System.err.println("Linea: "+i+", Estado: "+result+" -> "+subToken);
                            return 0;
                        }
                    }
                    else {
                        //System.err.println("Error: No pertenece al languaje");
                        //System.err.println("Linea: "+i+", Estado: " + result + " -> "+subToken+"");
                        return 0;
                    }
                }
        }
            }//System.out.print(lector);
} return 1;
    }
    private String discardComments(String line) {
        return line.replaceAll("//(([a-z]|[A-Z])([0-9]|[a-z]+|[A-Z]|_)).*?", "");
    }
    private String checkToken(String split) {
        if (this.palabrasReservadas.contains(split)) {
           //System.out.print("palabra reservada: ");
           lector=lector+"preservada ";
        } else if (split.matches(this.textoString)) {
           //System.out.print("texto string: ");
           lector=lector+"textoString ";
        } else if (split.matches(this.clase)) {
           //System.out.print("clase: ");
           lector=lector+"clase ";
        } else if (this.asignacion.contains(split)) {
           //System.out.print("asignación: ");
           lector=lector+"asignacion ";
        } else if (this.main.contains(split)) {
           //System.out.print("clase principal: ");
           lector=lector+"claseP ";
        } else if (this.delimitadores.contains(split)) {
           //System.out.print("delimitador: ");
           lector=lector+"delimitador ";
        } else if (this.comparadores.contains((split))) {
           //System.out.print("comparador: ");
           lector=lector+"comparador ";
        } else if (this.operadoresM.contains(split)) {
           //System.out.print("operador arimético: ");
           lector=lector+"opM ";
        } else if (this.fin.contains(split)) {
           //System.out.print("fin de sentencia: ");
           lector=lector+"fin ";
           lector="";
        } else if (split.matches(this.DigitosInt)){
           //System.out.print("entero: ");
           lector=lector+"valor ";
        } else if (split.matches(this.DigitosFloat)) {
            //System.out.print("decimal: ");
            lector=lector+"valor ";
        } else if (split.matches(this.array)) {
            //System.out.print("arreglo: ");
            lector=lector+"array ";
        } else if (split.matches(this.condicion)) {
            //System.out.print("condicion: ");
            lector=lector+"cond ";
        } else if (split.matches(this.identificador)) {
            if (this.booleanos.contains(split)) {
            //System.out.print("booleano: ");
            lector=lector+"id ";
            }
            //System.out.print("identificador ");
        } else if (split.matches("\\{(?s).*?")) {
        } else{
        } 
    if (lector.equals("claseP identificador fin")){
        System.out.println("sintaxis correcta");
    } if (lector.equals("array identificador asignación valor fin")){
        System.out.println("sintaxis correcta");
    } 
    else {
        System.out.println("error sintactico");
    } return "Syntaxis revisada";
    }
}