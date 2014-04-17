angular.module("SwagNav", ["ngRoute", "MagSwag", "MagTimer"])
.config(["$routeProvider", function ($routeProvider) {
			$routeProvider.when("/calc", {
				templateUrl : "views/calc.html",
				controller : "SwagController"
			});
			$routeProvider.when("/about", {
				templateUrl : "views/about.html"
			});
			$routeProvider.when("/optimizer", {
				templateUrl : "views/optimizer.html",
				controller : "SwagController"
			});
			$routeProvider.when("/items", {
				templateUrl : "views/items.html",
				controller : "SwagController"
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
					link : "calc",
					title : "Calculator"
				}, {
					link : "optimizer",
					title : "Optimizer"
				}, {
					link : "items",
					title : "Item List"
				}, {
					link : "about",
					title : "About"
				}
			];
		}
	]);
