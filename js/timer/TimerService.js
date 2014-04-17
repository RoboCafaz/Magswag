angular.module('MagTimer', [])
.directive("eventList", function () {
	return {
		restrict : 'E',
		scope : {
			events : '=',
		},
		templateUrl : 'views/directives/timer/eventList.html',
		replace : true,
		link : function ($scope, $element, $attrs) {
			console.debug("Sweg");
		}
	}
})
.directive("clock", function () {
	return {
		restrict : 'E',
		template : "{{now | date: 'medium'}}",
		link : function ($scope, $element, $attrs) {
			setInterval(function () {
				$scope.now = new Date().getTime();
				$scope.$apply();
			}, 500);
		}
	}
});
