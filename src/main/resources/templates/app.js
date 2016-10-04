'use strict';


var App = angular.module('course', []);

App.controller('MainController', function ($scope, $http) {
    
  $http.get('users').success(function (data) {
    $scope.users = data;
  });
});