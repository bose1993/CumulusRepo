'use strict';

angular.module('cumulusApp')
    .controller('TemplateController', function ($scope, Template, Toc, Property, ParseLinks) {
        $scope.templates = [];
        $scope.tocs = Toc.query();
        $scope.propertys = Property.query();
        $scope.page = 1;
        $scope.loadAll = function() {
            Template.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.templates.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.templates = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.create = function () {
            Template.update($scope.template,
                function () {
                    $scope.reset();
                    $('#saveTemplateModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            Template.get({id: id}, function(result) {
                $scope.template = result;
                $('#saveTemplateModal').modal('show');
            });
        };

        $scope.delete = function (id) {
            Template.get({id: id}, function(result) {
                $scope.template = result;
                $('#deleteTemplateConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Template.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteTemplateConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.template = {XML: null, version: null, master: null, id: null};
        };
    });
