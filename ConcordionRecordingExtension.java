package org.concordion.ConcordionReport;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.*;
import java.util.concurrent.ConcurrentHashMap;


public class ConcordionRecordingExtension implements ConcordionExtension, OuterExampleListener {
    private static final ConcurrentHashMap<String, ConcordionExportableTestResult> recordedResults = new ConcurrentHashMap<>();

    public void addToMap(String key, ConcordionExportableTestResult value) {
        recordedResults.put(key, value);
    }

    public Object getFromMap(String key) {
        return recordedResults.get(key);
    }

    public int getNumberResults() {
        return recordedResults.size();
    }

    public ConcurrentHashMap<String,ConcordionExportableTestResult> getFullResults(){
        return new ConcurrentHashMap<>(recordedResults);
    }

    @Override
    public void beforeOuterExample(OuterExampleEvent event) {
        System.out.println(">>>>>>> starting");

    }

    @Override
    public void afterOuterExample(OuterExampleEvent event) {
        String externalRef = "";
        System.out.println(">>>>>>> after outer example");

            TestIdAnnotation externalRefAnnotation = event.getFixture().getFixtureType().getFixtureClass().getAnnotation(TestIdAnnotation.class);
            externalRef = externalRefAnnotation.externalReferenceKey();


        long failures = event.getResultSummary().getFailureCount();
        long exceptions = event.getResultSummary().getExceptionCount();
        ConcordionExportableTestResult xx = new ConcordionExportableTestResult(externalRef, event.getFixture().getFixtureType().getFixtureClass());
        xx.setResult(failures>0||exceptions>0?1:0);
        recordedResults.put(externalRef,xx);

    }

    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withOuterExampleListener(this);
    }

}
