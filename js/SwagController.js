angular.module('Swag.Controller', [])
.controller('SwagController', ['$scope', function ($scope) {
			/** Constants **/
			$scope.baseStatValue = 916;

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

			$scope.stats = {};
			$scope.stats["hp"] = {
				name : "Health",
				value : 0,
				icon : "images/icons/Health.png"
			};
			$scope.stats["armor"] = {
				name : "Armor",
				value : 0,
				icon : "images/icons/Armor.png"
			};
			$scope.stats["atk"] = {
				name : "Attack",
				value : 0,
				icon : "images/icons/Attack.png"
			};
			$scope.stats["crit"] = {
				name : "Critical Hit Chance",
				value : 0,
				icon : "images/icons/Critical_Chance.png"
			};
			$scope.stats["vit"] = {
				name : "Vitality",
				value : 0,
				icon : "images/icons/Vitality.png"
			};
			$scope.stats["tough"] = {
				name : "Toughness",
				value : 0,
				icon : "images/icons/Toughness.png"
			};
			$scope.stats["pow"] = {
				name : "Power",
				value : 0,
				icon : "images/icons/Power.png"
			};
			$scope.stats["prec"] = {
				name : "Precision",
				value : 0,
				icon : "images/icons/Precision.png"
			};
			$scope.stats["condi"] = {
				name : "Condition Damage",
				value : 0,
				icon : "images/icons/Condition_Damage.png"
			};
			$scope.stats["condid"] = {
				name : "Condition Duration",
				value : 0,
				icon : "images/icons/Condition_Duration.png"
			};
			$scope.stats["critd"] = {
				name : "Critical Damage",
				value : 0,
				icon : "images/icons/Critical_Damage.png"
			};
			$scope.stats["heal"] = {
				name : "Healing Power",
				value : 0,
				icon : "images/icons/Healing_Power.png"
			};
			$scope.stats["boon"] = {
				name : "Boon Duration",
				value : 0,
				icon : "images/icons/Boon_Duration.png"
			};
			$scope.stats["bonus"] = {
				name : "Profession-specific",
				value : 0,
				icon : "images/icons/Bonus.png"
			};

			$scope.changeClass = function (prof) {
				console.log("Class changed to " + prof.name);
				$scope.prof = prof;
				$scope.calculate();
			};

			$scope.classes = [{
					name : "Warrior",
					armor : 2,
					hp : 2,
					traits : [{
							name : "Strength",
							primary : "pow",
							secondary : "condid"
						}, {
							name : "Arms",
							primary : "prec",
							secondary : "condi"
						}, {
							name : "Defenses",
							primary : "tough",
							secondary : "heal"
						}, {
							name : "Tactics",
							primary : "vit",
							secondary : "boon"
						}, {
							name : "Discipline",
							primary : "bonus",
							secondary : "critd"
						}
					]
				}, {
					name : "Guardian",
					armor : 2,
					hp : 0,
					traits : [{
							name : "Zeal",
							primary : "pow",
							secondary : "condid"
						}, {
							name : "Radiance",
							primary : "prec",
							secondary : "condi"
						}, {
							name : "Valor",
							primary : "tough",
							secondary : "critd"
						}, {
							name : "Honor",
							primary : "vit",
							secondary : "heal"
						}, {
							name : "Virtues",
							primary : "bonus",
							secondary : "boon"
						}
					]
				}, {
					name : "Thief",
					armor : 1,
					hp : 0
				}, {
					name : "Ranger",
					armor : 1,
					hp : 1
				}, {
					name : "Engineer",
					armor : 1,
					hp : 1,
					traits : [{
							name : "Explosives",
							primary : "pow",
							secondary : "condid"
						}, {
							name : "Firearms",
							primary : "prec",
							secondary : "condi"
						}, {
							name : "Inventions",
							primary : "tough",
							secondary : "heal"
						}, {
							name : "Alchemy",
							primary : "vit",
							secondary : "boon"
						}, {
							name : "Tools",
							primary : "bonus",
							secondary : "critd"
						}
					]
				}, {
					name : "Elementalist",
					armor : 0,
					hp : 0
				}, {
					name : "Mesmer",
					armor : 0,
					hp : 1
				}, {
					name : "Necromancer",
					armor : 0,
					hp : 2
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
			$scope.prof = $scope.classes[0];

			$scope.calculate = function () {
				$scope.resetStats();
				$scope.calculateGear();
				$scope.calculateTrees();

				$scope.stats["atk"].value = ($scope.stats["pow"].value + $scope.getWeaponDamage());
				$scope.stats["crit"].value += ($scope.stats["prec"].value - 822) / 21;
				$scope.stats["crit"].value = Math.floor($scope.stats["crit"].value);
				$scope.stats["armor"].value += $scope.stats["tough"].value;
				$scope.stats["hp"].value = $scope.hp[$scope.prof.hp].value;
				$scope.stats["hp"].value += $scope.stats["vit"].value * 10;
			};
			
			$scope.getWeaponDamage = function () {
				return 0;
			};

			$scope.resetStats = function () {
				for (var key in $scope.stats) {
					$scope.stats[key].value = 0;
				}
				$scope.stats["pow"].value = $scope.baseStatValue;
				$scope.stats["tough"].value = $scope.baseStatValue;
				$scope.stats["vit"].value = $scope.baseStatValue;
				$scope.stats["prec"].value = $scope.baseStatValue;
			};

			$scope.calculateGear = function () {
				for (var i = 0; i < $scope.gear.length; i++) {
					var piece = $scope.gear[i];
					for (var key in $scope.stats) {
						if (piece[key]) {
							$scope.stats[key].value += piece[key];
						}
					}
				};
			};

			$scope.calculateTrees = function () {
				for (var i = 0; i < $scope.prof.traits.length; i++) {
					var line = $scope.prof.traits[i];
					$scope.stats[line.primary].value += $scope.traits[i] * 10;
					$scope.stats[line.secondary].value += $scope.traits[i] * 10;
				}
			};

			$scope.calculate();
		}
	]);
