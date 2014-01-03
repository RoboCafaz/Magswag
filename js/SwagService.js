angular.module("Swag.Service", [])
.factory("Professions", function ($http) {
	return $http.get('db/professions.json');
})
.factory("Armor", function ($http) {
	return $http.get('db/armor.json');
})
.factory("Stats", function ($http) {
	return $http.get('db/stats.json');
});
