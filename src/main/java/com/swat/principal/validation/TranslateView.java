/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swat.principal.validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
 
@ManagedBean(name="translateView")
public class TranslateView {
     
    private final static String TRANSLATE_URL_KEY = "translate.url";
    private final static String BUNDLE_NAME = "translate";
     
    private String translateURL;
    private String from;
    private String to;
    private String text;
    private String result;
    private Map<String,String> languages;
 
    @PostConstruct
    public void init() {
        languages = new LinkedHashMap<String, String>();
        languages.put("ingles", "en");
        languages.put("turco", "tr");
        languages.put("italiano", "it");
        languages.put("español", "es");
        languages.put("aleman", "de");
        languages.put("frances", "fr");
        languages.put("portugues", "pt");
         
        Map<String,Object> app = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        translateURL = (String) app.get(TRANSLATE_URL_KEY);
         
        if(translateURL == null) {
            translateURL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150527T204328Z.355e4a9041182171.988da4fc80a3a3f7fac5feab98869902fb21bcd3";
            app.put(TRANSLATE_URL_KEY, translateURL);
        }
    }
     
    public void translate() throws Exception {        
        String url = translateURL + "&lang=" + from + "-" + to + "&text=" + text;
        String response = getResponse(url);
        JSONObject json = new JSONObject(response);
        JSONArray jsonArray = json.getJSONArray("text");
        result = jsonArray.getString(0);
    }
     
    private String getResponse(String url) throws Exception {
        System.out.println("+++++++++ el url es " + url + " ++++++++++++");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
 
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
         
        return response.toString();
    }
 
    public Map<String, String> getLanguages() {
        return languages;
    }
 
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
 
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
 
    public String getResult() {
        return result;
    }
 
    public void setResult(String result) {
        this.result = result;
    }
}