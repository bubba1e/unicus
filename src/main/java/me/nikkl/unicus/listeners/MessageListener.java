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
import me.nikkl.unicus.Config;
import me.nikkl.unicus.commands.BaseCommand;
import me.nikkl.unicus.events.CommandEvent;
import me.nikkl.unicus.utils.ArgumentList;
import me.nikkl.unicus.utils.MessageHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().getIdLong() == Bot.inst().getJDA().getSelfUser().getIdLong()) {
			return;
		}

		if (!event.getMessage().getContentRaw().startsWith(Config.inst().getPrefix())) {
			return;
		}

		try {
			new MessageHandler(event).start();
		} catch (Exception e) {
			event.getChannel().sendMessage("Es ist ein unbekannter Fehler aufgetreten.").queue();
		}
	}
}
