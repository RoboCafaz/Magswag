package net.maguuma.magswag.calculator.controller;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import net.maguuma.magswag.calculator.controller.listener.CharacterChangeListener;
import net.maguuma.magswag.calculator.controller.listener.EquipmentChangeListener;
import net.maguuma.magswag.calculator.controller.listener.ProfessionChangeListener;
import net.maguuma.magswag.calculator.controller.listener.TraitChangeListener;
import net.maguuma.magswag.common.constants.Slot;
import net.maguuma.magswag.common.constants.Stat;
import net.maguuma.magswag.common.datatypes.items.Equipment;
import net.maguuma.magswag.common.datatypes.maps.Stats;
import net.maguuma.magswag.common.datatypes.professions.Profession;

public class CharacterController implements TraitChangeListener, ProfessionChangeListener, EquipmentChangeListener{
	private static Stats stats;
	
	private static Set<CharacterChangeListener> listeners = new HashSet<CharacterChangeListener>();
	
	static{
		CharacterController controller = new CharacterController();
		EquipmentController.addEquipmentChangeListener(controller);
		TraitController.addTraitChangeListener(controller);
		ProfessionController.addProfessionChangeListener(controller);
	}

	public static void recalculate() {
		Stats newStats = new Stats();
		newStats.add(Stat.POWER, 916);
		newStats.add(Stat.PRECISION, 916);
		newStats.add(Stat.TOUGHNESS, 916);
		newStats.add(Stat.VITALITY, 916);
		for (Equipment equip : EquipmentController.getGear().values()) {
			for (Entry<Stat, Integer> stat : equip.getStats().entrySet()) {
				if(!stat.getKey().isComplex()){
				newStats.add(stat.getKey(), stat.getValue());
			}}
		}
		newStats.add(Stat.HEALTH,ProfessionController.getProfession().getHealth());
		newStats.add(Stat.ARMOR, newStats.get(Stat.TOUGHNESS) + newStats.get(Stat.DEFENSE));
		newStats.add(Stat.CRITICAL_CHANCE, (int) (Math.floor((newStats.get(Stat.PRECISION) - 822) / 21.0)));
		newStats.add(Stat.CRITICAL_DAMAGE, (int) (Math.floor(newStats.get(Stat.FEROCITY) / 15.0) + 150));
	    newStats.add(Stat.HEALTH, newStats.get(Stat.VITALITY) * 10);
	    newStats.add(Stat.EFFECTIVE_HEALTH, (int) Math.floor((newStats.get(Stat.ARMOR) * newStats.get(Stat.HEALTH)) / 1000));

	    double baseDamage = newStats.get(Stat.POWER);
	    double critChance = (100 + newStats.get(Stat.CRITICAL_CHANCE)) / 100.0;
	    double critDamage = (newStats.get(Stat.CRITICAL_DAMAGE)) / 100.0;
	    newStats.add(Stat.EFFECTIVE_POWER, (int) Math.floor(baseDamage * (critChance * critDamage)));

		stats = newStats;
		fireCharacterChanged();
	}
	
	public static Stats getStats(){
		if(stats == null){
			recalculate();
		}
		return stats;
	}
	
	public static void fireCharacterChanged(){
		for(CharacterChangeListener listener : listeners){
			listener.characterStatsChanged();
		}
	}
	
	public static void addCharacterChangeListener(CharacterChangeListener listener){
		listeners.add(listener);
	}
	
	public static void removeCharacterChangeListener(CharacterChangeListener listener){
		listeners.remove(listener);
	}

	@Override
	public void equipmentChanged(Slot slot, Equipment gear) {
		recalculate();
	}

	@Override
	public void professionChanged(Profession oldProfession,
			Profession newProfession) {
		recalculate();
	}

	@Override
	public void traitChanged(int traitLine, int newValue) {
		recalculate();
	}
}
