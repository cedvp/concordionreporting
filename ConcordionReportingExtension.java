package org.concordion.ConcordionReport;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.OuterExampleEvent;
import org.concordion.api.listener.OuterExampleListener;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ConcordionReportingExtension implements ConcordionExtension, OuterExampleListener {
ArrayList<String> failures = new ArrayList<>();
    @Override
    public void beforeOuterExample(OuterExampleEvent event) {
        System.out.println(">>>>>>> starting");

    }

    @Override
    public void afterOuterExample(OuterExampleEvent event) {

        String originalPath = event.getResultSummary().getSpecificationDescription(); // Original file path
        String newFileName = "Blabla.csv"; // New file name

        // Create a File object for the original file
        File originalFile = new File(originalPath);

        // Find the file name

        String csvFileName = new String(originalFile.getName());
        csvFileName = csvFileName.replace(".html",".csv");

        String path = new String(originalFile.getPath());
        path = path.replace(originalFile.getName(),"");
        String path2 = path.replace("file:","");
        String filePath = path2+csvFileName;
        File directory = new File(path2); // Specify the directory name

        // Creates the directory if it does not exist
        if (!directory.exists()) {
            boolean created = directory.mkdir(); // Use mkdirs() for nested directories
            if (created) {
                System.out.println("Directory created successfully!");
            } else {
                System.out.println("Failed to create directory.");
            }
        } else {
            System.out.println("Directory already exists.");
        }




        String testplanExternalReference = "blabla";
        TestIdAnnotation externalRefAnnotation = event.getFixture().getFixtureType().getFixtureClass().getAnnotation(TestIdAnnotation.class);
        testplanExternalReference = externalRefAnnotation.externalReferenceKey();

        System.out.println(">>>>>>> finished");
        ConcordionRecordingExtension xx = new ConcordionRecordingExtension();
        ConcurrentHashMap<String,ConcordionExportableTestResult> results = xx.getFullResults();
        System.out.println(xx.getNumberResults());
        ArrayList <String[]> lines = new ArrayList<>();
        //creating a csv
        String[] header = ( new String[]{"TestPlan","TestCaseId","Result","TestClass","TestDate"});
        lines.add(header);

        for(ConcordionExportableTestResult x: results.values()){
            String[] line = (new String[]{testplanExternalReference,x.getExternalRef(),x.getResultReadable(),x.getConcordionTestClass().getName(),x.getExecutionDateTime().toString()});
            lines.add(line);
        }
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : lines) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //creating an html

    }

    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withOuterExampleListener(this);
    }
}
