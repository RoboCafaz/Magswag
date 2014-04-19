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
			chests : '=',
		},
		templateUrl : 'views/directives/timer/timeLine.html',
		replace : true,
		link : function ($scope, $element, $attrs) {
			$scope.predicate = "countdown.t";
			$scope.reverse = false;
			$scope.lastEvent = $scope.events[0];
			$scope.nextEvent = $scope.events[1];

			$scope.finishedString = function (event) {
				if ($scope.chests[event.id] === true) {
					return "complete-event";
				}
			};

			$scope.clearChests = function () {
				var r = confirm("Are you sure you want to clear all your rewards for the day?");
				if (r == true) {
					$scope.chests = new Array($scope.chests.length);
				}
			};

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
.factory("ChestStorage", [function () {
			var self = {};
			self.load = function (chests) {
				var loaded = localStorage.chests;
				if(loaded == null){
					return;
				}
				var results = new Array(loaded.length);
				for (var i = 0; i < loaded.length; i++) {
					if (loaded[i] === "1") {
						results[i] = true;
					} else {
						results[i] = false;
					}
				}
				console.log("Loaded");
				console.log(loaded);
				return results;
			};
			self.save = function (chests) {
				var string = "";
				for (var i = 0; i < chests.length; i++) {
					var value = chests[i];
					if (value === true) {
						string += "1";
					} else {
						string += "0";
					}
				}
				localStorage.chests = string;
				console.log("saved : " + string);
			};
			return self;
		}
	])
.directive("eventInfo", function () {
	return {
		restrict : 'E',
		scope : {
			event : '=',
			chest : '=',
		},
		templateUrl : 'views/directives/timer/eventInfo.html',
		replace : true,
		link : function ($scope, $element, $attrs) {
			$scope.finishedString = function (event) {
				if ($scope.chest === true) {
					return "complete-event";
				}
			};
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

Date.prototype.stdTimezoneOffset = function () {
	var jan = new Date(this.getFullYear(), 0, 1);
	var jul = new Date(this.getFullYear(), 6, 1);
	return Math.max(jan.getTimezoneOffset(), jul.getTimezoneOffset());
}

Date.prototype.dst = function () {
	return this.getTimezoneOffset() < this.stdTimezoneOffset();
}
