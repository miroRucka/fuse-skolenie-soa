package sk.posam.skolenie;

import org.apache.camel.Exchange;
import org.apache.camel.Expression;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rucka
 */
public class JavaRoute extends RouteBuilder {

	Logger LOGGER = LoggerFactory.getLogger(JavaRoute.class);

	@Override
	public void configure() throws Exception {
		/*from("activemq:queue:skolenie").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("in exchange!!!");
				System.out.println(exchange);
				LOGGER.info("in exchange!!!");
			}
		}).to("cxf:bean:private?dataFormat=MESSAGE");
*/

		/*from("cxf:bean:public").setHeader("operationName", new Expression() {
			public <T> T evaluate(Exchange exchange, Class<T> aClass) {
				return (T) "dolar";
			}
		}).to("cxf:bean:private");*/

		from("activemq:queue:skolenie").process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				System.out.println("in exchange!!!");
				System.out.println(exchange);
				LOGGER.info("in exchange!!!");
				LOGGER.info(exchange.getIn().getBody().toString());
			}
		})
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
