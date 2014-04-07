angular.module("MagSwag")
.directive('statValues', [function () {
			return {
				restrict : 'E',
				scope : {
					stats : '=',
				},
				replace : true,
				templateUrl : 'views/directives/statValues.html',
				link : function ($scope, $element, $attrs) {
					$scope.options = ["Up To", "At Most"];
				}
			}
		}
	]);
