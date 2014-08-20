package com.insane.illuminatedbows;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 19/08/2014.
 */
public class Config {

    public static boolean bowTakesDamage;

    public static int arrowEnergy;
    public static int bowEnergy;
    public static int saplingEnergy;

    public static int arrowLiquidAmount;
    public static int bowLiquidAmount;
    public static int saplingLiquidAmount;

    public static int boneMealChance;

    public static void doConfig(File file) {

        Configuration config = new Configuration(file);
        config.load();

        //Bow
        bowTakesDamage = config.get("Bow", "BowTakesDamage", true).getBoolean(true);
        bowEnergy = config.get("Bow", "BowTransposeEnergy", 8000, "Amount of energy (RF) required to make Bow").getInt(8000);
        bowLiquidAmount = config.get("Bow","BowLiquidAmount",2000, "Amount (in mB) of Energized Glowstone to create Bow").getInt(2000);

        //Arrow
        arrowEnergy = config.get("Arrow", "ArrowTransposeEnergy", 1000, "Amount of energy (RF) required to make Arrow").getInt(1000);
        arrowLiquidAmount = config.get("Arrow","ArrowLiquidAmount",250, "Amount (in mB) of Energized Glowstone to create Arrow").getInt(250);

        //Sapling
        saplingEnergy = config.get("Sapling", "SaplingTransposeEnergy",1000, "Amount of energy (RF) required to make Sapling").getInt(1000);
        saplingLiquidAmount = config.get("Sapling","SaplingLiquidAmount",500, "Amount (in mB) of Energized Glowstone to create Sapling").getInt(500);

        //Other
        boneMealChance = config.get("General", "BoneMealChance",3, "Chance for bonemeal to work (1 in n)").getInt(3);

        config.save();

    }
}
