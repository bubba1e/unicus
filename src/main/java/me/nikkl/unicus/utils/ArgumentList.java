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
 * This file was created on 24.11.2021 at 22:47.
 */
package me.nikkl.unicus.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class ArgumentList {
	private final HashMap<String, String> raw;
	private final HashMap<String, TextChannel> channels;
	private final HashMap<String, Member> members;
	private final HashMap<String, Role> roles;

	public ArgumentList(String[] names, String[] values, List<TextChannel> channels, List<Member> members, List<Role> roles) {
		this.raw = new HashMap<>();
		this.channels = new HashMap<>();
		this.members = new HashMap<>();
		this.roles = new HashMap<>();

		Pattern channelPattern = Pattern.compile("^<#[0-9]+>$");
		Pattern memberPattern = Pattern.compile("^<@!?[0-9]+>$");
		Pattern rolePattern = Pattern.compile("^<@&[0-9]+>$");

		for (int i = 0; i < Math.min(names.length, values.length); i++) {
			if (channelPattern.matcher(values[i]).matches()) {
				this.channels.put(names[i], channels.iterator().next());
				continue;
			}

			if (memberPattern.matcher(values[i]).matches()) {
				this.members.put(names[i], members.iterator().next());
				continue;
			}

			if (rolePattern.matcher(values[i]).matches()) {
				this.roles.put(names[i], roles.iterator().next());
				continue;
			}

			this.raw.put(names[i], values[i]);
		}
	}

	public TextChannel getChannel(String name) {
		return channels.getOrDefault(name, null);
	}

	public Member getMember(String name) {
		return members.getOrDefault(name, null);
	}

	public Role getRole(String name) {
		return roles.getOrDefault(name, null);
	}

	public String getString(String name) {
		return raw.getOrDefault(name, null);
	}
}
