'use strict';

angular.module('cumulusApp')
    .controller('CmDetailController', function ($scope, $stateParams, Cm, Accreditlab, Template) {
        $scope.cm = {};
        $scope.load = function (id) {
            Cm.get({id: id}, function(result) {
              $scope.cm = result;
            });
        };
        $scope.load($stateParams.id);
    });
