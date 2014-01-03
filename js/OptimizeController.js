angular.module("Optimize.Controller", ["ngSanitize", "Swag.Service"])
.controller("OptimizeController", ["$scope", "$sanitize", "Armor", "Stats", "Professions", function ($scope, $sanitize, $armor, $stats, $profs) {
			$scope.slots = [];
			$scope.spreads = [];
			$scope.stats = [];
			$scope.professions = [];

			$scope.weight = {};
			$scope.limit = {};
			$scope.value = {};

			$scope.limitTypes = ["At Least", "At Most"];

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
			});

			$scope.changeClass = function (prof) {
				console.log("Class changed to " + prof.name);
				$scope.prof = prof;
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

			$scope.getIcon = function (collection, name) {
				var item = $scope.getItem(collection, name);
				if (item != null && item.icon != null) {
					return " < img src = '" + item.icon + "' title = '" + name + "' /  > ";
				}
				return null;
			};
		}
	]);
