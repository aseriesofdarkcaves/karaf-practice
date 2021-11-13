package com.asodc.karaf;

import org.apache.camel.builder.RouteBuilder;

public class PropertyEncryption extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:myTimer?fixedRate=true&period=1000")
                .log("Plaintext: {{password}}");
    }
}
