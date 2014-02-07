package com.jkahigiso.ngomastations.domain;


import android.renderscript.Element;
import android.util.Log;

import com.jkahigiso.ngomastations.util.Util;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Jean Kahigiso on 06-02-2014.
 */
public class XmlParser {

    private List<Station> stations = new ArrayList<Station>();

    public  List<Station> parseStations(){

        try{
            File  file = new File(Util.STATION_XML);
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse (file);

            NodeList listOfStations = doc.getElementsByTagName("station");
            int length = listOfStations.getLength();

            for (int i = 0; i < length; i++) {
                Node currentNode = listOfStations.item(i);
                Station station = new Station();
                NodeList nChild = currentNode.getChildNodes();
                int cLength = nChild.getLength();
                //Read stations
                for (int j = 1; j < cLength; j = j + 2) {
                    Node thisNode = nChild.item(j);
                    String nodeData = null;
                    String nodeName = thisNode.getNodeName();
                    nodeData = nChild.item(j).getFirstChild().getNodeValue();
                    if (nodeData != null) {
                        if ("priority".equals(nodeName)) {
                            try{
                                station.setPriority(Integer.parseInt(nodeData.trim()));
                            }catch (NumberFormatException e){
                                Log.d("priority not number:","fix this: \n"+e.toString());
                            }
                        }else if("name".equals(nodeName)){
                            station.setName(nodeData);
                        }else if("url".equals(nodeName)){
                            station.setUrl(nodeData);
                        } else if ("icon".equals(nodeName)) {
                            station.setIcon(nodeData);
                        } else if ("frequency".equals(nodeName)) {
                            station.setFrequency(nodeData);
                        }
                    }
                }
                stations.add(station);
            }

        }catch (SAXParseException sax){
            sax.printStackTrace();
        }catch (SAXException e){
            e.printStackTrace();
        }catch (Throwable t){
            t.printStackTrace();
        }

        //Sort the list based on set priority
        Collections.sort(stations,new Comparator<Station>() {
            @Override
            public int compare(Station station1, Station station2) {
                return station1.getPriority().compareTo(station1.getPriority());
            }
        });

        return stations;
    }
}
