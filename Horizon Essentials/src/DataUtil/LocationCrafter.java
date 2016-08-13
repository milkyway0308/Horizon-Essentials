package DataUtil;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationCrafter {
	private String world;
	private double x;
	private double y;
	private double z;
	private float yaw = 0f;
	private float pitch = 0f;
public LocationCrafter(String world,double x,double y,double z){
	this.world = world;
	this.x = x;
	this.y = y;
	this.z = z;
}
public LocationCrafter(String world,double x,double y,double z,float yaw,float pitch){
	this.world = world;
	this.x = x;
	this.y = y;
	this.z = z;
	this.yaw = yaw;
	this.pitch = pitch;
}
public LocationCrafter(Location loc){
	this.world = loc.getWorld().getName();
	this.x = loc.getX();
	this.y = loc.getY();
	this.z = loc.getZ();
	this.yaw = loc.getYaw();
	this.pitch = loc.getPitch();
}
public double getX(){
	return x;
}
public double getY(){
	return y;
}
public double getZ(){
	return z;
}
public World getWorld(){
	return Bukkit.getWorld(world);
}
public float getYaw(){
	return yaw;
}
public float getPitch(){
	return pitch;
}
public Location getLocation(){
	World w = Bukkit.getWorld(world);
	if(w == null)
		return null;
	else
		return new Location(w,x,y,z,yaw,pitch);
}
public String toString(){
	return world + "," + x+ "," + y + "," + z+ "," + yaw+ "," + pitch;
}
public static LocationCrafter toLocationCrafter(String s){
	String[] split = s.split(",");
	if(split.length != 6)
		return null;
	try{
		return new LocationCrafter(split[0],Double.parseDouble(split[1]),Double.parseDouble(split[2]),Double.parseDouble(split[3]),Float.parseFloat(split[4]),Float.parseFloat(split[5]));
	}catch(Exception e){return null;}
}
public boolean isDifferent(LocationCrafter loc){
	if(!loc.getWorld().equals(this.getWorld()))
		return true;
	if(!(loc.getX() == this.getX()))
		return true;
	if(!(loc.getY() == this.getY()))
		return true;
	if(!(loc.getZ() == this.getZ()))
		return true;
	return false;
}
}
