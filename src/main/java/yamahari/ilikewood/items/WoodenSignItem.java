package yamahari.ilikewood.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;
import yamahari.ilikewood.Main;
import yamahari.ilikewood.tileentities.WoodenSignTileEntity;
import yamahari.ilikewood.util.OpenWoodenSignEditor;

import javax.annotation.Nullable;
import java.util.Map;

public class WoodenSignItem extends BlockItem {
    protected final Block wallBlock;
    protected final Block hangingBlock;

    public WoodenSignItem(Block floorBlock, Block wallBlockIn, Block hangingBlock, Item.Properties p_i48462_3_) {
        super(floorBlock, p_i48462_3_);
        this.wallBlock = wallBlockIn;
        this.hangingBlock = hangingBlock;
    }

    @Override
    protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
        boolean flag = super.onBlockPlaced(pos, worldIn, player, stack, state);
        if (!worldIn.isRemote && !flag && player != null) {
            //player.openSignEditor((SignTileEntity)worldIn.getTileEntity(pos));
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof WoodenSignTileEntity) {
                ((WoodenSignTileEntity)te).setPlayer(player);
                Main.simpleChannel.sendTo(new OpenWoodenSignEditor(pos), ((ServerPlayerEntity) player).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
            }
        }

        return flag;
    }

    @Nullable
    @Override
    protected BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState result = null;
        IWorldReader iworldreader = context.getWorld();
        BlockPos blockpos = context.getPos();

        boolean flag = false;
        for(Direction direction : context.getNearestLookingDirections()) {
            BlockState tmp = null;
            switch (direction) {
                case UP:
                    if((tmp = hangingBlock.getStateForPlacement(context)) != null && tmp.isValidPosition(iworldreader, blockpos)) {
                        result = tmp;
                        flag = true;
                    }
                    break;
                case DOWN:
                    if((tmp = this.getBlock().getStateForPlacement(context)) != null && tmp.isValidPosition(iworldreader, blockpos)) {
                        result = tmp;
                        flag = true;
                    }
                    break;
                default:
                    if((tmp = wallBlock.getStateForPlacement(context)) != null && tmp.isValidPosition(iworldreader, blockpos)) {
                        result = tmp;
                        flag = true;
                    }
                    break;
            }
            if(flag) break;
        }
        return result != null && iworldreader.func_217350_a(result, blockpos, ISelectionContext.dummy()) ? result : null;
    }

    @Override
    public void addToBlockToItemMap(Map<Block, Item> blockToItemMap, Item itemIn) {
        super.addToBlockToItemMap(blockToItemMap, itemIn);
        blockToItemMap.put(this.wallBlock, itemIn);
        blockToItemMap.put(this.hangingBlock, itemIn);
    }
}
