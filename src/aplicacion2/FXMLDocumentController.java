/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 *
 * @author usuario
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private MenuItem MenuNuevo;
    @FXML 
    private Pane pane1;
    @FXML
    private MenuItem MenuCerrar;
    @FXML
    private MenuItem menuAbrir;
    @FXML
    private MenuItem guardarBinario;
    @FXML
    private MenuItem guardarTXT;
    @FXML
    private Pane paneTXT;
    @FXML
    private TextField nombreTXT;
    @FXML
    private Button botonTXT;
    @FXML
    private TextField nombreSoft;
    @FXML
    private RadioButton versionGratis;
    @FXML
    private RadioButton versionPago;
    @FXML
    private TextArea requisitos;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField precio;
    @FXML
    private TextArea alternativas;
    @FXML
    private Pane paneBinario;
    @FXML
    private Pane paneXML;
    @FXML
    private TextField nombreBinario;
    @FXML
    private TextField nombreXML;
    
    @FXML
    public void aparecerPanel()
    {
        nombreSoft.setText(null);
        pane1.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup grupo= new ToggleGroup();
        versionGratis.setToggleGroup(grupo);
        versionPago.setToggleGroup(grupo);
        versionGratis.setSelected(true);
    }    

    @FXML
    private void salirPrograma() {
        System.exit(0);
    }

    @FXML
    private void guardarTXT() {
        FileWriter fichero=null;
        String version;
        if(versionGratis.isDisabled())
            version="De pago";
        else
            version="Gratis";
        try {
            fichero = new FileWriter(nombreTXT.getText()+".txt");
            fichero.write("Nombre: "+nombreSoft.getText()+"\n");
            fichero.write("Version: "+version+"\n");
            fichero.write("Requisitos: "+requisitos.getText()+"\n");
            fichero.write("Descripción: "+descripcion.getText()+"\n");
            fichero.write("Precio: "+precio.getText()+"\n");
            fichero.write("Alternativas: "+alternativas.getText()+"\n");
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fichero.close();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        paneTXT.setVisible(false);
    }
    
    @FXML
    private void guardarBinario(ActionEvent event) {
        String version;
        if(versionGratis.isDisabled())
            version="De pago";
        else
            version="Gratis";
        try {
            FileOutputStream fichero=new FileOutputStream(nombreBinario.getText()+".dat");
            DataOutputStream dFich=new DataOutputStream(fichero);
            dFich.writeUTF(nombreSoft.getText()+".");
            dFich.writeUTF(version+".");
            dFich.writeUTF(requisitos.getText()+".");
            dFich.writeUTF(descripcion.getText()+".");
            dFich.writeUTF(precio.getText()+".");
            dFich.writeUTF(alternativas.getText()+".");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void guardarXML(ActionEvent event) {
    }

    @FXML
    private void panelGuardarTXT(ActionEvent event) {
        pane1.setVisible(false);
        paneTXT.setVisible(true);
    }

    @FXML
    private void panelGuardarXML(ActionEvent event) {
        paneXML.setVisible(true);
        pane1.setVisible(false);
    }

    @FXML
    private void panelGuardarBinario(ActionEvent event) {
        paneBinario.setVisible(true);
        pane1.setVisible(false);
    }

    @FXML
    private void abrirArchivo(ActionEvent event) {
    }
}
