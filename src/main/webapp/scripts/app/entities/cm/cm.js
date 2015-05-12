'use strict';

angular.module('cumulusApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('cm', {
                parent: 'entity',
                url: '/cm',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.cm.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cm/cms.html',
                        controller: 'CmController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cm');
                        return $translate.refresh();
                    }]
                }
            })
            .state('cmDetail', {
                parent: 'entity',
                url: '/cm/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.cm.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/cm/cm-detail.html',
                        controller: 'CmDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('cm');
                        return $translate.refresh();
                    }]
                }
            });
    });
