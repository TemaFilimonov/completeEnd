'use strict';


var App = angular.module('profile', []);

App.controller('profileCtrl', function ($scope, $http) {
  $scope.user = [];
    
  $http.get('http://localhost:8080/curentUser/info').success(function (data) {
    $scope.user = data;
  });
});