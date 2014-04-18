angular.module("MagTimer")
.directive("clock", function () {
	return {
		restrict : 'E',
		template : "<div id='clock'><p>{{now | date: 'h:mm:ss a'}}</p></div>",
		link : function ($scope, $element, $attrs) {
			setInterval(function () {
				$scope.now = new Date().getTime();
				$scope.$apply();
			}, 1000);
		}
	}
})
.directive("eventTimeline", function () {
	return {
		restrict : 'E',
		scope : {
			events : '=',
		},
		templateUrl : 'views/directives/timer/timeLine.html',
		replace : true,
		link : function ($scope, $element, $attrs) {
			$scope.predicate = "countdown.t";
			$scope.reverse = false;
			$scope.lastEvent = $scope.events[0];
			$scope.nextEvent = $scope.events[1];

			setInterval(function () {
				var now = new Date().getTime();
				angular.forEach($scope.events, function (event) {
					var time = event.time - now;
					if (time < 0) {
						time += (24 * 60 * 60 * 1000);
					}
					event.countdown.t = time;
					event.countdown.h = Math.floor(time / (60 * 60 * 1000));
					event.countdown.m = Math.floor((time / (60 * 1000)) % 60);
					event.countdown.s = Math.floor((time / (1000) % 60));

					if (event.countdown.h == 23) {
						if (event.countdown.m >= 45) {
							if (event.countdown.m >= 55) {
								event.running = true;
							} else {
								event.running = false;
							}
							$scope.lastEvent = event;
						}
					}
					if (event.countdown.h == 0) {
						if (event.countdown.m < 15) {
							$scope.nextEvent = event;
						}
					}
				});
				$scope.$apply();
			}, 1000);
		}
	}
})
.directive("eventInfo", function () {
	return {
		restrict : 'E',
		scope : {
			event : '=',
		},
		templateUrl : 'views/directives/timer/eventInfo.html',
		replace : true,
		link : function ($scope, $element, $attrs) {
			$scope.getEventStatus = function () {
				if ($scope.event == null) {
					return "???";
				}
				if ($scope.event.running) {
					return "... is now playing!";
				} else {
					if ($scope.event.countdown.h >= 23) {
						return "...has passed.";
					} else {
						return "... is coming up!";
					}
				}
			};
		}
	}
});
