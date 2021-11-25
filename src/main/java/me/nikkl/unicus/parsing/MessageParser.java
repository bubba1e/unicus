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
 * This file was created on 25.11.2021 at 19:02.
 */
package me.nikkl.unicus.parsing;

import me.nikkl.unicus.Bot;
import me.nikkl.unicus.commands.BaseCommand;
import me.nikkl.unicus.exceptions.CommandNotFoundException;
import me.nikkl.unicus.exceptions.InvalidFormatException;
import me.nikkl.unicus.exceptions.ValidationException;
import me.nikkl.unicus.validator.Validator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.regex.Matcher;

public class MessageParser {
	/**
	 * Needed for message parsing & command execution.
	 */
	private final Bot bot;

	public MessageParser(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Parses message event and returns execution context.
	 *
	 * @param event raw message received event
	 * @return execution context
	 * @throws CommandNotFoundException when command name is not registered
	 * @throws ValidationException      when one of the arguments in formatted incorrectly
	 */
	public ExecutionContext parseMessage(MessageReceivedEvent event) throws CommandNotFoundException {
		ExecutionContext context = new ExecutionContext(event);

		// prepare content for parsing
		String[] parts = normalizeContent(event.getMessage().getContentRaw()).split(" ");

		// find command by command name
		BaseCommand command = findCommandByName(parts[0]);

		// store command in context or throw an error
		// if the command was not found
		if (command == null) {
			throw new CommandNotFoundException("Invalid command.");
		}
		context.setCommand(command);

		// parse arguments for command execution
		try {
			context.setArgs(parseArguments(
					command.getArgumentCollection(),
					String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
			));
		} catch (ValidationException exception) {
			command.handleValidationError(event, exception);
		}

		// return context
		return context;
	}

	private ArgumentCollection parseArguments(ArgumentCollection collection, String arguments) throws ValidationException {
		Matcher matcher = collection.toPattern().matcher(arguments);

		matcher.matches();

		for (ArgumentSlug slug : collection.getSlugs()) {
			// get argument value from matcher
			String value = matcher.group(slug.getName());

			// validate argument if a validator is registered
			// throws error if validation fails
			Validator validator = slug.getValidator();
			if (validator != null) {
				if (!validator.validate(value)) {
					return null;
				}
			}

			// set value if validation was successful
			slug.setValue(value);
		}

		return collection;
	}

	/**
	 * Prepares content for parsing.
	 *
	 * @param content raw message content
	 * @return normalized content
	 */
	private String normalizeContent(String content) {
		// remove prefix
		content = content.substring(bot.getPrefix().length());

		// trim whitespaces
		content = content.replaceAll(" +", " ");

		return content;
	}

	/**
	 * Find command by name or alias.
	 *
	 * @param name name or alias
	 * @return command or null
	 */
	public BaseCommand findCommandByName(String name) {
		for (BaseCommand command : bot.getCommands()) {
			if (command.getName().equalsIgnoreCase(name)) {
				return command;
			}
		}
		return null;
	}
}
