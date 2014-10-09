package com.insane.illuminatedbows.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.event.FMLInterModComms;

public class ThermalExpansionUtil {
	
	public static void addTransposerFillRecipe(ItemStack input, ItemStack output, FluidStack fluid, int energy) {
		NBTTagCompound toSend = new NBTTagCompound();
		toSend.setInteger("energy", energy);
		toSend.setTag("input", new NBTTagCompound());
		toSend.setTag("output", new NBTTagCompound());
		toSend.setTag("fluid", new NBTTagCompound());

		input.writeToNBT(toSend.getCompoundTag("input"));
		output.writeToNBT(toSend.getCompoundTag("output"));
		toSend.setBoolean("reversible", false);
		fluid.writeToNBT(toSend.getCompoundTag("fluid"));
		FMLInterModComms.sendMessage("ThermalExpansion", "TransposerFillRecipe", toSend);
	}

    public static void addSawmillRecipeWithChance(ItemStack input, ItemStack output, ItemStack output2, int chance, int energy) {
        NBTTagCompound toSend = new NBTTagCompound();

        toSend.setInteger("energy",energy);
        toSend.setTag("input",new NBTTagCompound());
        toSend.setTag("primaryOutput", new NBTTagCompound());
        toSend.setTag("secondaryOutput", new NBTTagCompound());

        input.writeToNBT(toSend.getCompoundTag("input"));
        output.writeToNBT(toSend.getCompoundTag("primaryOutput"));

        output2.writeToNBT(toSend.getCompoundTag("secondaryOutput"));
        toSend.setInteger("secondaryChance",chance);

        FMLInterModComms.sendMessage("ThermalExpansion", "SawmillRecipe", toSend);
    }

}
