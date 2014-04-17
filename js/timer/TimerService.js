angular.module("MagTimer")
.directive("clock", function () {
	return {
		restrict : 'E',
		template : "{{now | date: 'h:mm:ss a'}}",
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

			setInterval(function () {
				var now = new Date().getTime();
				angular.forEach($scope.events, function (event) {
					event.countdown = {};
					var time = event.time - now
						event.countdown.t = time;
					event.countdown.h = Math.floor(time / (60 * 60 * 1000));
					event.countdown.m = Math.floor((time / (60 * 1000)) % 60);
					event.countdown.s = Math.floor((time / (1000) % 60));
				});
				$scope.$apply();
			}, 1000);
		}
	}
})
