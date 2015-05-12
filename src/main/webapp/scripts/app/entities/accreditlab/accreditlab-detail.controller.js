'use strict';

angular.module('cumulusApp')
    .controller('AccreditlabDetailController', function ($scope, $stateParams, Accreditlab, Cm, User) {
        $scope.accreditlab = {};
        $scope.load = function (id) {
            Accreditlab.get({id: id}, function(result) {
              $scope.accreditlab = result;
            });
        };
        $scope.load($stateParams.id);
    });
