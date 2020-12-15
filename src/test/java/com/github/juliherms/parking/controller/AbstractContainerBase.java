package com.github.juliherms.parking.controller;

import org.testcontainers.containers.PostgreSQLContainer;

@SuppressWarnings("rawtypes")
public abstract class AbstractContainerBase {

	static final PostgreSQLContainer POSTGRE_SQL_CONTAINER;

	static {
		POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:10-alpine");
		POSTGRE_SQL_CONTAINER.start();

		// rewrite application config properties
		System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
		System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
		System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
		System.setProperty("spring.jpa.hibernate.ddl-auto", "create");
		System.setProperty("spring.jpa.show-sql", "true");
	}
}
