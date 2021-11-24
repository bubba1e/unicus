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
 * This file was created on 24.11.2021 at 22:25.
 */
package me.nikkl.unicus.events;

import me.nikkl.unicus.Config;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandEvent {
	private final MessageReceivedEvent event;
	private final String name;
	private final String[] arguments;

	public CommandEvent(MessageReceivedEvent event) {
		String[] split = event.getMessage().getContentRaw().substring(Config.inst().getPrefix().length()).split(" ");
		List<TextChannel> mentionedChannels = event.getMessage().getMentionedChannels();
		List<User> mentionedUsers = event.getMessage().getMentionedUsers();
		List<Role> mentionedRoles = event.getMessage().getMentionedRoles();

		this.event = event;
		this.name = split[0];
		this.arguments = Arrays.copyOfRange(split, 1, split.length);

	}

	public String getName() {
		return name;
	}

	public String[] getArguments() {
		return arguments;
	}

	public MessageReceivedEvent getEvent() {
		return event;
	}
}
