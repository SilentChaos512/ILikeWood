package yamahari.ilikewood.tileentities.chest;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.DoubleSidedInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.properties.ChestType;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.blocks.chest.WoodenChestBlock;
import yamahari.ilikewood.util.WoodType;

public class WoodenChestTileEntity extends LockableLootTileEntity implements IChestLid, ITickableTileEntity {
    private NonNullList<ItemStack> chestContents = NonNullList.withSize(27, ItemStack.EMPTY);
    protected float lidAngle;
    protected float prevLidAngle;
    protected int numPlayersUsing;
    private int ticksSinceSync;
    private net.minecraftforge.common.util.LazyOptional<net.minecraftforge.items.IItemHandlerModifiable> chestHandler;
    private WoodType woodType;

    protected WoodenChestTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    public WoodenChestTileEntity(TileEntityType<?> typeIn, WoodType woodType) {
        this(typeIn);
        this.woodType = woodType;
    }

    public WoodType getWoodType() { return woodType; }

    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if (!this.checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, this.chestContents);
        }

    }

    @Override
    public boolean canRenderBreaking() {
        return true;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (!this.checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, this.chestContents);
        }

        return compound;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.chestContents;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> nonNullList) {
        this.chestContents = nonNullList;
    }

    @Override
    protected ITextComponent getDefaultName() {
        switch (woodType) {
            case OAK:
            default:
                return new TranslationTextComponent("container.ilikewood.oak_chest");
            case DARK_OAK:
                return new TranslationTextComponent("container.ilikewood.dark_oak_chest");
            case SPRUCE:
                return new TranslationTextComponent("container.ilikewood.spruce_chest");
            case BIRCH:
                return new TranslationTextComponent("container.ilikewood.birch_chest");
            case JUNGLE:
                return new TranslationTextComponent("container.ilikewood.jungle_chest");
            case ACACIA:
                return new TranslationTextComponent("container.ilikewood.acacia_chest");
        }
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(pos.add(-1, 0, -1), pos.add(2, 2, 2));
    }

    @Override
    protected Container createMenu(int p_213906_1_, PlayerInventory p_213906_2_) {
        return ChestContainer.createGeneric9X3(p_213906_1_, p_213906_2_, this);
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for(ItemStack itemstack : this.chestContents) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public float getLidAngle(float partialTicks) {
        return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
    }

    @Override
    public void tick() {
        int i = this.pos.getX();
        int j = this.pos.getY();
        int k = this.pos.getZ();
        ++this.ticksSinceSync;
        this.numPlayersUsing = func_213977_a(this.world, this, this.ticksSinceSync, i, j, k, this.numPlayersUsing);
        this.prevLidAngle = this.lidAngle;
        float f = 0.1F;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
            this.playSound(SoundEvents.BLOCK_CHEST_OPEN);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
            float f1 = this.lidAngle;
            if (this.numPlayersUsing > 0) {
                this.lidAngle += 0.1F;
            } else {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F) {
                this.lidAngle = 1.0F;
            }

            float f2 = 0.5F;
            if (this.lidAngle < 0.5F && f1 >= 0.5F) {
                this.playSound(SoundEvents.BLOCK_CHEST_CLOSE);
            }

            if (this.lidAngle < 0.0F) {
                this.lidAngle = 0.0F;
            }
        }
    }

    private void playSound(SoundEvent soundIn) {
        ChestType chesttype = this.getBlockState().get(WoodenChestBlock.TYPE);
        if (chesttype != ChestType.LEFT) {
            double d0 = (double)this.pos.getX() + 0.5D;
            double d1 = (double)this.pos.getY() + 0.5D;
            double d2 = (double)this.pos.getZ() + 0.5D;
            if (chesttype == ChestType.RIGHT) {
                Direction direction = WoodenChestBlock.getDirectionToAttached(this.getBlockState());
                d0 += (double)direction.getXOffset() * 0.5D;
                d2 += (double)direction.getZOffset() * 0.5D;
            }

            this.world.playSound((PlayerEntity)null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }
    }

    @Override
    public boolean receiveClientEvent(int id, int type) {
        if (id == 1) {
            this.numPlayersUsing = type;
            return true;
        } else {
            return super.receiveClientEvent(id, type);
        }
    }

    @Override
    public void openInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.numPlayersUsing < 0) {
                this.numPlayersUsing = 0;
            }

            ++this.numPlayersUsing;
            this.onOpenOrClose();
        }

    }

    @Override
    public void closeInventory(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.numPlayersUsing;
            this.onOpenOrClose();
        }

    }

    protected void onOpenOrClose() {
        Block block = this.getBlockState().getBlock();
        if (block instanceof ChestBlock) {
            this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
            this.world.notifyNeighborsOfStateChange(this.pos, block);
        }
    }

    public static void swapContents(WoodenChestTileEntity chest, WoodenChestTileEntity otherChest) {
        NonNullList<ItemStack> nonnulllist = chest.getItems();
        chest.setItems(otherChest.getItems());
        otherChest.setItems(nonnulllist);
    }

    @Override
    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
        if (this.chestHandler != null) {
            this.chestHandler.invalidate();
            this.chestHandler = null;
        }
    }

    @Override
    public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(net.minecraftforge.common.capabilities.Capability<T> cap, Direction side) {
        if (!this.removed && cap == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (this.chestHandler == null) {
                this.chestHandler = net.minecraftforge.common.util.LazyOptional.of(this::createHandler);
            }
            return this.chestHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    private net.minecraftforge.items.IItemHandlerModifiable createHandler() {
        BlockState state = this.getBlockState();
        if (!(state.getBlock() instanceof WoodenChestBlock)) {
            return new net.minecraftforge.items.wrapper.InvWrapper(this);
        }
        ChestType type = state.get(WoodenChestBlock.TYPE);
        if (type != ChestType.SINGLE) {
            BlockPos opos = this.getPos().offset(WoodenChestBlock.getDirectionToAttached(state));
            BlockState ostate = this.getWorld().getBlockState(opos);
            if (state.getBlock() == ostate.getBlock()) {
                ChestType otype = ostate.get(WoodenChestBlock.TYPE);
                if (otype != ChestType.SINGLE && type != otype && state.get(WoodenChestBlock.FACING) == ostate.get(WoodenChestBlock.FACING)) {
                    TileEntity ote = this.getWorld().getTileEntity(opos);
                    if (ote instanceof WoodenChestTileEntity) {
                        IInventory top    = type == ChestType.RIGHT ? this : (IInventory)ote;
                        IInventory bottom = type == ChestType.RIGHT ? (IInventory)ote : this;
                        return new net.minecraftforge.items.wrapper.CombinedInvWrapper(
                                new net.minecraftforge.items.wrapper.InvWrapper(top),
                                new net.minecraftforge.items.wrapper.InvWrapper(bottom));
                    }
                }
            }
        }
        return new net.minecraftforge.items.wrapper.InvWrapper(this);
    }

    @Override
    public void remove() {
        super.remove();
        if (chestHandler != null)
            chestHandler.invalidate();
    }

    public static int func_213977_a(World p_213977_0_, LockableTileEntity p_213977_1_, int p_213977_2_, int p_213977_3_, int p_213977_4_, int p_213977_5_, int p_213977_6_) {
        if (!p_213977_0_.isRemote && p_213977_6_ != 0 && (p_213977_2_ + p_213977_3_ + p_213977_4_ + p_213977_5_) % 200 == 0) {
            p_213977_6_ = func_213976_a(p_213977_0_, p_213977_1_, p_213977_3_, p_213977_4_, p_213977_5_);
        }

        return p_213977_6_;
    }

    public static int func_213976_a(World p_213976_0_, LockableTileEntity p_213976_1_, int p_213976_2_, int p_213976_3_, int p_213976_4_) {
        int i = 0;
        float f = 5.0F;

        for(PlayerEntity playerentity : p_213976_0_.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double)((float)p_213976_2_ - 5.0F), (double)((float)p_213976_3_ - 5.0F), (double)((float)p_213976_4_ - 5.0F), (double)((float)(p_213976_2_ + 1) + 5.0F), (double)((float)(p_213976_3_ + 1) + 5.0F), (double)((float)(p_213976_4_ + 1) + 5.0F)))) {
            if (playerentity.openContainer instanceof ChestContainer) {
                IInventory iinventory = ((ChestContainer)playerentity.openContainer).getLowerChestInventory();
                if (iinventory == p_213976_1_ || iinventory instanceof DoubleSidedInventory && ((DoubleSidedInventory)iinventory).isPartOfLargeChest(p_213976_1_)) {
                    ++i;
                }
            }
        }

        return i;
    }
}
