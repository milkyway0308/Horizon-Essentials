package Horizon_Essentials;

import org.bukkit.Bukkit;

public class ServerInfomation {
	public static double nowTPS = 20.0;
	public static double percent = 100.0;
	public static long last = -1;
	public static int upTime = 0;
public static void runTPSChecker(){
	Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInst(), new Runnable(){
		@Override
		public void run() {
			upTime++;
			nowTPS = 20000d / (double)(System.currentTimeMillis() - last) ;
			percent = nowTPS * 5;
			if(nowTPS >= 20)
				nowTPS = 20;
			if(percent >= 100)
				percent = 100;
			last = System.currentTimeMillis();
		}
	},0L,20L);
}
public static String getString(){
	if(nowTPS >= 16)
		return "ColorCodea" + String.format("%.2fTPSColorCode7(ColorCodea%.2fpercentColorCode7)", nowTPS,percent);
	else if(nowTPS >= 12)
		return "ColorCodef" + String.format("%.2fTPSColorCode7(ColorCodea%.2fpercentColorCode7)", nowTPS,percent);
	else if(nowTPS >= 8)
		return "ColorCodec" + String.format("%.2fTPSColorCode7(ColorCodec%.2fpercentColorCode7)", nowTPS,percent);
	else if(nowTPS >= 4)
		return "ColorCode4" + String.format("%.2fTPSColorCode7(ColorCode4%.2fpercentColorCode7)", nowTPS,percent);
	else
		return "ColorCode4ColorCodel" + String.format("%.2fTPSColorCode7(ColorCode4ColorCodel%.2fpercentColorCode7ColorCodee[Dead]ColorCode7)", nowTPS,percent);
}
}
