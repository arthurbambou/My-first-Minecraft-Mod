package arthurbambou.paintingmod.util.handlers;

import arthurbambou.paintingmod.api.ColoredBlockMeta;
import arthurbambou.paintingmod.api.ColoredFallingBlockMeta;
import arthurbambou.paintingmod.init.PTMBlocks;
import arthurbambou.paintingmod.init.PTMItems;
import arthurbambou.paintingmod.util.PTMIHasModel;
import arthurbambou.paintingmod.util.PTMReference;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PTMRegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		if (PTMReference.getMinecraftVersion() == "[1.13]" || PTMReference.getMinecraftVersion() == "[1.13.1]" || PTMReference.getMinecraftVersion() == "[1.13.2]") {
			event.getRegistry().registerAll(PTMItems.ITEMS_ID.toArray(new Item[0]));
		} else {
            event.getRegistry().registerAll(PTMItems.ITEMS_META.toArray(new Item[0]));
        }
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		if (PTMReference.getMinecraftVersion() == "[1.13]" || PTMReference.getMinecraftVersion() == "[1.13.1]" || PTMReference.getMinecraftVersion() == "[1.13.2]") {
			event.getRegistry().registerAll(PTMBlocks.BLOCKS_ID.toArray(new Block[0]));
		} else {
            event.getRegistry().registerAll(PTMBlocks.BLOCKS_META.toArray(new Block[0]));

            event.getRegistry().registerAll(PTMBlocks.COLORED_BLOCKS_META.toArray(new Block[0]));

            event.getRegistry().registerAll(PTMBlocks.COLORED_FALLING_BLOCK_METAS.toArray(new Block[0]));

            event.getRegistry().register(PTMBlocks.SLIME_BLOCK_META);
		}
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
        if (PTMReference.getMinecraftVersion() == "[1.13]" || PTMReference.getMinecraftVersion() == "[1.13.1]" || PTMReference.getMinecraftVersion() == "[1.13.2]") {
        for (Item item : PTMItems.ITEMS_ID) {
            if (item instanceof PTMIHasModel) {
                ((PTMIHasModel) item).registerModels();
            }
        }

        for (Block block : PTMBlocks.BLOCKS_ID) {
            if (block instanceof PTMIHasModel) {
                ((PTMIHasModel) block).registerModels();
            }
        }
        } else {
        for (Block block : PTMBlocks.BLOCKS_META) {
            if (block instanceof PTMIHasModel) {
                ((PTMIHasModel) block).registerModels();
            }
        }

        for (Item item : PTMItems.ITEMS_META) {
            if (item instanceof PTMIHasModel) {
                ((PTMIHasModel) item).registerModels();
            }
        }

        for (Block block : PTMBlocks.COLORED_BLOCKS_META) {
            if (block instanceof PTMIHasModel) {
                ((PTMIHasModel) block).registerModels();
            }
        }
            for (Block block : PTMBlocks.COLORED_FALLING_BLOCK_METAS) {
                if (block instanceof PTMIHasModel) {
                    ((PTMIHasModel) block).registerModels();
                }
            }
            ((PTMIHasModel) PTMBlocks.SLIME_BLOCK_META).registerModels();
        }

	}

	public static void APIinit() {
	    for (ColoredBlockMeta block : PTMBlocks.COLORED_BLOCKS_META) {
	        block.getLocalizedName();
        }
        for (ColoredFallingBlockMeta blockMeta : PTMBlocks.COLORED_FALLING_BLOCK_METAS) {
            blockMeta.getLocalizedName();
        }
        PTMBlocks.SLIME_BLOCK_META.getLocalizedName();
    }
}