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
 * This file was created on 24.11.2021 at 21:44.
 */
package me.nikkl.unicus.listeners;

import me.nikkl.unicus.Bot;
import me.nikkl.unicus.exceptions.CommandNotFoundException;
import me.nikkl.unicus.parsing.ExecutionContext;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Bot bot = Bot.getInstance();

		// ignore own messages
		if (event.getAuthor().getIdLong() == bot.getJda().getSelfUser().getIdLong()) {
			return;
		}

		// ignore normal messages
		if (!event.getMessage().getContentRaw().startsWith(bot.getPrefix())) {
			return;
		}

		System.out.println("test");
		// parse message
		ExecutionContext context;
		try {
			context = bot.getParser().parseMessage(event);
		} catch (CommandNotFoundException e) {
			return;
		}

		// execute command
		if (context != null) {
			context.getCommand().execute(context);
		}
	}
}
