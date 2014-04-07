angular.module("MagSwag")
.controller("SwagController", ["$scope", "Health", "Professions", "Stats", "Items", function ($scope, Health, Professions, Stats, Items) {
			Items.then(function (data) {
				data.length = 50;
				$scope.items = data;
			});
			Stats.then(function (data) {
				$scope.stats = data;
			});
		}
	]);
