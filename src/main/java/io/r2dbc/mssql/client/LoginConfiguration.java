/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.r2dbc.mssql.client;

import io.r2dbc.mssql.message.token.Login7;
import reactor.util.annotation.Nullable;

import java.util.Objects;
import java.util.UUID;

/**
 * Login configuration properties.
 * 
 * @author Mark Paluch
 */
public final class LoginConfiguration {

	private final String username;
	private final String password;
	private final String database;
	private final String hostname;
	@Nullable private final String appName;
	private final String serverName;
	@Nullable private final UUID connectionId;

	public LoginConfiguration(String username, String password, String database, String hostname, String appName,
			String serverName, @Nullable UUID connectionId) {
		this.username = Objects.requireNonNull(username, "Username must not be null");
		this.password = Objects.requireNonNull(password, "Password must not be null");
		this.database = Objects.requireNonNull(database, "Database must not be null");
		this.hostname = Objects.requireNonNull(hostname, "Hostname must not be null");
		this.appName = appName;
		this.serverName = Objects.requireNonNull(serverName, "Server name must not be null");
		this.connectionId = connectionId;
	}

	@Nullable
	UUID getConnectionId() {
		return this.connectionId;
	}

	Login7.Builder asBuilder() {

		Login7.Builder builder = Login7.builder().username(this.username).password(this.password).database(this.database)
				.hostName(this.hostname).serverName(this.serverName);

		if (this.appName != null) {
			builder.appName(this.appName);
		}
		return builder;
	}
}