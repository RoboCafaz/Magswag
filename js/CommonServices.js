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