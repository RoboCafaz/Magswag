angular.module("MagSwag")
.factory("Health", ["QueryFor", function (QueryFor) {
			return QueryFor('health', 'db/health.json');
		}
	])
.factory("Professions", ["QueryFor", function (QueryFor) {
			return QueryFor('Professions', 'db/professions.json');
		}
	])
.factory("Stats", ["QueryFor", function (QueryFor) {
			return QueryFor('Stats', 'db/stats.json');
		}
	])
.factory("Items", ["QueryFor", function (QueryFor) {
			return QueryFor('Items', 'db/items.json');
		}
	])
.factory("ItemHandler", [function () {
			this.calculateValue = function (item, stats) {
				var value = 0;
				for(key in item.stats){
					var weight = stats[key].value;
					if(weight == null){
						weight = 0;
					}
					value += this.getStat(item,key) * weight;
				}
				return value;
			};
			this.getStat = function(item, stat){
				var value = 0;
				if(item.stats[stat] != null){
					value = item.stats[stat];
				}
				return value;
			};			
			return this;
		}
	])
.factory("QueryFor", function ($http, $q) {
	return function (name, url) {
		var results = $q.defer();
		var fetch = $http.get(url)
			.success(function (data, status, headers, config) {
				results.resolve(data);
				console.debug(" v v v ");
				console.debug("Successfully loaded " + name + " data.");
				console.debug(data);
				console.debug(" ^ ^ ^ ");
			})
			.error(function (data, status) {
				console.log("Failed to load " + name + " data - returned: " + status);
			});
		return results.promise;
	}
});
