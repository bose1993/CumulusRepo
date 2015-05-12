'use strict';

angular.module('cumulusApp')
    .controller('AccreditlabController', function ($scope, Accreditlab, Cm, User, ParseLinks) {
        $scope.accreditlabs = [];
        $scope.cms = Cm.query();
        $scope.users = User.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Accreditlab.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.accreditlabs.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.accreditlabs = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Accreditlab.update($scope.accreditlab,
                function () {
                    $scope.reset();
                    $('#saveAccreditlabModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Accreditlab.get({id: id}, function(result) {
                $scope.accreditlab = result;
                $('#saveAccreditlabModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Accreditlab.get({id: id}, function(result) {
                $scope.accreditlab = result;
                $('#deleteAccreditlabConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Accreditlab.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteAccreditlabConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.accreditlab = {Name: null, Url: null, id: null};
        };
    });
