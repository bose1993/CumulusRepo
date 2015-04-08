'use strict';

angular.module('cumulusApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('propertyAttribute', {
                parent: 'entity',
                url: '/propertyAttribute',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.propertyAttribute.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/propertyAttribute/propertyAttributes.html',
                        controller: 'PropertyAttributeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('propertyAttribute');
                        return $translate.refresh();
                    }]
                }
            })
            .state('propertyAttributeDetail', {
                parent: 'entity',
                url: '/propertyAttribute/:id',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'cumulusApp.propertyAttribute.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/propertyAttribute/propertyAttribute-detail.html',
                        controller: 'PropertyAttributeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('propertyAttribute');
                        return $translate.refresh();
                    }]
                }
            });
    });
