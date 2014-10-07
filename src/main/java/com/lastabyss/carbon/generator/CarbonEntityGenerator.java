package com.lastabyss.carbon.generator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

import net.minecraft.server.v1_7_R4.BiomeForest;
import net.minecraft.server.v1_7_R4.BiomeJungle;
import net.minecraft.server.v1_7_R4.BiomeMeta;
import net.minecraft.server.v1_7_R4.BiomeMushrooms;
import net.minecraft.server.v1_7_R4.BiomePlains;
import net.minecraft.server.v1_7_R4.BiomeTaiga;

import org.bukkit.generator.ChunkGenerator;

import com.lastabyss.carbon.entity.EntityRabbit;

//Entity Gen's
public class CarbonEntityGenerator extends ChunkGenerator {
	 
    BiomeForest biomeforest;
 
    BiomePlains plains;
    Constructor<BiomePlains> plainsConstructor;
 
    BiomeJungle jungle;
    Constructor<BiomeJungle> jungleConstructor;
 
    BiomeMushrooms biomeMushrooms;
 
    BiomeTaiga taiga;
    Constructor<BiomeTaiga> taigaConstructor;
    
    public void injectRabbitSpawner() {
    	//adds rabbits to spawn 
        try {
            Field as = net.minecraft.server.v1_7_R4.BiomeBase.class
                    .getDeclaredField("as");
            Field au = net.minecraft.server.v1_7_R4.BiomeBase.class
                    .getDeclaredField("au");
            Field av = net.minecraft.server.v1_7_R4.BiomeBase.class
                    .getDeclaredField("av");
            Field at = net.minecraft.server.v1_7_R4.BiomeBase.class
                    .getDeclaredField("at");
 
            as.setAccessible(true);
            au.setAccessible(true);
            av.setAccessible(true);
            at.setAccessible(true);
 
            ArrayList<BiomeMeta> listas = new ArrayList<BiomeMeta>();
            ArrayList<BiomeMeta> listau = new ArrayList<BiomeMeta>();
            ArrayList<BiomeMeta> listav = new ArrayList<BiomeMeta>();
            ArrayList<BiomeMeta> listat = new ArrayList<BiomeMeta>();
            try{
                jungleConstructor = BiomeJungle.class.getDeclaredConstructor(int.class, boolean.class);
                jungleConstructor.setAccessible(true);
                jungle = jungleConstructor.newInstance(21, false);
                
                /* BiomeMeta(Class arg0, int arg1, int arg2, int arg3)
                * int arg1: Spawn Probablility, int arg2: minSpawn, int arg3: maxSpawn
                *
                */

                listas.add(new BiomeMeta(EntityRabbit.class, 90, 3, 8));
              
 
                as.set(jungle, listas);
                au.set(jungle, listau);
                av.set(jungle, listav);
                at.set(jungle, listat);
 
 
                biomeforest = new BiomeForest(8,8);
               
             
                listat.add(new BiomeMeta(EntityRabbit.class, 50, 4, 8));
 
                as.set(biomeforest, listas);
                au.set(biomeforest, listau);
                av.set(biomeforest, listav);
                at.set(biomeforest, listat);
 
 
                taigaConstructor = BiomeTaiga.class.getDeclaredConstructor(int.class, int.class);
                taigaConstructor.setAccessible(true);
 
                taiga = taigaConstructor.newInstance(21, 8);
 
                listas.add(new BiomeMeta(EntityRabbit.class, 100, 3, 8));
            
 
                as.set(taiga, listas);
                au.set(taiga, listau);
                av.set(taiga, listav);
                at.set(taiga, listat);
 
 
                plainsConstructor = BiomePlains.class.getDeclaredConstructor(int.class);
                plainsConstructor.setAccessible(true);
 
                plains = plainsConstructor.newInstance(21);
 
                listas.add(new BiomeMeta(EntityRabbit.class, 120, 4, 8));
                
 
                as.set(plains, listas);
                au.set(plains, listau);
                av.set(plains, listav);
                at.set(plains, listat);
 
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Again, try to handle your errors better
        }
    }
}