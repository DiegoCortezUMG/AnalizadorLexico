/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lexical.analizer;

/**
 * Analizador léxico Jaguar
 * Proyecto compiladores, fase 2, grupo 4
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
/**
 *
 * @author Grupo 4 compiladores.
 */
public class IDE extends javax.swing.JFrame {
    /**
     * Creates new form IDE
     */
    File salida = new File("src/lexical/analizer/SalidaSin");
    PrintStream stream;
    public IDE() throws IOException{
        this.stream = new PrintStream(salida);
        System.setOut(stream);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TextoEntrada = new javax.swing.JTextArea();
        AnLex = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SalidaLexico = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        AnSintax = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TextoEntrada.setColumns(20);
        TextoEntrada.setRows(5);
        jScrollPane1.setViewportView(TextoEntrada);

        AnLex.setText("Analisis Léxico");
        AnLex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnLexActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 24)); // NOI18N
        jLabel1.setText("IDE: Jaguar");

        SalidaLexico.setEditable(false);
        SalidaLexico.setColumns(20);
        SalidaLexico.setRows(5);
        jScrollPane2.setViewportView(SalidaLexico);

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel2.setText("Código:");

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel3.setText("Salida:");

        AnSintax.setText("Analisis Sintáctico");
        AnSintax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnSintaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(254, 254, 254))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(AnLex)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AnSintax)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AnLex)
                    .addComponent(AnSintax))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AnLexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnLexActionPerformed
        // EL archivo se imprime a la jTextArea
    JFileChooser fileChooser = new JFileChooser(); // crea un objeto JFileChooser
    int seleccion = fileChooser.showOpenDialog(this); // muestra el cuadro de diálogo "Abrir archivo"
    if (seleccion == JFileChooser.APPROVE_OPTION) { // si se selecciona un archivo
        File archivo = fileChooser.getSelectedFile(); // obtiene el archivo seleccionado
        try {
            FileInputStream fstream = new FileInputStream(archivo);
            String nombre = archivo.getPath(); // obtiene la ruta del archivo seleccionado
            FileReader lector = new FileReader(nombre);
            BufferedReader lectorb = new BufferedReader(lector);
            TextoEntrada.read(lectorb,null);
            lectorb.close();
            TextoEntrada.requestFocus();
            InputStream entrada = new FileInputStream(archivo); 
        // entrada convierte el archivo a texto ara que lea el analizador
            BufferedReader br = new BufferedReader(new InputStreamReader(entrada));
        String strLine;
        ArrayList<String> lines = new ArrayList<String> ();
        //empieza el análisis léxico
        // Lee línea por línea
        while ((strLine = br.readLine()) != null)   {
          lines.add(strLine);
            //System.out.println(strLine);
        }
        // Cierra el flujo de tokens
        br.close();
        //Sintaxis sintax = new Sintaxis(strLine);
        Tokens token = new Tokens(lines);
        //System.out.println();
        int resp = token.lexicAnalizer();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileReader lector = new FileReader("src/lexical/analizer/SalidaLex");
            BufferedReader lectorb = new BufferedReader(lector);
            SalidaLexico.read(lectorb,null);
            lectorb.close();
            SalidaLexico.requestFocus();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_AnLexActionPerformed

    private void AnSintaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnSintaxActionPerformed
    JFileChooser fileChooser = new JFileChooser(); // crea un objeto JFileChooser
    int seleccion = fileChooser.showOpenDialog(this); // muestra el cuadro de diálogo "Abrir archivo"
    if (seleccion == JFileChooser.APPROVE_OPTION) { // si se selecciona un archivo
        File archivo = fileChooser.getSelectedFile(); // obtiene el archivo seleccionado
        try {
            FileInputStream fstream = new FileInputStream(archivo);
            String nombre = archivo.getPath(); // obtiene la ruta del archivo seleccionado
            FileReader lector = new FileReader(nombre);
            BufferedReader lectorb = new BufferedReader(lector);
            TextoEntrada.read(lectorb,null);
            lectorb.close();
            TextoEntrada.requestFocus();
            InputStream entrada = new FileInputStream(archivo); 
        // entrada convierte el archivo a texto ara que lea el analizador
        try (BufferedReader br = new BufferedReader(new InputStreamReader(entrada));) {
            StringBuilder codigo = new StringBuilder();
            String linea; 
            int n=1;
            while ((linea = br.readLine()) != null) {
                n++;
                codigo.append(linea).append("\n");
                Sintaxis an = new Sintaxis(codigo.toString());
            boolean revision = an.AnSintax();
            if (revision=true) {
                System.out.println("correcto");
            } else {
                System.out.println("Error de sintaxis en linea: "+n);
            }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            FileReader lector = new FileReader("src/lexical/analizer/SalidaSin");
            BufferedReader lectorb = new BufferedReader(lector);
            SalidaLexico.read(lectorb,null);
            lectorb.close();
            SalidaLexico.requestFocus();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_AnSintaxActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws FileNotFoundException, IOException{
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IDE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    // Abre el archivo
    /*FileInputStream fstream = new FileInputStream("src/lexical/analizer/SalidaLex");
    File salida = new File("src/lexical/analizer/SalidaLex");;
    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

    
    String strLine;
    ArrayList<String> lines = new ArrayList<String> ();

    // Lee línea por línea
    System.out.println("--");
    while ((strLine = br.readLine()) != null)   {
      lines.add(strLine);
        System.out.println(strLine);
    }
    // Cierra el flujo de tokens
    br.close();
    
    Tokens token = new Tokens(lines);
    System.out.println("--");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new IDE().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(IDE.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AnLex;
    private javax.swing.JButton AnSintax;
    private javax.swing.JTextArea SalidaLexico;
    private javax.swing.JTextArea TextoEntrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
