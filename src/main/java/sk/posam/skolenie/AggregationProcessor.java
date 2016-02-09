package sk.posam.skolenie;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Collection;

/**
 * @author rucka
 */
public class AggregationProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        Collection body = (Collection) exchange.getIn().getBody();
        if (body != null && !body.isEmpty()) {
            exchange.getOut().setBody(body.iterator().next() + " presiel som cez processor");
        }
    }
}
