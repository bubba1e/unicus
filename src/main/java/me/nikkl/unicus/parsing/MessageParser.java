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
import me.nikkl.unicus.Config;
import me.nikkl.unicus.commands.BaseCommand;
import me.nikkl.unicus.exceptions.InvalidCommandException;
import me.nikkl.unicus.exceptions.InvalidFormatException;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageParser {
	public static ParsedMessage parseEvent(String prefix, MessageReceivedEvent event) throws InvalidFormatException, InvalidCommandException {
		// normalize and split message
		String messageContent = event.getMessage().getContentRaw().substring(prefix.length()).replaceAll(" +", " ");
		String[] messageSplit = messageContent.split(" ");

		// find command by name
		BaseCommand command = MessageParser.findCommandByName(messageSplit[0]);

		if (command == null) {
			throw new InvalidCommandException("Command not found.");
		}

		// parse arguments
		return new ParsedMessage(
				command,
				ArgumentParser.parseArguments(Config.inst().getPrefix(), command.getArgumentCollection(), event)
		);
	}

	public static BaseCommand findCommandByName(String name) {
		for (BaseCommand command : Bot.inst().getCommands()) {
			if (command.getName().equalsIgnoreCase(name)) {
				return command;
			}
		}
		return null;
	}
}
