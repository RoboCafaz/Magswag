angular.module("WvWBingo")
.directive("bingoSheet", ["BingoLoader", function (BingoLoader) {
			return {
				restrict : 'E',
				templateUrl : 'views/directives/bingoSheet.html',
				replace : true,
				link : function ($scope, $element, $attrs) {
					var startBuffer = 72000000;
					var endBuffer = 14400000;
					BingoLoader.then(function (data) {
						$scope.bingoBoards = [];
						angular.forEach(data, function (board) {
							var now = new Date().getTime();
							if (board.start - startBuffer <= now) {
								$scope.bingoBoards.push(board);
								if (!$scope.board && board.end + endBuffer >= now) {
									$scope.live = true;
									$scope.board = board;
								}
							}
						});
					});
					$scope.getStyle = function (cell) {
						var style = "";
						if ($scope.isDante(cell)) {
							style += "dante";
						}
						if ($scope.isFinished(cell)) {
							style += " finished";
							if(cell.bingo){
								style += " bingo";
							}
						}
						return style;
					};
					$scope.isDante = function (cell) {
						return (cell === $scope.board.cells[2][2]);
					};
					$scope.isFinished = function (cell) {
						return (cell.submissions && cell.submissions.length > 0);
					};
					$scope.createName = function (board) {
						if (!board) {
							return "";
						}
						var string = board.name;
						string += " (" + new Date(board.start).toDateString();
						string += " to " + new Date(board.end).toDateString() + ")";
						return string;
					};
				}
			}
		}
	]);
