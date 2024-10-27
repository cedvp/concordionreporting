package org.concordion.ConcordionReport;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestIdAnnotation {
String externalReferenceKey();

}
