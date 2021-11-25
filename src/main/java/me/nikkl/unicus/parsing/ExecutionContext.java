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
 * This file was created on 25.11.2021 at 19:11.
 */
package me.nikkl.unicus.parsing;

import me.nikkl.unicus.commands.BaseCommand;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * Contains relevant data for command execution.
 */
public class ExecutionContext {

	/**
	 * Raw message received event
	 */
	private MessageReceivedEvent event;

	/**
	 * Executed command
	 */
	private BaseCommand command;

	/**
	 * Command arguments
	 */
	private ArgumentCollection args;

	public ExecutionContext(MessageReceivedEvent event) {
		this.event = event;
	}

	public String getArgString(String name) {
		return args.getArgString(name);
	}

	public Member getArgMember(String name) {
		String mentionRaw = args.getArgString(name);
		return event.getGuild().getMemberById(mentionRaw.replaceAll("[^0-9]",""));
	}

	public User getArgUser(String name) {
		String mentionRaw = args.getArgString(name);
		return event.getJDA().getUserById(mentionRaw.replaceAll("[^0-9]",""));
	}

	public Role getArgRole(String name) {
		String mentionRaw = args.getArgString(name);
		return event.getGuild().getRoleById(mentionRaw.replaceAll("[^0-9]",""));
	}

	public TextChannel getArgChannel(String name) {
		String mentionRaw = args.getArgString(name);
		return event.getGuild().getTextChannelById(mentionRaw.replaceAll("[^0-9]",""));
	}

	public void setEvent(MessageReceivedEvent event) {
		this.event = event;
	}

	public void setCommand(BaseCommand command) {
		this.command = command;
	}

	public void setArgs(ArgumentCollection args) {
		this.args = args;
	}

	public MessageReceivedEvent getEvent() {
		return event;
	}

	public BaseCommand getCommand() {
		return command;
	}

	public ArgumentCollection getArgs() {
		return args;
	}
}
