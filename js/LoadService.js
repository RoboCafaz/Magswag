angular.module("SwagNav")
.factory("Events", ["QueryFor", function (QueryFor) {
			return QueryFor('events', 'db/events.json');
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
