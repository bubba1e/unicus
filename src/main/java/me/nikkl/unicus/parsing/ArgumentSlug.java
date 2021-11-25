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
 * This file was created on 25.11.2021 at 17:36.
 */
package me.nikkl.unicus.parsing;

import java.util.regex.Pattern;

public class ArgumentSlug {
	private String name;
	private String defaultValue = null;
	private String value = null;

	public ArgumentSlug(String name, String defaultValue) {
		this.name = name;
		this.defaultValue = defaultValue;
	}

	public ArgumentSlug(String name) {
		this.name = name;
		this.defaultValue = null;
	}

	public String getName() {
		return name;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getValue() {
		if (value == null && defaultValue != null) {
			return defaultValue;
		}
		return value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Pattern toPattern() {
		return Pattern.compile(String.format("(?<%s>[^ ]+)%s", name, defaultValue != null ? "?" : ""));
	}
}
