'use strict';

angular.module('cumulusApp')
    .controller('PropertyDetailController', function ($scope, $stateParams, Property, Template) {
        $scope.property = {};
        $scope.load = function (id) {
            Property.get({id: id}, function(result) {
              $scope.property = result;
            });
        };
        $scope.load($stateParams.id);
    });
