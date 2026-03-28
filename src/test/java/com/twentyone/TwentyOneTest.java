package com.twentyone;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TwentyOneTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TwentyOnePlugin.class);
		RuneLite.main(args);
	}
}
