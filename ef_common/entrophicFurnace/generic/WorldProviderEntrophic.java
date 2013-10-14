package entrophicFurnace.generic;

import entrophicFurnace.world.gen.ChunkProviderEntrophic;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.DimensionManager;

/**
 * 
 * @author tachyony
 *
 */
public class WorldProviderEntrophic extends WorldProvider {
    @Override
    protected void registerWorldChunkManager() {
        this.worldChunkMgr = new WorldCunkManagerEntrophic(BiomeGenBase.mushroomIsland);
        this.hasNoSky = true;
    }

    /**
     * 
     * @param par0
     * @return World provider
     */
    public static WorldProvider getProviderForDimension(int par0)
    {
        return DimensionManager.createProviderFor(143);
    }
    
    /**
     * 
     */
    @Override
    public String getSaveFolder() {
        return "DIM_ENTROPHIC";
    }

    /**
     * 
     */
    @Override
    public String getWelcomeMessage()
    {
        return "Welcome to nowhere in particular.";
    }
    
    /**
     * 
     */
    @Override
    public String getDepartMessage()
    {
        return "Now leaving nowhere in particular.";
    }
    
    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderEntrophic(this.worldObj, this.worldObj.getSeed());
    }
    
    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    @Override
    public boolean canRespawnHere()
    {
        return false;
    }
    
    /**
     * The dimensions movement factor. Relative to normal overworld.
     * It is applied to the players position when they transfer dimensions.
     * Exa: Nether movement is 8.0
     * @return The movement factor
     */
    @Override
    public double getMovementFactor()
    {
        return 1.0;
    }
    
    /**
     * the y level at which clouds are rendered.
     */
    @Override
    public float getCloudHeight()
    {
        return 160.0F;
    }
    
    /**
     * 
     */
    @Override
    public String getDimensionName() {
        return "Entrophic";
    }
}
