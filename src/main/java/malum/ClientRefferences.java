package malum;

import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.UUID;

public class ClientRefferences
{

    public static final Minecraft minecraft = Minecraft.getInstance();

    public static PlayerEntity getClientPlayer() {

        return Minecraft.getInstance().player;
    }

    public static World getClientWorld() {

        return Minecraft.getInstance().world;
    }
    public static GameSettings getClientSettings() {

        return Minecraft.getInstance().gameSettings;
    }
    public static PlayerEntity getClientPlayerByUUID(UUID uuid) {

        return Minecraft.getInstance().world.getPlayerByUuid(uuid);
    }
}