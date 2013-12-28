angular.module("Swag.Service", [])
.factory("Armor", function ($http) {
	return $http.get('db/armor.json');
});
