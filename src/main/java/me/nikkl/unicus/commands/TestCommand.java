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
 * This file was created on 25.11.2021 at 19:14.
 */
package me.nikkl.unicus.commands;

import me.nikkl.unicus.exceptions.ArgumentException;
import me.nikkl.unicus.parsing.ArgumentCollection;
import me.nikkl.unicus.parsing.ArgumentSlug;
import me.nikkl.unicus.parsing.ExecutionContext;
import me.nikkl.unicus.validator.Validator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class TestCommand extends BaseCommand {
	public TestCommand() throws ArgumentException {
		this.name = "test";
		this.description = "test command";
		this.arguments = new ArgumentCollection(
				ArgumentSlug
						.create("Member")
						.validator(new Validator(Validator.MentionMember, "Invalid Member")),
				ArgumentSlug
						.create("Role")
						.validator(new Validator(Validator.MentionRole, "Invalid Role"))
		);
	}

	@Override
	public void execute(ExecutionContext context) {
		context.getArgMember("Member").getRoles().add(
				context.getArgRole("Role")
		);
	}
}
