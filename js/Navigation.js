angular.module("SwagNav", ["ngRoute", "ui.utils", "MagSwag", "MagTimer", "WvWBingo"])
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
			$routeProvider.when("/bingo", {
				templateUrl : "views/bingo.html",
				controller : "BingoController"
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
					link : "bingo",
					title : "Team WvW Bingo!"
				}, {
					link : "timer",
					title : "Event Timer"
				}, {
			//		link : "calc",
			//		title : "Calculator"
			//	}, {
			//		link : "optimizer",
			//		title : "Optimizer"
			//	}, {
			//		link : "items",
			//		title : "Item List"
			//	}, {
					link : "about",
					title : "About"
				}
			];
		}
	]);
