package com.target.demo.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.servers.Server

@OpenAPIDefinition(
    info = Info(title = "Target Demo API", version = "v1"),
    servers = [Server(url = "https://target-demo.spinachsoftware.com")]
)
class OpenAPIConfig