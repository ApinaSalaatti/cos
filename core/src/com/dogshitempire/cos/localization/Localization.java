package com.dogshitempire.cos.localization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.dogshitempire.cos.GameApplication;
import java.util.HashMap;

/**
 *
 * @author Merioksan Mikko
 */
public class Localization {
    private static FileHandle[] langs;
    private static String currentLanguage;
    
    private static HashMap<String, String> strings;

    public static void init() {   
        FileHandle handle = Gdx.files.local("lang");
        langs = handle.list("json");
        
        if(GameApplication.debugMode) {
            System.out.println("Available languages:");
            int index = 0;
            for(FileHandle lang : langs) {
                System.out.println(lang.nameWithoutExtension());
                index++;
            }
        }
    }
    
    public static String[] getAvailableLanguages() {
        int index = 0;
        String[] s = new String[langs.length];
        for(FileHandle lang : langs) {
            s[index] = lang.nameWithoutExtension();
            index++;
        }
        return s;
    }
    
    public static void setLanguage(String lang) {
        if(GameApplication.debugMode) System.out.println("Trying to set language to: " + lang);
        
        for(FileHandle l : langs) {
            if(l.nameWithoutExtension().equals(lang)) {
                set(l);
                return;
            }
        }
        
        throw new RuntimeException("Unknown language set");
    }
    
    private static void set(FileHandle lang) {
        if(GameApplication.debugMode) System.out.println("Setting language");
        
        JsonValue root = new JsonReader().parse(lang);
        JsonValue current = root.child;
 
        strings = new HashMap<String, String>();
        
        while(current != null) {
            if(GameApplication.debugMode) System.out.println(current.name() + " : " + current.asString());
            strings.put(current.name(), current.asString());
            current = current.next();
        }
    }
    
    public static String getString(String key, String ... params) {
        if(GameApplication.debugMode) System.out.println("Fetching string: " + key);
        
        String s = strings.get(key);
        if(s != null) {
            String[] s2 = s.split(" ");
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for(int i = 0; i < s2.length; i++) {
                if(s2[i].equals("%")) {
                    s2[i] = params[index];
                    index++;
                }
                else if(s2[i].equals("%%")) {
                    s2[i] = "%";
                }
                sb.append(s2[i]);
                
                if(i < s2.length-1) {
                    sb.append(" ");
                }
            }
            
            return sb.toString();
        }
        else {
            throw new RuntimeException("Unkown key passed to localization");
        }
    }
}
