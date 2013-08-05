package entrophicFurnace.generic;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import entrophicFurnace.EntrophicFurnace;


public class ItemStackValues {
    /**
     * List of stuff
     */
    public Map<ItemStack, Integer> itemStacks;
    
    /**
     * Build a list of stuff for resource values
     */
    @SuppressWarnings({ "boxing", "unused" })
    public ItemStackValues(boolean hardMode)
    {
        this.itemStacks = new HashMap<ItemStack, Integer>();
        this.itemStacks.put(new ItemStack(Block.cobblestone), 1);
        this.itemStacks.put(new ItemStack(Block.dirt), 1);
        this.itemStacks.put(new ItemStack(Block.sand), 1);
        this.itemStacks.put(new ItemStack(Block.stone), 1);
        this.itemStacks.put(new ItemStack(Block.stoneBrick), 1);
        this.itemStacks.put(new ItemStack(Block.glass), 1);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre), 1);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre1), 1);
        this.itemStacks.put(new ItemStack(Item.silk), 1);
        this.itemStacks.put(new ItemStack(Block.netherrack), 1);
        this.itemStacks.put(new ItemStack(Block.slowSand), 1);
        this.itemStacks.put(new ItemStack(Block.mycelium), 1);
        this.itemStacks.put(new ItemStack(Block.grass), 1);
        this.itemStacks.put(new ItemStack(Block.cobblestoneMossy), 1);  
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 0), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 1), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 2), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 3), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 4), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 5), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 6), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 7), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 8), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 9), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 10), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 11), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 12), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 13), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 14), 4);
        this.itemStacks.put(new ItemStack(Block.cloth, 1, 15), 4);
        this.itemStacks.put(new ItemStack(Block.gravel), 4);
        this.itemStacks.put(new ItemStack(Item.flint), 4);
        this.itemStacks.put(new ItemStack(Block.sandStone), 4);
        this.itemStacks.put(new ItemStack(Block.netherBrick), 4);
        this.itemStacks.put(new ItemStack(Item.feather), 5);
        this.itemStacks.put(new ItemStack(Item.egg), 6);
        this.itemStacks.put(new ItemStack(Item.chickenRaw), 6);
        this.itemStacks.put(new ItemStack(Item.chickenCooked), 6);
        this.itemStacks.put(new ItemStack(Item.porkRaw), 6);
        this.itemStacks.put(new ItemStack(Item.porkCooked), 6);
        this.itemStacks.put(new ItemStack(Item.netherStalkSeeds, 1, 3), 8);
        this.itemStacks.put(new ItemStack(Block.furnaceIdle), 8);
        this.itemStacks.put(new ItemStack(Item.clay), 8);
        this.itemStacks.put(new ItemStack(Item.brick), 8);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre2), 8); 
        this.itemStacks.put(new ItemStack(Block.melon), 16);
        this.itemStacks.put(new ItemStack(Block.pumpkin), 16);
        this.itemStacks.put(new ItemStack(Block.cactus), 16);
        this.itemStacks.put(new ItemStack(Item.seeds), 16);
        this.itemStacks.put(new ItemStack(Item.wheat), 16);
        this.itemStacks.put(new ItemStack(Item.reed), 16);
        this.itemStacks.put(new ItemStack(Item.carrot), 16);
        this.itemStacks.put(new ItemStack(Item.potato), 16);
        this.itemStacks.put(new ItemStack(Block.wood, 1, 0), 16);
        this.itemStacks.put(new ItemStack(Block.wood, 1, 1), 16);
        this.itemStacks.put(new ItemStack(Block.wood, 1, 2), 16);
        this.itemStacks.put(new ItemStack(Block.wood, 1, 3), 16);
        this.itemStacks.put(new ItemStack(Item.coal, 1, 0), 16);
        this.itemStacks.put(new ItemStack(Item.coal, 1, 1), 16);
        this.itemStacks.put(new ItemStack(Item.leather), 16);
        this.itemStacks.put(new ItemStack(Item.gunpowder), 24);
        this.itemStacks.put(new ItemStack(Block.blockClay), 32);
        this.itemStacks.put(new ItemStack(Block.brick), 32);
        this.itemStacks.put(new ItemStack(Block.obsidian), 32);
        this.itemStacks.put(new ItemStack(Block.waterlily), 32);
        this.itemStacks.put(new ItemStack(Item.enderPearl), 32);
        this.itemStacks.put(new ItemStack(Block.sapling, 1, 0), 40);
        this.itemStacks.put(new ItemStack(Block.sapling, 1, 1), 40);
        this.itemStacks.put(new ItemStack(Block.sapling, 1, 2), 40);
        this.itemStacks.put(new ItemStack(Block.sapling, 1, 3), 40);
        if (!hardMode)
        {
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 0), 48);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 1), 48);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 2), 48);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 3), 48);
        }
        
        this.itemStacks.put(new ItemStack(Item.ingotIron), 64);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.ingotCopper), 64);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.ingotTin), 64);
        this.itemStacks.put(new ItemStack(Item.redstone), 64);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre3), 64);
        this.itemStacks.put(new ItemStack(Item.blazeRod), 128);
        this.itemStacks.put(new ItemStack(Item.slimeBall), 128);
        this.itemStacks.put(new ItemStack(Block.glowStone), 128);
        this.itemStacks.put(new ItemStack(Item.ingotGold), 256);
        this.itemStacks.put(new ItemStack(Item.emerald), 256);
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre4), 512);
        this.itemStacks.put(new ItemStack(Item.diamond), 1024);
        if (!hardMode)
        {
            this.itemStacks.put(new ItemStack(Block.blockGold), 2304);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 0), 2496);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 1), 2496);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 2), 2496);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 3), 2496);
        }
        
        this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicOre5), 4096);
        this.itemStacks.put(new ItemStack(Block.blockDiamond), 8192);
        this.itemStacks.put(new ItemStack(Item.netherStar), 8192);
        if (!hardMode)
        {
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicPaxel), 9216);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 0), 18176);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 1), 18176);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 2), 18176);
            this.itemStacks.put(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 3), 18176);
        }
    }
}
