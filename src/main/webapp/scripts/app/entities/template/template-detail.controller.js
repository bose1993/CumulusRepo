'use strict';

angular.module('cumulusApp')
    .controller('TemplateDetailController', function ($scope, $stateParams, Template, Toc, Property) {
        $scope.template = {};
        $scope.load = function (id) {
            Template.get({id: id}, function(result) {
              $scope.template = result;
            });
        };
        $scope.load($stateParams.id);
    });
