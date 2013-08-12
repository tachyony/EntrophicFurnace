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
        this.itemStacks = new HashMap<ItemStack, Integer>();
        this.addStack(new ItemStack(Block.cobblestone), "cobblestone", 1);
        this.addStack(new ItemStack(Block.dirt), "dirt", 1);
        this.addStack(new ItemStack(Block.sand), "sand", 1);
        this.addStack(new ItemStack(Block.stone), "stone", 1);
        this.addStack(new ItemStack(Block.stoneBrick), "stoneBrick", 1);
        this.addStack(new ItemStack(Block.glass), "glass", 1);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre), "entrophicOre", 1);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre1), "entrophicOre1", 1);
        this.addStack(new ItemStack(Item.silk), "itemSilk", 1);
        this.addStack(new ItemStack(Block.netherrack), "netherrack", 1);
        this.addStack(new ItemStack(Block.slowSand), "slowSand", 1);
        this.addStack(new ItemStack(Block.mycelium), "mycelium", 1);
        this.addStack(new ItemStack(Block.grass), "grass", 1);
        this.addStack(new ItemStack(Block.cobblestoneMossy), "cobblestoneMossy", 1);  
        this.addStack(new ItemStack(Block.cloth, 1, 0), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 1), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 2), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 3), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 4), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 5), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 6), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 7), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 8), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 9), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 10), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 11), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 12), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 13), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 14), "cloth", 4);
        this.addStack(new ItemStack(Block.cloth, 1, 15), "cloth", 4);
        this.addStack(new ItemStack(Block.gravel), "blockGravel", 4);
        this.addStack(new ItemStack(Item.flint), "itemFlint", 4);
        this.addStack(new ItemStack(Block.sandStone), "sandStone", 4);
        this.addStack(new ItemStack(Block.netherBrick), "netherBrick", 4);
        this.addStack(new ItemStack(Item.feather), "feather", 5);
        this.addStack(new ItemStack(Item.egg), "egg", 6);
        this.addStack(new ItemStack(Item.chickenRaw), "chickenRaw", 6);
        this.addStack(new ItemStack(Item.chickenCooked), "chickenCooked", 6);
        this.addStack(new ItemStack(Item.porkRaw), "porkRaw", 6);
        this.addStack(new ItemStack(Item.porkCooked), "porkCooked", 6);
        this.addStack(new ItemStack(Item.netherStalkSeeds, 1, 3), "netherStalkSeeds", 8);
        this.addStack(new ItemStack(Block.furnaceIdle), "furnace", 8);
        this.addStack(new ItemStack(Item.clay), "itemClay", 8);
        this.addStack(new ItemStack(Item.brick), "itemBrick", 8);
        this.addStack(new ItemStack(EntrophicFurnace.entrophicOre2), "entrophicOre2", 8); 
        this.addStack(new ItemStack(Block.melon), "blockMelon", 16);
        this.addStack(new ItemStack(Block.pumpkin), "pumpkin", 16);
        this.addStack(new ItemStack(Block.cactus), "cactus", 16);
        this.addStack(new ItemStack(Item.seeds), "seeds", 16);
        this.addStack(new ItemStack(Item.wheat), "wheat", 16);
        this.addStack(new ItemStack(Item.reed), "sugarCane", 16);
        this.addStack(new ItemStack(Item.carrot), "carrot", 16);
        this.addStack(new ItemStack(Item.potato), "potato", 16);
        this.addStack(new ItemStack(Block.wood, 1, 0), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 1), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 2), "wood", 16);
        this.addStack(new ItemStack(Block.wood, 1, 3), "wood", 16);
        this.addStack(new ItemStack(Item.coal, 1, 0), "itemCoal", 16);
        this.addStack(new ItemStack(Item.coal, 1, 1), "itemCharcoal", 16);
        this.addStack(new ItemStack(Item.leather), "leather", 16);
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
