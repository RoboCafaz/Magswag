angular.module("MagSwag")
.controller("SwagController", ["$scope", "Health", "Professions", "Stats", "Items", function ($scope, Health, Professions, Stats, Items) {
			console.log(Health);
			console.log(Professions);
			console.log(Stats);
			console.log(Items);
		}
	]);
