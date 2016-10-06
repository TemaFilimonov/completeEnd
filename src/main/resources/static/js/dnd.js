/**
 * Created by Артем Константинович on 06.10.2016.
 */
//noinspection JSAnnotator
/**
 * The controller doesn't do much more than setting the initial data model
 */
angular.module("dnd").controller("NestedListsDemoController", function($scope) {

    $scope.models = {
        selected: null,
        templates: [
            {type: "item", id: 2},
            {type: "container", id: 1, columns: [[], []]}
        ],
        dropzones: {
        "A": [],
        "B": []
        }
    };

    $scope.$watch('models.dropzones', function(model) {
        $scope.modelAsJson = angular.toJson(model, true);
    }, true);

});