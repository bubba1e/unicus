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
 * This file was created on 25.11.2021 at 18:14.
 */
package me.nikkl.unicus.parsing;

import me.nikkl.unicus.exceptions.InvalidFormatException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgumentParser {
	public static ArgumentCollection parseArguments(String prefix, ArgumentCollection collection, MessageReceivedEvent event) throws InvalidFormatException {
		String messageContent = event.getMessage().getContentRaw().substring(prefix.length()).replaceAll(" +", " ");
		int firstSpaceIndex = messageContent.indexOf(' ');
		String argsString = messageContent.substring(firstSpaceIndex != -1 ? firstSpaceIndex : messageContent.length()).trim();
		Matcher matcher = collection.toPattern().matcher(argsString);

		if (!matcher.matches()) {
			System.out.println(argsString);
			System.out.println(collection.toPattern().toString());
			throw new InvalidFormatException("The provided message is formatted incorrectly.");
		}

		for (ArgumentSlug slug : collection.getSlugs()) {
			slug.setValue(matcher.group(slug.getName()));
		}

		return collection;
	}
}
