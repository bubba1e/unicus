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
 * This file was created on 24.11.2021 at 23:55.
 */
package me.nikkl.unicus.utils;

import me.nikkl.unicus.Bot;
import me.nikkl.unicus.commands.BaseCommand;
import me.nikkl.unicus.events.CommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageHandler extends Thread {
	private final MessageReceivedEvent event;

	public MessageHandler(MessageReceivedEvent event) {
		this.event = event;
	}

	public void run() {
		CommandEvent commandEvent = new CommandEvent(event);

		for (BaseCommand command : Bot.inst().getCommands()) {
			if (command.getName().equalsIgnoreCase(commandEvent.getName())) {
				ArgumentList argumentList = new ArgumentList(
						command.getArguments(),
						commandEvent.getArguments(),
						event.getMessage().getMentionedChannels(),
						event.getMessage().getMentionedMembers(event.getGuild()),
						event.getMessage().getMentionedRoles()
				);
				command.execute(commandEvent, argumentList);
			}
		}
	}
}
