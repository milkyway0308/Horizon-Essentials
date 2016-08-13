package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import DataUtil.PermissionType;
import Horizon_Essentials.Event.Horizon_EventCancelActor;
import Utility.DataUtil;
import Utility.PlayerUtil;

public class CommandServer extends CommandsRegistry implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			if(!PlayerUtil.hasPermission(sender, PermissionType.CommandServer))
			{
				super.m.sendMessage(sender, "��ɾ� ���� ����");
				return false;
			}
			if(args.length <= 1)
			{
				sender.sendMessage("    ��aH��7orizon ��aE��7ssentials Server Controller");
				if(DataUtil.isAlpha(label))
				{
					sender.sendMessage("��7/" + label + " chat <enable/disable> ��e - ��7������ ���� ������ ��ü ä���� Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " move <enable/disable> ��e - ��7������ ���� ������ ������ Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " blockdamage <enable/disable> ��e - ��7������ ���� ������ �� ��������  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " break <enable/disable> ��e - ��7������ ���� ������ �� �ı���  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " place <enable/disable> ��e - ��7������ ���� ������ �� ��ġ��  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " pickup <enable/disable> ��e - ��7������ ���� ������ ������ �Ⱦ���  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " drop <enable/disable> ��e - ��7������ ���� ������ ������ �����  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " pvp <enable/disable> ��e - ��7PvP��  Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " god <enable/disable> ��e - ��7�÷��̾� ������ Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " interact <enable/disable> ��e - ��7������ ���� ������ ��ȣ �ۿ���  Ȱ��ȭ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " explosionblock <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7���� �߻��� �� �ı� ���θ� �����մϴ�.");
					sender.sendMessage("��7/" + label + " levellimit <enable/disable> ��e - ��7���� ������ �մϴ�.");
					sender.sendMessage("��7/" + label + " level [���� ����] ��e - ��7���� ������ �����մϴ�.");
				}else{
					sender.sendMessage("��7/" + label + " ä�� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ ��ü ä���� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ���� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ ������ Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " �������� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ �� �������� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ���ı� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ �� �ı��� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ����ġ <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ �� ��ġ�� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " �Ⱦ� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ ������ �Ⱦ��� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ��� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ ������ ����� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " pvp <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7PvP�� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ���� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7�÷��̾� ������ Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " ���ߺ��ı� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7���� �߻��� �� �ı� ���θ� �����մϴ�.");
					sender.sendMessage("��7/" + label + " ��ȣ�ۿ� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7������ ���� ������ ��ȣ �ۿ��� Ȱ��ȭ ��Ű�ų� ��Ȱ��ȭ��ŵ�ϴ�.");
					sender.sendMessage("��7/" + label + " �������� <Ȱ��ȭ/��Ȱ��ȭ> ��e - ��7���� ������ �մϴ�.");
					sender.sendMessage("��7/" + label + " ���� [���� ����] ��e - ��7���� ������ �����մϴ�.");
				}
			}else{
				switch(args[0]){
					case "chat":
					case "ä��":
						Horizon_EventCancelActor.Chat = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.Chat)
							Bukkit.broadcastMessage(super.m.getMessage("ä�� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("ä�� Ȱ��ȭ"));
						break;
					case "move":
					case "����":
						Horizon_EventCancelActor.move = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.move)
							Bukkit.broadcastMessage(super.m.getMessage("���� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("���� Ȱ��ȭ"));
						break;
					case "blockdamage":
					case "��������":
						Horizon_EventCancelActor.BlockDamage = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.BlockDamage)
							Bukkit.broadcastMessage(super.m.getMessage("�� ������ ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("�� ������ Ȱ��ȭ"));
						break;
					case "break":
					case "���ı�":
						Horizon_EventCancelActor.BlockBreak = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.BlockBreak)
							Bukkit.broadcastMessage(super.m.getMessage("�� �ı� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("�� �ı� Ȱ��ȭ"));
						break;
					case "explosionblock":
					case "���ߺ��ı�":
						Horizon_EventCancelActor.BlockExplosion = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.BlockExplosion)
							Bukkit.broadcastMessage(super.m.getMessage("�� ���� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("�� ���� Ȱ��ȭ"));
						break;
					case "place":
					case "����ġ":
						Horizon_EventCancelActor.BlockPlace = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.BlockPlace)
							Bukkit.broadcastMessage(super.m.getMessage("�� ��ġ ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("�� ��ġ Ȱ��ȭ"));
						break;
					case "pickup":
					case "�Ⱦ�":
						Horizon_EventCancelActor.ItemPickup = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.ItemPickup )
							Bukkit.broadcastMessage(super.m.getMessage("������ �Ⱦ� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("������ �Ⱦ� Ȱ��ȭ"));
						break;
					case "drop":
					case "���":
						Horizon_EventCancelActor.ItemDrop = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.ItemDrop)
							Bukkit.broadcastMessage(super.m.getMessage("������ ��� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("������ ��� Ȱ��ȭ"));
						break;
					case "pvp":
						Horizon_EventCancelActor.pvp = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "false").replaceAll("enable|Ȱ��ȭ", "true"));
						if(Horizon_EventCancelActor.pvp)
							Bukkit.broadcastMessage(super.m.getMessage("pvp ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("pvp Ȱ��ȭ"));
						break;
					case "����":
					case "god":
						Horizon_EventCancelActor.Damage = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "false").replaceAll("enable|Ȱ��ȭ", "true"));
						if(Horizon_EventCancelActor.Damage)
							Bukkit.broadcastMessage(super.m.getMessage("���� Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("���� ��Ȱ��ȭ"));
						break;
					case "��ȣ�ۿ�":
					case "interact":
						Horizon_EventCancelActor.Interact = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "true").replaceAll("enable|Ȱ��ȭ", "false"));
						if(Horizon_EventCancelActor.Interact)
							Bukkit.broadcastMessage(super.m.getMessage("��ȣ �ۿ� ��Ȱ��ȭ"));
						else
							Bukkit.broadcastMessage(super.m.getMessage("��ȣ �ۿ� Ȱ��ȭ"));
						break;
					case "��������":
					case "levellimit":
						Horizon_EventCancelActor.Level_Up = Boolean.parseBoolean(args[1].replaceAll("disable|��Ȱ��ȭ", "false").replaceAll("enable|Ȱ��ȭ", "true"));
						if(Horizon_EventCancelActor.Level_Up)
							Bukkit.broadcastMessage(super.m.getMessage("���� ���� Ȱ��ȭ").replace("[����]",String.valueOf(Horizon_EventCancelActor.level)));
						else
							Bukkit.broadcastMessage(super.m.getMessage("���� ���� ��Ȱ��ȭ"));
						break;
					case "level":
					case "����":
						if(!DataUtil.isInteger(args[1]))
							sender.sendMessage( "��c������ ���ڸ� �����մϴ�.");
						else{
							Horizon_EventCancelActor.level = Integer.parseInt(args[1]);
							sender.sendMessage("��f[��aH��7orizon ��aE��7ssentials��f] ��f���������� ���� ������ " + args[1] + "���� ����Ǿ����ϴ�.");
						}
				}
			}
		return false;
	}
}