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
    private Map<ItemStack, Integer> itemStacks;
    
    /**
     * Build a list of stuff for resource values
     * @param hardMode Hard mode
     */
    public ItemStackValues(boolean hardMode)
    {
        this.itemStacks = new HashMap<>();
        /*this.addStack(new ItemStack(Block.cobblestone), 1);
        this.addStack(new ItemStack(Block.dirt), 1);
        this.addStack(new ItemStack(Block.sand), 1);
        this.addStack(new ItemStack(Block.stone), 1);
        this.addStack(new ItemStack(Block.stoneBrick), 1);
        this.addStack(new ItemStack(Block.glass), 1);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre), 1);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre1), 1);
        this.addStack(new ItemStack(Item.silk), 1);
        this.addStack(new ItemStack(Block.netherrack), 1);
        this.addStack(new ItemStack(Block.slowSand), 1);
        this.addStack(new ItemStack(Block.mycelium), 1);
        this.addStack(new ItemStack(Block.grass), 1);
        this.addStack(new ItemStack(Block.cobblestoneMossy), 1);  
        this.addStack(new ItemStack(Block.cloth, 1, 0), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 1), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 2), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 3), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 4), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 5), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 6), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 7), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 8), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 9), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 10), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 11), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 12), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 13), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 14), 4);
        this.addStack(new ItemStack(Block.cloth, 1, 15), 4);
        this.addStack(new ItemStack(Block.gravel), 4);
        this.addStack(new ItemStack(Item.flint), 4);
        this.addStack(new ItemStack(Block.sandStone), 4);
        this.addStack(new ItemStack(Block.netherBrick), 4);
        this.addStack(new ItemStack(Item.feather), 5);
        this.addStack(new ItemStack(Item.egg), 6);
        this.addStack(new ItemStack(Item.chickenRaw), 6);
        this.addStack(new ItemStack(Item.chickenCooked), 6);
        this.addStack(new ItemStack(Item.porkRaw), 6);
        this.addStack(new ItemStack(Item.porkCooked), 6);
        this.addStack(new ItemStack(Item.netherStalkSeeds, 1, 3), 8);
        this.addStack(new ItemStack(Block.furnaceIdle), 8);
        this.addStack(new ItemStack(Item.clay), 8);
        this.addStack(new ItemStack(Item.brick), 8);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre2), 8); 
        this.addStack(new ItemStack(Block.melon), 16);
        this.addStack(new ItemStack(Block.pumpkin), 16);
        this.addStack(new ItemStack(Block.cactus), 16);
        this.addStack(new ItemStack(Item.seeds), 16);
        this.addStack(new ItemStack(Item.wheat), 16);
        this.addStack(new ItemStack(Item.reed), 16);
        this.addStack(new ItemStack(Item.carrot), 16);
        this.addStack(new ItemStack(Item.potato), 16);
        this.addStack(new ItemStack(Block.wood, 1, 0), 16);
        this.addStack(new ItemStack(Block.wood, 1, 1), 16);
        this.addStack(new ItemStack(Block.wood, 1, 2), 16);
        this.addStack(new ItemStack(Block.wood, 1, 3), 16);
        this.addStack(new ItemStack(Item.coal, 1, 0), 16);
        this.addStack(new ItemStack(Item.coal, 1, 1), 16);
        this.addStack(new ItemStack(Item.leather), 16);*/
        this.addStack(new ItemStack(Item.gunpowder), "gunpowder", 24);
        this.addStack(new ItemStack(Block.blockClay), "blockClay", 32);
        this.addStack(new ItemStack(Block.brick), "blockBrick", 32);
        this.addStack(new ItemStack(Block.obsidian), "obsidian", 32);
        this.addStack(new ItemStack(Block.waterlily), "waterlily", 32);
        this.addStack(new ItemStack(Item.enderPearl), "enderPearl", 32);
        this.addStack(new ItemStack(Block.sapling, 1, 0), "sapling", 40);
        this.addStack(new ItemStack(Block.sapling, 1, 1), "sapling", 40);
        this.addStack(new ItemStack(Block.sapling, 1, 2), "sapling", 40);
        this.addStack(new ItemStack(Block.sapling, 1, 3), "sapling", 40);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 0), "entrophicFurnace1", 48, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 1), "entrophicFurnace1", 48, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 2), "entrophicFurnace1", 48, true);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace1, 1, 3), "entrophicFurnace1", 48, true);
        this.addStack(new ItemStack(Item.ingotIron), "ingotIron", 64);
        this.addStack(new ItemStack(EntrophicFurnace.ingotCopper), "ingotCopper", 64);
        this.addStack(new ItemStack(EntrophicFurnace.ingotTin), "ingotTin", 64);
        this.addStack(new ItemStack(Item.redstone), "itemRedstone", 64);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre3), "entrophicOre3", 64);
        this.addStack(new ItemStack(Item.blazeRod), "blazeRod", 128);
        this.addStack(new ItemStack(Item.slimeBall), "slimeBall", 128);
        this.addStack(new ItemStack(Block.glowStone), "blockGlowStone", 128);
        this.addStack(new ItemStack(Item.ingotGold), "ingotGold", 256);
        this.addStack(new ItemStack(Item.emerald), "itemEmerald", 256);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre4), "entrophicOre4", 512);
        this.addStack(new ItemStack(Item.diamond), "itemDiamond", 1024);
        this.addStack(new ItemStack(Block.blockGold), "blockGold", 2304, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 0), "entrophicFurnace2", 2496, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 1), "entrophicFurnace2", 2496, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 2), "entrophicFurnace2", 2496, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace2, 1, 3), "entrophicFurnace2", 2496, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre5), "entrophicOre5", 4096);
        this.addStack(new ItemStack(Block.blockDiamond), "blockDiamond", 8192);
        this.addStack(new ItemStack(Item.netherStar), "netherStar", 8192);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicPaxel), "entrophicPaxel", 9216, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 0), "entrophicFurnace3", 18176, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 1), "entrophicFurnace3", 18176, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 2), "entrophicFurnace3", 18176, hardMode);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicFurnace3, 1, 3), "entrophicFurnace3", 18176, hardMode);
    }

    /**
     * Add something to the value stack
     * @param stack Item
     * @param valueName Config value name
     * @param hardMode Ignore if hard mode is on
     */
    private void addStack(ItemStack stack, String valueName, int defaultValue, boolean hardMode)
    {
        int itemValue = EntrophicFurnace.CONFIGURATION.get("itemvalues", valueName, defaultValue).getInt();
        if (!hardMode)
        {
            this.itemStacks.put(stack, Integer.valueOf(itemValue));
        }
    }
    
    /**
     * Add something to the value stack
     * @param stack Item
     * @param valueName Config value name
     */
    private void addStack(ItemStack stack, String valueName, int defaultValue)
    {
        this.addStack(stack, valueName, defaultValue, false);
    }
    
    /**
     * Get an items value
     * @param sourceStack Source item
     * @return Item value
     */
    public int getItemValue(ItemStack sourceStack)
    {
        int itemValue = 0;
        for (Map.Entry<ItemStack, Integer> stackEntry : this.itemStacks.entrySet())
        {
            if (sourceStack.isItemEqual(stackEntry.getKey()))
            {
                itemValue = stackEntry.getValue().intValue();
            }
        }
        
        return itemValue;
    }
}
