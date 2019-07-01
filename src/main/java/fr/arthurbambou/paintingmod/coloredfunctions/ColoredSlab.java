package fr.arthurbambou.paintingmod.coloredfunctions;

import fr.arthurbambou.paintingmod.PaintingMod;
import fr.arthurbambou.paintingmod.api.ColoredFunction;
import fr.arthurbambou.paintingmod.api.ColoredObject;
import fr.arthurbambou.paintingmod.items.Paintbrush;
import fr.arthurbambou.paintingmod.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ColoredSlab extends ColoredFunction {
    public ColoredSlab() {
        super(new ResourceLocation(PaintingMod.MODID, "slabs"));
    }

    @Override
    public void paint(ColoredObject coloredObject, ItemUseContext context, Paintbrush paintbrush) {
        World worldIn = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();
        ArrayList<Paintbrush> paintbrushes = ModItems.PAINTBRUSHES;
        if (paintbrush == paintbrushes.get(0)) return;
        if (coloredObject instanceof fr.arthurbambou.paintingmod.api.coloredblocks.ColoredSlab) {
            if (worldIn.getBlockState(pos).getBlock() == coloredObject.replace) {
                BlockState blockState = worldIn.getBlockState(pos);
                for (int a = 0; a < coloredObject.getBlockArray().length; a++) {
                    if (player.getHeldItemMainhand().getItem() == paintbrushes.get(a +1)) {
                        worldIn.setBlockState(pos, coloredObject.getBlockArray()[a].getDefaultState()
                                .with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE))
                                .with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)));
                        usedpaintbrush(player);
                    }
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
        if (coloredObject instanceof fr.arthurbambou.paintingmod.api.coloredblocks.ColoredSlab) {
            List<Block> list = new ArrayList<>();
            for (int a = 0; a < coloredObject.getBlockArray().length; a++)
                list.add(coloredObject.getBlockArray()[a]);
            if (list.contains(worldIn.getBlockState(pos).getBlock())) {
                BlockState blockState = worldIn.getBlockState(pos);

                worldIn.setBlockState(pos, coloredObject.replace.getDefaultState()
                        .with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE))
                        .with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)));
                usedHeatGun(player);
            }
        }
    }
}
