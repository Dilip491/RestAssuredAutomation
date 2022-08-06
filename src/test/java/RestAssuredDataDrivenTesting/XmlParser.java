package RestAssuredDataDrivenTesting;

/*
 Get the document builder
 From the document builder get the document
 Normalize the xml structure
 Get all the elements
 */


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XmlParser {

    public static void main(String[] args)  {
      String filepath=System.getProperty("user.dir")+"/src/test/java/RestAssuredDataDrivenTesting/laptops.xml";

      DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db=dbf.newDocumentBuilder();
            Document document=db.parse(filepath);
            document.getDocumentElement().normalize();
            System.out.println("The Root element is :"+document.getDocumentElement().getTagName());

            NodeList laptoplist=document.getElementsByTagName("laptop");
            for(int i=0;i< laptoplist.getLength();i++){
               Node laptop=laptoplist.item(i);
               if(laptop.getNodeType()==Node.ELEMENT_NODE){
                 Element  laptopelement=(Element)laptop;
                   System.out.println("Laptop name :"+laptopelement.getAttribute("name"));

                   NodeList childNodes=laptopelement.getChildNodes();
                   for(int j=0;j< childNodes.getLength();j++){
                       Node node=childNodes.item(j);
                       if(node.getNodeType()==Node.ELEMENT_NODE){
                           Element childelement=(Element)node;
                           System.out.println("     "+childelement.getTagName()+" : "+childelement.getAttribute("value"));
                       }
                   }
               }
            }


        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

}
