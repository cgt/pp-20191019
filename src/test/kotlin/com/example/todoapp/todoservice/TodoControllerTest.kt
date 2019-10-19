package com.example.todoapp.todoservice

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import jdk.nashorn.internal.objects.NativeArray.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@WebMvcTest
class TodoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var repository: TodoRepository

    @Test
    fun contextLoads() {
    }

    @Test
    fun `given no todos, listTodos returns empty array`() {
        every {
            repository.findAllTodos()
        } returns emptyList()

        mockMvc
            .perform(get("/todos"))
            .andExpect(status().isOk)
            .andExpect(content().json("[]"))
    }

    @Test
    fun `given one todo "foo", listTodos returns array of "foo"`() {
        every {
            repository.findAllTodos()
        } returns listOf("foo")

        mockMvc
            .perform(get("/todos"))
            .andExpect(status().isOk)
            .andExpect(content().json("[\"foo\"]"))

    }
}

@RestController
class TodoController(private val repository: TodoRepository) {
    @GetMapping("/todos")
    fun listTodos(): List<String> = repository.findAllTodos()
}