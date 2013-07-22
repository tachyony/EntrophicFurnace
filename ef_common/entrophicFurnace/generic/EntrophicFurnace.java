package entrophicFurnace.generic;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import universalelectricity.core.UniversalElectricity;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 *
 * @author Tachyony
 *
 */
@Mod(modid = "EntrophicFurnace", name = "Entrophic Furnace", version = "1.5.2_1", dependencies = "after:BasicComponents", useMetadata = true, certificateFingerprint="", acceptedMinecraftVersions="")
@NetworkMod(channels = BlockIds.channel, clientSideRequired = true, serverSideRequired = false, packetHandler = universalelectricity.prefab.network.PacketManager.class)
public class EntrophicFurnace
{
    /**
	 *
	 */
    @Instance("EntrophicFurnace")
    public static EntrophicFurnace instance;

    /**
	 *
	 */
    @SidedProxy(clientSide = "entrophicFurnace.client.ClientProxy", serverSide = "entrophicFurnace.generic.CommonProxy")
    public static CommonProxy proxy;

    /**
	 *
	 */
    public static final Configuration CONFIGURATION = new Configuration(new File(Loader.instance().getConfigDir(),
            "EntrophicFurnace.cfg"));

    /**
	 *
	 */
    public static Block entrophicFurnace1;

    /**
	 *
	 */
    public static Block entrophicFurnace2;

    /**
	 *
	 */
    public static Block entrophicFurnace3;

    /**
	 *
	 */
    public static Item entrophicOre;

    /**
	 *
	 */
    public static Item entrophicOre1;

    /**
	 *
	 */
    public static Item entrophicOre2;

    /**
	 *
	 */
    public static Item entrophicOre3;

    /**
	 *
	 */
    public static Item entrophicOre4;

    /**
	 *
	 */
    public static Item entrophicOre5;

    /**
	 *
	 */
    public static Item ingotCopper;

    /**
	 *
	 */
    public static Item ingotTin;

    /**
     * Super paxel
     */
    public static Item entrophicPaxel;

    /**
     * Hard mode
     */
    public boolean hardMode;
    
    /**
     * Material for quantum paxel
     */
    public static final EnumToolMaterial entrophicMaterial = EnumHelper.addToolMaterial("Entrophic", 3, 4000, 24.0F, 6, 22);

    /**
     *
     * @param event
     */
    @PreInit
    public void preInit(FMLPreInitializationEvent event)
    {
        NetworkRegistry.instance().registerGuiHandler(this, EntrophicFurnace.proxy);
        EntrophicFurnace.CONFIGURATION.load();
        hardMode = EntrophicFurnace.CONFIGURATION.get("Config", "HardMode", false).getBoolean(false);
        int qfurnace1 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicFurnace1", BlockIds.entrophicFurnace1)
                .getInt();
        int qfurnace2 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicFurnace2", BlockIds.entrophicFurnace2)
                .getInt();
        int qfurnace3 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicFurnace3", BlockIds.entrophicFurnace3)
                .getInt();
        int qore = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre", BlockIds.entrophicOre).getInt();
        int qore1 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre1", BlockIds.entrophicOre1).getInt();
        int qore2 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre2", BlockIds.entrophicOre2).getInt();
        int qore3 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre3", BlockIds.entrophicOre3).getInt();
        int qore4 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre4", BlockIds.entrophicOre4).getInt();
        int qore5 = EntrophicFurnace.CONFIGURATION.getItem("EntrophicOre5", BlockIds.entrophicOre5).getInt();
        int inCopper = EntrophicFurnace.CONFIGURATION.getItem("ingotCopper", BlockIds.ingotCopper).getInt();
        int inTin = EntrophicFurnace.CONFIGURATION.getItem("ingotTin", BlockIds.ingotTin).getInt();
        int inPaxel = EntrophicFurnace.CONFIGURATION.getItem("EntrophicPaxel", BlockIds.entrophicPaxel).getInt();

        entrophicFurnace1 = (new BlockEntrophicFurnace1(410, UniversalElectricity.machine))
                .setHardness(0.1F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicFurnace2 = (new BlockEntrophicFurnace2(411, UniversalElectricity.machine))
                .setHardness(0.1F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicFurnace3 = (new BlockEntrophicFurnace3(412, UniversalElectricity.machine))
                .setHardness(0.1F).setStepSound(Block.soundWoodFootstep).setCreativeTab(CreativeTabs.tabMaterials);
        entrophicPaxel = new ItemEntrophicPaxel(413, entrophicMaterial, "entrophicPaxel");
        entrophicOre = (new ItemEntrophicOre(414));
        entrophicOre1 = (new ItemEntrophicOre1(415));
        entrophicOre2 = (new ItemEntrophicOre2(416));
        entrophicOre3 = (new ItemEntrophicOre3(417));
        entrophicOre4 = (new ItemEntrophicOre4(418));
        entrophicOre5 = (new ItemEntrophicOre5(419));
        ingotCopper = (new ItemIngotCopper(420));
        ingotTin = (new ItemIngotTin(421));
        EntrophicFurnace.CONFIGURATION.save();

        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace1, "EntrophicFurnace1");
        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace2, "EntrophicFurnace2");
        GameRegistry.registerBlock(EntrophicFurnace.entrophicFurnace3, "EntrophicFurnace3");
        GameRegistry.registerTileEntity(TileEntityEntrophicFurnace.class, "EntrophicFurnace1");
        GameRegistry.registerTileEntity(TileEntityEntrophicFurnace.class, "EntrophicFurnace2");
        GameRegistry.registerTileEntity(TileEntityEntrophicFurnace.class, "EntrophicFurnace3");

        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace1, "Entrophic Furnace 1");
        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace2, "Entrophic Furnace 2");
        LanguageRegistry.addName(EntrophicFurnace.entrophicFurnace3, "Entrophic Furnace 3");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre, "Entrophic Ore");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre1, "Entrophic Ore 1");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre2, "Entrophic Ore 2");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre3, "Entrophic Ore 3");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre4, "Entrophic Ore 4");
        LanguageRegistry.addName(EntrophicFurnace.entrophicOre5, "Entrophic Ore 5");
        LanguageRegistry.addName(EntrophicFurnace.ingotCopper, "Copper");
        LanguageRegistry.addName(EntrophicFurnace.ingotTin, "Tin");
        LanguageRegistry.addName(EntrophicFurnace.entrophicPaxel, "Entrophic Paxel");
        proxy.preInit();
    }

    /**
     *
     * @param event
     */
    @SuppressWarnings({ "boxing", "static-method" })
    @Init
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
        OreDictionary.registerOre("ingotTin", new ItemStack(ingotTin));
        OreDictionary.registerOre("quantumOre", new ItemStack(entrophicOre));
        OreDictionary.registerOre("quantumOre1", new ItemStack(entrophicOre1));
        OreDictionary.registerOre("quantumOre2", new ItemStack(entrophicOre2));
        OreDictionary.registerOre("quantumOre3", new ItemStack(entrophicOre3));
        OreDictionary.registerOre("quantumOre4", new ItemStack(entrophicOre4));
        OreDictionary.registerOre("quantumOre5", new ItemStack(entrophicOre5));
        OreDictionary.registerOre("EntrophicFurnace1", new ItemStack(EntrophicFurnace.entrophicFurnace1));
        OreDictionary.registerOre("EntrophicFurnace2", new ItemStack(EntrophicFurnace.entrophicFurnace2));
        OreDictionary.registerOre("EntrophicFurnace3", new ItemStack(EntrophicFurnace.entrophicFurnace3));

        // Add the entrophic ore, this is used for making other stuff and getting hard to find stuff,
        // like blaze rods or feathers
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(EntrophicFurnace.entrophicOre, 36), new Object[] {
            "III", "IFI", "III", 'I', Block.workbench, 'F', EntrophicFurnace.entrophicFurnace1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre1, new Object[] { "   ", "   ", " o ",
                'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre2, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre3, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre4, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicOre5, new Object[] { "ooo", "ooo", "oo ",
                'o', EntrophicFurnace.entrophicOre4 }));
        
        if (this.hardMode)
        {
            // Hard mode recipes, inspired by GregTech to make your life difficult.
        }
        else
        {
            // Older easy recipes, best for sky block or super flat worlds
            // Add in the entrophic furnaces
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace1, new Object[] {
            		"III", "IFI", "III", 'I', Block.wood, 'F', Block.workbench }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace2, new Object[] {
            		"I I", " F ", "I I", 'I', EntrophicFurnace.entrophicFurnace1, 'F', Block.blockGold }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.entrophicFurnace3, new Object[] {
            		"I I", " F ", "I I", 'I', EntrophicFurnace.entrophicFurnace2, 'F', Item.netherStar }));
            
            // Add the UBer paxel, because every mod has to have a bad ass weapon/ tool
            GameRegistry.addRecipe(new ItemStack(EntrophicFurnace.entrophicPaxel, 1), new Object[] {
            		"A", "X", "X", 'A', Block.blockDiamond, 'X', EntrophicFurnace.entrophicOre4 });
    
            // Generic tin and copper definition, because every forge tech mod uses these. Forge compatible!
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.ingotCopper, new Object[] { "ooo", "o o", "ooo",
                    'o', EntrophicFurnace.entrophicOre2 }));
            GameRegistry.addRecipe(new ShapedOreRecipe(EntrophicFurnace.ingotTin, new Object[] { "oo ", "ooo", "ooo", 'o',
                    EntrophicFurnace.entrophicOre2 }));
        }
        // Add alternate recipies for those (un)lucky enough to be using GregTech.
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockGold, new Object[] { "aab", "aab", "bb ", 'a',
                EntrophicFurnace.entrophicOre4, 'b', EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockDiamond, new Object[] { " o ", "   ", " o ", 'o',
                EntrophicFurnace.entrophicOre5 }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.cobblestone, new Object[] { "o  ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.dirt, new Object[] { " o ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.sand, new Object[] { "   ", "   ", "o  ", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.stone, new Object[] { "   ", "o  ", "   ", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.glass, new Object[] { "   ", " o ", "   ", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.cobblestoneMossy, new Object[] { "   ", "   ", " o ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.silk, new Object[] { "   ", "   ", "o  ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.netherrack, new Object[] { "   ", "o  ", "   ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.slowSand, new Object[] { "   ", " o ", "   ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.mycelium, new Object[] { "o  ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.grass, new Object[] { " o ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.gravel, new Object[] { " o ", "o o", " o ", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.flint, new Object[] { " o ", "o o", "  o", 'o',
                EntrophicFurnace.entrophicOre1 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.netherBrick, new Object[] { "o  ", " o ", "o o", 'o',
                EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.cloth, 1, 0), new Object[] { "o  ", "ooo",
                "   ", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.feather, 1, 3), new Object[] { " oo", "oo ",
                "o  ", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.egg, new Object[] { " oo", " oo",
            " oo", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.chickenRaw, new Object[] { "o o", " oo",
            " oo", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.chickenCooked, new Object[] { " oo", " oo",
            "o o", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.porkRaw, new Object[] { "ooo", " oo",
                "o  ", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.porkCooked, new Object[] { "ooo", "oo ",
                " o ", 'o', EntrophicFurnace.entrophicOre }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 0), new Object[] { "o o", " o ",
                "o o", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 1), new Object[] { " o ", "o o",
                "o o", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 2), new Object[] { "ooo", "o o",
                "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.sapling, 1, 3), new Object[] { "o  ", "ooo",
                "  o", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.netherStalkSeeds, 1, 3), new Object[] { "   ",
                " o ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.furnaceIdle, new Object[] { " o ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal, 1, 0), new Object[] { "   ", " o ", "  o",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Item.coal, 1, 1), new Object[] { "   ", " o ", " o ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 0), new Object[] { "o o", "   ", "   ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 1), new Object[] { "   ", "  o", " o ",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 2), new Object[] { "   ", "  o", "  o",
                'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Block.wood, 1, 3), new Object[] { "o  ", "   ", "  o",
                'o', EntrophicFurnace.entrophicOre2 }));

        GameRegistry.addRecipe(new ShapedOreRecipe(Item.seeds, new Object[] {
        		"o  ", "o  ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.wheat, new Object[] {
        		"o  ", "  o", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.reed, new Object[] {
        		"o  ", "   ", "o  ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.cactus, new Object[] {
        		" o ", " o ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.melon, new Object[] {
        		"oo ", "   ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.pumpkin, new Object[] {
        		"   ", "oo ", "   ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.carrot, new Object[] {
        		" o ", "   ", "o  ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.potato, new Object[] {
        		" o ", "   ", " o ", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.gunpowder, new Object[] {
        		"   ", " o ", "o o", 'o', EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.leather, new Object[] { "o  ", " o ", "   ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.blockClay, new Object[] { "oo ", " oo", "   ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.enderPearl, new Object[] { "oo ", "oo ", "   ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.obsidian, new Object[] { " o ", "o o", " o ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.waterlily, new Object[] { "   ", "oo ", "oo ", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.ingotIron, new Object[] { "ooo", "oo ", "ooo", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.redstone, new Object[] { "ooo", "ooo", "o o", 'o',
                EntrophicFurnace.entrophicOre2 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Block.glowStone, new Object[] { "   ", " oo", "   ", 'o',
                EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.slimeBall, new Object[] { "oo ", "   ", "   ", 'o',
                EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.blazeRod, new Object[] { "   ", "o o", "   ", 'o',
                EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.ingotGold, new Object[] { " o ", "o  ", " oo", 'o',
                EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.emerald, new Object[] { "   ", " o ", " o ", 'o',
                EntrophicFurnace.entrophicOre3 }));
        GameRegistry.addRecipe(new ShapedOreRecipe(Item.diamond, new Object[] { "   ", " o ", " o ", 'o',
                EntrophicFurnace.entrophicOre4 }));
    }

    /**
     *
     * @param event
     */
    @PostInit
    public void postInit(FMLPostInitializationEvent event)
    {
        //
    }
}