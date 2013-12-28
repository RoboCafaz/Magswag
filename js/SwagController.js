angular.module("Swag.Controller", ["Swag.Service"])
.controller("SwagController", ["$scope", "Armor", function ($scope, $armor) {
			/** Constants **/
			$scope.baseStatValue = 916;

			$armor.success(function (data) {
				$scope.slots = data.slots;
				$scope.spreads = data.stats;
			});

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

			$scope.gear = [];

			$scope.stats = [{
					name : "Health",
					value : 0,
					icon : "images/icons/Health.png"
				}, {
					name : "Armor",
					value : 0,
					icon : "images/icons/Armor.png"
				}, {
					name : "Attack",
					value : 0,
					icon : "images/icons/Attack.png"
				}, {
					name : "Critical Hit Chance",
					value : 0,
					icon : "images/icons/Critical_Chance.png"
				}, {
					name : "Vitality",
					value : 0,
					icon : "images/icons/Vitality.png"
				}, {
					name : "Toughness",
					value : 0,
					icon : "images/icons/Toughness.png"
				}, {
					name : "Power",
					value : 0,
					icon : "images/icons/Power.png"
				}, {
					name : "Precision",
					value : 0,
					icon : "images/icons/Precision.png"
				}, {
					name : "Condition Damage",
					value : 0,
					icon : "images/icons/Condition_Damage.png"
				}, {
					name : "Condition Duration",
					value : 0,
					icon : "images/icons/Condition_Duration.png"
				}, {
					name : "Critical Damage",
					value : 0,
					icon : "images/icons/Critical_Damage.png"
				}, {
					name : "Healing Power",
					value : 0,
					icon : "images/icons/Healing_Power.png"
				}, {
					name : "Boon Duration",
					value : 0,
					icon : "images/icons/Boon_Duration.png"
				}, {
					name : "Bonus",
					value : 0,
					icon : "images/icons/Bonus.png"
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
							secondary : "Critical Damage"
						}
					]
				}, {
					name : "Guardian",
					armor : 2,
					hp : 0,
					icon : "images/icons/Guardian.png",
					traits : [{
							name : "Zeal",
							primary : "Power ",
							secondary : "Condition Duration"
						}, {
							name : "Radiance",
							primary : "Precision",
							secondary : "Condition Damage"
						}, {
							name : "Valor",
							primary : "Toughness",
							secondary : "Critical Damage"
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
					icon : "images/icons/Thief.png"
				}, {
					name : "Ranger",
					armor : 1,
					hp : 1,
					icon : "images/icons/Ranger.png"
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
							secondary : "Critical Damage"
						}
					]
				}, {
					name : "Elementalist",
					armor : 0,
					hp : 0,
					icon : "images/icons/Elementalist.png"
				}, {
					name : "Mesmer",
					armor : 0,
					hp : 1,
					icon : "images/icons/Mesmer.png"
				}, {
					name : "Necromancer",
					armor : 0,
					hp : 2,
					icon : "images/icons/Necromancer.png"
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
			$scope.prof = $scope.professions[0];

			$scope.calculate = function () {
				$scope.resetStats();
				$scope.calculateGear();
				$scope.calculateTrees();

				$scope.adjustStat("Attack", ($scope.getStatValue("Power") + $scope.getWeaponDamage()));
				$scope.adjustStat("Critical Chance", ($scope.getStatValue("Precision") - 822) / 21);
				$scope.adjustStat("Critical Chance", Math.floor($scope.getStatValue("Critical Chance")));
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
					for (var i = 0; i < $scope.stats.length; i++) {
						$scope.stats[i].value += piece[i];
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

			$scope.getStat = function (name) {
				var stat = $scope.stats.filter(function (item) {
						return (item.name === name);
					});
				if (stat.length == 1) {
					return stat[0];
				}
				return null;
			};

			$scope.getStatValue = function (name) {
				var stat = $scope.getStat(name);
				if (stat != null) {
					return stat.value;
				} else {
					return 0;
				}
			};

			$scope.adjustStat = function (name, value) {
				var stat = $scope.getStat(name);
				if (stat != null) {
					stat.value += value;
				}
			};

			$scope.calculate();
		}
	]);
