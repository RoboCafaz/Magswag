angular.module("SwagNav")
.factory("ItemHandler", [function () {
			this.calculateValue = function (item, stats) {
				var value = 0;
				for (key in item.stats) {
					var weight = stats[key].value;
					if (weight == null) {
						weight = 0;
					}
					value += this.getStat(item, key) * weight;
				}
				return value;
			};
			this.getStat = function (item, stat) {
				var value = 0;
				if (item.stats[stat] != null) {
					value = item.stats[stat];
				}
				return value;
			};
			return this;
		}
	]);
