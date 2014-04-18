angular.module("MagTimer")
.controller("TimeController", ["$scope", "Events", function ($scope, Events) {
			$scope.timeline = [];
			var now = new Date();
			$scope.offset = -60 - now.getTimezoneOffset();
			if (now.dst()) {
				$scope.offset -= 60;
				console.debug("Detected DST!");
			}
			console.debug("Detected timezone offset of " + $scope.offset);

			Events.then(function (data) {
				$scope.events = data;
				normalizeEvents();
			});

			normalizeEvents = function () {
				var date = new Date();
				var now = date.getTime();
				date.setHours(0, 0, 0, 0);
				var midnight = date.getTime();
				var tomorrow = 24 * 60 * 60 * 1000;
				angular.forEach($scope.events, function (event) {
					event.realTimes = [];
					angular.forEach(event.times, function (time) {
						var adjusted = midnight;

						time.m += $scope.offset;
						time.m += time.h * 60;

						adjusted += time.m * 60 * 1000;
						
						$scope.timeline.push({
							"name" : event.name,
							"time" : adjusted,
							"location" : event.location,
							"link" : event.link,
							"countdown" : {
								"h" : 0,
								"m" : 0,
								"s" : 0
							},
							"running" : false
						});
					});
				});
			};
		}
	]);
