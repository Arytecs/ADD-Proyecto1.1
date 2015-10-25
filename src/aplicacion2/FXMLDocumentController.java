/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion2;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author araceliTeruel
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
    private TextField nombreAbrirXML;
    @FXML
    private Pane paneAbrirXML;
    @FXML
    private MenuItem abrirPaneXML;
    
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
        if(versionPago.isSelected()==true)
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
        if(versionPago.isSelected()==true)
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
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, nombreXML.getText(), null);
            document.setXmlVersion("1.0");
            Element raiz=document.getDocumentElement();
            
            Element itemNode = document.createElement("Software"); 
                
                Element nombre = document.createElement("Nombre"); 
                Text nombreT = document.createTextNode(nombreSoft.getText());
                nombre.appendChild(nombreT);      
                String version1;
                if(versionPago.isSelected()==true)
                    version1="De pago";
                else
                    version1="Gratis";
                Element version = document.createElement("Version"); 
                Text versionT = document.createTextNode(version1);                
                version.appendChild(versionT);
                
                Element requisito = document.createElement("Requisitos"); 
                Text requisitosT = document.createTextNode(requisitos.getText());                
                requisito.appendChild(requisitosT);
                
                Element descrip = document.createElement("Descripcion"); 
                Text descripT = document.createTextNode(descripcion.getText());                
                descrip.appendChild(descripT);
                
                Element price = document.createElement("Precio"); 
                Text priceT = document.createTextNode(precio.getText());                
                price.appendChild(priceT);
                
                Element alt = document.createElement("Alternativas"); 
                Text altT = document.createTextNode(alternativas.getText());                
                alt.appendChild(altT);
                
                itemNode.appendChild(nombre);
                itemNode.appendChild(version);
                itemNode.appendChild(requisito);
                itemNode.appendChild(descrip);
                itemNode.appendChild(price);
                itemNode.appendChild(alt);
                
                raiz.appendChild(itemNode);
                
                
                Source source = new DOMSource(document);
                //Indicamos donde lo queremos almacenar
                Result result = new StreamResult(new java.io.File(nombreXML.getText()+".xml")); //nombre del archivo
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        paneXML.setVisible(false);
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
        /*FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", "txt");
	JFileChooser fileChooserCargar = new JFileChooser();
	fileChooserCargar.setFileFilter(filtro);
	fileChooserCargar.setDialogTitle("Abrir");
	 
	int seleccion = fileChooserCargar.showOpenDialog(this);
	 
	if (seleccion == JFileChooser.APPROVE_OPTION) {
	File file = fileChooserCargar.getSelectedFile();
	Modificar m = new Modificar(this, file);
	}*/
    }
    
    @FXML
    private void abrirXML(ActionEvent event) {
        Document doc=null;
        paneAbrirXML.setVisible(false);
        pane1.setVisible(true);
        try{
            //Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder=factory.newDocumentBuilder();
            
            doc=builder.parse(nombreAbrirXML.getText()+".xml"); 
            //Ahora doc apunta al árbol DOM listo para ser recorrido. 
            Node raiz=doc.getFirstChild();
            Node nodo;
            NodeList nodos=raiz.getChildNodes();
            String datos[];
            nodo=nodos.item(0);
            if(nodo.getNodeType()==Node.ELEMENT_NODE){
                datos=procesarNodo(nodo);
                nombreSoft.setText(datos[0]);
                if(datos[1].equals("De pago"))
                    versionPago.selectedProperty();
                else
                    versionGratis.selectedProperty();
                requisitos.setText(datos[2]);
                descripcion.setText(datos[3]);
                precio.setText(datos[4]);
                alternativas.setText(datos[5]);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String[] procesarNodo(Node nodo){
        String datos[]= new String[6]; 
        Node ntemp=null;
        int contador=0;
        
        NodeList nodos=nodo.getChildNodes();
         for (int i=0; i<nodos.getLength(); i++)
         {
             ntemp = nodos.item(i);
             //Se debe comprobar el tipo de nodo que se quiere tratar por que posible que haya
             //nodos tipo TEXT que contengan retornos de carro al cambiar de línea en el XML.
             //En este ejemplo, cuando detecta un nodo que no es tipo ELEMENT_NODE es porque es tipo TEXT
             // y contiene los retornos de carro "\n" que se incluyen en el fichero de texto al crear el XML.
             if(ntemp.getNodeType()==Node.ELEMENT_NODE){
                  //IMPORTANTE: para obtener el texto con el título accede al nodo TEXT hijo de ntemp y saca su valor.
                   datos[contador]=ntemp.getChildNodes().item(0).getNodeValue();
                   contador++;
             }
             
             
         }
        
        return datos;
    }
    
    @FXML
    private void abrirPaneXML(){
        paneAbrirXML.setVisible(true);
        pane1.setVisible(false);
    }
}
