package Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import DataUtil.DoubleString;
import DataUtil.LocationCrafter;
import DataUtil.PermissionType;
import Horizon_Essentials.DataManager;
import Utility.PlayerUtil;

public class CommandTpask extends CommandsRegistry implements CommandExecutor{
	

	@Override
	public boolean onCommand(final CommandSender sender, Command command, String label, final String[] args) {
		if(sender instanceof Player)
		{
			if(!sender.isOp() && !PlayerUtil.hasPermission(sender, PermissionType.CommandTpask))
			{
					super.m.sendMessage(sender, "��ɾ� ���� ����");
					return false;
			}
			if(args.length <= 0)
				super.m.sendMessage(sender, "��ɾ� ����",new DoubleString("[����]","/" + label + " [�÷��̾�]"));
			else{
				try{
				final Player p = Bukkit.getPlayer(args[0]);
				if(p == null || !p.isOnline())
				{
					super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));
					return false;
				}
					if(sender.getName().toLowerCase().equals(args[0].toLowerCase())){
						super.m.sendMessage(sender,"�ڷ���Ʈ ��� ����");
						return false;
					}
					if(DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.contains((Player)sender)){
						super.m.sendMessage(sender, "��û �����",new DoubleString("[���]",args[0]));
					}else{
						if(DataManager.players.get(p).FullyIgnore.contains(sender.getName().toLowerCase()) || DataManager.players.get(p).tpaIgnore.contains(sender.getName().toLowerCase())){
							super.m.sendMessage(sender, "�ڷ���Ʈ ���ܵ�",new DoubleString("[���]",args[0]));
							return false;
						}
						new Thread(){
							public void run() {
								DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.add(((Player)sender));
								m.sendMessage(p, "�ڷ���Ʈ ��û��",new DoubleString("[���]",DataManager.getName(sender.getName())));
								m.sendMessage(sender, "�ڷ���Ʈ ��û",new DoubleString("[���]",DataManager.getName(p.getName())));
								new Thread(){
									@SuppressWarnings("deprecation")
									public void run() {
										 Player p = Bukkit.getPlayer(args[0]);
										int repeat = DataManager.tpAskDelay;
										while(repeat >= 1){
											p = Bukkit.getPlayer(args[0]);
											if(p == null || !p.isOnline()){
												m.sendMessage(sender, "�ڷ���Ʈ ������");
												stop();
												return;
											}
											if(DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).denyRequest.contains((Player)sender)){
												m.sendMessage(sender, "�ڷ���Ʈ ������");
												DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).denyRequest.remove((Player)sender);
												stop();
												return;
											}
											if(Bukkit.getPlayer(sender.getName()) == null || !((Player)sender).isOnline())
											{
												DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.remove((Player)sender);
												stop();
												return;
											}
											if(!DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.contains((Player)sender)){
												{
													
													if(DataManager.waitDelay >= 1)
													{
														m.sendMessage(sender, "�ڷ���Ʈ ������ �����",new DoubleString("[���ð�]",String.valueOf(DataManager.waitDelay)));
													new Thread(){
														public void run() {
															 Player p = Bukkit.getPlayer(args[0]);
															int loop = DataManager.waitDelay;
															LocationCrafter main = new LocationCrafter(((Player)sender).getLocation());
															while(loop >= 1)
															{
																if(DataManager.tpaMovestop && main.isDifferent(new LocationCrafter(((Player)sender).getLocation()))){
																	m.sendMessage(sender, "�ڷ���Ʈ ĵ�� - ����");
																	stop();
																	return;
																}
																if(p == null || !p.isOnline())
																{
																	stop();
																	return;
																}
																if(Bukkit.getPlayer(sender.getName()) == null || !((Player)sender).isOnline())
																{
																	stop();
																	return;
																}
																loop--;
																try {
																	sleep(1000);
																} catch (InterruptedException e) {
																}
															}
															((Player)sender).teleport(p);
															m.sendMessage(sender, "�ڷ���Ʈ ��û ������");
														};
													}.start();
														stop();
														return;
													}else{
														((Player)sender).teleport(p);
														m.sendMessage(sender, "�ڷ���Ʈ ��û ������");
													}
												}
											}
											try {
												sleep(1000);
											} catch (InterruptedException e) {
											}
										}
									};
								}.start();
							};
						}.start();
					}
				}catch(NullPointerException e){super.m.sendMessage(sender, "��� �÷��̾� ��������",new DoubleString("[���]",args[0]));}
				}
			
		}else{
			super.m.sendMessage(sender, "�÷��̾� ���� Ŀ�ǵ�");
		}
			
		return false;
	}
}