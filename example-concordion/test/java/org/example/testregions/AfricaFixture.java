package org.example.testregions;

import nl.bluetrails.concordion.report.RecordingExtension;
import nl.bluetrails.concordion.report.TestIdAnnotation;
import org.concordion.api.ConcordionFixture;
import org.concordion.api.extension.Extensions;
import org.example.CapitalsTestFrameworkFixture;

@TestIdAnnotation(externalReferenceKey = "CONC-002")
@Extensions(RecordingExtension.class)
@ConcordionFixture
public class AfricaFixture extends CapitalsTestFrameworkFixture
{

}
