package entrophicFurnace.generic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;

public class WorldCunkManagerEntrophic extends WorldChunkManager {
    private BiomeGenBase biomeGenerator;
    
    private float hellTemperature;
    
    private float rainfall;
    
    public WorldCunkManagerEntrophic(BiomeGenBase biomeGenerator) {
        this.biomeGenerator = biomeGenerator;
    }
    
    /**
     * Returns the BiomeGenBase related to the x, z position on the world.
     */
    @Override
    public BiomeGenBase getBiomeGenAt(int x, int z) {
            return this.biomeGenerator;
    }
    
    /**
     * Returns an array of biomes for the location input.
     */
    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] arrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        BiomeGenBase[] aarrayOfBiomeGenBase;
        if (arrayOfBiomeGenBase == null || arrayOfBiomeGenBase.length < par4 * par5) {
            aarrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
        else
        {
            aarrayOfBiomeGenBase = arrayOfBiomeGenBase;
        }

        Arrays.fill(aarrayOfBiomeGenBase, 0, par4 * par5, this.biomeGenerator);
        return aarrayOfBiomeGenBase;
    }
    
    /**
     * Returns a list of temperatures to use for the specified blocks. Args:
     * listToReuse, x, y, width, length
     */
    @Override
    public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
        float[] par1ArrayOfFloatt;
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloatt = new float[par4 * par5];
        }
        else
        {
            par1ArrayOfFloatt = par1ArrayOfFloat;
        }

        Arrays.fill(par1ArrayOfFloatt, 0, par4 * par5, this.hellTemperature);
        return par1ArrayOfFloatt;
    }
    
    /**
     * Returns a list of rainfall values for the specified blocks. Args:
     * listToReuse, x, z, width, length.
     */
    @Override
    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
        float[] par1ArrayOfFloatt;
        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
            par1ArrayOfFloatt = new float[par4 * par5];
        }
        else
        {
            par1ArrayOfFloatt = par1ArrayOfFloat;
        }

        Arrays.fill(par1ArrayOfFloatt, 0, par4 * par5, this.rainfall);
        return par1ArrayOfFloatt;
    }
    
    /**
     * Returns biomes to use for the blocks and loads the other data like
     * temperature and humidity onto the WorldChunkManager Args: oldBiomeList,
     * x, z, width, depth
     */
    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        BiomeGenBase[] par1ArrayOfBiomeGenBasee;
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBasee = new BiomeGenBase[par4 * par5];
        }
        else
        {
            par1ArrayOfBiomeGenBasee = par1ArrayOfBiomeGenBase;
        }
        
        Arrays.fill(par1ArrayOfBiomeGenBasee, 0, par4 * par5, this.biomeGenerator);
        return par1ArrayOfBiomeGenBasee;
    }
    
    /**
     * Return a list of biomes for the specified blocks. Args: listToReuse, x,
     * y, width, length, cacheFlag (if false, don't check biomeCache to avoid
     * infinite loop in BiomeCacheBlock)
     */
    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
        return this.loadBlockGeneratorData(par1ArrayOfBiomeGenBase, par2, par3, par4, par5);
    }
    
    /**
     * Finds a valid position within a range, that is in one of the listed
     * biomes. Searches {par1,par2} +-par3 blocks. Strongly favors positive y
     * positions.
     */
    @Override
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, @SuppressWarnings("rawtypes") List par4List, Random par5Random) {
        return par4List.contains(this.biomeGenerator) ? new ChunkPosition(par1 - par3 + par5Random.nextInt(par3 * 2 + 1), 0, par2 - par3 + par5Random.nextInt(par3 * 2 + 1)) : null;
    }

    /**
     * checks given Chunk's Biomes against List of allowed ones
     */
    @Override
    public boolean areBiomesViable(int par1, int par2, int par3, @SuppressWarnings("rawtypes") List par4List) {
        return par4List.contains(this.biomeGenerator);
    }
}
