/**
 * Created by Артем Константинович on 10.10.2016.
 */
'use strict';


var App = angular.module('siteShow', []);

App.controller('siteCtrl',['$scope','$http','$location', function ($scope, $http, $location) {
    $scope.site = [];

    $http.get('http://localhost:8080/showsite/' + window.location.search.slice(6)).success(function (data) {
        $scope.site = data;
    });
}]);