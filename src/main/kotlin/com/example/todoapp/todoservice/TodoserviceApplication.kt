package com.example.todoapp.todoservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoserviceApplication

fun main(args: Array<String>) {
	runApplication<TodoserviceApplication>(*args)
}
