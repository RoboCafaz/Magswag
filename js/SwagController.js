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
				value : 0
			};
			$scope.stats["armor"] = {
				name : "Armor",
				value : 0
			};
			$scope.stats["atk"] = {
				name : "Attack",
				value : 0
			};
			$scope.stats["crit"] = {
				name : "Critical Hit Chance",
				value : 0
			};
			$scope.stats["vit"] = {
				name : "Vitality",
				value : 0
			};
			$scope.stats["tough"] = {
				name : "Toughness",
				value : 0
			};
			$scope.stats["pow"] = {
				name : "Power",
				value : 0
			};
			$scope.stats["prec"] = {
				name : "Precision",
				value : 0
			};
			$scope.stats["condi"] = {
				name : "Condition Damage",
				value : 0
			};
			$scope.stats["condur"] = {
				name : "Condition Duration",
				value : 0
			};
			$scope.stats["critd"] = {
				name : "Critical Damage",
				value : 0
			};
			$scope.stats["heal"] = {
				name : "Healing Power",
				value : 0
			};
			$scope.stats["boon"] = {
				name : "Boon Duration",
				value : 0
			};

			$scope.changeClass = function (prof) {
				console.log("Class changed to " + prof.name);
				$scope.prof = prof;
				$scope.calculate();
			};

			$scope.classes = [{
					name : "Warrior",
					armor : 2,
					hp : 2
				}, {
					name : "Guardian",
					armor : 2,
					hp : 0
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
					hp : 1
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

			/** --- Begin Functionality --- **/
			$scope.prof = $scope.classes[0];

			$scope.calculate = function () {
				for (var key in $scope.stats) {
					$scope.stats[key].value = 0;
				}

				$scope.calculateGear();

				$scope.stats["pow"].value = $scope.baseStatValue;
				$scope.stats["tough"].value = $scope.baseStatValue;
				$scope.stats["vit"].value = $scope.baseStatValue;
				$scope.stats["prec"].value = $scope.baseStatValue;

				$scope.stats["crit"].value += ($scope.stats["prec"].value - 822) / 21;
				$scope.stats["crit"].value = Math.floor($scope.stats["crit"].value);
				$scope.stats["armor"].value += $scope.stats["tough"].value;
				$scope.stats["hp"].value = $scope.hp[$scope.prof.hp].value;
				$scope.stats["hp"].value += $scope.stats["vit"].value * 10;
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
		}
	]);
