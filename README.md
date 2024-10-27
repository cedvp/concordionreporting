This Concordion Extension allows to generate Reporting summaries on number of test OK vs NOK.
Each test can be linked to an external reference (JIRA ID for example)
and the starting page can be linked to an external reference (JIRA ID for the test plan for example).

The Extension will create
* a csv file with the summary of reportable test scenarios
* an html page with the same, ready to be sent by email to anyone who wants to be informed
* a json file with the same, ready to be imported into jira for Xray follow up
* an excel with the same, might you need the format additionally.

all files are produced in the same folder as the test results, with suffix "Report" and different extensions.
If your main page test result is GeoFixture.html: 

the files will be:
GeoFixtureReport.csv
GeoFixtureReport.html
GeoFixtureReport.json
GeoFixtureReport.xlsx

Good luck.

Cedric.
