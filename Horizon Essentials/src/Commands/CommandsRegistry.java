package Commands;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.util.StringUtil;

import com.google.common.collect.ImmutableList;

import Horizon_Essentials.IMessageManager;
import Horizon_Essentials.Main;
import Utility.DataUtil;
import Utility.VaultDataHooker;
import net.milkbowl.vault.Vault;

public class CommandsRegistry {
	public IMessageManager m = Main.getInst();

	public static void Registercommands() {
		commandRegister("broadcast", new CommandBroadcast());
		commandRegister("heal", new CommandHeal());
		commandRegister("gamemode", new CommandGamemode());
		commandRegister("item", new CommandItem());
		commandRegister("give", new CommandGive());
		commandRegister("motd", new CommandSetMotd());
		commandRegister("tp", new CommandTeleport());
		commandRegister("nick", new CommandChangeNickname());
		commandRegister("slay", new CommandSlay());

		commandRegister("kill", new CommandKill());
		commandRegister("say", new CommandSay());
		commandRegister("whisper", new CommandWhisper());
		commandRegister("suicide", new CommandSuicide());
		commandRegister("lag", new CommandLag());
		commandRegister("tpa", new CommandTpask());
		commandRegister("tpalist", new CommandTPaList());
		commandRegister("tpdeny", new CommandTPDeny());
		commandRegister("tpaccept", new CommandTpaccept());
		commandRegister("mute", new CommandMute());
		commandRegister("unmute", new CommandUnmute());
		commandRegister("setwarp", new CommandSetWarp());
		commandRegister("warp", new CommandWarp());
		commandRegister("itemdata", new CommandItemData());
		commandRegister("realname", new CommandRealname());
		commandRegister("setspawn", new CommandSetSpawn());
		commandRegister("spawn", new CommandSpawn());
		commandRegister("tpacceptall", new CommandTPAcceptAll());
		commandRegister("tpall", new CommandTPAll());
		commandRegister("gms", new CommandGMS());
		commandRegister("gmc", new CommandGMC());
		commandRegister("gma", new CommandGMA());
		commandRegister("killall", new CommandKillAll());
		commandRegister("warplist", new CommandWarpList());

		commandRegister("ignore", new CommandIgnore());
		commandRegister("ignorechat", new CommandIgnoreChat());
		commandRegister("ignoretpa", new CommandIgnoreTpa());
		commandRegister("ignorewhisper", new CommandIgnoreWhisper());

		commandRegister("unignore", new CommandUnignore());
		commandRegister("unignorechat", new CommandUnignoreChat());
		commandRegister("unignoretpa", new CommandUnignoreTpa());
		commandRegister("unignorewhisper", new CommandUnignoreWhisper());
		commandRegister("fly", new CommandFly());
		commandRegister("feed", new CommandFeed());
		try{
			commandRegister("server", new CommandServer(),
					Arrays.asList("chat", "move", "blockdamage", "break", "place", "pickup", "drop", "pvp", "god",
							"interact", "explosionblock", "levellimit", "level", "채팅", "동작", "블럭데미지", "블럭설치", "블럭파괴", "픽업",
							"드랍", "무적", "폭발블럭파괴", "상호작용", "레벨제한", "레벨"),
					Arrays.asList("활성화", "비활성화", "enable", "disable"));
		}catch(Exception e){commandRegister("server", new CommandServer());}
		commandRegister("top", new CommandTop());
		commandRegister("jump", new CommandJump());
		commandRegister("more", new CommandMore());
		commandRegister("book",new CommandBook());
		commandRegister("setjail",new CommandSetJail());
		commandRegister("jail",new CommandJail());
		commandRegister("tempban",new CommandTempBan());
		commandRegister("unban",new CommandUnban());
		commandRegister("strike",new CommandShock());
		commandRegister("break",new CommandBreak());
		commandRegister("jailview",new CommandViewJail());
		commandRegister("clear",new CommandClearInventory());
		commandRegister("summon",new CommandSummon());
		commandRegister("seen",new CommandSeen());
		if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
			new VaultDataHooker(Main.getInst());
			Vault va = (Vault) Bukkit.getPluginManager().getPlugin("Vault");
			try {
				Method m = va.getClass().getDeclaredMethod("hookEconomy", String.class, Class.class,
						ServicePriority.class, String[].class);
				m.setAccessible(true);
				m.invoke(va, "Horizon Essentials", VaultDataHooker.base.getClass(), ServicePriority.Highest,
						new String[] { "Horizon_Essentials.Main" });
				commandRegister("money", new CommandEconomy(),
						DataUtil.asArray("view", "add", "remove", "set", "reset", "조회", "지급", "추가", "차감", "제거", "초기화"));
			} catch (Exception e) {
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("§f * §cCannot Find Vault / Skip Economy Registration");
			if (!Main.checkEconomy()) {
				commandRegister("money", new UnusedCommand());
			}
		}
	}

	public static void commandRegister(String s, CommandExecutor excute) {
		Main.getInst().getCommand(s).setExecutor(excute);

	}

	public static void commandRegister(String s, CommandExecutor excute, final List<String> complete) {
		Main.getInst().getCommand(s).setExecutor(excute);
		Main.getInst().getCommand(s).setTabCompleter(new TabCompleter() {
			@Override
			public List<String> onTabComplete(CommandSender sender, Command arg1, String alias, String[] args) {
				Validate.notNull(sender, "명령어 사용자는 null이 될 수 없습니다.");
				Validate.notNull(args, "명령어 매개 변수는 null이 될 수 없습니다.");
				Validate.notNull(alias, "사용한 명령어 값은 null이 될 수 업습니다.");
				if (!(sender instanceof Player)) {
					return ImmutableList.of();
				} else {
					if (args.length == 1) {
						String lastWord = args[args.length - 1];
						if (lastWord.length() == 0) {
							return ImmutableList.of();
						} else {
							ArrayList<String> matchedAliases = new ArrayList<String>();
							for (int i$ = 0; i$ < complete.size(); ++i$) {
								String name = complete.get(i$);
								if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
									matchedAliases.add(name);
								}
							}

							Collections.sort(matchedAliases, String.CASE_INSENSITIVE_ORDER);
							return matchedAliases;
						}
					} else {
						String lastWord = args[args.length - 1];
						ArrayList<String> matchedPlayers = new ArrayList<String>();
						Player[] arr$ = sender.getServer().getOnlinePlayers();
						int len$ = arr$.length;

						for (int i$ = 0; i$ < len$; ++i$) {
							Player player = arr$[i$];
							String name = player.getName();
							if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
								matchedPlayers.add(name);
							}
						}

						Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
						return matchedPlayers;
					}
				}
			};
		});

	}

	public static void commandRegister(String s, CommandExecutor excute, final List<String> complete,
			final List<String> secondComplete) {
		Main.getInst().getCommand(s).setExecutor(excute);
		Main.getInst().getCommand(s).setTabCompleter(new TabCompleter() {
			@Override
			public List<String> onTabComplete(CommandSender sender, Command arg1, String alias, String[] args) {
				Validate.notNull(sender, "명령어 사용자는 null이 될 수 없습니다.");
				Validate.notNull(args, "명령어 매개 변수는 null이 될 수 없습니다.");
				Validate.notNull(alias, "사용한 명령어 값은 null이 될 수 업습니다.");
				if (!(sender instanceof Player)) {
					return ImmutableList.of();
				} else {
					if (args.length == 1) {
						String lastWord = args[args.length - 1];
						if (lastWord.length() == 0) {
							return ImmutableList.of();
						} else {
							ArrayList<String> matchedAliases = new ArrayList<String>();
							for (int i$ = 0; i$ < complete.size(); ++i$) {
								String name = complete.get(i$);
								if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
									matchedAliases.add(name);
								}
							}

							Collections.sort(matchedAliases, String.CASE_INSENSITIVE_ORDER);
							return matchedAliases;
						}
					} else if (args.length == 2) {
						String lastWord = args[args.length - 1];
						if (lastWord.length() == 0) {
							return ImmutableList.of();
						} else {
							ArrayList<String> matchedAliases = new ArrayList<String>();
							for (int i$ = 0; i$ < secondComplete.size(); ++i$) {
								String name = secondComplete.get(i$);
								if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
									matchedAliases.add(name);
								}
							}

							Collections.sort(matchedAliases, String.CASE_INSENSITIVE_ORDER);
							return matchedAliases;
						}
					} else {
						String lastWord = args[args.length - 1];
						ArrayList<String> matchedPlayers = new ArrayList<String>();
						Player[] arr$ = sender.getServer().getOnlinePlayers();
						int len$ = arr$.length;

						for (int i$ = 0; i$ < len$; ++i$) {
							Player player = arr$[i$];
							String name = player.getName();
							if (StringUtil.startsWithIgnoreCase(name, lastWord)) {
								matchedPlayers.add(name);
							}
						}

						Collections.sort(matchedPlayers, String.CASE_INSENSITIVE_ORDER);
						return matchedPlayers;
					}
				}
			};
		});

	}
}
