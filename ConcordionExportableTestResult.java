package org.concordion.ConcordionReport;

import java.time.LocalDateTime;

public class ConcordionExportableTestResult {
    public String getExternalRef() {
        return externalRef;
    }

    public void setExternalRef(String externalRef) {
        this.externalRef = externalRef;
    }

    public String getResultReadable() {
        return resultReadable;
    }

    public void setResultReadable(String resultReadable) {
        this.resultReadable = resultReadable;
    }

    public int getResult() {
        return result;
    }

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public Class getConcordionTestClass() {
        return concordionTestClass;
    }

    public void setConcordionTestClass(Class concordionTestClass) {
        this.concordionTestClass = concordionTestClass;
    }

    String externalRef;
    int result;
    String resultReadable;
    LocalDateTime executionDateTime;
    Class concordionTestClass;
    public ConcordionExportableTestResult(String ref, Class classTest){
        externalRef = ref;
        executionDateTime = LocalDateTime.now();
        concordionTestClass = classTest;
    }

    public void setResult(int result) {
        this.result = result;
        this.resultReadable=this.result==0?"SUCCESS":"FAILURE";
    }


}
