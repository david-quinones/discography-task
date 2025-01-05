package com.dquinones.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Configuration clas for Swagger
 *
 * This class uses OpenApi annotations to define Api informatiion and server detauls.
 */

@OpenAPIDefinition(
        info = @Info(
                title = "CodiTramuntana Discography Task",
                description = "Api per gestionar Artistes i LP",
                version = "1.0.0"
        ),
        servers = {
                @Server(
                        description = "Dev Server",
                        url = "http://localhost:8080"
                )
        }
)
public class SwaggerConfig {

}
