'use strict';


var App = angular.module('profile', []);

App.controller('profileUserCtrl', function ($scope, $http) {
  $scope.user = [];
    
  $http.get('http://localhost:8080/curentUser/info').success(function (data) {
    $scope.user = data;
  });
});
App.controller('profileSiteCtrl', function ($scope, $http) {
  $scope.site = [];

  $http.get('http://localhost:8080/someDir').success(function (data) {
    $scope.site = data;
  });
});