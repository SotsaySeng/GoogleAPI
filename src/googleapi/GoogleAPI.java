/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author DV6
 */
public class GoogleAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
                   String json = "";

                    
            	URL googleAPI = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=auckland+university+of+technology&destinations=90+akoranga+drivenorthcote,auckland&departure_time=now&key=AIzaSyAvgsJSKtEXz1RYdYrkrOiMqjjGMNysVgc");
                URLConnection googleCon = googleAPI.openConnection();
                
                BufferedReader in = new BufferedReader(new InputStreamReader(googleCon.getInputStream()));
                StringBuilder sbuild = new StringBuilder();
                String line = null;
                
                while ((line = in.readLine()) != null) {
                    sbuild.append(line);
                }
                
                json = sbuild.toString(); 
               
                //Parsing 
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
                //JSON array
                JSONArray  destination = (JSONArray)jsonObject.get("destination_addresses");
                System.out.println("destination = "+destination);
              
                JSONArray  rows = (JSONArray)jsonObject.get("rows");
                
                for(int i=0; i<rows.size(); i++){
                	System.out.println("elements:"+rows.get(i));
                	
                }
                
                java.util.Iterator i = (java.util.Iterator) rows.iterator();
                
                while (((java.util.Iterator) i).hasNext()) {
                		            	
                	JSONObject elements = (JSONObject) i.next();
                 
                	JSONArray  duration = (JSONArray) elements.get("elements");	            	
                		for(int i1=0; i1<elements.size(); i1++){
                				JSONObject duration1 = (JSONObject) duration.get(i1);
                
                				JSONObject traveltime = (JSONObject) duration1.get("duration");
                				JSONObject distance = (JSONObject) duration1.get("distance");
                				JSONObject traveltime2 = (JSONObject) duration1.get("duration_in_traffic");
                
                				System.out.println("duration:"+traveltime.get("text"));
                				System.out.println("distance:"+distance.get("text"));
                				System.out.println("duration_in_traffic:"+traveltime2.get("text"));

                		}
                }
  	}
             
}
