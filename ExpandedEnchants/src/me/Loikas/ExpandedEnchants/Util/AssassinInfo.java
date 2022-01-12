package me.Loikas.ExpandedEnchants.Util;

import java.util.UUID;

import org.bukkit.boss.BossBar;

public class AssassinInfo
{
	private UUID player;
	private double countdownMax;
	private double countdown;
	private boolean shouldCountdown = true;
	private boolean tryCountdown = true;
	private boolean shouldRefill = false;
	private double refill = 0;
	private int refillMax;
	public BossBar bar;
	
	public AssassinInfo(UUID _player, double _countdownMax, int _refillMax, BossBar _bar) {
		player = _player;
		countdownMax = _countdownMax;
		refillMax = _refillMax;
		countdown = _countdownMax;
		bar = _bar;
	}

	public UUID getPlayer() {
		return player;
	}
	
	public double getCountdown() {
		return countdown;
	}
	
	public void setCountdown(double count) {
		countdown = count;
	}
	
	public void setCountdownMax(double count) {
		countdownMax = count;
	}
	
	public boolean getShouldCountdown()
	{
		return shouldCountdown;
	}

	public void setShouldCountdown(boolean shouldCountdown)
	{
		this.shouldCountdown = shouldCountdown;
	}

	public boolean getShouldRefill()
	{
		return shouldRefill;
	}

	public void setShouldRefill(boolean shouldRefill)
	{
		this.shouldRefill = shouldRefill;
	}

	public double getRefill()
	{
		return refill;
	}
	
	public void setRefill(double fill) {
		refill = fill;
	}
	
	public void setRefillMax(int fill) {
		refillMax = fill;
	}

	public int getRefillMax()
	{
		return refillMax;
	}

	public double getCountdownMax()
	{
		return countdownMax;
	}

	public boolean getTryCountdown()
	{
		return tryCountdown;
	}

	public void setTryCountdown(boolean tryCountdown)
	{
		this.tryCountdown = tryCountdown;
	}
	
	
	
}
