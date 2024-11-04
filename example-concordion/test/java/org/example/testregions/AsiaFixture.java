package org.example.testregions;

import nl.bluetrails.concordion.report.RecordingExtension;
import nl.bluetrails.concordion.report.TestIdAnnotation;
import org.concordion.api.ConcordionFixture;
import org.concordion.api.extension.Extensions;
import org.example.CapitalsTestFrameworkFixture;

@TestIdAnnotation(externalReferenceKey = "CONC-003")
@Extensions(RecordingExtension.class)
@ConcordionFixture
public class AsiaFixture extends CapitalsTestFrameworkFixture
{

}
