/**
 * Created by Артем Константинович on 09.10.2016.
 */
'use strict';


var App = angular.module('siteCreate', []);

App.controller('creationCtrl',['$scope','$http','$location', function ($scope, $http, $location) {

    $scope.saveSite = function (action) {
        var site = $scope.site.name;
        var tag = $scope.tag.listOfTags;
        $http.post("save/site", site, action)};


    $scope.site = [];

    $http.get('/site/info/' + window.location.search.slice(6)).success(function (data) {
        $scope.site = data;
    });
}]);

