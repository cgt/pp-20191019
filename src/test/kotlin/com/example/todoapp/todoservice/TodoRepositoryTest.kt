package com.example.todoapp.todoservice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.ResultSet

@DataJdbcTest
@Import(TodoRepository::class)
class TodoRepositoryTest {
	@Autowired
	private lateinit var repository: TodoRepository

	@Test
	fun `create todo`() {
		repository.createTodo("test")
		val query = repository.findAllTodos()
		assertThat(query).containsExactly("test")
	}

	@Test
	fun `given no todos, findAllTodos returns empty list`() {
        assertThat(repository.findAllTodos()).isEmpty()
	}
}

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