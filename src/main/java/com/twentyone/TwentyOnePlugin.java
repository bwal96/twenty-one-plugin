package com.twentyone;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.google.inject.Provides;

@Slf4j
@PluginDescriptor(
		name = "Twenty One",
		description = "Plays a sound of 21 Savage saying \"21\" when you hit a 21."
)
public class TwentyOnePlugin extends Plugin
{
	@Inject private AudioPlayer audioPlayer;
        @Inject private TwentyOneConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("TwentyOne started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("TwentyOne stopped!");
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplatApplied)
	{
            if (hitsplatApplied.getHitsplat().getAmount() != 21)
                return;

            if (hitsplatApplied.getHitsplat().isMine() && config.playMine())
                playSound();

            if (hitsplatApplied.getHitsplat().isOthers() && config.playOthers())
                playSound();
	}

	@Provides
	TwentyOneConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(TwentyOneConfig.class);
	}

	private void playSound()
	{
		int volume = config.volume();
		if (volume == 0)
			return;

		float gain = (float) (20.0 * Math.log10(volume / 5.0));
		try
		{
			audioPlayer.play(getClass(), "/sounds/21.wav", gain);
		}
		catch (Exception e)
		{
			log.debug("Failed to play sound", e);
		}
	}
}
