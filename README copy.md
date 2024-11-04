This Concordion extension allows to count and export scenario results, 
attached to an external reference (which could refer to a JIRA Xray for example).

The extension records the final result for each Fixture (Success or Failure) called, and then reports
it from the main calling class :

1) as a csv file
2) as a summary html file
3) as a JSON file ready to be imported into JIRA
4) prints the final number of success and failures (1 failure or success per page)


to activate this:

1)  on each of the called pages that need to be reported:
    @TestIdAnnotation(externalReferenceKey = "CONC-999")
    @Extensions(RecordingExtension.class)

        where the externalReferenceKey is the unique test case external reference (the JIRA Xray for example)
        and the extension RecordingExtension ensures the test results is recorded.

2) on the starting page
    @TestIdAnnotation(externalReferenceKey = "CONC-000")
    @Extensions(ReportingExtension.class)

        where the externalReferenceKey is the unique id for the test plan as a whole (for example the JIRA Xray Test Plan id)
        and the extension ReportingExtension ensures reports are produced.

Good luck and have fun.

    Cedric.

