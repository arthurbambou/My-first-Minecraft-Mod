package fr.arthurbambou.paintingmod.mainmod.coloredfunctions;

import fr.arthurbambou.paintingmod.mainmod.api.ColoredFunction;
import fr.arthurbambou.paintingmod.mainmod.api.ColoredObject;
import fr.arthurbambou.paintingmod.mainmod.coloredblocks.ColoredSlab;
import fr.arthurbambou.paintingmod.mainmod.registery.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SlabFunction extends ColoredFunction {
    public SlabFunction(Identifier identifier) {
        super(identifier);
    }

    @Override
    public void paint(ItemUsageContext var1, ColoredObject coloredObject) {
        BlockPos pos = var1.getBlockPos();
        World worldIn = var1.getWorld();
        PlayerEntity player = var1.getPlayer();
        BlockState blockState = worldIn.getBlockState(pos);
        if (coloredObject instanceof ColoredSlab
                && worldIn.getBlockState(pos).getBlock() == coloredObject.getReplace()) {
            for (int a = 0; a < coloredObject.getArrayList().size(); a++) {
                if (player.getStackInHand(Hand.MAIN_HAND).getItem() == ModItems.PAINTBRUSHES.get(a + 1)) {
                    worldIn.setBlockState(pos, coloredObject.getArrayList().get(a).getStateFactory().getDefaultState()
                            .with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE))
                            .with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)));
                    usedpaintbrush(player);
                }
            }
        }
    }

    @Override
    public void unPaint(ItemUsageContext var1, ColoredObject coloredObject) {
        BlockPos pos = var1.getBlockPos();
        World worldIn = var1.getWorld();
        PlayerEntity player = var1.getPlayer();
        BlockState blockState = worldIn.getBlockState(pos);
        if (coloredObject instanceof ColoredSlab) {
            for (int b = 0; b < coloredObject.getArrayList().size(); b++) {
                if (worldIn.getBlockState(pos).getBlock() == coloredObject.getArrayList().get(b)) {
                    worldIn.setBlockState(pos, coloredObject.getReplace().getStateFactory().getDefaultState()
                            .with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE))
                            .with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)));
                    heatgun(player.getStackInHand(Hand.MAIN_HAND));
                }
            }
        }
    }

    @Override
    public boolean isFromType(ColoredObject coloredObject) {
        if (coloredObject instanceof ColoredSlab) return true;
        return false;
    }

    @Override
    public Class getColoredObjectType() {
        return ColoredSlab.class;
    }
}
