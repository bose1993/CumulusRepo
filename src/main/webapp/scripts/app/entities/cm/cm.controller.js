'use strict';

angular.module('cumulusApp')
    .controller('CmController', function ($scope, Cm, Accreditlab, Template, ParseLinks) {
        $scope.cms = [];
        $scope.accreditlabs = Accreditlab.query();
        $scope.templates = Template.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Cm.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.cms.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.cms = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Cm.update($scope.cm,
                function () {
                    $scope.reset();
                    $('#saveCmModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Cm.get({id: id}, function(result) {
                $scope.cm = result;
                $('#saveCmModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Cm.get({id: id}, function(result) {
                $scope.cm = result;
                $('#deleteCmConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Cm.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteCmConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.cm = {Xml: null, Cmid: null, Xmlid: null, Version: null, id: null};
        };
    });
