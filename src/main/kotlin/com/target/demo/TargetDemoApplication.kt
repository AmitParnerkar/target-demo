package com.target.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TargetDemoApplication

fun main(args: Array<String>) {
    runApplication<TargetDemoApplication>(*args)
}
