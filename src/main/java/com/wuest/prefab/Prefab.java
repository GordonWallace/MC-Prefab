package com.wuest.prefab;

import java.util.List;

import com.wuest.prefab.Proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry.Type;

/**
 * The starting point to load all of the blocks, items and other objects
 * associated with this mod.
 * 
 * @author WuestMan
 *
 */
@Mod(modid = Prefab.MODID, version = Prefab.VERSION, acceptedMinecraftVersions = "[1.10.2]", guiFactory = "com.wuest.prefab.Gui.ConfigGuiFactory", updateJSON = "https://raw.githubusercontent.com/Brian-Wuest/MC-Prefab/master/changeLog.json")
public class Prefab
{
	/**
	 * This is the ModID
	 */
	public static final String MODID = "prefab";
	
	/**
	 * This is the current mod version.
	 */
	public static final String VERSION = "@VERSION@";
	
	/**
	 * This is used to determine if the mod is currently being debugged.
	 */
	public static boolean isDebug = false;

	/**
	 * This is the static instance of this class.
	 */
	@Instance(value = Prefab.MODID)
	public static Prefab instance;

	/**
	 * Says where the client and server 'proxy' code is loaded.
	 */
	@SidedProxy(clientSide = "com.wuest.prefab.Proxy.ClientProxy", serverSide = "com.wuest.prefab.Proxy.CommonProxy")
	public static CommonProxy proxy;

	/**
	 * The network class used to send messages.
	 */
	public static SimpleNetworkWrapper network;
	
	/**
	 * This is the configuration of the mod.
	 */
	public static Configuration config;

	static
	{
		Prefab.isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().contains("-agentlib:jdwp");
	}

	/**
	 * The pre-initialization event.
	 * @param event The event from forge.
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Prefab.proxy.preInit(event);
	}

	/**
	 * The initialization event.
	 * @param event The event from forge.
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		Prefab.proxy.init(event);
	}

	/**
	 * The post-initialization event.
	 * @param event The event from forge.
	 */
	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{
		Prefab.proxy.postinit(event);
	}
	
	@EventHandler
	public void OnMissingMapping(FMLMissingMappingsEvent event) 
	{
		List<MissingMapping> missingMappings = event.get();
		
		for(MissingMapping mapping : missingMappings) 
		{
			if (mapping.type == Type.BLOCK)
			{
				Block mappedBlock = null;
				
				switch (mapping.resourceLocation.getResourcePath())
				{
					case "blockCompressedStone":
					{
						mappedBlock = ModRegistry.CompressedStoneBlock();
					}
				}
				
				if (mappedBlock != null)
				{
					mapping.remap(mappedBlock);
				}
			}
			else
			{
				Item mappedItem = null;
				
				switch (mapping.resourceLocation.getResourcePath())
				{
					case "blockCompressedStone":
					{
						mappedItem = ModRegistry.ModItems.stream().filter(item -> item.getRegistryName().getResourcePath().equals("block_compressed_stone")).findFirst().get();
						break;
					}
					
					case "itemProduceFarm" :
					{
						mappedItem = ModRegistry.ProduceFarm();
						break;
					}
					
					case "itemPileOfBricks" :
					{
						mappedItem = ModRegistry.PileOfBricks();
						break;
					}
					
					case "itemHorseStable" :
					{
						mappedItem = ModRegistry.HorseStable();
						break;
					}
					
					case "itemNetherGate" :
					{
						mappedItem = ModRegistry.NetherGate();
						break;
					}
					
					case "itemWareHouseUpgrade" :
					{
						mappedItem = ModRegistry.WareHouseUpgrade();
						break;
					}
					
					case "itemChickenCoop" :
					{
						mappedItem = ModRegistry.ChickenCoop();
						break;
					}
					
					case "itemTreeFarm" :
					{
						mappedItem = ModRegistry.TreeFarm();
						break;
					}
					
					case "itemCompressedChest" :
					{
						mappedItem = ModRegistry.CompressedChestItem();
						break;
					}
					
					case "itemBundleOfTimber" :
					{
						mappedItem = ModRegistry.BundleOfTimber();
						break;
					}
					
					case "itemWareHouse" :
					{
						mappedItem = ModRegistry.WareHouse();
						break;
					}
					
					case "itemPalletOfBricks" :
					{
						mappedItem = ModRegistry.PalletOfBricks();
						break;
					}
					
					case "itemFishPond" :
					{
						mappedItem = ModRegistry.FishPond();
						break;
					}
					
					case "itemMonsterMasher" :
					{
						mappedItem = ModRegistry.MonsterMasher();
						break;
					}
					
					case "itemStartHouse" :
					{
						mappedItem = ModRegistry.StartHouse();
						break;
					}
					
					case "itemAdvancedWareHouse" :
					{
						mappedItem = ModRegistry.AdvancedWareHouse();
						break;
					}
				}
				
				if (mappedItem != null)
				{
					mapping.remap(mappedItem);
				}
			}
		}
	}
}
