angular.module("MagSwag")
.directive('itemTable', ["ItemHandler", function (ItemHandler) {
			return {
				restrict : 'E',
				scope : {
					items : '=',
					stats : '='
				},
				templateUrl : 'views/directives/itemTable.html',
				replace : true,
				link : function ($scope, $element, $attrs) {
					$scope.calculateValue = function (item) {
						return ItemHandler.calculateValue(item, $scope.stats);
					};
					$scope.getStat = function (item, stat) {
						return ItemHandler.getStat(item, stat);
					};
				}
			}
		}
	]);
