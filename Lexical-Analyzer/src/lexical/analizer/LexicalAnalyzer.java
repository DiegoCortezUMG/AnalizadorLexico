package lexical.analizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * Analizador léxico Jaguar
 * Proyecto compiladores, fase 2, grupo 4
 */
public class LexicalAnalyzer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
    // Abre el archivo
    FileInputStream fstream = new FileInputStream("src/lexical/analizer/textfile");
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    // Abre el IDE
    IDE ide = new IDE();
    ide.show();
    
    String strLine;
    ArrayList<String> lines = new ArrayList<String> ();

    // Lee línea por línea
    while ((strLine = br.readLine()) != null)   {
      lines.add(strLine);
        System.out.println(strLine);
    }
    // Cierra el flujo de tokens
    br.close();
    
    Tokens token = new Tokens(lines);
    System.out.println();
    int resp = token.lexicAnalizer();
    
    }
}