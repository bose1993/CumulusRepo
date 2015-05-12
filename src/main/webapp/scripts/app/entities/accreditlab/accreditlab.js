'use strict';

angular.module('cumulusApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('accreditlab', {
                parent: 'entity',
                url: '/accreditlab',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.accreditlab.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/accreditlab/accreditlabs.html',
                        controller: 'AccreditlabController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('accreditlab');
                        return $translate.refresh();
                    }]
                }
            })
            .state('accreditlabDetail', {
                parent: 'entity',
                url: '/accreditlab/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.accreditlab.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/accreditlab/accreditlab-detail.html',
                        controller: 'AccreditlabDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('accreditlab');
                        return $translate.refresh();
                    }]
                }
            });
    });
