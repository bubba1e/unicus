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
 * This file was created on 24.11.2021 at 20:46.
 */
package me.nikkl.unicus;

import me.nikkl.unicus.commands.TestCommand;
import me.nikkl.unicus.exceptions.ArgumentException;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Unicus {
	public static void main(String[] args) throws LoginException, ArgumentException {
		BotConfigurator configurator = new BotConfigurator(Config.inst().getPrefix(), Config.inst().getToken());

		configurator.builder.setEnableShutdownHook(true);
		configurator.builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
		configurator.builder.setBulkDeleteSplittingEnabled(false);
		configurator.builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

		configurator.addCommands(new TestCommand());

		configurator.start();
	}
}
