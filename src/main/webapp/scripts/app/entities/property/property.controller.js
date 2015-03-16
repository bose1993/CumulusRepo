'use strict';

angular.module('cumulusApp')
    .controller('PropertyController', function ($scope, Property, Template, ParseLinks) {
        $scope.propertys = [];
        $scope.templates = Template.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Property.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.propertys.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.propertys = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Property.update($scope.property,
                function () {
                    $scope.reset();
                    $('#savePropertyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Property.get({id: id}, function(result) {
                $scope.property = result;
                $('#savePropertyModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Property.get({id: id}, function(result) {
                $scope.property = result;
                $('#deletePropertyConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Property.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deletePropertyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.property = {rules: null, id: null};
        };
    });
