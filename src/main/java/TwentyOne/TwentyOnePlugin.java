package TwentyOne;

import javax.inject.Inject;
import javax.sound.sampled.*;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import java.io.BufferedInputStream;
import java.io.InputStream;

@Slf4j
@PluginDescriptor(
		name = "Twenty One",
		description = "Plays a sound when you hit a 21"
)
public class TwentyOnePlugin extends Plugin
{
	@Inject
	private Client client;

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
			InputStream audioSrc = getClass().getResourceAsStream("/sounds/21.wav");
			InputStream buffered = new BufferedInputStream(audioSrc);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(buffered);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		}
		catch (Exception e)
		{
			log.debug("Failed to play sound", e);
		}
	}
}