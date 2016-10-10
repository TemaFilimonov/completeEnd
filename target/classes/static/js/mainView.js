/**
 * Created by Артем Константинович on 10.10.2016.
 */
var App = angular.module('mainView', []);

App.controller('mainCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
    $scope.siteByCreation = [];
    $scope.siteByAlhp = [];
    $scope.maxByCreation = 5;
    $scope.maxByAlph = 5;


    $http.get('http://localhost:8080/site/sortedByCreate/' + $scope.maxByCreation).success(function (data) {
        $scope.siteByCreation = data;
    });
    $http.get('http://localhost:8080/site/sortedByAlph/' + $scope.maxByAlph).success(function (data) {
        $scope.siteByAlhp = data;
    });
}]);
