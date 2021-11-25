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
 * This file was created on 25.11.2021 at 17:57.
 */
package me.nikkl.unicus.parsing;

import me.nikkl.unicus.exceptions.ArgumentException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ArgumentCollection {
	private final List<ArgumentSlug> slugs;

	public ArgumentCollection(ArgumentSlug... slugs) throws ArgumentException {
		boolean optionalSlugsFound = false;
		for (ArgumentSlug slug : slugs) {
			if (slug.getDefaultValue() != null) {
				optionalSlugsFound = true;
			} else if (optionalSlugsFound) {
				throw new ArgumentException("Optional arguments cannot precede a required argument.");
			}
		}

		this.slugs = Arrays.asList(slugs);
	}

	public Pattern toPattern() {
		String[] patternStrings = new String[slugs.size()];

		for (int i = 0; i < slugs.size(); i++) {
			patternStrings[i] = slugs.get(i).toPattern().toString();
		}

		return Pattern.compile(String.format("^%s$", String.join(" ?", patternStrings)));
	}

	public List<ArgumentSlug> getSlugs() {
		return slugs;
	}

	public String getArgString(String name) {
		for (ArgumentSlug slug : slugs) {
			if (slug.getName().equalsIgnoreCase(name)) {
				return slug.getValue();
			}
		}
		return null;
	}
}
