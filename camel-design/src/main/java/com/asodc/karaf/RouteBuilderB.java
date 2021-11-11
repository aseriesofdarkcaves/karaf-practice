package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class RouteBuilderB extends RouteBuilder {
    public static final String INPUT = "direct:input";

    @Override
    public void configure() {
        from(INPUT)
                .setBody().constant("ROUTE B")
                .log("${body}");
    }
}
