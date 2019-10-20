package malum.capabilities;


import net.minecraft.nbt.CompoundNBT;

public class PlayerMadeDoll {

    private int playerMadeDoll = 0;

    public PlayerMadeDoll() {
    }

    public int hasPlayerMadeDoll() {
        return playerMadeDoll;
    }

    public void setPlayerMadeDoll(int playerMadeDoll) {
        this.playerMadeDoll = playerMadeDoll;
    }

    public void copyFrom(PlayerMadeDoll source) {
        playerMadeDoll = source.playerMadeDoll;
    }


    public void saveNBTData(CompoundNBT compound) {
        compound.putInt("madeDoll", playerMadeDoll);
    }

    public void loadNBTData(CompoundNBT compound) {
        playerMadeDoll = compound.getInt("madeDoll");
    }
}
