package Utility;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

import DataUtil.EssentialsPlayer;
import HorizonEssentials.Economy.EssentialsBase;
import Horizon_Essentials.DataManager;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class VaultDataHooker implements Economy
{
    private static final Logger log;
    private final String name = "Horizon Essentials";
    private Plugin plugin; 
    private EssentialsBase econ;
    public static VaultDataHooker base;
    public VaultDataHooker(final Plugin plugin) {
        this.plugin = plugin;
        this.econ = new EssentialsBase();
        this.plugin = plugin;
        if (this.econ == null) {
            final Plugin econ = plugin.getServer().getPluginManager().getPlugin("MineConomy");
            if (econ != null && econ.isEnabled()) {
                this.econ = (EssentialsBase)econ;
                VaultDataHooker.log.info(String.format("[%s][Economy] %s hooked.", plugin.getDescription().getName(), "Horizon Essentials"));
            }
        }
        base = this;
        
    }
    
    @Override
    public boolean isEnabled() {
        return this.econ != null;
    }
    
    @Override
    public String getName() {
        return "Horizon Essentials";
    }
    

    
    @Override
    public String currencyNameSingular() {
        return EssentialsBase.moneyName;
    }
    
    @Override
    public String currencyNamePlural() {
        return EssentialsBase.moneyName;
    }
    
    @Override
    public double getBalance(final String playerName) {
        	return EssentialsBase.getMoney(Bukkit.getOfflinePlayer(playerName));
    }
    
    @Override
    public boolean has(final String playerName, final double amount) {
        return EssentialsBase.hasMoney(Bukkit.getOfflinePlayer(playerName), amount);
    }
    
    @Override
    public EconomyResponse withdrawPlayer(final String playerName, final double amount) {
        double balance = EssentialsBase.getMoney(Bukkit.getOfflinePlayer(playerName));
        if (amount < 0.0) {
            return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "빼낼 돈은 마이너스가 될수 없습니다.");
        }
        if (balance >= amount) {
            final double finalBalance = balance - amount;
            EssentialsBase.removeMoney(Bukkit.getOfflinePlayer(playerName), amount);
            return new EconomyResponse(amount, finalBalance, EconomyResponse.ResponseType.SUCCESS, null);
        }
        return new EconomyResponse(0.0, balance, EconomyResponse.ResponseType.FAILURE, "Insufficient funds");
    }
    
    @Override
    public EconomyResponse depositPlayer(final String playerName, final double amount) {
        double balance = EssentialsBase.getMoney(Bukkit.getOfflinePlayer(playerName));
        if (amount < 0.0) {
            return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.FAILURE, "마이너스의 돈을 줄 수 없습니다.");
        }
        balance += amount;
        EssentialsBase.addMoney(Bukkit.getOfflinePlayer(playerName), amount);
        return new EconomyResponse(amount, balance, EconomyResponse.ResponseType.SUCCESS, null);
    }
    
    @Override
    public EconomyResponse createBank(final String name, final String player) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 메소드 사용시 자동으로 계정을 생성하기 때문에 계정 생성을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse deleteBank(final String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse bankHas(final String name, final double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse bankWithdraw(final String name, final double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse bankDeposit(final String name, final double amount) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse isBankOwner(final String name, final String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse isBankMember(final String name, final String playerName) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public EconomyResponse bankBalance(final String name) {
        return new EconomyResponse(0.0, 0.0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "호라이즌 에센셜은 은행을 지원하지 않습니다.");
    }
    
    @Override
    public List<String> getBanks() {
        return new ArrayList<String>();
    }
    
    @Override
    public boolean hasBankSupport() {
        return false;
    }
    
    @Override
    public boolean hasAccount(final String playerName) {
        return DataManager.players.containsKey(Bukkit.getOfflinePlayer(playerName));
    }
    
    @Override
    public boolean createPlayerAccount(final String playerName) {
    	if(DataManager.players.containsKey(Bukkit.getOfflinePlayer(playerName)))
    		return false;
    	else
    		DataManager.players.put(Bukkit.getOfflinePlayer(playerName), new EssentialsPlayer(playerName, playerName, playerName, EssentialsBase.defaultmoney,-1l));
    	return true;
    }
    
    @Override
    public int fractionalDigits() {
        return 2;
    }
    
    @Override
    public boolean hasAccount(final String playerName, final String worldName) {
        return DataManager.players.containsKey(playerName);
    }
    
    @Override
    public double getBalance(final String playerName, final String world) {
        return this.getBalance(playerName);
    }
    
    @Override
    public boolean has(final String playerName, final String worldName, final double amount) {
        return this.has(playerName, amount);
    }
    
    @Override
    public EconomyResponse withdrawPlayer(final String playerName, final String worldName, final double amount) {
        return this.withdrawPlayer(playerName, amount);
    }
    
    @Override
    public EconomyResponse depositPlayer(final String playerName, final String worldName, final double amount) {
        return this.depositPlayer(playerName, amount);
    }
    
    @Override
    public boolean createPlayerAccount(final String playerName, final String worldName) {
        return this.createPlayerAccount(playerName);
    }
    
    static {
        log = Logger.getLogger("Minecraft");
    }
    

	@Override
	public String format(double arg0) {
		return String.valueOf(arg0);
	}
}
