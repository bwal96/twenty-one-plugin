package com.twentyone;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("twentyone")
public interface TwentyOneConfig extends Config
{
    @ConfigItem(
        keyName = "playMine",
        name = "Play mine",
        description = "Play on my 21 hit-splats.",
        position = 1
    )
    default boolean playMine()
    {
        return true;
    }

    @ConfigItem(
        keyName = "playOthers",
        name = "Play others'",
        description = "Play on others' 21 hit-splats.",
        position = 2
    )
    default boolean playOthers()
    {
        return false;
    }

    @Range(min = 0, max = 10)
    @ConfigItem(
        keyName = "volume",
        name = "Volume",
        description = "Volume of the sound. 5 is the original clip volume.",
        position = 3
    )
    default int volume()
    {
        return 5;
    }
}
