package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class CamelDesign extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        getContext().addRoutes(new RouteBuilderA());
        getContext().addRoutes(new RouteBuilderB());
    }
}
