package com.example.todoapp.todoservice

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@Repository
class TodoRepository(private val jdbcTemplate: JdbcTemplate) {
	fun createTodo(name: String) {
		jdbcTemplate.update("INSERT INTO todos (name) VALUES (?)", name)
	}

	fun findAllTodos(): List<String> {
		return jdbcTemplate.query("SELECT name FROM todos") { rs: ResultSet, _: Int ->
			rs.getString(1)
		}
	}
}