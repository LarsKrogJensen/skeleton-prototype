package se.lars;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

public class Main {
    public static void main(String[] args) {
        System.setProperty("vertx.logger-delegate-factory-class-name",
                           "io.vertx.core.logging.SLF4JLogDelegateFactory");
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(WebServerVerticle.class.getName());
    }
}
