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
			
		}
	}
});
