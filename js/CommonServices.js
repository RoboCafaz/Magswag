angular.module("SwagNav")
.directive('selectOnClick', function () {
	return {
		restrict : 'A',
		link : function ($scope, $element, $attrs) {
			$element.on('click', function () {
				var elem = $element[0];
				if (document.createTextRange) {
					var range = document.createTextRange();
					range.moveToElementText(elem);
					range.select();
				} else if (window.getSelection) {
					var range = document.createRange();
					range.selectNode(elem);
					window.getSelection().addRange(range);
				}
			});
		}
	};
})
.directive('companyName', function () {
	return {
		restrict : 'E',
		template : '{{name}}',
		link : function ($scope, $element, $attrs) {
			var items = [
				"LLC", "Co.", "Inc.", "Enterprises", "Institute of Technology",
				"Institute of Techlology", "Institute", "College", "and Sons",
				"and Son", "and Haikuus", "and K-Pop", "Esq.", "Offices", "Technical Institute",
				"Sr.", "Jr.", "Booties", "and Company", "& Knuckles", "feat. Lil' Jon"];
			$scope.name = items[Math.floor(Math.random() * items.length)]; ;
		}
	}
})
.filter('paddedNumber', function () {
	return function (n, len) {
		var num = parseInt(n, 10);
		len = parseInt(len, 10);
		if (isNaN(num) || isNaN(len)) {
			return n;
		}
		num = '' + num;
		while (num.length < len) {
			num = '0' + num;
		}
		return num;
	};
});
