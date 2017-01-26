package com.horstmann.violet.framework.help.shortcuts;

import javax.swing.filechooser.FileSystemView;
import javax.xml.stream.*;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ShortcutReadWriter {

    public void writeShortcutXML(Map<String, String> elementsMap) {

        String userPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        String fileName = userPath + "\\shortcuts.xml";

        try{
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(fileName), "UTF-8");
            //start writing xml file
            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeCharacters("\n");

            xmlStreamWriter.writeStartElement("shortcuts");

            for(Map.Entry<String, String> shortcut : elementsMap.entrySet()){
                xmlStreamWriter.writeCharacters("\n\t");
                xmlStreamWriter.writeStartElement("shortcut");
                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("key");
                xmlStreamWriter.writeCharacters(shortcut.getKey());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t\t");
                xmlStreamWriter.writeStartElement("value");
                xmlStreamWriter.writeCharacters(shortcut.getValue());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n\t");
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

        }catch(XMLStreamException e){
            e.printStackTrace();
        }catch( FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, String> readShortcutXML() throws FileNotFoundException{

        String userPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        String fileName = userPath + "\\shortcuts.xml";

        HashMap<String, String> userMap = new HashMap<String, String>();
        String key = "";
        String value = "";

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while(xmlEventReader.hasNext()){
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("key")){
                        xmlEvent = xmlEventReader.nextEvent();
                        key = xmlEvent.asCharacters().getData();
                    }
                    else if(startElement.getName().getLocalPart().equals("value")){
                        xmlEvent = xmlEventReader.nextEvent();
                        value = xmlEvent.asCharacters().getData();
                    }
                }
                //if shortcut end element is reached, add employee object to list
                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("shortcut")){
                        userMap.put(key, value);
                    }
                }
            }
        }catch(XMLStreamException e){
            e.printStackTrace();
        }
        return userMap;
    }
}
