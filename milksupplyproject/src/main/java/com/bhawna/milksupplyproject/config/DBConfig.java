package com.bhawna.milksupplyproject.config;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class DBConfig extends Configuration {


	private static final String DATABASE = "database";

	@NotNull
	@Valid
	public DataSourceFactory database = new DataSourceFactory();

	public DBConfig() {

	}
	@JsonProperty(DATABASE)
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}

	@JsonProperty(DATABASE)
	public void setDataSourceFactory(DataSourceFactory factory) {
		this.database = factory;
	}


}
