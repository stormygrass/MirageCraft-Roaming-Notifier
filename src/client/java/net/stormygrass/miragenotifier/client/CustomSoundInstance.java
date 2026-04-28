package net.stormygrass.miragenotifier.client;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;

import static net.stormygrass.miragenotifier.client.MirageCraftNotifierClient.LOGGER;

public class CustomSoundInstance extends AbstractTickableSoundInstance {
	private final LivingEntity entity;

	public CustomSoundInstance(LivingEntity entity, SoundEvent soundEvent, SoundSource soundCategory) {
		super(soundEvent, soundCategory, SoundInstance.createUnseededRandom());
		LOGGER.debug("custom sound instance created");

		this.entity = entity;
		// default values
		this.volume = 1.0f; //TODO add config settings
		this.pitch = 1.0f;
		this.looping = false;
		this.setPositionToEntity();
	}

	@Override
	public void tick() {
		if (this.entity == null || this.entity.isRemoved() || this.entity.isDeadOrDying()) {
			this.stop();
			LOGGER.debug("entity not found, stopping audio");
			return;
		}

		// move sound position over to the new position for every tick
		this.setPositionToEntity();
	}

	@Override
	public boolean canStartSilent() {
		// override to true, so that the SoundInstance can start
		// or add your own condition to the SoundInstance, if necessary
		return true;
	}

	private void setPositionToEntity() {
		this.x = this.entity.getX();
		this.y = this.entity.getY();
		this.z = this.entity.getZ();
	}
}
