package com.bhawna.milksupplyproject;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bhawna.milksupplyproject.config.DBConfig;
import com.bhawna.milksupplyproject.resource.UserResource;
import com.bhawna.milksupplyproject.service.UserService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<DBConfig> {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	private static final String SQL = "sql";

	@Override
	public void run(DBConfig config, Environment env) throws Exception {
		LOGGER.info("Registering REST resources");
		final DataSource dataSource = config.getDataSourceFactory().build(env.metrics(), SQL);
		DBI dbi = new DBI(dataSource);

		env.jersey().register(new UserResource(dbi.onDemand(UserService.class)));
	}

	@Override
	public void initialize(Bootstrap<DBConfig> b) {
	}

	public static void main(String[] args) throws Exception {
		new App().run(args);
	}
}
