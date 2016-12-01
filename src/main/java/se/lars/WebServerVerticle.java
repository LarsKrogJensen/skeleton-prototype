package se.lars;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class WebServerVerticle extends AbstractVerticle {
    private static Logger log = LoggerFactory.getLogger(WebServerVerticle.class);

    @Override
    public void start(Future<Void> fut) {
        // Create a router object.
        Router router = Router.router(vertx);

        // Bind "/" to our hello message - so we are still compatible.
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response
                    .putHeader("content-type", "text/html")
                    .end("<h1>Hello from my first Vert.x 3 application</h1>");
        });

        int port = Optional.ofNullable(System.getenv("PORT"))
                           .map(Integer::parseInt)
                           .orElse(config().getInteger("http.port", 8080));
        // Create the HTTP server and pass the "accept" method to the request handler.
        vertx.createHttpServer()
             .requestHandler(router::accept)
             .listen(port,
                     server -> {
                         if (server.succeeded()) {
                             log.info("Http service started on port: {}", server.result().actualPort());
                             fut.complete();
                         } else {
                             fut.fail(server.cause());
                         }
                     }
             );
    }
}
