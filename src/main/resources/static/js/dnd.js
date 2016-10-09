/**
 * Created by Артем Константинович on 06.10.2016.
 */
//noinspection JSAnnotator
/**
 * The controller doesn't do much more than setting the initial data model
 */
angular.module("dnd", ['dndLists','ngSanitize','summernote'])

    .controller("editCtrl", function($scope, $http) {
        $scope.site = [];
        $http.get('http://localhost:8080/site/info/' + window.location.search.slice(6)).success(function (data) {
            $scope.site = data;
            console.log( $scope.site[1].source);
            $scope.model.dropzones = $scope.site[1].source;
        });

        $scope.models = {
            selected: null,
            templates: [
                {type: "item", code: ""},
                {type: "container", columns: [[], []]}
            ],
            dropzones: {
                "A": []
            }
        };

        $scope.$watch('models.dropzones', function (model) {
            $scope.modelAsJson = angular.toJson(model, true);
        }, true);

        $scope.savePage = function (action) {
            console.log($scope.modelAsJson);
            var source = $scope.modelAsJson;
            $http.post("edit/source/"+ window.location.search.slice(6), source, action)};

    }
);