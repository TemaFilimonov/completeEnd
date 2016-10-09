/**
 * Created by Артем Константинович on 06.10.2016.
 */
//noinspection JSAnnotator

angular.module("siteCreate", ['ngSanitize'])

    .controller("creationCtrl", function($scope, $http) {
            $scope.site = [];

            $scope.saveSite = function (action) {
                console.log($scope.site);
                var source = $scope.site;
                $http.post("save/site/", source, action)};

        }
    );
