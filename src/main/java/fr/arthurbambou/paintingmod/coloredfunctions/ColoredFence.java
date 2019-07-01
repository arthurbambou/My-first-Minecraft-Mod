package fr.arthurbambou.paintingmod.coloredfunctions;

import fr.arthurbambou.paintingmod.PaintingMod;
import fr.arthurbambou.paintingmod.api.ColoredFunction;
import fr.arthurbambou.paintingmod.api.ColoredObject;
import fr.arthurbambou.paintingmod.items.Paintbrush;
import fr.arthurbambou.paintingmod.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ColoredFence extends ColoredFunction {
    public ColoredFence() {
        super(new ResourceLocation(PaintingMod.MODID, "fence"));
    }

    @Override
    public void paint(ColoredObject coloredObject, ItemUseContext context, Paintbrush paintbrush) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        ArrayList<Paintbrush> paintbrushes = ModItems.PAINTBRUSHES;
        if (paintbrush == paintbrushes.get(0)) return;
        if (coloredObject instanceof fr.arthurbambou.paintingmod.api.coloredblocks.ColoredFence &&
                worldIn.getBlockState(pos).getBlock() == coloredObject.replace) {
                BlockState blockState = worldIn.getBlockState(pos);
                for (int a = 0; a < coloredObject.getBlockArray().length; a++) {
                    if (player.getHeldItemMainhand().getItem() == paintbrushes.get(a +1)) {
                        worldIn.setBlockState(pos, coloredObject.getBlockArray()[a].getDefaultState()
                                .with(FenceBlock.NORTH, blockState.get(FenceBlock.NORTH))
                                .with(FenceBlock.EAST, blockState.get(FenceBlock.EAST))
                                .with(FenceBlock.SOUTH, blockState.get(FenceBlock.SOUTH))
                                .with(FenceBlock.WEST, blockState.get(FenceBlock.WEST))
                                .with(FenceBlock.WATERLOGGED, blockState.get(FenceBlock.WATERLOGGED)));
                        usedpaintbrush(player);
                    }
                }
        } else {
            return;
        }
    }

    @Override
    public void unPaint(ColoredObject coloredObject, ItemUseContext context) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        if (coloredObject instanceof fr.arthurbambou.paintingmod.api.coloredblocks.ColoredFence) {
            List<Block> list = new ArrayList<>();
            for (int a = 0; a < coloredObject.getBlockArray().length; a++)
                list.add(coloredObject.getBlockArray()[a]);
            if (list.contains(worldIn.getBlockState(pos).getBlock())) {
                BlockState blockState = worldIn.getBlockState(pos);
                FenceBlock blockFence = (FenceBlock) coloredObject.replace;

                worldIn.setBlockState(pos, blockFence.getStateForPlacement(new BlockItemUseContext(context)));
                usedHeatGun(player);
            }
        }
    }
}
