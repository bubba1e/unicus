/*
 * Copyright 2021 Nikkl <main@nikkl.me>.
 *
 * Licenced under the Apache License, Version 2.0 (the "License");
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
 *
 * This file was created on 24.11.2021 at 21:15.
 */
package me.nikkl.unicus;

import com.typesafe.config.ConfigFactory;
import me.nikkl.unicus.exceptions.ConfigException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    private static final Config _instance = new Config();

    private Path path;
    private com.typesafe.config.Config config;

    private String token;
    private String prefix;

    public Config() {
        ConfigFactory.invalidateCaches();

        try {
            path = Paths.get(System.getProperty("user.dir"), System.getProperty("config", "config.json"));

            if (!path.toFile().exists()) {
                throw new ConfigException("Config file not found: " + path.toString());
            }

            config = ConfigFactory.parseFile(path.toFile()).withFallback(ConfigFactory.load());

            token = config.getString("token");
            prefix = config.getString("prefix");
        } catch (ConfigException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Config inst() {
        return _instance;
    }

    public String getToken() {
        return token;
    }

    public String getPrefix() {
        return prefix;
    }
}
