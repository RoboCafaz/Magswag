angular.module("SwagDirectory", ["ngRoute", "Swag.Controller"])
.config(["$routeProvider", function ($routeProvider) {
			$routeProvider.when("/calc", {
				templateUrl : "views/calc.html",
				controller : "SwagController"
			});
			$routeProvider.when("/about", {
				templateUrl : "views/about.html"
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
					link : "calc",
					title : "Calculator"
				}, {
					link : "about",
					title : "About"
				}
			];
		}
	]);
