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
import me.nikkl.unicus.listeners.MessageListener;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class BotConfigurator {
	public JDABuilder builder;
	public Bot bot;

	public BotConfigurator(String prefix, String token) throws LoginException {
		this.builder = JDABuilder.createDefault(token);
		this.bot = new Bot(prefix);
	}

	public void addCommands(BaseCommand ...commands) {
		bot.addCommands(commands);
	}

	public void start() throws LoginException {
		bot.setJda(builder.build());
		bot.getJda().addEventListener(new MessageListener());
	}
}
