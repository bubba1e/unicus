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
 * This file was created on 24.11.2021 at 21:52.
 */
package me.nikkl.unicus;

import me.nikkl.unicus.commands.BaseCommand;
import me.nikkl.unicus.parsing.MessageParser;
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.Arrays;

public class Bot {
	private JDA jda;
	private String prefix;
	private final ArrayList<BaseCommand> commands;
	private final MessageParser parser;

	private static Bot _instance;

	public Bot(String prefix) {
		this.prefix = prefix;
		this.commands = new ArrayList<>();
		this.parser = new MessageParser(this);
		Bot._instance = this;
	}

	public static Bot getInstance() {
		return Bot._instance;
	}

	public JDA getJda() {
		return jda;
	}

	public String getPrefix() {
		return prefix;
	}

	public ArrayList<BaseCommand> getCommands() {
		return commands;
	}

	public MessageParser getParser() {
		return parser;
	}

	public void setJda(JDA jda) {
		this.jda = jda;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void addCommands(BaseCommand ...commands) {
		this.commands.addAll(Arrays.asList(commands));
	}
}
