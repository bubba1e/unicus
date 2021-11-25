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
 * This file was created on 24.11.2021 at 22:22.
 */
package me.nikkl.unicus.commands;

import me.nikkl.unicus.exceptions.ArgumentException;
import me.nikkl.unicus.exceptions.InvalidFormatException;
import me.nikkl.unicus.exceptions.ValidationException;
import me.nikkl.unicus.parsing.ArgumentCollection;
import me.nikkl.unicus.parsing.ExecutionContext;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class BaseCommand {
	protected String name;
	protected String description;
	protected ArgumentCollection arguments;

	public BaseCommand() throws ArgumentException {
		this.name = null;
		this.description = null;
		this.arguments = null;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public ArgumentCollection getArgumentCollection() {
		return arguments;
	}

	public void handleValidationError(MessageReceivedEvent event, ValidationException error) {
		event.getChannel().sendMessage(error.getMessage()).queue();
	}

	public void handleFormatError(MessageReceivedEvent event, InvalidFormatException error) {
		event.getChannel().sendMessage(error.getMessage()).queue();
	}

	public abstract void execute(ExecutionContext context);
}
