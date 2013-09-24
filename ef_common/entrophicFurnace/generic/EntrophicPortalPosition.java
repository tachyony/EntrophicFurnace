package entrophicFurnace.generic;

import net.minecraft.util.ChunkCoordinates;

/**
 * 
 * @author tachyony
 *
 */
public class EntrophicPortalPosition extends ChunkCoordinates
{
    /**
     * 
     */
    public long field_85087_d;
    
    final EntrophicTeleporter field_85088_e;
    
    /**
     * 
     * @param tutorialTeleporter
     * @param par2
     * @param par3
     * @param par4
     * @param par5
     */
    public EntrophicPortalPosition(EntrophicTeleporter tutorialTeleporter, int par2, int par3, int par4, long par5)
    {
        super(par2, par3, par4);
        this.field_85088_e = tutorialTeleporter;
        this.field_85087_d = par5;
    }
}