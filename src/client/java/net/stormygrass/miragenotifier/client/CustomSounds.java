package net.stormygrass.miragenotifier.client;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import static net.stormygrass.miragenotifier.client.MirageCraftNotifierClient.LOGGER;
import static net.stormygrass.miragenotifier.client.MirageCraftNotifierClient.MOD_ID;

public class CustomSounds {
    private CustomSounds() {
       // a
    }

    public static final SoundEvent ROAMINGPOKE_ALERT = registerSound("roamingpoke_alert");

    private static SoundEvent registerSound(String id) {
        ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent.createVariableRangeEvent(identifier));
    }

    public static void initialize() {
        LOGGER.info("Registering " + MOD_ID + " Sounds");
    }
}