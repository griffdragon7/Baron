package me.griffdragon.NewBaron.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemGenerator {

	public static String healthLore = ChatColor.translateAlternateColorCodes('&', " * &7Health: &a");
	public static String physDamageLore = ChatColor.translateAlternateColorCodes('&', " * &7Physical Damage: &c");
	public static String magDamageLore = ChatColor.translateAlternateColorCodes('&', " * &7Magic Damage: &d");
	public static String defenceLore = ChatColor.translateAlternateColorCodes('&', " * &7Defence: &e");
	public static String crLore = ChatColor.translateAlternateColorCodes('&', " * &7Crit Rate: &e");
	public static String cdLore = ChatColor.translateAlternateColorCodes('&', " * &7Crit Damage: &c");
	public static String luckLore = ChatColor.translateAlternateColorCodes('&', " * &7Luck: &a");
	public static String speedLore = ChatColor.translateAlternateColorCodes('&', " * &7Speed: &b");
	public static String jumpLore = ChatColor.translateAlternateColorCodes('&', " * &7Jump: &d");

	static HashMap<String, String> tierColor = new HashMap<String, String>();

	static HashMap<String, Material> helmets = new HashMap<String, Material>();
	static HashMap<String, Material> chest = new HashMap<String, Material>();
	static HashMap<String, Material> legs = new HashMap<String, Material>();
	static HashMap<String, Material> boots = new HashMap<String, Material>();

	static HashMap<String, Double> healthMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> physMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> magMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> defMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> crMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> cdMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> speedMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> luckMultiplier = new HashMap<String, Double>();
	static HashMap<String, Double> jumpMultiplier = new HashMap<String, Double>();

	static HashMap<String, Double> healthPercentMultiplier = new HashMap<String, Double>();

	static ArrayList<String> tier1Stats = new ArrayList<String>();
	static ArrayList<String> tier2Stats = new ArrayList<String>();
	static ArrayList<String> tier3Stats = new ArrayList<String>();
	static ArrayList<String> tier4Stats = new ArrayList<String>();

	static HashMap<Integer, String> possibleStats = new HashMap<Integer, String>();

	public static void putMaps() {
		helmets.put("Common", Material.LEATHER_HELMET);
		helmets.put("Uncommon", Material.LEATHER_HELMET);
		helmets.put("Rare", Material.LEATHER_HELMET);
		helmets.put("Epic", Material.LEATHER_HELMET);
		helmets.put("Legendary", Material.LEATHER_HELMET);
		helmets.put("Relic", Material.LEATHER_HELMET);
		helmets.put("Shadow", Material.LEATHER_HELMET);
		helmets.put("Draconic", Material.LEATHER_HELMET);
		helmets.put("Mythical", Material.LEATHER_HELMET);

		chest.put("Common", Material.LEATHER_CHESTPLATE);
		chest.put("Uncommon", Material.LEATHER_CHESTPLATE);
		chest.put("Rare", Material.LEATHER_CHESTPLATE);
		chest.put("Epic", Material.LEATHER_CHESTPLATE);
		chest.put("Legendary", Material.LEATHER_CHESTPLATE);
		chest.put("Relic", Material.LEATHER_CHESTPLATE);
		chest.put("Shadow", Material.LEATHER_CHESTPLATE);
		chest.put("Draconic", Material.LEATHER_CHESTPLATE);
		chest.put("Mythical", Material.LEATHER_CHESTPLATE);

		legs.put("Common", Material.LEATHER_LEGGINGS);
		legs.put("Uncommon", Material.LEATHER_LEGGINGS);
		legs.put("Rare", Material.LEATHER_LEGGINGS);
		legs.put("Epic", Material.LEATHER_LEGGINGS);
		legs.put("Legendary", Material.LEATHER_LEGGINGS);
		legs.put("Relic", Material.LEATHER_LEGGINGS);
		legs.put("Shadow", Material.LEATHER_LEGGINGS);
		legs.put("Draconic", Material.LEATHER_LEGGINGS);
		legs.put("Mythical", Material.LEATHER_LEGGINGS);

		boots.put("Common", Material.LEATHER_BOOTS);
		boots.put("Uncommon", Material.LEATHER_BOOTS);
		boots.put("Rare", Material.LEATHER_BOOTS);
		boots.put("Epic", Material.LEATHER_BOOTS);
		boots.put("Legendary", Material.LEATHER_BOOTS);
		boots.put("Relic", Material.LEATHER_BOOTS);
		boots.put("Shadow", Material.LEATHER_BOOTS);
		boots.put("Draconic", Material.LEATHER_BOOTS);
		boots.put("Mythical", Material.LEATHER_BOOTS);

		tierColor.put("Common", "&f");
		tierColor.put("Uncommon", "&2");
		tierColor.put("Rare", "&d");
		tierColor.put("Epic", "&e");
		tierColor.put("Legendary", "&6");
		tierColor.put("Relic", "&4");
		tierColor.put("Shadow", "&5&l");
		tierColor.put("Draconic", "&b");
		tierColor.put("Mythical", "&c");

		healthMultiplier.put("Common", 1.0);
		healthMultiplier.put("Uncommon", 1.5);
		healthMultiplier.put("Rare", 2.0);
		healthMultiplier.put("Epic", 2.5);
		healthMultiplier.put("Legendary", 3.5);
		healthMultiplier.put("Relic", 4.0);
		healthMultiplier.put("Shadow", 4.5);
		healthMultiplier.put("Draconic", 5.5);
		healthMultiplier.put("Mythical", 7.0);

		crMultiplier.put("Common", 1.0);
		crMultiplier.put("Uncommon", 1.5);
		crMultiplier.put("Rare", 2.0);
		crMultiplier.put("Epic", 2.5);
		crMultiplier.put("Legendary", 3.5);
		crMultiplier.put("Relic", 4.0);
		crMultiplier.put("Shadow", 4.5);
		crMultiplier.put("Draconic", 5.5);
		crMultiplier.put("Mythical", 7.0);

		cdMultiplier.put("Common", 1.0);
		cdMultiplier.put("Uncommon", 1.5);
		cdMultiplier.put("Rare", 2.0);
		cdMultiplier.put("Epic", 2.5);
		cdMultiplier.put("Legendary", 3.5);
		cdMultiplier.put("Relic", 4.0);
		cdMultiplier.put("Shadow", 4.5);
		cdMultiplier.put("Draconic", 5.5);
		cdMultiplier.put("Mythical", 7.0);

		speedMultiplier.put("Common", 1.0);
		speedMultiplier.put("Uncommon", 1.5);
		speedMultiplier.put("Rare", 2.0);
		speedMultiplier.put("Epic", 2.5);
		speedMultiplier.put("Legendary", 3.5);
		speedMultiplier.put("Relic", 4.0);
		speedMultiplier.put("Shadow", 4.5);
		speedMultiplier.put("Draconic", 5.5);
		speedMultiplier.put("Mythical", 7.0);

		luckMultiplier.put("Common", 1.0);
		luckMultiplier.put("Uncommon", 1.5);
		luckMultiplier.put("Rare", 2.0);
		luckMultiplier.put("Epic", 2.5);
		luckMultiplier.put("Legendary", 3.5);
		luckMultiplier.put("Relic", 4.0);
		luckMultiplier.put("Shadow", 4.5);
		luckMultiplier.put("Draconic", 5.5);
		luckMultiplier.put("Mythical", 7.0);

		jumpMultiplier.put("Common", 1.0);
		jumpMultiplier.put("Uncommon", 2.0);
		jumpMultiplier.put("Rare", 3.0);
		jumpMultiplier.put("Epic", 5.0);
		jumpMultiplier.put("Legendary", 6.0);
		jumpMultiplier.put("Relic", 7.0);
		jumpMultiplier.put("Shadow", 10.0);
		jumpMultiplier.put("Draconic", 13.0);
		jumpMultiplier.put("Mythical", 16.0);

		healthPercentMultiplier.put("Common", 1.0);
		healthPercentMultiplier.put("Uncommon", 1.5);
		healthPercentMultiplier.put("Rare", 2.0);
		healthPercentMultiplier.put("Epic", 2.2);
		healthPercentMultiplier.put("Legendary", 2.5);
		healthPercentMultiplier.put("Relic", 3.0);
		healthPercentMultiplier.put("Shadow", 3.3);
		healthPercentMultiplier.put("Draconic", 3.6);
		healthPercentMultiplier.put("Mythical", 4.0);

		physMultiplier.put("Common", 1.0);
		physMultiplier.put("Uncommon", 2.0);
		physMultiplier.put("Rare", 3.0);
		physMultiplier.put("Epic", 4.0);
		physMultiplier.put("Legendary", 4.6);
		physMultiplier.put("Relic", 5.0);
		physMultiplier.put("Shadow", 6.0);
		physMultiplier.put("Draconic", 7.0);
		physMultiplier.put("Mythical", 8.0);

		magMultiplier.put("Common", 1.0);
		magMultiplier.put("Uncommon", 2.0);
		magMultiplier.put("Rare", 3.0);
		magMultiplier.put("Epic", 4.0);
		magMultiplier.put("Legendary", 4.6);
		magMultiplier.put("Relic", 5.0);
		magMultiplier.put("Shadow", 6.0);
		magMultiplier.put("Draconic", 7.0);
		magMultiplier.put("Mythical", 8.0);

		defMultiplier.put("Common", 1.0);
		defMultiplier.put("Uncommon", 1.2);
		defMultiplier.put("Rare", 1.4);
		defMultiplier.put("Epic", 1.6);
		defMultiplier.put("Legendary", 1.8);
		defMultiplier.put("Relic", 2.0);
		defMultiplier.put("Shadow", 2.2);
		defMultiplier.put("Draconic", 2.4);
		defMultiplier.put("Mythical", 2.6);

		tier1Stats.add("Common");
		tier1Stats.add("Uncommon");
		tier1Stats.add("Rare");
		tier1Stats.add("Epic");
		tier1Stats.add("Legendary");
		tier1Stats.add("Relic");
		tier1Stats.add("Shadow");
		tier1Stats.add("Draconic");
		tier1Stats.add("Mythical");

		tier2Stats.add("Rare");
		tier2Stats.add("Epic");
		tier2Stats.add("Legendary");
		tier2Stats.add("Relic");
		tier2Stats.add("Shadow");
		tier2Stats.add("Draconic");
		tier2Stats.add("Mythical");

		tier3Stats.add("Legendary");
		tier3Stats.add("Relic");
		tier3Stats.add("Shadow");
		tier3Stats.add("Draconic");
		tier3Stats.add("Mythical");

		tier4Stats.add("Draconic");
		tier4Stats.add("Mythical");

	}

	public String determineStat(String tier) {
		Random rand = new Random();
		int healthPercent = rand.nextInt((int) (30 * healthPercentMultiplier.get(tier)) + 1);
		int phys = (int) (rand.nextInt((int) (25 * physMultiplier.get(tier))) + 10 * physMultiplier.get(tier));
		int mag = (int) (rand.nextInt((int) (25 * magMultiplier.get(tier))) + 10 * magMultiplier.get(tier));
		int def = (int) (rand.nextInt((int) (10 * defMultiplier.get(tier))) + 1 * defMultiplier.get(tier));
		int cr = (int) (rand.nextInt((int) (1 * crMultiplier.get(tier))) + 1);
		int cd = (int) (rand.nextInt((int) (10 * cdMultiplier.get(tier))) + 1 * cdMultiplier.get(tier));
		int luck = (int) (rand.nextInt((int) (4 * luckMultiplier.get(tier))) + 1 * luckMultiplier.get(tier));
		int speed = (int) (rand.nextInt((int) (1 * speedMultiplier.get(tier))) + 1);
		int jump = (int) (rand.nextInt((int) (1 * jumpMultiplier.get(tier))) + 1);
		int x = rand.nextInt(10);
		if (x == 0) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + healthLore + healthPercent + "%");
		} else if (x == 1) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + physDamageLore + phys);
		} else if (x == 2) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + defenceLore + def);
		} else if (x == 3) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + magDamageLore + mag);
		} else if (x == 4) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + physDamageLore + phys);
		} else if (x == 5) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + crLore + cr + "%");
		} else if (x == 6) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + cdLore + cd + "%");
		} else if (x == 7) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + speedLore + speed);
		} else if (x == 8) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + luckLore + luck);
		} else if (x == 9) {
			return ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + jumpLore + jump);
		} else {
			return null;
		}
	}

	public ItemStack Armor(String tier, String armor) {

		Random rand = new Random();
		int flatHealth = (int) (rand.nextInt((int) (80 * healthMultiplier.get(tier)))
				+ 20 * healthMultiplier.get(tier));
		int magic = (int) (rand.nextInt((int) (80 * magMultiplier.get(tier))) + 20 * magMultiplier.get(tier));
		int phys = (int) (rand.nextInt((int) (80 * physMultiplier.get(tier))) + 20 * physMultiplier.get(tier));
		ItemStack i = null;
		if (armor.equalsIgnoreCase("helmet")) {
			i = new ItemStack(helmets.get(tier));
		} else if (armor.equalsIgnoreCase("legs")) {
			i = new ItemStack(legs.get(tier));
		} else if (armor.equalsIgnoreCase("chest")) {
			i = new ItemStack(chest.get(tier));
		} else if (armor.equalsIgnoreCase("boots")) {
			i = new ItemStack(boots.get(tier));
		} else if (armor.equalsIgnoreCase("staff")) {
			i = new ItemStack(Material.STICK);
		} else if (armor.equalsIgnoreCase("bow")) {
			i = new ItemStack(Material.BOW);
		} else if (armor.equalsIgnoreCase("sword")) {
			i = new ItemStack(Material.WOODEN_SWORD);
		} else if (armor.equalsIgnoreCase("axe")) {
			i = new ItemStack(Material.WOODEN_AXE);
		}

		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + tier + " " + armor));
		ArrayList<String> lore = new ArrayList<String>();
		if (armor.equalsIgnoreCase("helmet") || armor.equalsIgnoreCase("chest") || armor.equalsIgnoreCase("legs")
				|| armor.equalsIgnoreCase("boots")) {
			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + healthLore + flatHealth));
		} else if (armor.equalsIgnoreCase("staff")) {
			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + magDamageLore + magic));
		} else if (armor.equalsIgnoreCase("bow")) {
			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + physDamageLore + phys));
		} else if (armor.equalsIgnoreCase("sword")) {
			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + physDamageLore + phys));
		} else if (armor.equalsIgnoreCase("axe")) {
			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + physDamageLore + phys));
		} else {

			lore.add(ChatColor.translateAlternateColorCodes('&', tierColor.get(tier) + magDamageLore + phys));
		}
		if (tier1Stats.contains(tier)) {
			lore.add(determineStat(tier));
		}
		if (tier2Stats.contains(tier)) {
			lore.add(determineStat(tier));
		}
		if (tier3Stats.contains(tier)) {
			lore.add(determineStat(tier));
		}
		if (tier4Stats.contains(tier)) {
			lore.add(determineStat(tier));
		}
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im.setUnbreakable(true);
		i.setItemMeta(im);

		return i;

	}
}