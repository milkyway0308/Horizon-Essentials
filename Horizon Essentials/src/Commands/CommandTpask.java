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
					super.m.sendMessage(sender, "명령어 권한 부족");
					return false;
			}
			if(args.length <= 0)
				super.m.sendMessage(sender, "명령어 사용법",new DoubleString("[사용법]","/" + label + " [플레이어]"));
			else{
				try{
				final Player p = Bukkit.getPlayer(args[0]);
				if(p == null || !p.isOnline())
				{
					super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));
					return false;
				}
					if(sender.getName().toLowerCase().equals(args[0].toLowerCase())){
						super.m.sendMessage(sender,"텔레포트 대상 오류");
						return false;
					}
					if(DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.contains((Player)sender)){
						super.m.sendMessage(sender, "요청 대기중",new DoubleString("[대상]",args[0]));
					}else{
						if(DataManager.players.get(p).FullyIgnore.contains(sender.getName().toLowerCase()) || DataManager.players.get(p).tpaIgnore.contains(sender.getName().toLowerCase())){
							super.m.sendMessage(sender, "텔레포트 차단됨",new DoubleString("[대상]",args[0]));
							return false;
						}
						new Thread(){
							public void run() {
								DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).teleportRequest.add(((Player)sender));
								m.sendMessage(p, "텔레포트 요청됨",new DoubleString("[대상]",DataManager.getName(sender.getName())));
								m.sendMessage(sender, "텔레포트 요청",new DoubleString("[대상]",DataManager.getName(p.getName())));
								new Thread(){
									@SuppressWarnings("deprecation")
									public void run() {
										 Player p = Bukkit.getPlayer(args[0]);
										int repeat = DataManager.tpAskDelay;
										while(repeat >= 1){
											p = Bukkit.getPlayer(args[0]);
											if(p == null || !p.isOnline()){
												m.sendMessage(sender, "텔레포트 거절됨");
												stop();
												return;
											}
											if(DataManager.players.get(Bukkit.getOfflinePlayer(args[0])).denyRequest.contains((Player)sender)){
												m.sendMessage(sender, "텔레포트 거절됨");
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
														m.sendMessage(sender, "텔레포트 딜레이 대기중",new DoubleString("[대기시간]",String.valueOf(DataManager.waitDelay)));
													new Thread(){
														public void run() {
															 Player p = Bukkit.getPlayer(args[0]);
															int loop = DataManager.waitDelay;
															LocationCrafter main = new LocationCrafter(((Player)sender).getLocation());
															while(loop >= 1)
															{
																if(DataManager.tpaMovestop && main.isDifferent(new LocationCrafter(((Player)sender).getLocation()))){
																	m.sendMessage(sender, "텔레포트 캔슬 - 동작");
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
															m.sendMessage(sender, "텔레포트 요청 수락됨");
														};
													}.start();
														stop();
														return;
													}else{
														((Player)sender).teleport(p);
														m.sendMessage(sender, "텔레포트 요청 수락됨");
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
				}catch(NullPointerException e){super.m.sendMessage(sender, "대상 플레이어 오프라인",new DoubleString("[대상]",args[0]));}
				}
			
		}else{
			super.m.sendMessage(sender, "플레이어 전용 커맨드");
		}
			
		return false;
	}
}