/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demosendjson;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.*;

/**
 *
 * @author HelloThang
 */
public class DemoSendJSON {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            SaveWorkFlow("http://10.64.100.17:9119");
        } catch (IOException ex) {
            Logger.getLogger(DemoSendJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void SaveWorkFlow(String URL) throws IOException {
        try {
            //build JSON OBJECT
            JSONObject js = new JSONObject();
            js.put("api", "get_config");
            System.out.println("thangpro");
            System.out.println(js.toString());

            //Send Request
            URL url = new URL(URL);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setDoInput(true);
            httpCon.setUseCaches(false);
            httpCon.setRequestProperty("Content-Type", "application/json");
            httpCon.setRequestProperty("Accept", "application/json");
            httpCon.setRequestMethod("POST");
            httpCon.connect();
            OutputStream os = httpCon.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(js.toString());
            osw.flush();
            osw.close();

            //Read data
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            JSONObject json = null;
            JSONObject json1 = new JSONObject(sb.toString());
            for (String key : JSONObject.getNames(json1)) {
                System.out.println(key + "    " + (String) json1.get(key));
            }
            br.close();

        } catch (JSONException ex) {
            Logger.getLogger(DemoSendJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
