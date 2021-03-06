package com.naxanria.colouredeggs.block;

import com.naxanria.colouredeggs.ColouredEggs;
import com.naxanria.colouredeggs.ColouredEggsTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockBase extends Block implements IBlockBase
{
  public final String name;
  
  public BlockBase(Material blockMaterial, String name)
  {
    super(blockMaterial);
    
    this.name = name;
    
    setUnlocalizedName(ColouredEggs.MODID + "." + name);
    setRegistryName(ColouredEggs.MODID, name);
    
    setCreativeTab(ColouredEggsTab.INSTANCE);
  }
  
  public Item getAsItem()
  {
    return Item.getItemFromBlock(this);
  }
  
  @Override
  public void registerItemModel(Item itemBlock)
  {
    ColouredEggs.proxy.registerItemRender(itemBlock, 0, name);
  }
  
  @Override
  public void registerItemModel()
  {
    registerItemModel(getAsItem());
  }
  
  @Override
  public Item createItemBlock()
  {
    return new ItemBlock(this).setRegistryName(getRegistryName());
  }
  
  @Override
  public Block getBlock()
  {
    return this;
  }
  
  @Override
  public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player)
  {
    if (!player.capabilities.isCreativeMode)
    {
      dropBlockAsItem(world, pos, state, 0);
      //dirty workaround because of Forge calling Item.onBlockStartBreak() twice
      world.setBlockToAir(pos);
    }
  }
  
 
}
