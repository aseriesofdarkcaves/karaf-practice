package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class Greeter extends RouteBuilder {
    @Override
    public void configure() {
        from("jetty:http://localhost:8080/greeter")
                .transform().constant("{{greeterMessage}}");
    }
}
