'use strict';

angular.module('cumulusApp')
    .controller('TocDetailController', function ($scope, $stateParams, Toc, Template) {
        $scope.toc = {};
        $scope.load = function (id) {
            Toc.get({id: id}, function(result) {
              $scope.toc = result;
            });
        };
        $scope.load($stateParams.id);
    });
