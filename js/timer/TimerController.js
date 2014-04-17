angular.module("MagTimer")
.controller("TimeController", ["$scope", "Events", function ($scope, Events) {
			$scope.timeline = [];

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
						adjusted += time.m * 60 * 1000;
						adjusted += time.h * 60 * 60 * 1000;
						if (adjusted < now) {
							adjusted += tomorrow;
						}
						$scope.timeline.push({
							"name" : event.name,
							"time" : adjusted,
							"location" : event.location,
							"link" : event.link
						});
					});
				});
				$scope.events.length = 0;
			};
		}
	]);
