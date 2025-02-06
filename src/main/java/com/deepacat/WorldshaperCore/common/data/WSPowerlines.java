package com.deepacat.WorldshaperCore.common.data;

import net.deepacat.createpowerlines.blocks.connector.ConnectorStyle;
import net.deepacat.createpowerlines.blocks.connector.ConnectorType;
import net.deepacat.createpowerlines.blocks.connector.ConnectorTypes;
import net.deepacat.createpowerlines.item.WireMaterial;
import net.deepacat.createpowerlines.item.WireMaterials;

import java.util.ArrayList;
import java.util.List;

import static net.deepacat.createpowerlines.blocks.connector.ConnectorTypes.registerOne;
import static net.deepacat.createpowerlines.blocks.connector.ConnectorTypes.registerTier;

public class WSPowerlines {
    public static void init() {
        ArrayList<WireMaterial> allTier = new ArrayList<>(); // All
        ArrayList<WireMaterial> tierULV = new ArrayList<>(); // 0
        ArrayList<WireMaterial> tierLV = new ArrayList<>();  // 1
        ArrayList<WireMaterial> tierMV = new ArrayList<>();  // 2
        ArrayList<WireMaterial> tierHV = new ArrayList<>();  // 3
        ArrayList<WireMaterial> tierEV = new ArrayList<>();  // 4

//        WireMaterial silver = WireMaterials.getOrRegister("Silver", 0xCECECE);
//        allTier.add(silver);

////        public static ConnectorType[] registerTier(String tier, long baseEnergy, double[] energyMults, int color, List<WireMaterial> wireMaterials) {
//        registerTier("Silver", 2048, 1, 0xCECECE, allTier);

////        public static ConnectorType registerOne(String tier, String size, int connections, int wireLength, long baseEnergy, double energyMult,
////          List<WireMaterial> wireMaterials, int width, int height, int color, ConnectorStyle style)
//        registerOne("testConnector", "Small", 4, 16, 4096, 2, allTier, 3, 4, 0xCECECE, ConnectorStyle.LARGE);
    }
}
