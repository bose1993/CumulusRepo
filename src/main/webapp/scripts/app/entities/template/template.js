'use strict';

angular.module('cumulusApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('template', {
                parent: 'entity',
                url: '/template',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.template.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/template/templates.html',
                        controller: 'TemplateController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('template');
                        return $translate.refresh();
                    }]
                }
            })
            .state('templateDetail', {
                parent: 'entity',
                url: '/template/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.template.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/template/template-detail.html',
                        controller: 'TemplateDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('template');
                        return $translate.refresh();
                    }]
                }
            });
    });
