package org.example;

public class CapitalsTestFrameworkFixture {
    public String getCapital(String country){
        Capitals cap = new Capitals();
        return cap.getCapital(country);
    }
}
