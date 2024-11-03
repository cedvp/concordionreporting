package nl.bluetrails.concordion.report;

import org.concordion.api.extension.ConcordionExtender;
import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.listener.OuterExampleEvent;
import org.concordion.api.listener.OuterExampleListener;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ReportingExtension implements ConcordionExtension, OuterExampleListener {
ArrayList<String> failures = new ArrayList<>();
    @Override
    public void beforeOuterExample(OuterExampleEvent event) {
    }

    String pathAndFileAsString = "";
    String filename = "";
    String folderString = "";
    Path directory = null;
    String fileNameCsv, fileNameJson, fileNameHtml = "";
    //final paths + names
    Path fileCsv = null;
    Path fileJson = null;
    Path fileHtml = null;

    String testplanExternalReference= "";

    long countSuccesses = 0;
    long countFailures = 0;
    long countTotal = 0;
    long percentageSucceeded = 0;
    long percentageFailed = 0;
    ConcurrentHashMap<String, TestResultCompact> results = null;
    ArrayList <String[]> lines = new ArrayList<>();

    private void setResultInLinesArray(){
        String[] header = ( new String[]{"TestPlan","TestCaseId","Result","TestClass","TestDate"});
        lines.add(header);
        for(TestResultCompact x: results.values()){
            String[] line = (new String[]{testplanExternalReference,x.getExternalRef(),x.getResultReadable(),x.getConcordionTestClass().getName(),x.getExecutionDateTime().toString()});
            lines.add(line);
        }
    }

    private void writeCSV(){
        try (FileWriter writer = new FileWriter(fileCsv.toString())) {
            for (String[] row : lines) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
            System.out.println("CSV Export file written to:");
            System.out.println(fileCsv.toUri());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getFileNames(OuterExampleEvent event){
        pathAndFileAsString = event.getResultSummary().getSpecificationDescription(); // Original file path

        int lastbackslash = pathAndFileAsString.lastIndexOf("/");
        if(lastbackslash!=-1){
            folderString = pathAndFileAsString.substring(0,lastbackslash);
            filename = pathAndFileAsString.substring(lastbackslash+1,pathAndFileAsString.length());
        }

        directory = Paths.get(URI.create(folderString));

        // Find the file name

        fileNameCsv  = fileNameJson = fileNameHtml= new String(filename);
        fileNameCsv = "Report_"+fileNameCsv.replace(".html",".csv");
        fileCsv = directory.resolve(fileNameCsv);
        fileHtml = directory.resolve(fileNameHtml);
        fileJson = directory.resolve(fileNameJson);
    }
    private void setTestplanExternalReference(OuterExampleEvent event){
        TestIdAnnotation externalRefAnnotation = event.getFixture().getFixtureType().getFixtureClass().getAnnotation(TestIdAnnotation.class);
        testplanExternalReference = externalRefAnnotation.externalReferenceKey();
    }
    private void setCounts(){
        countFailures =  results.values().stream().filter(result -> result.getResultReadable().equalsIgnoreCase("FAILURE")).count();
        countSuccesses =  results.values().stream().filter(result -> result.getResultReadable().equalsIgnoreCase("SUCCESS")).count();
        countTotal =  countFailures+countSuccesses;
        percentageSucceeded = Math.round(((double)countSuccesses/(double)countTotal)*100);
        percentageFailed = Math.round(((double)countFailures/(double)countTotal)*100);
    }
    @Override
    public void afterOuterExample(OuterExampleEvent event) {
        getFileNames(event);
        setTestplanExternalReference(event);
        results = new RecordingExtension().getFullResults();
        System.out.println("=======      BEGIN OF EXPORT RESULTS EXTENSION     =================");
        setCounts();
        setResultInLinesArray();
        writeCSV();

        System.out.println("final stats:");
        System.out.println("Successes = "+countSuccesses+"/"+countTotal+" (= "+percentageSucceeded+"%)");
        System.out.println("Failures = "+countFailures+"/"+countTotal+" (= "+percentageFailed+"%)");
        System.out.println("=====       END OF EXPORT RESULTS EXTENSION        =================");
    }



    @Override
    public void addTo(ConcordionExtender concordionExtender) {
        concordionExtender.withOuterExampleListener(this);
    }
}
