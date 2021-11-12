package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderA extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:myTimer?fixedRate=true&period=1000")
                .setBody().constant("{{bodyRouteA}}")
                .log("${body}")
                .to(RouteBuilderB.INPUT);
    }
}
