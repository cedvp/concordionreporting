package org.example.testregions;

import nl.bluetrails.concordion.report.RecordingExtension;
import nl.bluetrails.concordion.report.TestIdAnnotation;
import org.concordion.api.ConcordionFixture;
import org.concordion.api.extension.Extensions;
import org.example.CapitalsTestFrameworkFixture;

@TestIdAnnotation(externalReferenceKey = "CONC-004")
@Extensions(RecordingExtension.class)
@ConcordionFixture
public class NorthAmericaFixture extends CapitalsTestFrameworkFixture
{

}
