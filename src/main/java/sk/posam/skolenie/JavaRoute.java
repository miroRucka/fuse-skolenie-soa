package sk.posam.skolenie;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author rucka
 */
public class JavaRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activemq:queue:router")
                .choice()
                .when(body().contains("1"))
                .to("activemq:queue:euro")
                .when(body().contains("2"))
                .to("activemq:queue:dolar")
                .when(body().contains("3"))
                .to("activemq:queue:libra")
                .otherwise()
                .to("activemq:queue:chyba");
    }
}
