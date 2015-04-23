'use strict';

angular.module('cumulusApp')
    .controller('PropertyAttributeController', function ($scope, PropertyAttribute, Property, ParseLinks,Principal) {
        $scope.propertyAttributes = [];
        $scope.propertys = Property.query();
        $scope.page = 1;
        $scope.isInRole = Principal.isInRole;

        $scope.loadAll = function() {
            PropertyAttribute.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.propertyAttributes.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.propertyAttributes = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            PropertyAttribute.update($scope.propertyAttribute,
                function () {
                    $scope.reset();
                    $('#savePropertyAttributeModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            PropertyAttribute.get({id: id}, function(result) {
                $scope.propertyAttribute = result;
                $('#savePropertyAttributeModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            PropertyAttribute.get({id: id}, function(result) {
                $scope.propertyAttribute = result;
                $('#deletePropertyAttributeConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            PropertyAttribute.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deletePropertyAttributeConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.propertyAttribute = {name: null, type: null, required: null, id: null};
        };
    });
