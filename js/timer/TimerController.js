angular.module("MagTimer")
.controller("TimeController", ["$scope", "Events", function ($scope, Events) {
			Events.then(function (data) {
				$scope.events = data;
			});
		}
	]);
