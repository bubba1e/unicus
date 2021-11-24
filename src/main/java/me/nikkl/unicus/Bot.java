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
import net.dv8tion.jda.api.JDA;

import java.util.ArrayList;
import java.util.Arrays;

public class Bot {
	private static final Bot _instance = new Bot();

	private JDA jda;
	private final ArrayList<BaseCommand> commands;

	public Bot() {
		this.commands = new ArrayList<BaseCommand>();
	}

	public static Bot inst() {
		return _instance;
	}

	public JDA getJDA() {
		return jda;
	}

	public ArrayList<BaseCommand> getCommands() {
		return commands;
	}

	public void setJDA(JDA jda) {
		this.jda = jda;
	}

	public void addCommands(BaseCommand ...commands) {
		this.commands.addAll(Arrays.asList(commands));
	}
}
