package yamahari.ilikewood.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.item.ItemTier;
import yamahari.ilikewood.objectholders.ModBlocks;

import java.util.Map;

public class Constants {
    public static final String MOD_ID = "ilikewood";
    public static final String PROTOCOL_VERSION = "1.0";

    public static final ImmutableSet<Block> BARRELS =
            ImmutableSet.of(
                    ModBlocks.oak_barrel,
                    ModBlocks.dark_oak_barrel,
                    ModBlocks.spruce_barrel,
                    ModBlocks.birch_barrel,
                    ModBlocks.jungle_barrel,
                    ModBlocks.acacia_barrel
            );

    public static final ImmutableSet<Block> WALLS =
            ImmutableSet.of(
                    ModBlocks.oak_wall,
                    ModBlocks.dark_oak_wall,
                    ModBlocks.spruce_wall,
                    ModBlocks.birch_wall,
                    ModBlocks.jungle_wall,
                    ModBlocks.acacia_wall
            );

    public static final ImmutableSet<Block> CHESTS =
            ImmutableSet.of(
                    ModBlocks.oak_chest,
                    ModBlocks.dark_oak_chest,
                    ModBlocks.spruce_chest,
                    ModBlocks.birch_chest,
                    ModBlocks.jungle_chest,
                    ModBlocks.acacia_chest
            );

    public static final ImmutableSet<Block> CRAFTING_TABLES =
            ImmutableSet.of(
                    ModBlocks.oak_crafting_table,
                    ModBlocks.dark_oak_crafting_table,
                    ModBlocks.spruce_crafting_table,
                    ModBlocks.birch_crafting_table,
                    ModBlocks.jungle_crafting_table,
                    ModBlocks.acacia_crafting_table
            );

    public static final ImmutableSet<Block> POSTS =
            ImmutableSet.of(
                    ModBlocks.oak_post,
                    ModBlocks.dark_oak_post,
                    ModBlocks.spruce_post,
                    ModBlocks.birch_post,
                    ModBlocks.jungle_post,
                    ModBlocks.acacia_post
            );

    public static final ImmutableSet<Block> STRIPPED_POSTS =
            ImmutableSet.of(
                    ModBlocks.stripped_oak_post,
                    ModBlocks.stripped_dark_oak_post,
                    ModBlocks.stripped_spruce_post,
                    ModBlocks.stripped_birch_post,
                    ModBlocks.stripped_jungle_post,
                    ModBlocks.stripped_acacia_post
            );

    public static final ImmutableSet<Block> PANELS =
            ImmutableSet.of(
                    ModBlocks.oak_panels,
                    ModBlocks.dark_oak_panels,
                    ModBlocks.spruce_panels,
                    ModBlocks.birch_panels,
                    ModBlocks.jungle_panels,
                    ModBlocks.acacia_panels
            );

    public static final ImmutableSet<Block> BOOKSHELFS =
            ImmutableSet.of(
                    ModBlocks.oak_bookshelf,
                    ModBlocks.dark_oak_bookshelf,
                    ModBlocks.spruce_bookshelf,
                    ModBlocks.birch_bookshelf,
                    ModBlocks.jungle_bookshelf,
                    ModBlocks.acacia_bookshelf
            );

    public static final ImmutableSet<Block> LECTERNS =
            ImmutableSet.of(
                    ModBlocks.oak_lectern,
                    ModBlocks.dark_oak_lectern,
                    ModBlocks.spruce_lectern,
                    ModBlocks.birch_lectern,
                    ModBlocks.jungle_lectern,
                    ModBlocks.acacia_lectern
            );

    public static final ImmutableSet<Block> BEDS =
            ImmutableSet.of(
                    ModBlocks.black_oak_bed,
                    ModBlocks.black_dark_oak_bed,
                    ModBlocks.black_spruce_bed,
                    ModBlocks.black_birch_bed,
                    ModBlocks.black_jungle_bed,
                    ModBlocks.black_acacia_bed,
                    ModBlocks.blue_oak_bed,
                    ModBlocks.blue_dark_oak_bed,
                    ModBlocks.blue_spruce_bed,
                    ModBlocks.blue_birch_bed,
                    ModBlocks.blue_jungle_bed,
                    ModBlocks.blue_acacia_bed,
                    ModBlocks.brown_oak_bed,
                    ModBlocks.brown_dark_oak_bed,
                    ModBlocks.brown_spruce_bed,
                    ModBlocks.brown_birch_bed,
                    ModBlocks.brown_jungle_bed,
                    ModBlocks.brown_acacia_bed,
                    ModBlocks.cyan_oak_bed,
                    ModBlocks.cyan_dark_oak_bed,
                    ModBlocks.cyan_spruce_bed,
                    ModBlocks.cyan_birch_bed,
                    ModBlocks.cyan_jungle_bed,
                    ModBlocks.cyan_acacia_bed,
                    ModBlocks.gray_oak_bed,
                    ModBlocks.gray_dark_oak_bed,
                    ModBlocks.gray_spruce_bed,
                    ModBlocks.gray_birch_bed,
                    ModBlocks.gray_jungle_bed,
                    ModBlocks.gray_acacia_bed,
                    ModBlocks.green_oak_bed,
                    ModBlocks.green_dark_oak_bed,
                    ModBlocks.green_spruce_bed,
                    ModBlocks.green_birch_bed,
                    ModBlocks.green_jungle_bed,
                    ModBlocks.green_acacia_bed,
                    ModBlocks.light_blue_oak_bed,
                    ModBlocks.light_blue_dark_oak_bed,
                    ModBlocks.light_blue_spruce_bed,
                    ModBlocks.light_blue_birch_bed,
                    ModBlocks.light_blue_jungle_bed,
                    ModBlocks.light_blue_acacia_bed,
                    ModBlocks.light_gray_oak_bed,
                    ModBlocks.light_gray_dark_oak_bed,
                    ModBlocks.light_gray_spruce_bed,
                    ModBlocks.light_gray_birch_bed,
                    ModBlocks.light_gray_jungle_bed,
                    ModBlocks.light_gray_acacia_bed,
                    ModBlocks.lime_oak_bed,
                    ModBlocks.lime_dark_oak_bed,
                    ModBlocks.lime_spruce_bed,
                    ModBlocks.lime_birch_bed,
                    ModBlocks.lime_jungle_bed,
                    ModBlocks.lime_acacia_bed,
                    ModBlocks.magenta_oak_bed,
                    ModBlocks.magenta_dark_oak_bed,
                    ModBlocks.magenta_spruce_bed,
                    ModBlocks.magenta_birch_bed,
                    ModBlocks.magenta_jungle_bed,
                    ModBlocks.magenta_acacia_bed,
                    ModBlocks.orange_oak_bed,
                    ModBlocks.orange_dark_oak_bed,
                    ModBlocks.orange_spruce_bed,
                    ModBlocks.orange_birch_bed,
                    ModBlocks.orange_jungle_bed,
                    ModBlocks.orange_acacia_bed,
                    ModBlocks.pink_oak_bed,
                    ModBlocks.pink_dark_oak_bed,
                    ModBlocks.pink_spruce_bed,
                    ModBlocks.pink_birch_bed,
                    ModBlocks.pink_jungle_bed,
                    ModBlocks.pink_acacia_bed,
                    ModBlocks.purple_oak_bed,
                    ModBlocks.purple_dark_oak_bed,
                    ModBlocks.purple_spruce_bed,
                    ModBlocks.purple_birch_bed,
                    ModBlocks.purple_jungle_bed,
                    ModBlocks.purple_acacia_bed,
                    ModBlocks.red_oak_bed,
                    ModBlocks.red_dark_oak_bed,
                    ModBlocks.red_spruce_bed,
                    ModBlocks.red_birch_bed,
                    ModBlocks.red_jungle_bed,
                    ModBlocks.red_acacia_bed,
                    ModBlocks.white_oak_bed,
                    ModBlocks.white_dark_oak_bed,
                    ModBlocks.white_spruce_bed,
                    ModBlocks.white_birch_bed,
                    ModBlocks.white_jungle_bed,
                    ModBlocks.white_acacia_bed,
                    ModBlocks.yellow_oak_bed,
                    ModBlocks.yellow_dark_oak_bed,
                    ModBlocks.yellow_spruce_bed,
                    ModBlocks.yellow_birch_bed,
                    ModBlocks.yellow_jungle_bed,
                    ModBlocks.yellow_acacia_bed
            );

    public static final ImmutableSet<Block> SCAFFOLDINGS =
            ImmutableSet.of(
                    ModBlocks.oak_scaffolding,
                    ModBlocks.dark_oak_scaffolding,
                    ModBlocks.spruce_scaffolding,
                    ModBlocks.birch_scaffolding,
                    ModBlocks.jungle_scaffolding,
                    ModBlocks.acacia_scaffolding
            );

    public static final ImmutableSet<Block> LADDERS =
            ImmutableSet.of(
                    ModBlocks.oak_ladder,
                    ModBlocks.dark_oak_ladder,
                    ModBlocks.spruce_ladder,
                    ModBlocks.birch_ladder,
                    ModBlocks.jungle_ladder,
                    ModBlocks.acacia_ladder
            );

    public static final ImmutableSet<Block> COMPOSTERS =
            ImmutableSet.of(
                    ModBlocks.oak_composter,
                    ModBlocks.dark_oak_composter,
                    ModBlocks.spruce_composter,
                    ModBlocks.birch_composter,
                    ModBlocks.jungle_composter,
                    ModBlocks.acacia_composter
            );

    public static final ImmutableSet<Block> SIGNS =
            ImmutableSet.of(
                    ModBlocks.oak_sign,
                    ModBlocks.dark_oak_sign,
                    ModBlocks.spruce_sign,
                    ModBlocks.birch_sign,
                    ModBlocks.jungle_sign,
                    ModBlocks.acacia_sign
            );

    public static final ImmutableSet<Block> LOG_PILES =
            ImmutableSet.of(
                    ModBlocks.oak_log_pile,
                    ModBlocks.dark_oak_log_pile,
                    ModBlocks.spruce_log_pile,
                    ModBlocks.birch_log_pile,
                    ModBlocks.jungle_log_pile,
                    ModBlocks.acacia_log_pile
            );

    public static final Map<Block, Block> POST_STRIPPING_MAP =
            (new ImmutableMap.Builder<Block, Block>())
                    .put(ModBlocks.oak_post, ModBlocks.stripped_oak_post)
                    .put(ModBlocks.dark_oak_post, ModBlocks.stripped_dark_oak_post)
                    .put(ModBlocks.spruce_post, ModBlocks.stripped_spruce_post)
                    .put(ModBlocks.birch_post, ModBlocks.stripped_birch_post)
                    .put(ModBlocks.jungle_post, ModBlocks.stripped_jungle_post)
                    .put(ModBlocks.acacia_post, ModBlocks.stripped_acacia_post).build();

    public static final Map<String, ItemTier> ITEM_TIER_MAP =
            (new ImmutableMap.Builder<String, ItemTier>())
                    .put("wooden", ItemTier.WOOD)
                    .put("stone", ItemTier.STONE)
                    .put("iron", ItemTier.IRON)
                    .put("diamond", ItemTier.DIAMOND)
                    .put("golden", ItemTier.GOLD).build();
}
