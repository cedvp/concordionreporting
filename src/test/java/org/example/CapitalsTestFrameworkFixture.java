package org.example;

import java.util.HashMap;
import java.util.Map;

public class CapitalsTestFrameworkFixture {
    public String getCapital(String country){
            Map<String,String> capitals = new HashMap<>();
            capitals.put("BELGIUM","Brussels");
            capitals.put("FRANCE","Paris");
            capitals.put("NETHERLANDS","Amsterdam");
            capitals.put("LUXEMBOURG","Luxembourg");
            capitals.put("ITALY","Roma");
            capitals.put("SPAIN","Madrid");
            capitals.put("PORTUGAL","Lisboa");
            capitals.put("USA","Washington");
            capitals.put("CANADA","Ottawa");
            capitals.put("MEXICO","Mexico City");
            capitals.put("CHINA","Beijing");
            capitals.put("LAOS","Vientiane");
            capitals.put("VIETNAM","Hanoi");
            capitals.put("PERU","Lima");
            capitals.put("COLOMBIA","Bogota");
            return capitals.get(country.toUpperCase());
    }
}
