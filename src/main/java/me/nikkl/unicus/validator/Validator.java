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
 * This file was created on 25.11.2021 at 19:32.
 */
package me.nikkl.unicus.validator;

import me.nikkl.unicus.exceptions.ValidationException;

import java.util.regex.Pattern;

public class Validator {
	public static final String MentionMember = "<@!?[0-9]+>";
	public static final String MentionChannel = "<#[0-9]+>";
	public static final String MentionRole = "<@&[0-9]+>";

	private final Pattern pattern;
	private final String message;

	public Validator(String pattern, String message) {
		this.pattern = Pattern.compile(pattern);
		this.message = message;
	}

	public boolean validate(String input) throws ValidationException {
		if (!this.pattern.matcher(input).matches()) {
			throw new ValidationException(this.message);
		}
		return true;
	}
}
