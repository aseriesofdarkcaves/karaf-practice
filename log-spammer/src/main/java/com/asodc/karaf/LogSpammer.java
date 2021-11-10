package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class LogSpammer extends RouteBuilder {
    @Override
    public void configure() {
        from("timer://timer?period=1000")
                .log("{{message}}");
    }
}
