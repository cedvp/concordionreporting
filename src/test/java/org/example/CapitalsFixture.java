package org.example;
import nl.bluetrails.concordion.report.ReportingExtension;
import nl.bluetrails.concordion.report.TestIdAnnotation;
import org.concordion.api.ConcordionFixture;
import org.concordion.api.extension.Extensions;

@TestIdAnnotation(externalReferenceKey = "CONC-000")
@Extensions(ReportingExtension.class)
@ConcordionFixture
public class CapitalsFixture extends CapitalsTestFrameworkFixture{
}
