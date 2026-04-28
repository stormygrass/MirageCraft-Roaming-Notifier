package net.stormygrass.miragenotifier.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MirageCraftNotifierClient implements ClientModInitializer {
	public static final String MOD_ID = "miragecraft-notifier";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.debug("client loaded");
		CustomSounds.initialize();

		// checks if a message is from the SERVER, that it isn't from a discord webhook, and that the message contains "amy" and "roaming".
		ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
			if (!message.getString().toLowerCase().contains("discord") && message.getString().contains("Amy") && message.getString().toLowerCase().contains("roaming")) {
				Minecraft client = Minecraft.getInstance();
				CustomSoundInstance instance = new CustomSoundInstance(client.player, CustomSounds.ROAMINGPOKE_ALERT, SoundSource.VOICE);

				// play the alert
				client.getSoundManager().play(instance);
			}
		});
	}
}