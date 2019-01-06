package com.onarandombox.MultiverseCore.utils;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;

public class GetMostNonSolidBlocks {

    //Gets most non solid blocks
    
    private static List<String> nonSolidBlocks = new ArrayList<>();
    private static List<String> formattedNonSolidBlocks = new ArrayList();

    public static void main(String[] args) {
        for (Material material : Material.values()) {
            if (!material.isSolid() && !material.name().contains("LEGACY") && material.isBlock()) {
                nonSolidBlocks.add("Material." + material.name());
            }
        }
        System.out.println(StringUtils.join(nonSolidBlocks, ","));
    }

}
