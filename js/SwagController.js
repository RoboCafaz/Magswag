angular.module("Swag.Controller", ["ngSanitize", "Swag.Service"])
.controller("SwagController", ["$scope", "$sanitize", "Armor", "Stats", "Professions", function ($scope, $sanitize, $armor, $stats, $profs) {
			$scope.slots = [];
			$scope.spreads = [];
			$scope.stats = [];

			/** Constants **/
			$scope.baseStatValue = 916;

			$armor.success(function (data) {
				$scope.slots = data.slots;
				$scope.spreads = data.spreads;
			});

			$stats.success(function (data) {
				$scope.stats = data.stats;
			});

			$profs.success(function (data) {
				$scope.professions = data.professions;
				$scope.prof = $scope.professions[0];
				$scope.calculate();
			});

			$scope.getIcon = function (collection, name) {
				var item = $scope.getItem(collection, name);
				if (item != null && item.icon != null) {
					return " < img src = '" + item.icon + "' title = '" + name + "' /  > ";
				}
				return null;
			};

			$scope.armor = [
				"Light",
				"Medium",
				"Heavy"
			];

			$scope.hp = [{
					name : "Low",
					value : 1645
				}, {
					name : "Medium",
					value : 5922
				}, {
					name : "High",
					value : 9212
				}
			];

			$scope.gear = [{
					name : "Helmet",
					slots : ["Helmet"],
					item : null,
					ascended : false
				}, {
					name : "Chest",
					slots : ["Chest"],
					item : null,
				}, {
					name : "Legs",
					slots : ["Legs"],
					item : null,
					ascended : false
				}, {
					name : "Gloves",
					slots : ["Gloves"],
					item : null,
					ascended : false
				}, {
					name : "Boots",
					slots : ["Boots"],
					item : null,
					ascended : false
				}, {
					name : "Main-Hand",
					slots : ["Two-Handed Weapon", "One-Handed Weapon"],
					item : null,
					ascended : false
				}, {
					name : "Off-Hand",
					slots : ["One-Handed Weapon", "Shield"],
					item : null,
					ascended : false
				}, {
					name : "Backpiece",
					slots : ["Backpiece"],
					item : null,
					ascended : false
				}, {
					name : "Accessory 1",
					slots : ["Accessory"],
					item : null,
					ascended : false
				}, {
					name : "Accessory 2",
					slots : ["Accessory"],
					item : null,
					ascended : false
				}, {
					name : "Ring 1",
					slots : ["Ring"],
					item : null,
					ascended : false
				}, {
					name : "Ring 2",
					slots : ["Ring"],
					item : null,
					ascended : false
				}
			];

			$scope.changeClass = function (prof) {
				console.log("Class changed to " + prof.name);
				$scope.prof = prof;
				$scope.calculate();
			};

			$scope.professions = [{
					name : "Warrior",
					armor : 2,
					hp : 2,
					icon : "images/icons/Warrior.png",
					traits : [{
							name : "Strength",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Arms",
							primary : "Precision",
							secondary : "Condition Damage"
						}, {
							name : "Defenses",
							primary : "Toughness",
							secondary : "Healing Power"
						}, {
							name : "Tactics",
							primary : "Vitality",
							secondary : "Boon Duration"
						}, {
							name : "Discipline",
							primary : "Bonus",
							secondary : "Ferocity"
						}
					]
				}, {
					name : "Guardian",
					armor : 2,
					hp : 0,
					icon : "images/icons/Guardian.png",
					traits : [{
							name : "Zeal",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Radiance",
							primary : "Precision",
							secondary : "Condition Damage"
						}, {
							name : "Valor",
							primary : "Toughness",
							secondary : "Ferocity"
						}, {
							name : "Honor",
							primary : "Vitality",
							secondary : "Healing Power"
						}, {
							name : "Virtues",
							primary : "Bonus",
							secondary : "Boon Duration"
						}
					]
				}, {
					name : "Thief",
					armor : 1,
					hp : 0,
					icon : "images/icons/Thief.png",
					traits : [{
							name : "Deadly Arts",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Critical Strikes",
							primary : "Precision",
							secondary : "Ferocity"
						}, {
							name : "Shadow Arts",
							primary : "Toughness",
							secondary : "Healing Power"
						}, {
							name : "Acrobatics",
							primary : "Vitality",
							secondary : "Boon Duration"
						}, {
							name : "Trickery",
							primary : "Bonus",
							secondary : "Condition Damage"
						}
					]
				}, {
					name : "Ranger",
					armor : 1,
					hp : 1,
					icon : "images/icons/Ranger.png",
					traits : [{
							name : "Marksmanship",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Skirmishing",
							primary : "Precision",
							secondary : "Ferocity"
						}, {
							name : "Wilderness Survival",
							primary : "Toughness",
							secondary : "Condition Damage"
						}, {
							name : "Nature Magic",
							primary : "Vitality",
							secondary : "Boon Duration"
						}, {
							name : "Beastmastery",
							primary : "Bonus",
							secondary : "Healing Power"
						}
					]
				}, {
					name : "Engineer",
					armor : 1,
					hp : 1,
					icon : "images/icons/Engineer.png",
					traits : [{
							name : "Explosives",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Firearms",
							primary : "Precision",
							secondary : "Condition Damage"
						}, {
							name : "Inventions",
							primary : "Toughness",
							secondary : "Healing Power"
						}, {
							name : "Alchemy",
							primary : "Vitality",
							secondary : "Boon Duration"
						}, {
							name : "Tools",
							primary : "Bonus",
							secondary : "Ferocity"
						}
					]
				}, {
					name : "Elementalist",
					armor : 0,
					hp : 0,
					icon : "images/icons/Elementalist.png",
					traits : [{
							name : "Fire Magic",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Air Magic",
							primary : "Precision",
							secondary : "Ferocity"
						}, {
							name : "Earth Magic",
							primary : "Toughness",
							secondary : "Condition Damage"
						}, {
							name : "Water Magic",
							primary : "Vitality",
							secondary : "Healing Power"
						}, {
							name : "Arcana",
							primary : "Bonus",
							secondary : "Boon Duration"
						}
					]
				}, {
					name : "Mesmer",
					armor : 0,
					hp : 1,
					icon : "images/icons/Mesmer.png",
					traits : [{
							name : "Domination",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Dueling",
							primary : "Precision",
							secondary : "Ferocity"
						}, {
							name : "Chaos",
							primary : "Toughness",
							secondary : "Boon Duration"
						}, {
							name : "Inspiration",
							primary : "Vitality",
							secondary : "Healing Power"
						}, {
							name : "Illusions",
							primary : "Bonus",
							secondary : "Condition Damage"
						}
					]
				}, {
					name : "Necromancer",
					armor : 0,
					hp : 2,
					icon : "images/icons/Necromancer.png",
					traits : [{
							name : "Spite",
							primary : "Power",
							secondary : "Condition Duration"
						}, {
							name : "Curses",
							primary : "Precision",
							secondary : "Condition Damage"
						}, {
							name : "Death Magic",
							primary : "Toughness",
							secondary : "Boon Duration"
						}, {
							name : "Blood Magic",
							primary : "Vitality",
							secondary : "Healing Power"
						}, {
							name : "Soul Reaping",
							primary : "Bonus",
							secondary : "Ferocity"
						}
					]
				}
			];

			/** --- Trait Functionality --- **/
			$scope.maxTraits = 70;
			$scope.totalTraits = 0;
			$scope.traits = [0, 0, 0, 0, 0];

			$scope.resetTraits = function () {
				for (var i = 0; i < $scope.traits.length; i++) {
					$scope.traits[i] = 0;
				}
				$scope.totalTraits = 0;
				$scope.calculate();
				console.log("Traits reset.");
			};

			$scope.adjustTrait = function (index, value) {
				if ($scope.totalTraits + value <= $scope.maxTraits) {
					var newVal = $scope.traits[index] + value
						if (newVal >= 0 && newVal <= 30) {
							$scope.traits[index] = newVal;
							console.log($scope.prof.traits[index].name + " adjusted by " + value);
							$scope.totalTraits += value;
							$scope.calculate();
							return;
						}
				}
				console.log("Could not adjust " + $scope.prof.traits[index].name + " by " + value);
			};

			/** --- Begin Functionality --- **/

			$scope.calculate = function () {
				$scope.resetStats();
				$scope.calculateGear();
				$scope.calculateTrees();

				$scope.adjustStat("Attack", ($scope.getStatValue("Power") + $scope.getWeaponDamage()));
				$scope.adjustStat("Critical Chance", Math.floor(($scope.getStatValue("Precision") - 822) / 21));
				$scope.adjustStat("Critical Damage", Math.floor(($scope.getStatValue("Ferocity")) / 15) + 50);
				$scope.adjustStat("Armor", $scope.getStatValue("Toughness"));
				$scope.adjustStat("Health", $scope.hp[$scope.prof.hp].value);
				$scope.adjustStat("Health", $scope.getStatValue("Vitality") * 10);
			};

			$scope.getWeaponDamage = function () {
				return 0;
			};

			$scope.resetStats = function () {
				for (var i = 0; i < $scope.stats.length; i++) {
					$scope.stats[i].value = 0;
				}
				$scope.adjustStat("Power", $scope.baseStatValue);
				$scope.adjustStat("Toughness", $scope.baseStatValue);
				$scope.adjustStat("Vitality", $scope.baseStatValue);
				$scope.adjustStat("Precision", $scope.baseStatValue);
			};

			$scope.calculateGear = function () {
				for (var i = 0; i < $scope.gear.length; i++) {
					var piece = $scope.gear[i];
					var item = piece.item;
					if (item != null) {
						var slot = $scope.getItem($scope.slots, piece.slots[0]);
						var quality = 0;
						if (piece.ascended) {
							quality = 1;
						}
						if (item.celestial) {
							$scope.adjustStat("Power", slot.celestial[quality]);
							$scope.adjustStat("Precision", slot.celestial[quality]);
							$scope.adjustStat("Toughness", slot.celestial[quality]);
							$scope.adjustStat("Vitality", slot.celestial[quality]);
							$scope.adjustStat("Ferocity", slot.celestial[quality]);
							$scope.adjustStat("Healing Power", slot.celestial[quality]);
							$scope.adjustStat("Condition Damage", slot.celestial[quality]);
						} else {
							$scope.adjustStat(item.primary, slot.stats[quality][0]);
							$scope.adjustStat(item.secondary, slot.stats[quality][1]);
							$scope.adjustStat(item.tertiary, slot.stats[quality][1]);
						}
					}
				};
			};

			$scope.calculateTrees = function () {
				for (var i = 0; i < $scope.prof.traits.length; i++) {
					var line = $scope.prof.traits[i];
					$scope.adjustStat(line.primary, $scope.traits[i] * 10);
					$scope.adjustStat(line.secondary, $scope.traits[i] * 10);
				}
			};

			$scope.getItem = function (collection, name) {
				var items = collection.filter(function (item) {
						return (item.name === name);
					});
				if (items.length == 1) {
					return items[0];
				}
				return null;
			};

			$scope.setAscended = function (gearSlot, ascended) {
				gearSlot.ascended = ascended;
				$scope.calculate();
			};

			$scope.changeGear = function (gearSlot, stat) {
				gearSlot.item = stat;
				$scope.calculate();
			};

			$scope.getStatDisplay = function (gearSlot, stat) {
				var slot = $scope.getItem($scope.slots, gearSlot.slots[0]);
				if (stat != null && stat.icon != null) {
					var title = $scope.getToolTipForStat(stat, gearSlot);
					return "<img src='" + stat.icon + "' title='" + title + "' />";
				}
				return null;
			};

			$scope.getToolTipForStat = function (stat, gearSlot) {
				if (!stat || !gearSlot) {
					return "";
				}
				var slot = $scope.getItem($scope.slots, gearSlot.slots[0]);
				var title = stat.name + "\n";
				var quality = 0;
				if (gearSlot.ascended) {
					quality = 1;
				}
				var stats = slot.stats;
				var cel = false;
				if(stat.stats.length > 3){
					cel = true;
					stats = slot.celestial;
				}
				for(var $i = 0; $i < stat.stats.length ; $i++){
					var type = 0;
					if(!cel && $i == 0){
						type = 1;
					}
					title += "+" + stats[quality][type] + " " + stat.stats[$i] + "\n";
				}
				return title;
			};

			$scope.getStatValue = function (name) {
				var stat = $scope.getItem($scope.stats, name);
				if (stat != null) {
					return stat.value;
				} else {
					return 0;
				}
			};

			$scope.adjustStat = function (name, value) {
				var stat = $scope.getItem($scope.stats, name);
				if (stat != null) {
					stat.value += value;
				}
			};
		}
	]);
