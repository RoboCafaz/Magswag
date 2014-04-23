angular.module("SwagNav", ["ngRoute", "ui.utils", "MagSwag", "MagTimer"])
.config(["$routeProvider", function ($routeProvider) {
			$routeProvider.when("/about", {
				templateUrl : "views/about.html"
			});
			$routeProvider.when("/timer", {
				templateUrl : "views/timer.html",
				controller : "TimeController"
			});
			$routeProvider.otherwise({
				templateUrl : "views/main.html"
			});
		}
	])
.controller("Navigation.Controller", ["$scope", function ($scope) {
			$scope.items = [{
					link : "",
					title : "Home"
				}, {
					link : "timer",
					title : "Event Timer"
				}, {
					link : "about",
					title : "About"
				}
			];
		}
	]);
