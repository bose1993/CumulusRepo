'use strict';

angular.module('cumulusApp')
    .controller('PropertyAttributeDetailController', function ($scope, $stateParams, PropertyAttribute, Property) {
        $scope.propertyAttribute = {};
        $scope.load = function (id) {
            PropertyAttribute.get({id: id}, function(result) {
              $scope.propertyAttribute = result;
            });
        };
        $scope.load($stateParams.id);
    });
