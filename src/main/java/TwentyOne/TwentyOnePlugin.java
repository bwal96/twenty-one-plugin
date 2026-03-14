package TwentyOne;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.audio.AudioPlayer;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Slf4j
@PluginDescriptor(
		name = "Twenty One",
		description = "Plays a sound when you hit a 21"
)
public class TwentyOnePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private AudioPlayer audioPlayer;

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
		if (hitsplatApplied.getHitsplat().getAmount() == 21)
		{
			playSound();
		}
	}

	private void playSound()
	{
		try
		{
			audioPlayer.play(getClass(), "/sounds/21.wav", 1.0f);
		}
		catch (Exception e)
		{
			log.debug("Failed to play sound", e);
		}
	}
}